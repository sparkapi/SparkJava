package com.sparkplatform.api.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateField implements Serializable {
	private static final long serialVersionUID = 21L;

	private static final SimpleDateFormat ISO_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US);
	private Date value;
	private boolean restricted = false;
	public DateField(String str) {
		if ("********".equals( str )) {
			restricted = true;
		} else {
			try {
				value = ISO_FORMAT.parse(str);
			} catch (Exception e) {
				throw new RuntimeException("Could not parse date: "+str, e);
			}
		}
	}
	public Date getValue() {
		return value;
	}
	public void setValue( Date value ) {
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
		else
			return value.toString();
	}
}
