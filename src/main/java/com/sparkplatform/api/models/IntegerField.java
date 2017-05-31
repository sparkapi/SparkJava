package com.sparkplatform.api.models;

import java.io.Serializable;

public class IntegerField implements Serializable {
	private static final long serialVersionUID = 23L;

	private Integer value;
	private boolean restricted = false;
	public IntegerField(String str) {
		if ("********".equals( str )) {
			restricted = true;
		} else if (str==null) {
		} else {
			try {
				value = Integer.valueOf(str);
			} catch (Exception e) {
				throw new RuntimeException("Could not parse Integer: "+str, e);
			}
		}
	}
	public IntegerField(int val) {
		value = Integer.valueOf( val );
	}
	public IntegerField(long val) {
		value = Integer.valueOf( (int)val );
	}
	public IntegerField(double val) {
		value = Integer.valueOf( (int)val );
	}
	public IntegerField() {
		value = null;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue( Integer value ) {
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
