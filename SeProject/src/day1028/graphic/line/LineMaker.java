package day1028.graphic.line;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LineMaker extends JFrame implements ActionListener{
	LineCanvas can;
	JPanel pl;
	JLabel lb1;
	JLabel lb2;
	JLabel lb3;
	JLabel lb4;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JButton bt;
	
	public LineMaker() {
		can = new LineCanvas(0,0,0,0);
		pl = new JPanel();
		lb1 = new JLabel("x");
		lb2 = new JLabel("y");
		lb3 = new JLabel("x2");
		lb4 = new JLabel("y2");
		tf1 = new JTextField(5);
		tf2 = new JTextField(5);
		tf3 = new JTextField(5);
		tf4 = new JTextField(5);
		bt = new JButton("그리기");
		bt.addActionListener(this);
			pl.add(lb1);
			pl.add(tf1);
			pl.add(lb2);
			pl.add(tf2);
			pl.add(lb3);
			pl.add(tf3);
			pl.add(lb4);
			pl.add(tf4);
			pl.add(bt);
		
		
		
		add(pl, BorderLayout.NORTH);
		add(can);
		pl.setBackground(Color.YELLOW);
		can.setBackground(Color.GREEN);
		
		setSize(700,600);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		int xt =Integer.parseInt(tf1.getText());
		int yt = Integer.parseInt(tf2.getText());
		int x2t = Integer.parseInt(tf3.getText());
		int y2t  = Integer.parseInt(tf4.getText());
		JButton btn = (JButton)obj;
		if(btn.equals(bt)) {
			System.out.println("나눌렀어?");
			remove(can);
			new LineCanvas(xt, yt, x2t, y2t);
		}
	}
	
	public static void main(String[] args) {
		new LineMaker();
	}

}
