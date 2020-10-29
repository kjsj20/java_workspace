package day1029.ImageGall;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MygallPanel extends JPanel implements MouseListener{
	Toolkit kit; 
	
	Image img;
	
	int srcNum;
	
	String src;
	
	MyimgCanvas mic;
	
	ImageGallApp iga;
	
	public MygallPanel(String dir, String src, int srcNum, MyimgCanvas mic, ImageGallApp iga){
		this.iga = iga;
		this.mic = mic;
		this.src = src;
		this.srcNum = srcNum;
		this.setLayout(new GridLayout(3,2));
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(dir + src);
		this.setPreferredSize(new Dimension(100,100));
		this.addMouseListener(this);
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
		System.out.println(srcNum);
		mic.num = srcNum;
		iga.imgLabel.setText(src);
		mic.repaint();
	}
}
