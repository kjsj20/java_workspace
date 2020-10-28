package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum extends JFrame implements ActionListener{
	AlbumPanel p; //paint메서드를 재정의 하려면, 클래스로 정의해야 한다.
	JPanel p_south; //버튼 2개를 얹힐 패널
	JButton bt_prev, bt_next; //이전, 다음 버튼
	
	public PhotoAlbum() {
		//객체 생성
		p = new AlbumPanel();
		p.setBackground(Color.YELLOW);
		p_south = new JPanel();
		bt_prev = new JButton("이전 사진");
		bt_next = new JButton("다음 사진"); 
		
		//조립
		add(p);//중앙에 앨범 패널 넣기
		p_south.add(bt_prev);
		p_south.add(bt_next);
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		add(p_south, BorderLayout.SOUTH);
		
		
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new PhotoAlbum();
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JButton btn = (JButton)obj;
		if(btn == bt_next) {
			p.num++;
		} else if (btn == bt_prev) {
			p.num--;
		}
		p.repaint();
	}
}
