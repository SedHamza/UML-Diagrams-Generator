package org.mql.java.models;

import org.mql.java.ui.EntityNode;

public abstract class Entity {
	private String name;
	private String simpleName;
	private Entity superClass;
	private EntityNode entityNode;

	public Entity() {

	}

	public Entity(String name, String simpleName) {
		super();
		this.name = name;
		this.simpleName = simpleName;
	}

	public Entity(String name) {
		this.name = name;
		this.simpleName = name.split("\\.")[name.split("\\.").length - 1];
	}

	public abstract boolean isClass();

	public abstract boolean isInterface();

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

	public Entity getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Entity superClass) {
		this.superClass = superClass;
		System.out.println(superClass.getSimpleName());

	}

	public EntityNode getEntityNode() {
		return entityNode;
	}

	public void setEntityNode(EntityNode entityNode) {
		this.entityNode = entityNode;
	}

}
