package org.mql.java.examples;

import org.mql.java.reflection.ProjectExtractor;

public class Examples { 
	public Examples() {
		exp01();
	}

	void exp01() {
		String path = "C:\\Users\\pc\\eclipse-workspace\\MQL\\Seddouki hamza - UML Diagrams Generator";
		ProjectExtractor.extractProject(path);
	}

	public static void main(String[] args) {
		new Examples();
	}
}
