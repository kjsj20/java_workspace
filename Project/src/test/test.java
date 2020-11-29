package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class test extends JFrame{
	public test() {
		hi();
	}
	
	private void hi() {
		setSize(400,360);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setPreferredSize(new Dimension(400, 40));
		panel.add(panel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_1_2.add(lblNewLabel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setPreferredSize(new Dimension(400, 40));
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1_1.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(400, 40));
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);
	}
	
	public static void main(String[] args) {
		new test();
	}
}
