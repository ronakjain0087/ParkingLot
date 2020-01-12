/**
 * 
 */
package com.gojek.parkinglot.executor;

import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.models.Car;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.utils.CommonConstants;
import com.gojek.parkinglot.utils.ErrorCodeEnum;

/**
 * @author rkala
 *
 */
public class ParkingLotExecutorServiceImpl implements ParkingLotExecutorService {

	private ParkingLotService parkingLotService;

	@Override
	public void attachService(ParkingLotService service) {
		this.parkingLotService = service;

	}

	@Override
	public void executeAction(String input) throws GoJekParkingException {
		int level = 1;
		String[] inputs = input.split(" ");
		String action = inputs[0];

		switch (action) {
		case CommonConstants.CREATE_PARKING_LOT:
			try {
				int capacity = Integer.parseInt(inputs[1]);
				parkingLotService.createParkingLot(level, capacity);
			} catch (NumberFormatException e) {
				throw new GoJekParkingException(
						ErrorCodeEnum.INVALID_VALUE.getDescription().replace("{variable}", "capacity"));
			}

			break;
		case CommonConstants.PARK_VEHICLE:
			parkingLotService.park(level, new Car(inputs[1], inputs[2]));
			break;
		case CommonConstants.LEAVE:
			try {
				int slotNumber = Integer.parseInt(inputs[1]);
				parkingLotService.unPark(level, slotNumber);
			} catch (NumberFormatException e) {
				throw new GoJekParkingException(
						ErrorCodeEnum.INVALID_VALUE.getDescription().replace("{variable}", "slot_number"));
			}
			break;
		case CommonConstants.STATUS:
			parkingLotService.getStatus(level);
			break;
			
		// Enquiry cases	
		case CommonConstants.REG_NUMBER_BY_COLOR:
			parkingLotService.getRegNumber(level, inputs[1]);
			break;
		case CommonConstants.SLOT_NUMBER_BY_COLOR:
			parkingLotService.getSlotNumber(level, inputs[1]);
			break;
		case CommonConstants.SLOTS_NUMBER_BY_REG_NUMBER:
			parkingLotService.getSlotNoFromRegistrationNo(level, inputs[1]);
			break;
		default:
			break;
		}

	}

}
