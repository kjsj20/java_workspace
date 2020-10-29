package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ThumbCanvas_my extends Canvas implements MouseListener{
	JPanel j_center; 
	Toolkit kit = Toolkit.getDefaultToolkit();
	String dir = "D:/java_workspace/SeProject/res/travel2/";
	String src;
	Image img;
	
	public ThumbCanvas_my(String src, JPanel j_center) {
		this.j_center = j_center;
		img = kit.getImage(dir + src);
		this.setPreferredSize(new Dimension(100,100));
		this.addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img,0,0,this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		j_center.setBackground(Color.red);
	}
}
