package org.mql.java.models;

public interface RelationType {
	public boolean isAssociation();
	public boolean isAggregation();
	public boolean isComposition();
	public boolean isImplementation();
	public boolean isExtension();
}
