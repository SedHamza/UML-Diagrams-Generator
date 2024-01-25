package org.mql.java.examples;

import java.io.File;

import org.mql.java.models.Project;
import org.mql.java.reflection.ProjectExtractor;
import org.mql.java.ui.Window;
import org.mql.java.xml.XMLParser;
import org.mql.java.xml.XMLUtils;
import org.mql.java.xml.XMLWriter;

public class Examples {
	public Examples() {
		exp03();
	}

	void exp01() {
		String path = "C:\\Users\\pc\\eclipse-workspace\\MQL\\TestProjetct";
		Project project = ProjectExtractor.extractProject(path);
		XMLWriter.projectToXML(project);
	}


	void exp02() {
		String path = "C:\\Users\\pc\\eclipse-workspace\\MQL\\TestProjetct";
		new Window(ProjectExtractor.extractProject(path));
	}

	void exp03() {
		String resourcesFolderPath = "resources";
		String xmlFilePath = resourcesFolderPath + File.separator + "TestProjetct";
		XMLParser root = new XMLParser(xmlFilePath);
		XMLUtils.xmlNodeToProject(root);
	}

	public static void main(String[] args) {
		new Examples();
	}
}
