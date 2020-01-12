/**
 * 
 */
package com.gojek.parkinglot.service;

import java.util.Optional;

import com.gojek.parkinglot.exceptions.ParkingException;
import com.gojek.parkinglot.models.Vehicle;

/**
 * @author rkala
 *
 */
public interface ParkingLotService {

	public void createParkingLot(int level, int intialCapacity) throws ParkingException;

	public Optional<Integer> park(int level, Vehicle vehicle) throws ParkingException;

	public void unPark(int level, int slotNumber) throws ParkingException;

	public void getStatus(int level) throws ParkingException;

	public Optional<Integer> getAvailableSlotsCount(int level) throws ParkingException;

	public void getRegNumber(int level, String color) throws ParkingException;

	public void getSlotNumber(int level, String colour) throws ParkingException;

	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingException;

	public void destroy();

}
