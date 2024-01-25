package org.mql.java.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.mql.java.models.Class;
import org.mql.java.models.Entity;
import org.mql.java.models.Package;
import org.mql.java.models.Project;

public class XMLUtils {
	public static void writeToFile(String content, String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Project xmlNodeToProject(XMLParser root) {
		Project project = new Project(root.getChild("name").getValue(), root.getChild("path").getValue());
		XMLParser[] packageNodes = root.getChild("packages").getChildren();
		for (int i = 0; i < packageNodes.length; i++) {

			Package pack = new Package(packageNodes[i].getChild("name").getValue());

			XMLParser[] classesNodes = packageNodes[i].getChild("classes").getChildren();

			Vector<Entity> classes = getClassesFromXML(classesNodes);

			project.addAllClass(classes);

			project.addPackage(pack);
		}
		for (int i = 0; i < project.getClasses().size(); i++) {
			System.out.println(project.getClasses().get(i).getSimpleName()+" - ");
		}
		return null;
	}

	private static Vector<Entity> getClassesFromXML(XMLParser[] clsNode) {
		Vector<Entity> classes = new Vector<Entity>();

		for (int i = 0; i < clsNode.length; i++) {
			Class cls = new Class(clsNode[i].getChild("name").getValue());
			cls.setSimpleName(clsNode[i].getChild("simpleName").getValue());
			classes.add(cls);
		}

		return classes;
	}

}
