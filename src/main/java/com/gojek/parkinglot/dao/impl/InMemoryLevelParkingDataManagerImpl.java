/**
 * 
 */
package com.gojek.parkinglot.dao.impl;

import java.util.List;

import com.gojek.parkinglot.dao.InMemoryLevelParkingDataManager;
import com.gojek.parkinglot.models.Vehicle;

/**
 * @author rkala
 *
 */
public class InMemoryLevelParkingDataManagerImpl implements InMemoryLevelParkingDataManager<Vehicle> {

	@Override
	public int parkVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean leaveVehicle(int slotNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRegNumberForColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(String colour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSlotNoFromRegistrationNo(String registrationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAvailableSlotsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
