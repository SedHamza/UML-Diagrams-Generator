package org.mql.java.reflection;

import java.io.File;
import org.mql.java.models.Class;
import org.mql.java.models.Interface;
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
			project.setName(projectDirectory.getParentFile().getName());
			project.setPath(projectDirectory.getParentFile().getAbsolutePath());

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
					exploreDirectory(file, project, pack.isEmpty() ? file.getName() : pack + "." + file.getName());
				} else if (file.isFile() && file.getName().endsWith(".class")) {
					Package pk = project.addPackage(pack);
					try {
						java.lang.Class<?> cls = new MaClassLoader(project.getPath()+"//bin", pk.getName() + "." + file.getName().replace(".class", "")).getMaClass();
						if(cls.isInterface()) {
							pk.addInterface(new Interface(cls));
						}
						else {
							pk.addClass(new Class(cls));	
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
