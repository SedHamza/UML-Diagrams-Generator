package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MethodPart extends JPanel {

	private Method[] methods;

	public MethodPart(Method[] methods) {
		this.methods = methods;
		setLayout(new BorderLayout()); 
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		displayMethodes(textArea);
	}

	private void displayMethodes(JTextArea textArea) {
		StringBuilder methodText = new StringBuilder();
		for (Method m : methods) {
	       	if(Modifier.toString(m.getModifiers()).equals("public")) {
	       		methodText.append(" + ");
        	}
        	else if(Modifier.toString(m.getModifiers()).equals("private")){
        		methodText.append(" - ");

        	}
        	else {
        		methodText.append(" # ");

        	}
			methodText.append(m.getName());
			methodText.append("(").append(getAttributeMethod(m)).append(") : ");
			methodText.append(m.getReturnType().getSimpleName()).append("\n");
		}
		textArea.setText(methodText.toString());
	}

	private String getAttributeMethod(Method m) {
		String att = "";
		for (int i = 0; i < m.getParameterCount(); i++) {
			att += m.getParameterTypes()[i].getSimpleName() + ", ";
		}
		return att.isEmpty() ? "" : att.substring(0, att.length()-2);
	}
}
