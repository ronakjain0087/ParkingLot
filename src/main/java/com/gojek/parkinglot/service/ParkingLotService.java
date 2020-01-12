/**
 * 
 */
package com.gojek.parkinglot.service;

import java.util.Optional;

import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.models.Vehicle;

/**
 * @author rkala
 *
 */
public interface ParkingLotService {

	public void createParkingLot(int level, int intialCapacity) throws GoJekParkingException;

	public Optional<Integer> park(int level, Vehicle vehicle) throws GoJekParkingException;

	public void unPark(int level, int slotNumber) throws GoJekParkingException;

	public void getStatus(int level) throws GoJekParkingException;

	public Optional<Integer> getAvailableSlotsCount(int level) throws GoJekParkingException;

	public void getRegNumber(int level, String color) throws GoJekParkingException;

	public void getSlotNumber(int level, String colour) throws GoJekParkingException;

	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws GoJekParkingException;

	public void destroy();

}
