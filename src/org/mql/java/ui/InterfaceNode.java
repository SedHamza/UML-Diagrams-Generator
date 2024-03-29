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
import org.mql.java.models.Interface;

public class InterfaceNode extends JPanel implements EntityNode{
	Interface cls;
	ClassDiagram classDiagram;

	private int x, y; 

	public static final int WIDTH = 200;
	public static final int HEIGHT = 300;

	public InterfaceNode(Interface cls, int x, int y, ClassDiagram classDiagram) {
		this.cls = cls;
		this.x = x;
		this.y = y;
		this.classDiagram = classDiagram;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		EntityNamePart classNamePart = new EntityNamePart("<<inteface>> "+cls.getSimpleName());
		classNamePart.setBackground(Color.CYAN);
		classNamePart.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * 0.1)));
		add(classNamePart);

		FieldPart fieldPart=new FieldPart(cls.getFields());
		add(fieldPart);
		
		MethodPart methodPart=new MethodPart(cls.getMethodes());
		add(methodPart);	
		
		MyMouseListener mouseListener = new MyMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
		
	}

    private class MyMouseListener extends MouseAdapter {

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		// TODO Auto-generated method stub
    		super.mouseClicked(e);
    		System.out.println("hazma");
    	}

        @Override
        public void mouseDragged(MouseEvent e) {


            setLocation(e.getX()+x, e.getY()+y);

            x=e.getX()+x;
            y=e.getY()+y;

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
