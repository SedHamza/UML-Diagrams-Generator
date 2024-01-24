package org.mql.java.util;

import java.awt.Point;
import java.awt.Rectangle;

public class PointsProchesPerimetreRectangles {

	  public static double distance(Point p1, Point p2) {
	        // Calcule la distance entre deux points
	        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	    }

	    public static Point[] pointsLesPlusProchesPerimetre(Rectangle rect1, Rectangle rect2) {
	        Point[] pointsProches = { null, null };
	        double distanceMinimale = Double.MAX_VALUE;

	        // Itérer sur le périmètre du rectangle 1
	        for (Point point1 : getPerimetre(rect1)) {
	            // Itérer sur le périmètre du rectangle 2
	            for (Point point2 : getPerimetre(rect2)) {
	                double distanceActuelle = distance(point1, point2);
	                if (distanceActuelle < distanceMinimale) {
	                    distanceMinimale = distanceActuelle;
	                    pointsProches[0] = point1;
	                    pointsProches[1] = point2;
	                }
	            }
	        }

	        return pointsProches;
	    }

	    public static Point[] getPerimetre(Rectangle rect) {
	        // Retourne un tableau des points du périmètre du rectangle
	        return new Point[] {
	            new Point(rect.x, rect.y),
	            new Point(rect.x + rect.width/4, rect.y),

	            new Point(rect.x + rect.width/2, rect.y),
	            new Point(rect.x + 3*rect.width/4, rect.y),

	            new Point(rect.x + rect.width, rect.y),
	            new Point(rect.x, rect.y + rect.height/2),
	            new Point(rect.x, rect.y + rect.height),
	            new Point(rect.x + rect.width/2, rect.y + rect.height),
	            new Point(rect.x + rect.width, rect.y + rect.height/4),
	            new Point(rect.x + rect.width, rect.y + rect.height/2),
	            new Point(rect.x + rect.width, rect.y + 3*rect.height/4),
	            new Point(rect.x + rect.width, rect.y + rect.height)
	        };
	    }
}
