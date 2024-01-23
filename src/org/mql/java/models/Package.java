package org.mql.java.models;

import java.util.Vector;

public class Package {
	private String name;
	private Vector<Class> classes;
	private Vector<Interface> interfaces;

	public Package() {
		interfaces = new Vector<Interface>();
		classes = new Vector<Class>();
	}

	public Package(String name) {
		this.name = name;
		classes = new Vector<Class>();
		interfaces = new Vector<Interface>();

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

	
	public Vector<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Vector<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public void addClass(Class cls) {
		classes.add(cls);
	}

	public void addInterface(Interface in) {
		interfaces.add(in);
	}

	@Override
	public String toString() {
		String rs = name;
		if (!classes.isEmpty()) {
			rs += "\n\t\t la liste des Class est";
			for (Class c : classes) {
				rs += "\n\t\t-" + c;
			}
		}
		
		if (!interfaces.isEmpty()) {
			rs += "\n\t\t la liste des interfaces est";
			for (Interface in : interfaces) {
				rs += "\n\t\t-" + in;
			}
		}
		return rs;
	}

}
