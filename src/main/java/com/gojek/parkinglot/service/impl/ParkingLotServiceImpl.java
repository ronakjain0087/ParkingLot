/**
 * 
 */
package com.gojek.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.gojek.parkinglot.dao.InMemoryParkingDataManager;
import com.gojek.parkinglot.dao.impl.InMemoryParkingDataManagerImpl;
import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.utils.CommonConstants;
import com.gojek.parkinglot.utils.ErrorCodeEnum;
import com.gojek.parkinglot.utils.NearFirstParkingPolicy;
import com.gojek.parkinglot.utils.ParkingPolicy;

/**
 * @author rkala
 *
 */
public class ParkingLotServiceImpl implements ParkingLotService {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	@SuppressWarnings("rawtypes")
	private InMemoryParkingDataManager dataManager = null;

	@Override
	public void createParkingLot(int level, int intialCapacity) throws GoJekParkingException {
		// If parking lot is already created then throw exception
		if (dataManager != null) {
			throw new GoJekParkingException(ErrorCodeEnum.PARKING_LOT_ALREADY_EXIST.getDescription());
		}

		if (dataManager != null)
			throw new GoJekParkingException(ErrorCodeEnum.PARKING_LOT_ALREADY_EXIST.getDescription());
		List<Integer> levels = new ArrayList<>();
		List<Integer> capacity = new ArrayList<>();
		List<ParkingPolicy> parkingPolicies = new ArrayList<>();
		levels.add(level);
		capacity.add(intialCapacity);
		parkingPolicies.add(new NearFirstParkingPolicy());
		this.dataManager = InMemoryParkingDataManagerImpl.getInstance(levels, capacity, parkingPolicies);
		String message = CommonConstants.PARKING_LOT_INITIALISED_MSG.replace("{variable}", String.valueOf(intialCapacity));
		System.out.println(message);

	}

	@Override
	public Optional<Integer> park(int level, Vehicle vehicle) throws GoJekParkingException {
		Optional<Integer> value = Optional.empty();
		lock.writeLock().lock();
		validateParkingLot();
		try {
			value = Optional.of(dataManager.parkVehicle(level, vehicle));
			if (value.get() == CommonConstants.NOT_AVAILABLE)
				System.out.println("Parking lot is fully occupied");
			else if (value.get() == CommonConstants.VEHICLE_ALREADY_EXIST)
				System.out.println("Sorry, vehicle is already parked.");
			else
				System.out.println("Allocated slot number: " + value.get());
		} catch (Exception e) {
			throw new GoJekParkingException(ErrorCodeEnum.PROCESSING_ERROR.getDescription(), e);
		} finally {
			lock.writeLock().unlock();
		}
		return value;
	}

	@Override
	public void unPark(int level, int slotNumber) throws GoJekParkingException {

		lock.writeLock().lock();
		validateParkingLot();
		try {

			if (dataManager.leaveVehicle(level, slotNumber))
				System.out.println("Slot number " + slotNumber + " is available");
			else
				System.out.println("Slot number is Empty Already.");
		} catch (Exception e) {
			throw new GoJekParkingException(
					ErrorCodeEnum.INVALID_VALUE.getDescription().replace("{variable}", "slot_number"), e);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void getStatus(int level) throws GoJekParkingException {
		lock.readLock().lock();
		validateParkingLot();
		try {
			System.out.println("Associated Slot No.\t Associated Registration No.\t Associated Color");
			List<String> statusList = dataManager.getStatus(level);
			if (statusList.size() == 0)
				System.out.println("Parking lot is empty.");
			else {
				for (String statusSting : statusList) {
					System.out.println(statusSting);
				}
			}
		} catch (Exception e) {
			throw new GoJekParkingException(ErrorCodeEnum.PROCESSING_ERROR.getDescription(), e);
		} finally {
			lock.readLock().unlock();
		}

	}

	@Override
	public Optional<Integer> getAvailableSlotsCount(int level) throws GoJekParkingException {
		lock.readLock().lock();
		Optional<Integer> value = Optional.empty();
		validateParkingLot();
		try {
			value = Optional.of(dataManager.getAvailableSlotsCount(level));
		} catch (Exception e) {
			throw new GoJekParkingException(ErrorCodeEnum.PROCESSING_ERROR.getDescription(), e);
		} finally {
			lock.readLock().unlock();
		}
		return value;
	}

	@Override
	public void getRegNumber(int level, String color) throws GoJekParkingException {
		lock.readLock().lock();
		validateParkingLot();
		try {
			List<String> registrationList = dataManager.getRegNumberForColor(level, color);
			if (registrationList.size() == 0)
				System.out.println("Not Found");
			else
				System.out.println(String.join(",", registrationList));
		} catch (Exception e) {
			throw new GoJekParkingException(ErrorCodeEnum.PROCESSING_ERROR.getDescription(), e);
		} finally {
			lock.readLock().unlock();
		}

	}

	@Override
	public void getSlotNumber(int level, String colour) throws GoJekParkingException {
		lock.readLock().lock();
		validateParkingLot();
		try {
			List<Integer> slotList = dataManager.getSlotNumbersFromColor(level, colour);
			if (slotList.size() == 0)
				System.out.println("Not Found");
			StringJoiner joiner = new StringJoiner(",");
			for (Integer slot : slotList) {
				joiner.add(slot + "");
			}
			System.out.println(joiner.toString());
		} catch (Exception e) {
			throw new GoJekParkingException(ErrorCodeEnum.PROCESSING_ERROR.getDescription(), e);
		} finally {
			lock.readLock().unlock();
		}

	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws GoJekParkingException {
		int value = -1;
		lock.readLock().lock();
		validateParkingLot();
		try {
			value = dataManager.getSlotNoFromRegistrationNo(level, registrationNo);
			System.out.println(value != -1 ? value : "Not Found");
		} catch (Exception e) {
			throw new GoJekParkingException(ErrorCodeEnum.PROCESSING_ERROR.getDescription(), e);

		} finally {
			lock.readLock().unlock();
		}
		return value;
	}

	@Override
	public void destroy() {
		if (dataManager != null)
			dataManager.destroy();
	}

	/**
	 * @throws ParkingException
	 */
	private void validateParkingLot() throws GoJekParkingException

	{
		if (dataManager == null) {
			throw new GoJekParkingException(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription());
		}
	}

}
