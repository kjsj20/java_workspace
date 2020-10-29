package day1029.ImageGall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageGallApp extends JFrame implements ActionListener{
	JPanel p_north, p_south, p_center, p_west;
	JButton bt_prev, bt_next;
	JLabel imgLabel;
	MyimgCanvas mic;

	public ImageGallApp() {
		p_north = new JPanel();
		p_south = new JPanel();
		p_center = new JPanel();
		p_west = new JPanel();
		imgLabel = new JLabel("image");
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		mic = new MyimgCanvas();

		add(p_north, BorderLayout.NORTH);
		p_north.setBackground(Color.pink);
		add(p_south, BorderLayout.SOUTH);
		p_south.setBackground(Color.CYAN);
		add(p_center, BorderLayout.CENTER);
		p_center.setBackground(Color.orange);
		add(p_west, BorderLayout.WEST);
		p_west.setBackground(Color.red);

		p_north.add(imgLabel);
		p_south.add(bt_prev);
		p_south.add(bt_next);
		add(mic);
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		imgLabel.setText(mic.src[mic.num]);
		
		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj == bt_prev) {
				mic.num--;
				imgLabel.setText(mic.src[mic.num]);
			} else if (obj == bt_next) {
				mic.num++;				
				imgLabel.setText(mic.src[mic.num]);
			}
			mic.repaint();
	}
	
	public static void main(String[] args) {
		new ImageGallApp();
	}
}
