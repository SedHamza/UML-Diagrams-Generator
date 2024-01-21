package org.mql.java.models;

public class Association  extends Relation implements RelationType{

	
	public Association(Class source, Class target, String carSource, String carTarget) {
		super(source,target,carSource,carTarget);
	}
	
	@Override
	public boolean isAggregation() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAssociation() {
		// TODO Auto-generated method stub
		return true;
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
		return false;
	}

	

}
