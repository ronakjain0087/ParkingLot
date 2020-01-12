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
	public void add(int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSlot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeSlot(int slot) {
		// TODO Auto-generated method stub

	}

}
