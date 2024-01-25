package org.mql.java.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

public class PackageNode extends JPanel {
    
	private static final long serialVersionUID = 1L;
	
	private PackageDiagram packageDiagram;
    private int x, y;
    private org.mql.java.models.Package pack;

    public PackageNode(org.mql.java.models.Package pack, int x, int y, PackageDiagram diagram) {
        this.pack = pack;
        this.x = x;
        this.y = y;
        this.packageDiagram = diagram;
        setBackground(Color.red);

        setLayout(new BorderLayout());
        setBackground(Color.red);
        EntityNamePart packNamePart = new EntityNamePart(pack.getName());
        packNamePart.setBackground(Color.CYAN);
        packNamePart.setPreferredSize(new Dimension(400, 30));
        add(packNamePart, BorderLayout.PAGE_START);

        MyMouseListener mouseListener = new MyMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        setBorder(new LineBorder(Color.BLACK));

        drawClasses();
    }

    private void drawClasses() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (int i = 0; i < pack.getClasses().size(); i++) {
                JLabel label = new JLabel(pack.getClasses().get(i).getSimpleName());
                EmptyBorder paddingBorder = new EmptyBorder(0, 0, 0, 0);
                Border marginBorder = new EmptyBorder(5, 5, 5, 5);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(marginBorder, paddingBorder),
                        BorderFactory.createLineBorder(Color.black, 2) // Border color and thickness
                ));
                panel.add(label);
            
        }


        add(panel, BorderLayout.CENTER);
    }

    public PackageDiagram getPackageDiagram() {
        return packageDiagram;
    }

    public void setPackageDiagram(PackageDiagram packageDiagram) {
        this.packageDiagram = packageDiagram;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public org.mql.java.models.Package getPack() {
        return pack;
    }

    public void setPack(org.mql.java.models.Package pack) {
        this.pack = pack;
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            setLocation(e.getX() + x, e.getY() + y);
            x = e.getX() + x;
            y = e.getY() + y;
        }
    }
}
