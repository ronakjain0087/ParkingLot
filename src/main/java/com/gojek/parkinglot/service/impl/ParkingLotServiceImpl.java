/**
 * 
 */
package com.gojek.parkinglot.service.impl;

import java.util.Optional;

import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.service.ParkingLotService;

/**
 * @author rkala
 *
 */
public class ParkingLotServiceImpl implements ParkingLotService {

	@Override
	public void createParkingLot(int level, int intialCapacity) throws GoJekParkingException {
		// TODO Auto-generated method stub
		
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
