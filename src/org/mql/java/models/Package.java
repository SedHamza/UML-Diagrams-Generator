package org.mql.java.models;

import java.util.Vector;

public class Package {
	private String name;
	private Vector<Class> classes;

	public Package() {
		classes = new Vector<Class>();
	}

	public Package(String name) {
		this.name = name;
		classes = new Vector<Class>();
	}

	public Package(String name, Vector<Class> classes) {
		super();
		this.name = name;
		this.classes = classes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<Class> getClasses() {
		return classes;
	}

	public void setClasses(Vector<Class> classes) {
		this.classes = classes;
	}

	public void addClass(Class cls) {
		classes.add(cls);
	}

	@Override
	public String toString() {
		String rs = name;
		for (Class c : classes) {
			rs += "\n\t\t-" + c;
		}
		return rs;
	}

}
