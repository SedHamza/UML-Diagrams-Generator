package org.mql.java.ui.relation;

import java.awt.Graphics;
import java.awt.Point;

public class Implementation {


    private int class1X = 100;
    private int class1Y = 100;
    private int class1Width = 150;
    private int class1Height = 80;

    private int class2X = 300;
    private int class2Y = 100;
    private int class2Width = 150;
    private int class2Height = 80;

    protected void draw(Graphics g) {

        Point class1Center = new Point(class1X + class1Width / 2, class1Y + class1Height / 2);
        Point class2Center = new Point(class2X + class2Width / 2, class2Y + class2Height / 2);

        drawImplementationArrow(g, class1Center, class2Center);
    }

    private void drawImplementationArrow(Graphics g, Point start, Point end) {
        g.drawLine(start.x, start.y, end.x, end.y);

        drawArrowhead(g, start, end);
    }

    private void drawArrowhead(Graphics g, Point start, Point end) {
        double angle = Math.atan2(end.y - start.y, end.x - start.x);
        double arrowLength = 10;
        double arrowAngle = Math.toRadians(20);

        int x1 = end.x;
        int y1 = end.y;

        int x2 = (int) (end.x - arrowLength * Math.cos(angle - arrowAngle));
        int y2 = (int) (end.y - arrowLength * Math.sin(angle - arrowAngle));

        int x3 = (int) (end.x - arrowLength * Math.cos(angle + arrowAngle));
        int y3 = (int) (end.y - arrowLength * Math.sin(angle + arrowAngle));

        g.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
    }

}
