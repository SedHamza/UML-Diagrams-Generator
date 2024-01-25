package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.mql.java.models.Relation;
import org.mql.java.ui.relation.Association;
import org.mql.java.ui.relation.Extends;
import org.mql.java.ui.relation.Implementation;
import org.mql.java.models.Class;
import org.mql.java.models.Entity;
import org.mql.java.models.Interface;
import org.mql.java.models.Project;

public class ClassDiagram extends JPanel {
	Vector<EntityNode> entityNodes;
	Vector<Entity> entities;
	Vector<Relation> relations;

	public ClassDiagram() {
		setLayout(null);
	}

	public ClassDiagram(Project project) {
		setLayout(null);
		entities = project.getClasses();
		relations = project.getRelations();
		draw();
	}

	public ClassDiagram(Vector<Entity> classes, Vector<Relation> relations) {
		super();
		setLayout(null);
		this.entities = classes;
		this.relations = relations;

	}

	public void draw() {
		this.entityNodes = new Vector<EntityNode>();
		int lgn = 0, cln = 0;
		for (int i = 0; i < entities.size(); i++) {

			if (cln >= 5) {
				cln = 0;
				lgn++;
			}
			if (entities.get(i).isClass()) {
				entities.get(i).setEntityNode(addClass((Class) entities.get(i), cln, lgn));

			} else {
				entities.get(i).setEntityNode(addInterface((Interface) entities.get(i), cln, lgn));
			}
			cln++;
		}
	}

	ClassNode addClass(Class c, int cln, int lgn) {
		ClassNode classNode = new ClassNode(c, ClassNode.WIDTH * cln + 50 * (cln) + 10,
				ClassNode.HEIGHT * lgn + 50 * (lgn)+10, this);
		classNode.setLocation(classNode.getX(), classNode.getY());

		classNode.setSize(ClassNode.WIDTH, ClassNode.HEIGHT);
		classNode.setBackground(Color.white);

		add(classNode);

		entityNodes.add(classNode);
		return classNode;
	}

	private InterfaceNode addInterface(Interface in, int cln, int lgn) {
		InterfaceNode interfaceNode = new InterfaceNode(in, ClassNode.WIDTH * cln + 50 * (cln) + 10,
				ClassNode.HEIGHT * lgn + 50 * (lgn) + 10, this);
		interfaceNode.setLocation(interfaceNode.getX(), interfaceNode.getY());
		interfaceNode.setSize(ClassNode.WIDTH, ClassNode.HEIGHT);
		interfaceNode.setBackground(Color.white);
		add(interfaceNode);
		entityNodes.add(interfaceNode);

		return interfaceNode;
	}

	public void updateAssociations() {
		repaint(); 
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		for (Relation r : relations) {
			if (r.isAssociation()) {
				new Association(r.getSource(), r.getTarget()).draw(g);
			}
			if (r.isExtension()) {
				new Extends(r.getSource(), r.getTarget()).draw(g);
			}
			if (r.isImplementation()) {
				new Implementation(r.getSource(), r.getTarget()).draw(g);

			}

		}

	}

}
