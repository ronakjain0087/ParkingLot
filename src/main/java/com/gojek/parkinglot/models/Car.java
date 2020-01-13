/**
 * 
 */
package com.gojek.parkinglot.models;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author rkala
 *
 */
public class Car extends Vehicle{

	//default constructor
	public Car() {
		super();
	}
	
	public Car(String regNo, String color) {
		super(regNo, color);
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		super.writeExternal(out);
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		super.readExternal(in);
	}
}
