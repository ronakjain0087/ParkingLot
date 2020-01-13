package com.gojek.parkinglot;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.models.Car;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.service.impl.ParkingLotServiceImpl;
import com.gojek.parkinglot.utils.ErrorCodeEnum;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */

	private int parkingLevel;
	private final ByteArrayOutputStream outputData = new ByteArrayOutputStream();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void init() {
		parkingLevel = 1;
		System.setOut(new PrintStream(outputData));
	}

	@After
	public void cleanUp() {
		System.setOut(null);
	}

	@Test
	public void createParkingLot() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		instance.createParkingLot(parkingLevel, 10);
		assertTrue("Parkinglotinitialsiedwith10slots".equalsIgnoreCase(outputData.toString().trim().replace(" ", "")));
		instance.destroy();
	}

	@Test
	public void parkingLotExists() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		instance.createParkingLot(parkingLevel, 10);
		assertTrue("Parkinglotinitialsiedwith10slots".equalsIgnoreCase(outputData.toString().trim().replace(" ", "")));
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_LOT_ALREADY_EXIST.getDescription()));
		instance.createParkingLot(parkingLevel, 10);
		instance.destroy();
	}

	@Test
	public void getSlotsByRegNo() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription()));
		instance.getSlotNoFromRegistrationNo(parkingLevel, "TN-01-AB-1234");
		assertEquals("CarParkingDoesnotExist", outputData.toString().trim().replace(" ", ""));
		instance.createParkingLot(parkingLevel, 10);
		instance.park(parkingLevel, new Car("TN-01-AB-1234", "White"));
		instance.park(parkingLevel, new Car("TN-01-XY-9999", "White"));
		instance.getSlotNoFromRegistrationNo(parkingLevel, "TN-01-AB-1234");
		assertEquals("CarParkingDoesnotExist\n" + "Parkinglotinitialsiedwith 6slots\n" + "\n"
				+ "Allocatedslotnumber:1\n" + "\n" + "Allocatedslotnumber:2\n1",
				outputData.toString().trim().replace(" ", ""));
		instance.getSlotNoFromRegistrationNo(parkingLevel, "TN-01-HH-1235");
		assertEquals("CarParkingDoesnotExist\n" + "Parkinglotinitialsiedwith 10slots\n" + "\n"
				+ "Allocatedslotnumber:1\n" + "\n" + "Allocatedslotnumber:2\n1\nNotFound",
				outputData.toString().trim().replace(" ", ""));
		instance.destroy();
	}

	@Test
	public void getSlotsByColor() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription()));
		instance.getRegNumber(parkingLevel, "white");
		assertEquals("CarParkingDoesnotExist", outputData.toString().trim().replace(" ", ""));
		instance.createParkingLot(parkingLevel, 7);
		instance.park(parkingLevel, new Car("TN-01-AB-1234", "White"));
		instance.park(parkingLevel, new Car("TN-01-HH-9999", "White"));
		instance.getStatus(parkingLevel);
		instance.getRegNumber(parkingLevel, "Cyan");
		assertEquals(
				"CarParkingDoesnotExist\n" + "Parkinglotinitialsiedwith 7slots\n" + "\n" + "Allocatedslotnumber:1\n"
						+ "\n" + "Allocatedslotnumber:2\nTN-01-AB-1234,TN-01-HH-9999",
				outputData.toString().trim().replace(" ", ""));
		instance.getRegNumber(parkingLevel, "Red");
		assertEquals(
				"CarParkingDoesnotExist\n" + "Parkinglotinitialsiedwith 6slots\n" + "\n" + "Allocatedslotnumber:1\n"
						+ "\n" + "Allocatedslotnumber:2\n" + "TN-01-AB-1234,TN-01-HH-9999,Notfound",
				outputData.toString().trim().replace(" ", ""));
		instance.destroy();
	}

	@Test
	public void vehicleAlreadyPresent() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription()));
		instance.park(parkingLevel, new Car("TN-01-HH-1234", "White"));
		assertEquals("CarParkingDoesnotExist", outputData.toString().trim().replace(" ", ""));
		instance.createParkingLot(parkingLevel, 3);
		instance.park(parkingLevel, new Car("TN-01-HH-1234", "White"));
		instance.park(parkingLevel, new Car("TN-01-HH-1234", "White"));
		assertTrue(
				"CarParkingDoesnotExist\nParkinglotinitialsiedwith 3slots\nAllocatedslotnumber:1\nSorry,vehicleisalreadyparked."
						.equalsIgnoreCase(outputData.toString().trim().replace(" ", "")));
		instance.destroy();
	}

	@Test
	public void parkingLotFull() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription()));
		instance.park(parkingLevel, new Car("KA-01-HH-1234", "White"));
		assertEquals("CarParkingDoesnotExist", outputData.toString().trim().replace(" ", ""));
		instance.createParkingLot(parkingLevel, 2);
		instance.park(parkingLevel, new Car("TN-01-HH-1234", "White"));
		instance.park(parkingLevel, new Car("TN-01-HH-9999", "White"));
		instance.park(parkingLevel, new Car("TN-01-BB-0001", "Black"));
		assertTrue("Parkinglotinitialsiedwith 2slots\\nAllocatedslotnumber:1\nAllocatedslotnumber:2\nParkinglotisfullyoccupied"
				.equalsIgnoreCase(outputData.toString().trim().replace(" ", "")));
		instance.destroy();
	}

	@Test
	public void nearestSlotAllotment() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription()));
		instance.park(parkingLevel, new Car("KA-01-HH-1234", "White"));
		assertEquals("CarParkingDoesnotExist", outputData.toString().trim().replace(" ", ""));
		instance.createParkingLot(parkingLevel, 5);
		instance.park(parkingLevel, new Car("TN-01-HH-1234", "White"));
		instance.park(parkingLevel, new Car("TN-01-HH-9999", "White"));
		instance.getSlotNoFromRegistrationNo(parkingLevel, "TN-01-HH-1234");
		instance.getSlotNoFromRegistrationNo(parkingLevel, "TN-01-HH-9999");
		assertTrue("Parkinglotinitialsiedwith 5slots\nAllocatedslotnumber:1\nAllocatedslotnumber:2"
				.equalsIgnoreCase(outputData.toString().trim().replace(" ", "")));
		instance.destroy();
	}

	@Test
	public void leave() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription()));
		instance.unPark(parkingLevel, 2);
		assertEquals("CarParkingDoesnotExist", outputData.toString().trim().replace(" ", ""));
		instance.createParkingLot(parkingLevel, 6);
		instance.park(parkingLevel, new Car("TN-01-HH-1234", "White"));
		instance.park(parkingLevel, new Car("TN-01-HH-9999", "White"));
		instance.park(parkingLevel, new Car("TN-01-BB-0001", "Black"));
		instance.unPark(parkingLevel, 4);
		assertTrue(
				"CarParkingDoesnotExist\nParkinglotinitialsiedwith 6slots\nAllocatedslotnumber:1\nAllocatedslotnumber:2\nAllocatedslotnumber:3\nSlotnumber4isfree"
						.equalsIgnoreCase(outputData.toString().trim().replace(" ", "")));
		instance.destroy();
	}

	@Test
	public void testStatus() throws Exception {
		ParkingLotService instance = new ParkingLotServiceImpl();
		thrown.expect(GoJekParkingException.class);
		thrown.expectMessage(is(ErrorCodeEnum.PARKING_NOT_EXIST.getDescription()));
		instance.getStatus(parkingLevel);
		assertEquals("CarParkingDoesnotExist", outputData.toString().trim().replace(" ", ""));
		instance.createParkingLot(parkingLevel, 8);
		instance.park(parkingLevel, new Car("TN-01-HH-1234", "White"));
		instance.park(parkingLevel, new Car("TN-01-HH-9999", "White"));
		instance.getStatus(parkingLevel);
		assertTrue(
				"CarParkingDoesnotExist\nParkinglotinitialsiedwith 8slots\nAllocatedslotnumber:1\nAllocatedslotnumber:2\nSlotNo.\tRegistrationNo.\tColor\n1\tKA-01-HH-1234\tWhite\n2\tKA-01-HH-9999\tWhite"
						.equalsIgnoreCase(outputData.toString().trim().replace(" ", "")));
		instance.destroy();
	}

}
