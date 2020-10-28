package day1028.graphic.line;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LIneMaker2 extends JFrame {
	JLabel la_x1, la_y1, la_x2, la_y2;
	JTextField t_x1, t_y1, t_x2, t_y2;
	MyButton bt; //쓸데없는 짓이지만, 공부목적으로 커스텀 버튼 넣어봤다!!
	JPanel p_north;
	XCanvas can;
	
	public LIneMaker2() {
		super("선 그리기 어플리케이션");
		//객체생성
		la_x1 = new JLabel("x1");
		la_y1 = new JLabel("y1");
		la_x2 = new JLabel("x2");
		la_y2 = new JLabel("y2");
		t_x1 = new JTextField("0",10);
		t_y1 = new JTextField("0", 10);
		t_x2 = new JTextField("0", 10);
		t_y2 = new JTextField("0", 10);
		bt = new MyButton("커스텀 버튼");
		p_north = new JPanel();
		can = new XCanvas();
		can.setLineMaker(this);
		bt.addActionListener(new MyListener(can));
		
		//스타일 적용
		can.setBackground(Color.YELLOW);
		
		//조립
		p_north.add(la_x1);
		p_north.add(t_x1);
		p_north.add(la_y1);
		p_north.add(t_y1);
		p_north.add(la_x2);
		p_north.add(t_x2);
		p_north.add(la_y2);
		p_north.add(t_y2);
		p_north.add(bt);
		
		//부분 조립이 완성되었으므로, 최종적으로 프레임에 붙이자!
		add(p_north, BorderLayout.NORTH);
		add(can);
		
		//윈도우와 관련된 속성 지정
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new LIneMaker2();
	}
}
