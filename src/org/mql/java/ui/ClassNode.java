package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.mql.java.models.Class;
import org.mql.java.ui.relation.Association;

public class ClassNode extends JPanel implements EntityNode {
	ClassDiagram classDiagram;
	Class cls;
	private int x, y; // Coordonnées du coin supérieur gauche du rectangle

	public static final int WIDTH = 200; // Largeur du rectangle
	public static final int HEIGHT = 300; // Hauteur du rectangle

	public ClassNode(Class cls, int x, int y, ClassDiagram classDiagram) {
		this.cls = cls;
		this.x = x;
		this.y = y;
		this.classDiagram = classDiagram;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Partie 1 : le nom du class
		EntityNamePart classNamePart = new EntityNamePart(cls.getSimpleName());
		classNamePart.setBackground(Color.CYAN);
		classNamePart.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * 0.1)));
		add(classNamePart);

		// Partie 2 : Deuxième JPanel (ajoutez votre contenu ici)
		FieldPart fieldPart = new FieldPart(cls.getFields());
		add(fieldPart);

		// Partie 3 : Troisième JPanel avec JScrollPane
		MethodPart methodPart = new MethodPart(cls.getMethodes());
		add(methodPart);

		MyMouseListener mouseListener = new MyMouseListener();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);

	}
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		// TODO Auto-generated method stub
		super.setPreferredSize(preferredSize);
	}

	private class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			setLocation(e.getX() + x, e.getY() + y);
			x = e.getX() + x;
			y = e.getY() + y;
			classDiagram.updateAssociations();
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
