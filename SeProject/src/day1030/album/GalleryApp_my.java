package day1030.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GalleryApp_my extends JFrame implements ActionListener{
	JPanel p_west;
	JPanel p_center;
	JPanel p_south;
	JButton bt_prev, bt_next;
	JScrollPane scroll;	
	JLabel la_name; //제목 나올 라벨
	XCanvas_my can;
	
	ArrayList<Thumb_my> list = new ArrayList<Thumb_my>();
	
	String dir = "D:/java_workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg",
			"ab.jpg",
			"ax.jpg",
			"bm.jpg",
			"et.jpg",
			"kg.jpg",
			"mx.jpg",
			"pk.jpg",
			"ub.jpg",
			"ya.jpg"
	};
	
	int n=0; //배열의 index
	
	public GalleryApp_my(){
		//객체 생성
		super("자바 갤러리");
		p_west = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		scroll = new JScrollPane(p_west);
		la_name = new JLabel(src[n]+"(" + (n + 1) + "/" + src.length  +")", SwingConstants.CENTER);
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		can = new XCanvas_my(dir+src[n]);
		
		//썸네일 객체 생성
		for(int i=0; i<10; i++) {
			Thumb_my thumb = null;
			list.add(thumb = new Thumb_my(dir+src[i], can, this, i));
			p_west.add(thumb);
		}
		
		//스타일
		la_name.setPreferredSize(new Dimension(700,50));
		la_name.setFont(new Font("Verdana", Font.BOLD, 25));
		p_west.setPreferredSize(new Dimension(100,600));
		p_center.setPreferredSize(new Dimension(700,600));
		p_west.setBackground(Color.yellow);
		p_center.setBackground(Color.green);
		
		//객체 조립
		p_south.add(bt_prev);
		p_south.add(bt_next);
		p_center.add(la_name);
		p_center.add(can);
		p_center.add(p_south);
		add(scroll, BorderLayout.WEST);
		add(p_center);
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize(800,600);
		setVisible(true);
		//윈도우를 화면 중앙에 띄우는 법
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_prev) {
			prev();
		} else if (obj == bt_next) {
			next();
		}
		updateData();
	}
	
	public void updateData() {
		can.setSrc(dir + src[n]);
		la_name.setText(src[n]+"(" + (n + 1) + "/" + src.length  +")");
		can.repaint();
	}
	
	public void prev() {
		if(n > 0) {
			n--;			
		} else {
			JOptionPane.showMessageDialog(this, "첫 이미지입니다.");
		}
	}
	
	public void next() {
		//배열을 넘어서지 않는 범위내에서 ++ 허용
		if(n < src.length - 1) {
			n++;			
		} else {
			JOptionPane.showMessageDialog(this, "마지막 이미지입니다.");
		}
		//넘어서면 욕!!
	}
	
	public static void main(String[] args) {
		new GalleryApp_my();
	}
}
