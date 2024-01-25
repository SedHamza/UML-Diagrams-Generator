package org.mql.java.ui.relation;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import org.mql.java.models.Class;
import org.mql.java.ui.ClassNode;
import org.mql.java.ui.EntityNode;
import org.mql.java.util.PointsProchesPerimetreRectangles;

public class Association {
	private int sx, sy, tx, ty;

	public Association(Class source, Class target) {
		EntityNode node = source.getEntityNode();
		Rectangle r1 = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
		node = target.getEntityNode();
		Rectangle r2 = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
		Point[] pts = PointsProchesPerimetreRectangles.pointsLesPlusProchesPerimetre(r1, r2);
		sx = (int) pts[0].getX();
		sy = (int) pts[0].getY();
		tx = (int) pts[1].getX();
		ty = (int) pts[1].getY();
	}

	public Association(int sx, int sy, int tx, int ty) {
		super();
		this.sx = sx;
		this.sy = sy;
		this.tx = tx;
		this.ty = ty;
	}

	public void updatePosition(int x, int y) {
		this.sx = x;
		this.sy = y;
	}

	public void draw(Graphics g) {

		g.drawLine(sx, sy, tx, ty);
		g.drawLine(sx, sy, tx, ty+1);

	}
}
