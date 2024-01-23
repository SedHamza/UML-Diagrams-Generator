package org.mql.java.ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.mql.java.models.Project;


public class Window extends JFrame{
	private JPanel screen;
	

	public Window(Project project) {
		screen = new JPanel();
		screen.setLayout(null);
		

        JPanel leftPanel = new JPanel();

        ClassDiagram rightPanel = new ClassDiagram(project.getClasses(),project.getRelations());  
        JScrollPane rightScrollPane = new JScrollPane(rightPanel);

        // Utiliser JSplitPane pour diviser la fenêtre en panneau gauche et panneau droit
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightScrollPane);
        splitPane.setResizeWeight(0.2);  // Définir le poids pour ajuster la taille initiale des panneaux

		setContentPane(splitPane);
		
		setSize(1500,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		rightPanel.setBackground(Color.yellow);

	}
	

}
