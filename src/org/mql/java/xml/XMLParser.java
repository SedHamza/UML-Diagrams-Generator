package org.mql.java.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class XMLParser {

	private Node node;
	private XMLParser[] children;

	public XMLParser(Node node, XMLParser[] children) {
		this.node = node;
		this.children = children;
	}

	public XMLParser(Node node) {
		this.node = node;
		extractChildren();
	}

	public XMLParser(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		// factory.setValidating(true);//pour un parseur validant
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(source);
			// Element et un Node
			// document.getDocumentElement();//=>Element
			Node node = document.getFirstChild();
			while (node.getNodeType() != Node.ELEMENT_NODE) {
				node = node.getNextSibling();
			}
			setNode(node);

		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	public static XMLParser fromSource(String source) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return new XMLParser(builder.parse(source).getDocumentElement());
	}

	private void extractChildren() {
		LinkedList<XMLParser> nodes = new LinkedList<XMLParser>();
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i).getNodeType() == Node.ELEMENT_NODE)
				nodes.add(new XMLParser(list.item(i)));
		}

		children = new XMLParser[nodes.size()];
		nodes.toArray(children);
	}

	public XMLParser getChild(String name) {

		List<XMLParser> c =  Arrays.stream(children).filter(child -> child.isNamed(name)).toList();
		return c.size() == 0 ? null : c.getFirst();
	}

	public boolean isNamed(String name) {
		return node.getNodeName().equals(name);
	}

	public String getName() {
		return node.getNodeName();
	}

	public String getValue() {
		return Optional.ofNullable(node.getFirstChild()).filter(child -> child.getNodeType() == Node.TEXT_NODE)
				.map(Node::getNodeValue).orElse("");
	}

	public XMLParser[] getChildren() {
		return children;
	}

	public void setChildren(XMLParser[] children) {
		this.children = children;
	}

	public boolean isElement() {
		return node instanceof Element;
	}

	public boolean isText() {
		return node.getNodeType() == Node.TEXT_NODE;
	}

	public Map<String, String> getAttributes() {
		Map<String, String> attributes = new HashMap<>();
		if (node instanceof Element) {
			NamedNodeMap attributeMap = ((Element) node).getAttributes();
			for (int i = 0; i < attributeMap.getLength(); i++) {
				Node attribute = attributeMap.item(i);
				attributes.put(attribute.getNodeName(), attribute.getNodeValue());
			}
		}
		return attributes;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
		extractChildren();

	}

}
