package org.mql.java.ui;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import org.mql.java.models.Class;
import org.mql.java.models.Entity;
import org.mql.java.models.Interface;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import org.mql.java.models.Relation;

public class PackageDiagram extends JPanel {
	Vector<PackageNode> packageNodes;
	Vector<Entity> entities;
	Vector<Relation> relations;
	Vector<Package> packages;

	public PackageDiagram(Project project) {
		setLayout(null);
		entities = project.getClasses();
		relations = project.getRelations();
		this.packages = project.getPackages();
		draw();
	}

	public void draw() {
		this.packageNodes = new Vector<PackageNode>();
		int lgn = 0, cln = 0;
		for (int y = 0; y < 5; y++) {
			for (int i = 0; i < packages.size(); i++) {
				if (cln >= 3) {
					cln = 0;
					lgn++;
				}
				packages.get(i).setPackageNode(drawPackageNode(packages.get(i), cln, lgn));
				cln++;
			}
		}

	}

	private PackageNode drawPackageNode(Package p, int cln, int lgn) {

		PackageNode packNode = new PackageNode(p, 300* cln + 50 * (cln) + 10,
				200 * lgn + 50 * (lgn) + 10, this);

		packNode.setLocation(packNode.getX(), packNode.getY());
		packNode.setSize(300, 200);
		packNode.setBackground(Color.white);
		add(packNode);

		return packNode;
	}

}
