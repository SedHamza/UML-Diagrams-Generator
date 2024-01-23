package org.mql.java.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import org.mql.java.models.Relation;
import org.mql.java.models.Class;
import org.mql.java.models.Project;

public class ClassDiagram extends JPanel {
	Vector<ClassNode> classNodes;
	Vector<Class> classes;
	Vector<Relation> relations;

	public ClassDiagram() {
		setLayout(null);
	}

	public ClassDiagram(Project project) {
		setLayout(null);

		classes = project.getClasses();
		relations = project.getRelations();
	}

	public ClassDiagram(Vector<Class> classes, Vector<Relation> relations) {
		super();
		setLayout(null);
		this.classNodes = new Vector<ClassNode>();
		this.classes = classes;
		this.relations = relations;
		init();

	}

	public void init() {
		int y = 0, x = 0;

		for (int i = 0; i < classes.size(); i++) {
			ClassNode classNode;
			if (x >= 2) {
				x = 0;
				y++;
			}

			classNode = new ClassNode(classes.get(i), 200 * x + 10, 300 * y + 10);
			classNode.setLocation(classNode.getX(), classNode.getY());
			classNode.setSize(classNode.WIDTH, classNode.HEIGHT);
			classNode.setBackground(Color.white);

			
			add(classNode);


			classNodes.add(classNode);
			x++;

		}
	}


//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
////		for (int i = 0; i < classNodes.size(); i++) {
////			JPanel classNode = classNodes.get(i);
////			((ClassNode) classNode).draw(g);
////		}
//	}



}
