/**
 * 
 */
package com.gojek.parkinglot.dao;

import java.util.List;

import com.gojek.parkinglot.models.Vehicle;

/**
 * @author rkala
 *
 */
public interface InMemoryLevelParkingDataManager<T extends Vehicle> {

	public int parkVehicle(T vehicle);

	public boolean leaveVehicle(int slotNumber);

	public List<String> getStatus();

	public List<String> getRegNumberForColor(String color);

	public List<Integer> getSlotNumbersFromColor(String colour);

	public int getSlotNoFromRegistrationNo(String registrationNo);

	public int getAvailableSlotsCount();

	public void destroy();

}
