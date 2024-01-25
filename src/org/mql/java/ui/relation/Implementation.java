package org.mql.java.ui.relation;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import org.mql.java.models.Class;
import org.mql.java.models.Entity;
import org.mql.java.ui.ClassNode;
import org.mql.java.ui.EntityNode;
import org.mql.java.util.PointsProchesPerimetreRectangles;

public class Implementation {

	private Entity superClass;
	private Entity subClass;

	public Implementation(Entity superClass, Entity subClass) {
		this.superClass = superClass;
		this.subClass = subClass;
	}

	public void draw(Graphics g) {
		EntityNode node = superClass.getEntityNode();
		Rectangle r1 = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
		node = subClass.getEntityNode();

		Rectangle r2 = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
		Point[] pts = PointsProchesPerimetreRectangles.pointsLesPlusProchesPerimetre(r1, r2);
		int x1 = (int) pts[0].getX();
		int y1 = (int) pts[0].getY();
		int x2 = (int) pts[1].getX();
		int y2 = (int) pts[1].getY();

		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x1, y1, x2+1, y2+1);

		drawArrow(g, x1, y1, x2, y2);
	}

	private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
		// Draw an arrowhead at the end of the line
		int arrowSize = 16;
		double angle = Math.atan2(y2 - y1, x2 - x1);
		int x3 = x2 - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
		int y3 = y2 - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
		int x4 = x2 - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
		int y4 = y2 - (int) (arrowSize * Math.sin(angle + Math.PI / 6));

		Polygon triangle = createFilledTriangle(x2, y2, x3, y3, x4, y4);

//		g.drawLine(x2, y2, x3, y3);
//		g.drawLine(x2, y2, x4, y4);
		g.fillPolygon(triangle);

//		g.drawLine(x3, y3, x4, y4);
	}

	private Polygon createFilledTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		int[] xPoints = { x1, x2, x3 };
		int[] yPoints = { y1, y2, y3 };

		return new Polygon(xPoints, yPoints, 3);
	}
}
