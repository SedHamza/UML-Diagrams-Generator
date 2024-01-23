package org.mql.java.models;

import java.beans.JavaBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Interface {

	private String name;
	private String simpleName;
	private Method[] methodes;
	private Field[] fields;

	public Interface(java.lang.Class<?> cls) {
		name = cls.getName();
		simpleName = cls.getSimpleName();
		methodes = cls.getDeclaredMethods();
		fields=cls.getDeclaredFields();
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
	@Override
	public String toString() {
		return simpleName;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

}
