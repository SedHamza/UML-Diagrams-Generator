package org.mql.java.ui;
import java.awt.Dimension;

import javax.swing.*;

import org.mql.java.models.Project;

public class Window extends JFrame {
    private JPanel screen;

    public Window(Project project) {
        screen = new JPanel();
        screen.setLayout(null);
        JPanel leftPanel = new JPanel();

        // Create rightPanel with a preferred size
        ClassDiagram rightPanel = new ClassDiagram(project);
        rightPanel.setPreferredSize(new Dimension(8000, 8000));

        // Add rightPanel to a JScrollPane
        JScrollPane rightScrollPane = new JScrollPane(rightPanel);
        rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set up JSplitPane with left and right components
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightScrollPane);
        splitPane.setResizeWeight(0.2);
        setContentPane(splitPane);

        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

