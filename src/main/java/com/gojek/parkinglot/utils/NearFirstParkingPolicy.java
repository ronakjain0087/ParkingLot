/**
 * 
 */
package com.gojek.parkinglot.utils;

import java.util.TreeSet;

/**
 * @author rkala
 *
 */
public class NearFirstParkingPolicy implements ParkingPolicy {

	private TreeSet<Integer> availableSlots;

	public NearFirstParkingPolicy() {
		availableSlots = new TreeSet<Integer>();
	}

	@Override
	public void add(int i)
	{
		availableSlots.add(i);
	}
	
	@Override
	public int getSlot()
	{
		return availableSlots.first();
	}
	
	@Override
	public void removeSlot(int availableSlot)
	{
		availableSlots.remove(availableSlot);
	}

}
