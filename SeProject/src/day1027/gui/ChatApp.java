package day1027.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatApp extends JFrame {
	JTextArea area;
	JTextField tf;
	JButton bt;
	BorderLayout bl;
	JPanel pl;
	
	public ChatApp() {
		pl = new JPanel();
		area = new JTextArea();
		tf = new JTextField();
		bt = new JButton("제출");
		bl = new BorderLayout();
		
		add(area, BorderLayout.NORTH);
		pl.setLayout(new FlowLayout());
		pl.add(tf);
		pl.add(bt);
		add(area);
		add(pl);
		
		setSize(300,400);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ChatApp();
	}
}
