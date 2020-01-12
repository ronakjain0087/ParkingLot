/**
 * 
 */
package com.gojek.parkinglot.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gojek.parkinglot.dao.InMemoryParkingDataManager;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.utils.NearFirstParkingPolicy;
import com.gojek.parkinglot.utils.ParkingPolicy;

/**
 * @author rkala
 * @param <T>
 *
 */
public class InMemoryParkingDataManagerImpl<T> implements InMemoryParkingDataManager<Vehicle> {

	private Map<Integer, InMemoryLevelParkingDataManagerImpl<T>> levelParkingMap;

	@SuppressWarnings("rawtypes")
	private static InMemoryParkingDataManagerImpl instance = null;

	public static <T extends Vehicle> InMemoryParkingDataManagerImpl<T> getInstance(List<Integer> parkingLevels,
			List<Integer> capacityList, List<ParkingPolicy> parkingStrategies) {
		// Make sure the each of the lists are of equal size
		if (instance == null) {
			synchronized (InMemoryParkingDataManagerImpl.class) {
				if (instance == null) {
					instance = new InMemoryParkingDataManagerImpl<T>(parkingLevels, capacityList, parkingStrategies);
				}
			}
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	private InMemoryParkingDataManagerImpl(List<Integer> parkingLevels, List<Integer> capacityList,
			List<ParkingPolicy> parkingStrategies) {
		if (levelParkingMap == null)
			levelParkingMap = new HashMap<>();
		for (int i = 0; i < parkingLevels.size(); i++) {
			levelParkingMap.put(parkingLevels.get(i),
					(InMemoryLevelParkingDataManagerImpl<T>) InMemoryLevelParkingDataManagerImpl
							.getInstance(parkingLevels.get(i), capacityList.get(i), new NearFirstParkingPolicy()));

		}
	}

	@Override
	public int parkVehicle(int level, Vehicle vehicle) {
		return levelParkingMap.get(level).parkVehicle(vehicle);
	}

	@Override
	public boolean leaveVehicle(int level, int slotNumber) {
		return levelParkingMap.get(level).leaveVehicle(slotNumber);
	}

	@Override
	public List<String> getStatus(int level) {
		return levelParkingMap.get(level).getStatus();
	}

	@Override
	public List<String> getRegNumberForColor(int level, String color) {
		return levelParkingMap.get(level).getRegNumberForColor(color);
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(int level, String colour) {
		return levelParkingMap.get(level).getSlotNumbersFromColor(colour);
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) {
		return levelParkingMap.get(level).getSlotNoFromRegistrationNo(registrationNo);
	}

	@Override
	public int getAvailableSlotsCount(int level) {
		return levelParkingMap.get(level).getAvailableSlotsCount();
	}

	@Override
	public void destroy() {
		for (InMemoryLevelParkingDataManagerImpl<T> levelDataManager : levelParkingMap.values()) {
			levelDataManager.destroy();
		}
		levelParkingMap = null;
		instance = null;
	}

}
