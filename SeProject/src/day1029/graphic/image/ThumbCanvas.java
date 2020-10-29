package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThumbCanvas extends Canvas implements MouseListener {
	Toolkit kit;
	Image img;
	DetailPanel p_center;
	
	public ThumbCanvas(String path, DetailPanel p_center) {
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(path);
		this.setPreferredSize(new Dimension(100,100));
		this.p_center = p_center;
		this.addMouseListener(this);
	}
	//도화지에 그림을 그리자!! 모든 컴포넌트는 스스로 그림의 주체이자, 그려질 대상이기도 하다
	@Override
	public void paint(Graphics g) {
		g.drawImage(img,0,0, this);
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
		p_center.setImg(img);
		p_center.repaint();
	}
}
