/**
 * 
 */
package com.gojek.parkinglot.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author rkala
 *
 */
public abstract class Vehicle implements Externalizable {

	private String registrationNo;
	private String color;

	public Vehicle(String regNo, String color) {
		this.registrationNo = regNo;
		this.color = color;
	}

	public Vehicle() {
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(getRegistrationNo());
		out.writeObject(getColor());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setRegistrationNo((String) in.readObject());
		setColor((String) in.readObject());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Vehicle that = (Vehicle) o;

		return new EqualsBuilder().append(registrationNo, that.registrationNo).append(color, that.color).isEquals();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + registrationNo.hashCode();
		result = 31 * result + color.hashCode();
		return result;
	}

}
