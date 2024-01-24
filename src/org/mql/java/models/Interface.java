package org.mql.java.models;

import java.beans.JavaBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Interface extends Entity {

	private Method[] methodes;
	private Field[] fields;

	public Interface(java.lang.Class<?> cls) {
		super(cls.getName(), cls.getSimpleName());

		methodes = cls.getDeclaredMethods();
		fields = cls.getDeclaredFields();
		if (cls.getInterfaces().length > 0) {
			System.out.println(" interface const " + cls.getInterfaces()[0]);
			setSuperClass(new Interface(cls.getInterfaces()[0].getName()));
		}
	}

	public Interface(String name) {
		super(name);

	}

	public Method[] getMethodes() {
		return methodes;
	}

	public void setMethodes(Method[] methodes) {
		this.methodes = methodes;
	}

	@Override
	public String toString() {
		return super.getSimpleName();
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	@Override
	public boolean isClass() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInterface() {
		// TODO Auto-generated method stub
		return true;
	}

}
