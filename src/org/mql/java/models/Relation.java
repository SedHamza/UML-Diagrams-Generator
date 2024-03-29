package org.mql.java.models;

import java.util.Vector;

public class Relation implements RelationType{

	private Entity source;
	private Entity target;
	private String carSource;
	private String carTarget;

	public Relation() {

	}

	public Relation(Entity source, Entity target) {
		super();
		this.source = source;
		this.target = target;
	}
	
	public Relation(Entity source, Entity target, String carSource, String carTarget) {
		super();
		this.source = source;
		this.target = target;
		this.carSource = carSource;
		this.carTarget = carTarget;
	}

	public Entity getSource() {
		return source;
	}

	public void setSource(Class source) {
		this.source = source;
	}

	public Entity getTarget() {
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

	@Override
	public boolean isAssociation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAggregation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isComposition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isImplementation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExtension() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
