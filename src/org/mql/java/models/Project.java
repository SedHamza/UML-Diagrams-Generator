package org.mql.java.models;

import java.util.Vector;

public class Project {
	private String name;
	private String path;
	private Vector<Package> packages;
	private Vector<Relation> relations; 
	private Vector<Class> classes;
	

	public Project() {
		packages = new Vector<Package>();
		relations=new Vector<Relation>();
		classes =new Vector<Class>();
	}

	public Project(String name, String path) {
		super();
		this.name = name;
		this.path = path;
		packages = new Vector<Package>();
		relations=new Vector<Relation>();
		classes =new Vector<Class>();
	}

	public Project(String name, String path, Vector<Package> packages) {
		super();
		this.name = name;
		this.path = path;
		this.packages = packages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Vector<Package> getPackages() {
		return packages;
	}

	public void setPackages(Vector<Package> packages) {
		this.packages = packages;
	}

	public void addPackage(Package pkg) {
		packages.add(pkg);
	}

	public Package addPackage(String pkg) {

		for (int i = 0; i < packages.size(); i++) {
			if (packages.get(i).getName().endsWith(pkg)) {
				return packages.get(i);
			}
		}
		packages.add(new Package(pkg));
		return packages.getLast();
	}
	public void addClass(Class c) {
		classes.add(c);
	}
	public void addRelation(Relation r) {
		relations.add(r);
	}
	
	public Vector<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Vector<Relation> relations) {
		this.relations = relations;
	}

	public Vector<Class> getClasses() {
		return classes;
	}

	public void setClasses(Vector<Class> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		String rs="le nom de Projet : " + name + "\nle chmein de projet : " + path+"\n \t la liste des package est : ";
		
		for (Package p : packages) {
			rs+="\n\t-"+p;
		}
		return rs;
	}

}
