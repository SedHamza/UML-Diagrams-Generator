package org.mql.java.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EntityNamePart extends JPanel{
	String className;
	
	public EntityNamePart(String className) {
		this.className=className;
		add(new JLabel(this.className));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

}
