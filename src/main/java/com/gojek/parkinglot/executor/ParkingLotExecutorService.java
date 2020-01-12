/**
 * 
 */
package com.gojek.parkinglot.executor;

import com.gojek.parkinglot.exceptions.ParkingException;
import com.gojek.parkinglot.service.ParkingLotService;

/**
 * @author rkala
 *
 */
public interface ParkingLotExecutorService {
	
	public void attachService(ParkingLotService service);
	
	public void peform(String action) throws ParkingException;

}
