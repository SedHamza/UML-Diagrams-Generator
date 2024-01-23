package org.mql.java.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.mql.java.models.Class;

public class ClassNode extends JPanel {
	Class cls;
	private int x, y; // Coordonnées du coin supérieur gauche du rectangle

	public static final int WIDTH = 200; // Largeur du rectangle
	public static final int HEIGHT = 300; // Hauteur du rectangle

	public ClassNode(Class cls, int x, int y) {
		this.cls = cls;
		this.x = x;
		this.y = y;
		
		MyMouseListener mouseListener = new MyMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		draw(g);
	}
    private class MyMouseListener extends MouseAdapter {

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		// TODO Auto-generated method stub
    		super.mouseClicked(e);
    		System.out.println("hazma");
    	}
//        @Override
//        public void mousePressed(MouseEvent e) {
//            classNodes.get(i).setX(e.getX());
//            classNodes.get(i).setY(e.getY());
//        }

        @Override
        public void mouseDragged(MouseEvent e) {


            setLocation(e.getX()+x, e.getY()+y);

            x=e.getX()+x;
            y=e.getY()+y;

            
        }
    }
	


	public void draw(Graphics g) {
		g.drawRect(10, 10, 20, 20);
		// Dessiner le rectangle représentant la classe
//		g.drawRect(x, y, WIDTH, HEIGHT);
//
//		// Dessiner le nom de la classe
//		g.drawString(cls.getSimpleName(), x + 10, y + 20);
//		g.drawLine(x, y + 30, x + WIDTH, y + 30);
//		// Dessiner les attributs
//		int attributeY = y + 45;
//		for (Field f : cls.getFields()) {
//			g.drawString("- " + f.getName(), x + 10, attributeY);
//			attributeY += 20;
//		}
//		g.drawLine(x, y + 30, x + WIDTH, y + 30);
//		// Dessiner les méthodes
//		int methodY = attributeY + 30;
//		for (Method m : cls.getMethodes()) {
//			g.drawString("+ " + m.getName(), x + 10, methodY);
//			methodY += 20;
//		}
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
