package org.mql.java.models;

import java.util.Vector;

public class Relation {

	private Class source;
	private Class target;
	private String carSource;
	private String carTarget;

	public Relation() {

	}

	public Relation(Class source, Class target) {
		super();
		this.source = source;
		this.target = target;
	}
	
	public Relation(Class source, Class target, String carSource, String carTarget) {
		super();
		this.source = source;
		this.target = target;
		this.carSource = carSource;
		this.carTarget = carTarget;
	}

	public Class getSource() {
		return source;
	}

	public void setSource(Class source) {
		this.source = source;
	}

	public Class getTarget() {
		return target;
	}

	public void setTarget(Class target) {
		this.target = target;
	}

	public String getCarSource() {
		return carSource;
	}

	public void setCarSource(String carSource) {
		this.carSource = carSource;
	}

	public String getCarTarget() {
		return carTarget;
	}

	public void setCarTarget(String carTarget) {
		this.carTarget = carTarget;
	}
}
