package com.gojek.parkinglot.dao;
import java.util.List;

import com.gojek.parkinglot.models.Vehicle;

/**
 * 
 */

/**
 * @author rkala
 *
 */
public interface InMemoryParkingDataManager<T extends Vehicle> {

	public int parkVehicle(int level, T vehicle);

	public boolean leaveVehicle(int level, int slotNumber);

	public List<String> getStatus(int level);

	public List<String> getRegNumberForColor(int level, String color);

	public List<Integer> getSlotNumbersFromColor(int level, String colour);

	public int getSlotNoFromRegistrationNo(int level, String registrationNo);

	public int getAvailableSlotsCount(int level);

	public void destroy();

}
