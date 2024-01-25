package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.mql.java.ui.ClassNode;

public class Class extends Entity {
	private String modifier;
	private Method[] methodes;
	private Constructor[] constructors;
	private Field[] fields;
	private Class[] implementedClass;
	private Class[] localClasses;

	public Class(java.lang.Class<?> cls) {

		super(cls.getName(), cls.getSimpleName());
		this.modifier = Modifier.toString(cls.getModifiers());

		methodes = cls.getDeclaredMethods();
		constructors = cls.getDeclaredConstructors();
		fields = cls.getDeclaredFields();
		modifier = Modifier.toString(cls.getModifiers());
		if (cls.getSuperclass() != Object.class) {
			setSuperClass(new Class(cls.getSuperclass().getName()));
		}
//			superClass = cls.getSuperclass() == Object.class ? null : new Class(cls.getSuperclass());
		implementedClass = cls.getInterfaces() == null ? new Class[0] : new Class[cls.getInterfaces().length];
		for (int i = 0; i < implementedClass.length; i++) {
			implementedClass[i] = new Class(cls.getInterfaces()[i].getName());
		}
		localClasses = new Class[cls.getClasses().length];
		for (int i = 0; i < localClasses.length; i++) {
			localClasses[i] = new Class(cls.getClasses()[i]);
		}

	}

	public Class(String className) {
		super(className);
		System.out.println("--" + className);
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
		return super.getSimpleName();
	}

	@Override
	public boolean isClass() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isInterface() {
		// TODO Auto-generated method stub
		return false;
	}

}
