package day1029.graphic.image;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class imagePickerApp_my extends JFrame{
	JPanel j_north;
	JPanel j_center; 
	String src[] = {"aa.jpg","ab.jpg","ax.jpg","bm.jpg","et.jpg","kg.jpg","mx.jpg"};
	ThumbCanvas_my[] thumb = new ThumbCanvas_my[7];
	public imagePickerApp_my() {
		JPanel j_center = new JPanel();
		j_center.setBackground(Color.CYAN);
		JPanel j_north = new JPanel();
		
		add(j_center);
		
		for(int i=0; i<thumb.length; i++) {
			thumb[i] = new ThumbCanvas_my(src[i], this.j_center);
			j_north.add(thumb[i]);
		}
		
		add(j_north, BorderLayout.NORTH);
		
		setSize(770,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new imagePickerApp_my();
	}
}
