package org.mql.java.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.mql.java.models.Entity;
import org.mql.java.models.Interface;
import org.mql.java.models.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLWriter {
	public static void projectToXML(Project project) {
		String resourcesFolderPath = "resources";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			// Créer l'élément racine
			Element rootElement = document.createElement("project");
			document.appendChild(rootElement);
			Element projectName = document.createElement("name");
			projectName.appendChild(document.createTextNode(project.getName()));

			Element projectPath = document.createElement("path");
			projectPath.appendChild(document.createTextNode(project.getPath()));

			rootElement.appendChild(projectName);
			rootElement.appendChild(projectPath);
			// Créer l'élément du package
			if (project.getPackages().size() != 0) {
				Element packsElement = document.createElement("packages");
				for (int i = 0; i < project.getPackages().size(); i++) {
					Element packElement = document.createElement("package");
					Element packNameElement = document.createElement("name");
					packNameElement.appendChild(document.createTextNode(project.getPackages().get(i).getName()));
					packElement.appendChild(packNameElement);
					Element classesElement = getClassesXML(project.getPackages().get(i).getClasses(), document,
							"classes");
					if (classesElement != null)
						packElement.appendChild(classesElement);
					Element interfacesElement = getInterfacesXML(project.getPackages().get(i).getInterfaces(),
							document);
					if (interfacesElement != null)
						packElement.appendChild(interfacesElement);
					packsElement.appendChild(packElement);
				}
				rootElement.appendChild(packsElement);
			}
			String xmlString = convertDocumentToString(document);
			String xmlFilePath = resourcesFolderPath + File.separator + project.getName();
			XMLUtils.writeToFile(xmlString, xmlFilePath);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private static Element getInterfacesXML(Vector<Interface> interfaces, Document document) {
		Element intefacesElement = document.createElement("interfaces");
		for (int i = 0; i < interfaces.size(); i++) {
			Element intefaceElement = document.createElement("interface");
			Element intefaceName = document.createElement("name");
			intefaceName.appendChild(document.createTextNode(interfaces.get(i).getName()));
			intefaceElement.appendChild(intefaceName);
			Element intefaceSName = document.createElement("simpleName");
			intefaceSName.appendChild(document.createTextNode(interfaces.get(i).getSimpleName()));
			intefaceElement.appendChild(intefaceSName);
			Element filedsElement = getClassFieldsXML(interfaces.get(i).getFields(), document);
			if (filedsElement != null)
				intefaceElement.appendChild(filedsElement);
			Element methodesElement = getClassMethodesXML(interfaces.get(i).getMethodes(), document);
			if (methodesElement != null)
				intefaceElement.appendChild(methodesElement);
			intefacesElement.appendChild(intefaceElement);

		}
		return intefacesElement;
	}

	private static Element getClassesXML(Vector<org.mql.java.models.Class> cls, Document document, String element) {

		Element classesElement = document.createElement(element);

		for (int i = 0; i < cls.size(); i++) {

			Element classElement = document.createElement(element.substring(0, element.length() - 2));
			classElement.setAttribute("modifier", cls.get(i).getModifier());

			Element classNameElement = document.createElement("name");
			classNameElement.appendChild(document.createTextNode(cls.get(i).getName()));

			Element classSNameElement = document.createElement("simpleName");
			classSNameElement.appendChild(document.createTextNode(cls.get(i).getSimpleName()));

			classElement.appendChild(classNameElement);
			classElement.appendChild(classSNameElement);

			Element superClass = getSupperClassXML(cls.get(i).getSuperClass(), document);
			if (superClass != null)
				classElement.appendChild(superClass);

			Element implementations = getClassImplementedXML(cls.get(i).getImplementedClass(), document);
			if (implementations != null)
				classElement.appendChild(implementations);

			Element fieldElement = getClassFieldsXML(cls.get(i).getFields(), document);
			if (fieldElement != null)
				classElement.appendChild(fieldElement);

			Element constructorsElement = getClassConstractorXML(cls.get(i).getConstructors(), document);
			if (constructorsElement != null)
				classElement.appendChild(constructorsElement);

			Element methodesElement = getClassMethodesXML(cls.get(i).getMethodes(), document);
			if (methodesElement != null)
				classElement.appendChild(methodesElement);

			Element localClasses = getLocalClassXML(cls.get(i).getLocalClasses(), document);
			if (localClasses != null)
				classElement.appendChild(localClasses);

			classesElement.appendChild(classElement);
		}
		return classesElement;
	}

	private static Element getLocalClassXML(org.mql.java.models.Class[] cls, Document document) {

		Element implementedClassElement = document.createElement("localClasses");
		Vector<org.mql.java.models.Class> clss = new Vector<>();
		for (int i = 0; i < cls.length; i++) {
			clss.add(cls[i]);
		}
		if (clss.size() != 0)
			implementedClassElement.appendChild(getClassesXML(clss, document, "localClasses"));

		return implementedClassElement;
	}

	private static Element getSupperClassXML(Entity cls, Document document) {
		Element superClassElement = document.createElement("superClass");

		if (cls == null)
			return superClassElement;
		Element superClassNameElement = document.createElement("name");
		superClassNameElement.appendChild(document.createTextNode(cls.getName()));
		Element superClassSNameElement = document.createElement("simpleName");
		superClassSNameElement.appendChild(document.createTextNode(cls.getSimpleName()));
		superClassElement.appendChild(superClassNameElement);
		superClassElement.appendChild(superClassSNameElement);
		return superClassElement;
	}

	private static Element getClassImplementedXML(org.mql.java.models.Class[] cls, Document document) {
		Element implementedClassElement = document.createElement("implentations");

		if (cls.length == 0)
			return implementedClassElement;

		for (int i = 0; i < cls.length; i++) {
			Element implementation = document.createElement("implentation");

			Element implementationNameElement = document.createElement("name");
			implementationNameElement.appendChild(document.createTextNode(cls[i].getName()));
			Element implementationSNameElement = document.createElement("simpleName");
			implementationSNameElement.appendChild(document.createTextNode(cls[i].getSimpleName()));
			implementation.appendChild(implementationNameElement);
			implementation.appendChild(implementationSNameElement);
			implementedClassElement.appendChild(implementation);
		}

		return implementedClassElement;
	}

	private static Element getClassFieldsXML(Field[] fields, Document document) {
		Element fieldsElement = document.createElement("fields");

		if (fields.length == 0)
			return fieldsElement;

		for (int i = 0; i < fields.length; i++) {
			Element fieldElement = document.createElement("field");
			fieldElement.setAttribute("modifier", Modifier.toString(fields[i].getModifiers()));
			Element fieldTypeElement = document.createElement("type");
			fieldTypeElement.appendChild(document.createTextNode(fields[i].getType().getName().toString()));
			Element fieldNameElement = document.createElement("name");
			fieldNameElement.appendChild(document.createTextNode(fields[i].getName()));
			fieldElement.appendChild(fieldTypeElement);
			fieldElement.appendChild(fieldNameElement);
			fieldsElement.appendChild(fieldElement);
		}
		return fieldsElement;
	}

	private static Element getClassConstractorXML(Constructor[] constructors, Document document) {
		Element constructorsElement = document.createElement("constructors");
		for (int i = 0; i < constructors.length; i++) {
			Element constructorElement = document.createElement("constructor");
			constructorElement.setAttribute("modifier", Modifier.toString(constructors[i].getModifiers()));
			for (int j = 0; j < constructors[i].getParameterCount(); j++) {
				Element parametreElement = document.createElement("parametre" + j + 1);
				parametreElement.setAttribute("type", constructors[i].getParameterTypes()[j].getName());
				constructorElement.appendChild(parametreElement);
			}
			constructorsElement.appendChild(constructorElement);
		}
		return constructorsElement;
	}

	private static Element getClassMethodesXML(Method[] methods, Document document) {
		Element constructorsElement = document.createElement("constructors");
		for (int i = 0; i < methods.length; i++) {
			Element constructorElement = document.createElement("constructor");
			constructorElement.setAttribute("modifier", Modifier.toString(methods[i].getModifiers()));
			constructorElement.setAttribute("type", methods[i].getReturnType().getName());
			for (int j = 0; j < methods[i].getParameterCount(); j++) {
				Element parametreElement = document.createElement("parametre" + j + 1);
				parametreElement.setAttribute("type", methods[i].getParameterTypes()[j].getName());
				constructorElement.appendChild(parametreElement);
			}
			constructorsElement.appendChild(constructorElement);
		}
		return constructorsElement;
	}

	private static String convertDocumentToString(Document document) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StringWriter stringWriter = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
			return stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String getResourceFolderPath() {
		ClassLoader classLoader = XMLWriter.class.getClassLoader();
		File resourcesDirectory = new File(classLoader.getResource("").getFile());
		return resourcesDirectory.getAbsolutePath();
	}
}
