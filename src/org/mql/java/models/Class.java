package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Class {
	private String modifier;
	private String name;
	private String simpleName;
	private Method[] methodes;
	private Constructor[] constructors;
	private Field[] fields;
	private Class[] classes;

	public Class(java.lang.Class<?> cls) {
		this.name = cls.getName();
		this.simpleName = cls.getSimpleName();
		methodes = cls.getDeclaredMethods();
		constructors = cls.getDeclaredConstructors();
		fields = cls.getDeclaredFields();
		modifier = Modifier.toString(cls.getModifiers());
		classes = new Class[cls.getClasses().length];
		for (int i = 0; i < classes.length; i++) {
			classes[i] = new Class(cls.getClasses()[i]);
		}
	}

	public Class(String className) {
		this.name = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public Method[] getMethodes() {
		return methodes;
	}

	public void setMethodes(Method[] methodes) {
		this.methodes = methodes;
	}

	public Constructor[] getConstructors() {
		return constructors;
	}

	public void setConstructors(Constructor[] constructors) {
		this.constructors = constructors;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		String rs="";
		
		return simpleName;
	}

}
