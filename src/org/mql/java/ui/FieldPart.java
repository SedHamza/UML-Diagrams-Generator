package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FieldPart extends JPanel {

    private Field[] fields;

    public FieldPart(Field[] fields) {
        this.fields = fields;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);        
        add(scrollPane, BorderLayout.CENTER);
        displayFields(textArea);
    }

    private void displayFields(JTextArea textArea) {
        StringBuilder fieldText = new StringBuilder();
        for (Field field : fields) {
        	if(Modifier.toString(field.getModifiers()).equals("public")) {
        		fieldText.append(" + ");
        	}
        	else {
        		fieldText.append(" - ");

        	}
            fieldText.append(field.getName()).append("\n");
        }
        textArea.setText(fieldText.toString());
    }
}
