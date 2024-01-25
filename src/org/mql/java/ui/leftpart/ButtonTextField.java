package org.mql.java.ui.leftpart;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.mql.java.models.Project;
import org.mql.java.reflection.ProjectExtractor;
import org.mql.java.ui.ClassDiagram;
import org.mql.java.ui.PackageDiagram;
import org.mql.java.ui.Window;

import javax.swing.ButtonGroup;

public class ButtonTextField extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField pathTextField;
	private JRadioButton packageRadioButton;
	private JRadioButton classRadioButton;
	private Window window;

	public ButtonTextField(Window window) {
		this.window = window;
		setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton chooseButton = new JButton("Choose");
		JButton run = new JButton("Run");

		pathTextField = new JTextField(100);
		packageRadioButton = new JRadioButton("Package");
		classRadioButton = new JRadioButton("Class");
		classRadioButton.setSelected(true);

		chooseButton.addActionListener(e -> chooseDirectory());
		run.addActionListener(e -> run());

		add(pathTextField);
		add(chooseButton);
		add(packageRadioButton);
		add(classRadioButton);
		add(run);

		chooseButton.setHorizontalAlignment(JButton.LEFT);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(packageRadioButton);
		radioButtonGroup.add(classRadioButton);

	}

	private void run() {
		Project project = ProjectExtractor.extractProject(pathTextField.getText());
		if (diagrameChoose() == "class")
			this.window.diagramePanel(new ClassDiagram(project));
		else
			this.window.diagramePanel(new PackageDiagram(project));

		this.window.getContentPane().remove(1);

		this.window.rep();
	}

	private void chooseDirectory() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
			pathTextField.setText(selectedPath);
		}
	}

	private String diagrameChoose() {
		boolean isPackageSelected = packageRadioButton.isSelected();
		boolean isClassSelected = classRadioButton.isSelected();
		if (isPackageSelected) {
			System.out.println("Package selected");
			return "package";
		} else
			System.out.println("Class selected");
		return "class";

	}

	public JTextField getPathTextField() {
		return pathTextField;
	}

	public boolean isPackageSelected() {
		return packageRadioButton.isSelected();
	}

	public boolean isClassSelected() {
		return classRadioButton.isSelected();
	}
}
