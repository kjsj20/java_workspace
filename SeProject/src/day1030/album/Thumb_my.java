package day1030.album;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Thumb_my extends JPanel implements MouseListener{
	XCanvas_my can;
	GalleryApp_my ga;
	int srcNum;
	private Toolkit kit; //플랫폼에 의존적인 경로를 이용할때 툴킷이 필요 window d://
	private Image img;
	
	//자바에서 개발자가 말하는 상수의 형태.
	public static final int WIDTH = 75;
	public static final int HEIGHT = 55;
	
	public Thumb_my(String src, XCanvas_my can, GalleryApp_my ga, int srcNum) {
		this.ga = ga;
		this.can = can;
		this.srcNum = srcNum;
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(src);
		img= img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ga.n = srcNum;
		ga.la_name.setText(ga.src[srcNum]+"(" + (srcNum + 1) + "/" + ga.src.length  +")");
		can.setSrc(ga.dir + ga.src[srcNum]);
		can.repaint();
	}
	
	@Override
	//그려주는 paint메서드를 작성
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
