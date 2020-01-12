/**
 * 
 */
package com.gojek.parkinglot.service.impl;

import java.util.Optional;

import com.gojek.parkinglot.dao.InMemoryParkingDataManager;
import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.utils.ErrorCodeEnum;

/**
 * @author rkala
 *
 */
public class ParkingLotServiceImpl implements ParkingLotService {
	
	private InMemoryParkingDataManager daoManager;

	@Override
	public void createParkingLot(int level, int intialCapacity) throws GoJekParkingException {	
		// If parking lot is already created then throw exception	
		if(daoManager != null) {
			throw new GoJekParkingException(ErrorCodeEnum.PARKING_LOT_ALREADY_EXIST.getDescription());
		}
		
		
	}

	@Override
	public Optional<Integer> park(int level, Vehicle vehicle) throws GoJekParkingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unPark(int level, int slotNumber) throws GoJekParkingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getStatus(int level) throws GoJekParkingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Integer> getAvailableSlotsCount(int level) throws GoJekParkingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getRegNumber(int level, String color) throws GoJekParkingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSlotNumber(int level, String colour) throws GoJekParkingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws GoJekParkingException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
