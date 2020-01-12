/**
 * 
 */
package com.gojek.parkinglot.utils;

/**
 * @author rkala
 *
 */
public enum ErrorCodeEnum {
	
	
	PARKING_LOT_ALREADY_EXIST("Parking Already Created."),
	PARKING_NOT_EXIST("Car Parking Does not Exist"), 
	INVALID_VALUE("{variable} value is incorrect"),
	INVALID_FILE("Invalid File"),
	PROCESSING_ERROR("Processing Error "), 
	INVALID_REQUEST("Invalid Request");
	
	private String description;

	
	private ErrorCodeEnum(String description)
	{
		this.description = description;
	}
	
	
	public String getDescription()
	{
		return description;
	}
	

}
