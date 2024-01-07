package org.mql.java.models;

import java.util.Vector;

public class Project {
	private String name;
	private String path;
	private Vector<Package> packages;

	public Project() {
		packages = new Vector<Package>();
	}

	public Project(String name, String path) {
		super();
		this.name = name;
		this.path = path;
		packages = new Vector<Package>();

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

	@Override
	public String toString() {
		String rs="le nom de Projet : " + name + "\nle chmein de projet : " + path;
		for (Package p : packages) {
			rs+="\n\t-"+p;
		}
		return rs;
	}

}
