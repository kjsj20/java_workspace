package day1103.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircleMove extends JFrame{
	JPanel can;
	JButton bt;
	Thread thread;
	int x,y;
	
	public CircleMove() {
		can = new JPanel() { //내부익명클래스도 클래스이므로, .class로 존재하되 명칭이 없으므로
									//내부적으로 $순번 형태의 파일명을 갖게된다!!!!
			//내부익명클래스 사용시 장점? .java를 굳이 만들지 않아도 된다.. 개발 시간 단축
			//외부 클래스의 멤버를 마치 자기꺼처럼 사용할 수 있다..

			public void paint(Graphics g) {
				g.setColor(Color.YELLOW);//페인트 색상 적용
				g.fillRect(0, 0, 740, 640);//채워진 사각형..
				
				//원 그리기 
				g.setColor(Color.RED); //페인트 색상 적용
//				g.drawOval(100, 100, 50, 50);
				g.fillOval(x, y, 50, 50);
			}
		};
		
		bt = new JButton("움직이기");
		
		thread = new Thread() {
			
			public void run() {
				while(true) {
					move();
					can.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		//스타일 적용
		can.setPreferredSize(new Dimension(700,600));
		
		setLayout(new FlowLayout());
		add(bt);
		add(can);
		
		//버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//move();
				//can.repaint();
				thread.start();
			}
		});
		setSize(740, 640);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//원의 좌표를 변겅한다!!
	public void move() {
		x+=2;
		y+=2;
	}
	
	public static void main(String[] args) {
		new CircleMove();
	}

}
