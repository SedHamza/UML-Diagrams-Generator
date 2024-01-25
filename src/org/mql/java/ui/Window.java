package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import org.mql.java.models.Project;
import org.mql.java.ui.leftpart.ButtonTextField;

public class Window extends JFrame {
	private JPanel screen;

	public Window(Project project) {

		// Create topPanel with a BoxLayout
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(new ButtonTextField(this));

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);

		diagramePanel(new ClassDiagram(project));

		setSize(1500, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		///////////////////
		JButton button = new JButton("hamza");
		topPanel.add(button);
		button.addActionListener(e -> run());
	}
	

	void run(){
		for (int i = 0; i < this.getContentPane().getComponentCount(); i++) {
	    	System.out.println(this.getContentPane().getComponents()[0].getClass());
		}
		this.getContentPane().remove(1);
		revalidate();
		repaint();
    }
	public void diagramePanel(JPanel bottomPanel) {
	
		
		bottomPanel.setPreferredSize(new Dimension(8000, 8000));
		// Add bottomPanel to a JScrollPane
		JScrollPane bottomScrollPane = new JScrollPane(bottomPanel);
		bottomScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		bottomScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		getContentPane().add(bottomScrollPane, BorderLayout.CENTER);
	}
	public void rep() {
		revalidate();
		repaint();
	}

}
