package day1029.ImageGall;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class MyimgCanvas extends Canvas{
	Toolkit kit; 
	String dir = "D:/java_workspace/SeProject/res/travel2/";
	String[] src = {"aa.jpg", "ab.jpg", "ax.jpg", "bm.jpg", "et.jpg", "kg.jpg", "mx.jpg"};
	Image[] img = new Image[src.length];
	
	int num = 0;
	
	public MyimgCanvas(){
		kit = Toolkit.getDefaultToolkit();
		for(int i=0; i<img.length; i++) {
			img[i] = kit.getImage(dir + src[i]);
			img[i] = img[i].getScaledInstance(700, 800, Image.SCALE_SMOOTH);
		}
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img[num], 0, 0, this);
	}
}
