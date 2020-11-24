package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class test extends JFrame{
	public test() {
		hi();
	}
	
	private void hi() {
		JButton button = new JButton("hi");
		add(button);
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new testNew();
//			}
//		});
		
		button.addActionListener((e)->{
			new testNew();
		});
		setSize(200,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new test();
	}
}
