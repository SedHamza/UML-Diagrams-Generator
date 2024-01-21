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
	private Class superClass;
	private Class[] implementedClass;
	private Class[] localClasses;

	public Class(java.lang.Class<?> cls) {
		
			this.modifier = Modifier.toString(cls.getModifiers());
			this.name = cls.getName();
			this.simpleName = cls.getSimpleName();
			methodes = cls.getDeclaredMethods();
			constructors = cls.getDeclaredConstructors();
			fields = cls.getDeclaredFields();
			modifier = Modifier.toString(cls.getModifiers());
			superClass = cls.getSuperclass() == null ? null : new Class(cls.getSuperclass());
			implementedClass = cls.getInterfaces() == null ? null : new Class[cls.getInterfaces().length];
			for (int i = 0; i < implementedClass.length; i++) {
				implementedClass[i] = new Class(cls.getInterfaces()[i]);
			}
			localClasses = new Class[cls.getClasses().length];
			for (int i = 0; i < localClasses.length; i++) {
				localClasses[i] = new Class(cls.getClasses()[i]);
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

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Class getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Class superClass) {
		this.superClass = superClass;
	}

	public Class[] getImplementedClass() {
		return implementedClass;
	}

	public void setImplementedClass(Class[] implementedClass) {
		this.implementedClass = implementedClass;
	}

	public Class[] getLocalClasses() {
		return localClasses;
	}

	public void setLocalClasses(Class[] localClasses) {
		this.localClasses = localClasses;
	}

	@Override
	public String toString() {
		String rs = "";

		return simpleName;
	}

}
