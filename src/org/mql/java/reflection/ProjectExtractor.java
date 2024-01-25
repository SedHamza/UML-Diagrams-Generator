package org.mql.java.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mql.java.models.Association;
import org.mql.java.models.Class;
import org.mql.java.models.Entity;
import org.mql.java.models.Extension;
import org.mql.java.models.Implementation;
import org.mql.java.models.Interface;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import org.mql.java.models.Relation;

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
		extractRelations(project);
		Vector<Relation> relations = project.getRelations();
//		for (int i = 0; i < relations.size(); i++) {
//			if (relations.get(i).isAssociation()) {
//				System.out.println("we have assosiation from " + relations.get(i).getSource().getSimpleName() + " to "
//						+ relations.get(i).getTarget().getSimpleName());
//			} else if (relations.get(i).isExtension()) {
//				System.out.println("we have Extension from " + relations.get(i).getSource().getSimpleName() + " to "
//						+ relations.get(i).getTarget().getSimpleName());
//			} else if (relations.get(i).isImplementation()) {
//				System.out.println("we have Implementation from " + relations.get(i).getSource().getSimpleName()
//						+ " to " + relations.get(i).getTarget().getSimpleName());
//			}
//		}
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
						java.lang.Class<?> cls = new MaClassLoader(project.getPath() + "//bin",
								pk.getName() + "." + file.getName().replace(".class", "")).getMaClass();
						if (cls != null) {
							
							if (cls.isInterface()) {
								Interface in = new Interface(cls);
								pk.addInterface(in);
								project.addClass(in);

							} else {
								Class c = new Class(cls);
								pk.addClass(c);
								project.addClass(c);
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static void extractRelations(Project project) {
		Vector<Package> pkgs = project.getPackages();
		for (int i = 0; i < pkgs.size(); i++) {
			Vector<Class> cls = pkgs.get(i).getClasses();

			for (int j = 0; j < cls.size(); j++) {
				setExtensionRelation(project, cls.get(j));
				setImplemetationRelation(project, cls.get(j));
				setRelations(project, cls.get(j));
			}
			Vector<Interface> interfaces = pkgs.get(i).getInterfaces();
			for (int j = 0; j < interfaces.size(); j++) {
				setExtensionRelation(project, interfaces.get(j));
			}
			
			
		}
	}

	private static void setRelations(Project project, Class cls) {
		for (Field f : cls.getFields()) {
			if (f.getType().isArray()) {
				List<Entity> cl = project.getClasses().stream()
						.filter(e -> e.getName().equals(f.getType().getComponentType().getName())).toList();

				if (!cl.isEmpty()) {
					project.addRelation(new Association(cl.get(0), cls, "0,*", "0,1"));
				}
			} else if (Collection.class.isAssignableFrom(f.getType())) {

			} else {
				List<Entity> cl = project.getClasses().stream().filter(e -> e.getName().equals(f.getType().getName()))
						.toList();
				if (!cl.isEmpty()) {
					project.addRelation(new Association(cl.get(0), cls, "0,1", "0,*"));
				}
			}

		}
	}

	private static void setExtensionRelation(Project project, Entity cls) {
		if (cls.getSuperClass() != null) {
//			System.out.println(
//					"ona une extension de class " + cls.getSuperClass().getSimpleName() + " to " + cls.getSimpleName());
			Entity en=project.getClasses().stream().filter(e->e.getName().equals(cls.getSuperClass().getName())).toList().get(0);
			project.addRelation(new Extension(en, cls));
		}
	}

	private static void setImplemetationRelation(Project project, Class cls) {
		for (int i = 0; i < cls.getImplementedClass().length; i++) {
			Class cl=cls.getImplementedClass()[i];
			Entity en=project.getClasses().stream().filter(e->e.isInterface()&&e.getName().equals(cl.getName())).toList().get(0);
			project.addRelation(new Implementation(en, cls));
		}
	}
}
