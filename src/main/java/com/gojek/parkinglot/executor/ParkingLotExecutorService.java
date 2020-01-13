/**
 * 
 */
package com.gojek.parkinglot.executor;

import com.gojek.parkinglot.exceptions.GoJekParkingException;
import com.gojek.parkinglot.service.ParkingLotService;

/**
 * @author rkala
 *
 */
public interface ParkingLotExecutorService {
	
	public void attachService(ParkingLotService service);
	
	public void executeAction(String action) throws GoJekParkingException;
	
	public boolean validate(String inputString);


}
