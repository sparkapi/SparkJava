package com.sparkplatform.api.models;

import java.io.Serializable;

public class DoubleField implements Serializable {
	private static final long serialVersionUID = 22L;

	private Double value;
	private boolean restricted = false;
	public DoubleField(String str) {
		if ("********".equals( str )) {
			restricted = true;
		} else {
			try {
				value = Double.valueOf(str);
			} catch (Exception e) {
				throw new RuntimeException("Could not parse Double: "+str, e);
			}
		}
	}
	public DoubleField(int val) {
		value = Double.valueOf( val );
	}
	public DoubleField(long val) {
		value = Double.valueOf( val );
	}
	public DoubleField(double val) {
		value = Double.valueOf( val );
	}
	public DoubleField() {
		value = null;
	}
	public Double getValue() {
		return value;
	}
	public void setValue( Double value ) {
		this.value = value;
	}
	public boolean isRestricted() {
		return restricted;
	}
	public void setRestricted( boolean restricted ) {
		this.restricted = restricted;
	}
	public String toString() {
		if (restricted)
			return "********";
		else if (value!=null)
			return value.toString();
		else
			return "";
	}
}
