/**
 * 
 */
package com.gojek.parkinglot.dao.impl;

import java.util.List;

import com.gojek.parkinglot.dao.InMemoryParkingDataManager;
import com.gojek.parkinglot.models.Vehicle;

/**
 * @author rkala
 *
 */
public class InMemoryParkingDataManagerImpl implements InMemoryParkingDataManager<Vehicle> {

	@Override
	public int parkVehicle(int level, Vehicle vehicle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean leaveVehicle(int level, int slotNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getStatus(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRegNumberForColor(int level, String color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(int level, String colour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAvailableSlotsCount(int level) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
