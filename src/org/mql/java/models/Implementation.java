package org.mql.java.models;

public class Implementation  extends Relation implements RelationType{
	
	
	public Implementation(Class source, Class target) {
		super(source, target);
	}
	public boolean isAggregation() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAssociation() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isComposition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExtension() {
		// TODO Auto-generated method stub
		return false;
	}@Override
	public boolean isImplementation() {
		// TODO Auto-generated method stub
		return true;
	}

}
