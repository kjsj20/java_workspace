package day1028.graphic.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/*나만의 패널 정의하기 - 기존 패널의 커스터마이징 custom*/
public class ThumbPanel extends JPanel implements MouseListener{
		JPanel p_center; // null 
		Color color;
		//너비, 높이, 색상을 가진 패널로 태어나게!!
		public ThumbPanel(JPanel p_center, Color color) {
			this.p_center = p_center;
			this.color = color;
			this.setPreferredSize(new Dimension(100,100));
			this.setBackground(color);
			this.addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
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
				p_center.setBackground(color);
		}
}
