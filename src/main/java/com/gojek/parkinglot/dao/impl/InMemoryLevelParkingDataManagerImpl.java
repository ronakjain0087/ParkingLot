/**
 * 
 */
package com.gojek.parkinglot.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.gojek.parkinglot.dao.InMemoryLevelParkingDataManager;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.utils.CommonConstants;
import com.gojek.parkinglot.utils.NearFirstParkingPolicy;
import com.gojek.parkinglot.utils.ParkingPolicy;

/**
 * @author rkala
 * @param <T>
 *
 */
public class InMemoryLevelParkingDataManagerImpl<T> implements InMemoryLevelParkingDataManager<Vehicle> {

	// Multiple Parking lot - 0 -> Ground floor 1 -> First Floor and so on
	private AtomicInteger level = new AtomicInteger(0);
	private AtomicInteger capacity = new AtomicInteger();
	private AtomicInteger availability = new AtomicInteger();

	// Allocation policy for parking
	private ParkingPolicy parkingStrategy;
	// this is per level - slot - vehicle
	private Map<Integer, Optional<T>> slotVehicleMap;

	@SuppressWarnings("rawtypes")
	private static InMemoryLevelParkingDataManagerImpl singleton = null;

	// handles singleton for this class
	public static <T extends Vehicle> InMemoryLevelParkingDataManagerImpl<T> getInstance(int level, int capacity,
			ParkingPolicy parkingStrategy) {
		if (singleton == null) {
			synchronized (InMemoryLevelParkingDataManagerImpl.class) {
				if (singleton == null) {
					singleton = new InMemoryLevelParkingDataManagerImpl<T>(level, capacity, parkingStrategy);
				}
			}
		}
		return singleton;
	}

	private InMemoryLevelParkingDataManagerImpl(int level, int capacity, ParkingPolicy parkingPolicy) {
		this.level.set(level);
		this.capacity.set(capacity);
		this.availability.set(capacity);
		this.parkingStrategy = parkingPolicy;
		if (parkingPolicy == null)
			parkingPolicy = new NearFirstParkingPolicy();
		slotVehicleMap = new ConcurrentHashMap<>();
		for (int i = 1; i <= capacity; i++) {
			slotVehicleMap.put(i, (Optional<T>) Optional.empty());
			parkingPolicy.add(i);
		}
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	@Override
	public int parkVehicle(Vehicle vehicle) {
		int availableSlot;
		if (availability.get() == 0) {
			return CommonConstants.NOT_AVAILABLE;
		} else {
			availableSlot = parkingStrategy.getSlot();
			if (slotVehicleMap.containsValue(Optional.of(vehicle)))
				return CommonConstants.VEHICLE_ALREADY_EXIST;

			slotVehicleMap.put(availableSlot, (Optional<T>) Optional.of(vehicle));
			availability.decrementAndGet();
			parkingStrategy.removeSlot(availableSlot);
		}
		return availableSlot;
	}

	@Override
	public boolean leaveVehicle(int slotNumber) {
		if (!slotVehicleMap.get(slotNumber).isPresent()) // Slot already empty
			return false;
		availability.incrementAndGet();
		parkingStrategy.add(slotNumber);
		slotVehicleMap.put(slotNumber, (Optional<T>) Optional.empty());
		return true;
	}

	@Override
	public List<String> getStatus() {
		List<String> statusList = new ArrayList<>();
		for (int i = 1; i <= capacity.get(); i++) {
			Optional<T> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent()) {
				statusList.add(i + "\t\t" + ((Vehicle) vehicle.get()).getRegistrationNo() + "\t\t"
						+ ((Vehicle) vehicle.get()).getColor());
			}
		}
		return statusList;
	}

	@Override
	public List<String> getRegNumberForColor(String color) {
		List<String> statusList = new ArrayList<>();
		for (int i = 1; i <= capacity.get(); i++) {
			Optional<T> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent() && color.equalsIgnoreCase(((Vehicle) vehicle.get()).getColor())) {
				statusList.add(((Vehicle) vehicle.get()).getRegistrationNo());
			}
		}
		return statusList;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(String colour) {
		List<Integer> slotList = new ArrayList<>();
		for (int i = 1; i <= capacity.get(); i++) {
			Optional<T> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent() && colour.equalsIgnoreCase(((Vehicle) vehicle.get()).getColor())) {
				slotList.add(i);
			}
		}
		return slotList;
	}

	@Override
	public int getSlotNoFromRegistrationNo(String registrationNo) {
		int result = CommonConstants.NOT_FOUND;
		for (int i = 1; i <= capacity.get(); i++) {
			Optional<T> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent() && registrationNo.equalsIgnoreCase(((Vehicle) vehicle.get()).getRegistrationNo())) {
				result = i;
			}
		}
		return result;
	}

	@Override
	public int getAvailableSlotsCount() {
		return availability.get();
	}

	@Override
	public void destroy() {
		this.level = new AtomicInteger();
		this.capacity = new AtomicInteger();
		this.availability = new AtomicInteger();
		this.parkingStrategy = null;
		slotVehicleMap = null;
		singleton = null;
	}

}
