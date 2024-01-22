package org.mql.java.examples;

import org.mql.java.models.Project;
import org.mql.java.reflection.ProjectExtractor;
import org.mql.java.xml.XMLWriter;

public class Examples {
	public Examples() {
		exp01();
	}

	void exp01() {
		String path = "C:\\Users\\pc\\eclipse-workspace\\MQL\\TestProjetct";
		Project project = ProjectExtractor.extractProject(path);
		XMLWriter.projectToXML(project);
	}

	public static void main(String[] args) {
		new Examples();
	}
}
