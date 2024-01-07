package org.mql.java.reflection;

import java.io.File;
import org.mql.java.models.Class;
import org.mql.java.models.Package;
import org.mql.java.models.Project;

public class ProjectExtractor {


	public static Project extractProject(String projectUrl) {
		Project project = new Project();
		
		try {
			if (!projectUrl.contains("bin")) {
				projectUrl += "\\bin";
			}
			File projectDirectory = new File(projectUrl);
			project.setName(projectDirectory.getName());
			project.setPath(projectUrl);

			// Explorer récursivement les fichiers du projet
			exploreDirectory(projectDirectory, project, "");
		} catch (Exception e) {
			System.out.println("Erreur en find file project : " + e.getMessage());
		}
		System.out.println(project);
		return project;
	}

	private static void exploreDirectory(File directory, Project project, String pack) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					//System.out.println("Directory :" + file.getName()+" package :"+pack);
					exploreDirectory(file, project, pack.isEmpty() ? file.getName() : pack + "." + file.getName());
				} else if (file.isFile() && file.getName().endsWith(".class")) {
					Package pk = project.addPackage(pack);
//					System.out.println(pk.getName() + "." + file.getName().replace(".class", ""));
					try {
						java.lang.Class<?> cls = java.lang.Class
								.forName(pk.getName() + "." + file.getName().replace(".class", ""));
						pk.addClass(new Class(cls));
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
