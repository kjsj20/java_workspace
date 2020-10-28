package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp_my extends JFrame implements MouseListener {

	JPanel main_p, north_p, red, orange, yellow, green, cyan, blue, pink;

	public ColorPickerApp_my() {
		int pw, ph;
		pw = 80;
		ph = 100;
		main_p = new JPanel();
		north_p = new JPanel();
		red = new JPanel();
		orange = new JPanel();
		yellow = new JPanel();
		green = new JPanel();
		cyan = new JPanel();
		blue = new JPanel();
		pink = new JPanel();

		add(main_p);
		main_p.setBackground(Color.black);
		add(north_p, BorderLayout.NORTH);

		north_p.add(red);
		red.setBackground(Color.RED);
		red.setPreferredSize(new Dimension(pw, ph));
		red.addMouseListener(this);
		
		north_p.add(orange);
		orange.setBackground(Color.ORANGE);
		orange.setPreferredSize(new Dimension(pw, ph));
		orange.addMouseListener(this);
		
		north_p.add(yellow);
		yellow.setBackground(Color.yellow);
		yellow.setPreferredSize(new Dimension(pw, ph));
		yellow.addMouseListener(this);
		
		north_p.add(green);
		green.setBackground(Color.green);
		green.setPreferredSize(new Dimension(pw, ph));
		green.addMouseListener(this);
		
		north_p.add(cyan);
		cyan.setBackground(Color.CYAN);
		cyan.setPreferredSize(new Dimension(pw, ph));
		cyan.addMouseListener(this);
		
		north_p.add(blue);
		blue.setBackground(Color.blue);
		blue.setPreferredSize(new Dimension(pw, ph));
		blue.addMouseListener(this);
		
		north_p.add(pink);
		pink.setBackground(Color.pink);
		pink.setPreferredSize(new Dimension(pw, ph));
		pink.addMouseListener(this);
		
		setSize(700, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ColorPickerApp_my();
	}

	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj.equals(red)) {
			main_p.setBackground(Color.RED);
		} else if(obj.equals(orange)) {
			main_p.setBackground(Color.orange);
		} else if(obj.equals(yellow)) {
			main_p.setBackground(Color.yellow);
		} else if(obj.equals(green)) {
			main_p.setBackground(Color.green);
		} else if(obj.equals(cyan)) {
			main_p.setBackground(Color.CYAN);
		} else if(obj.equals(blue)) {
			main_p.setBackground(Color.blue);
		} else if(obj.equals(pink)) {
			main_p.setBackground(Color.pink);
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
}
