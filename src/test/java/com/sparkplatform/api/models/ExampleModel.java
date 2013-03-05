package com.sparkplatform.api.models;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sparkplatform.api.models.ResourceEntity;

public class ExampleModel extends ResourceEntity {

	private static final long serialVersionUID = -7843007344873293021L;
	@JsonProperty("Foo")
	String foo;
	@JsonProperty("Bar")
	int bar;
	
	@JsonProperty("Barx")
	int barx;
	public String getFoo() {
		return foo;
	}
	public void setFoo(String foo) {
		this.foo = foo;
	}
	public int getBar() {
		return bar;
	}
	public void setBar(int bar) {
		this.bar = bar;
	}
	public int getBarx() {
		return barx;
	}
	public void setBarx(int barx) {
		this.barx = barx;
	}
	
}
