/**
 * 
 */
package com.gojek.parkinglot.utils;

/**
 * @author rkala
 *
 */
public interface ParkingPolicy {

	public void add(int i);

	public int getSlot();

	public void removeSlot(int slot);

}
