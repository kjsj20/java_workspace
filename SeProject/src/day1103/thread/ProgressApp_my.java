/*
 * 진행상황을 직관적으로 알 수 있는 프로그래스바를 활용해보자
 * */

package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ProgressApp_my extends JFrame{
	JLabel la;
	JProgressBar bar, bar2;
	Thread barThread,barThread2; //바를 증가시킬 쓰레드
	int n = 0;
	int n2 = 0;
	
	public ProgressApp_my() {
		la = new JLabel("0", SwingConstants.CENTER);	
		bar = new JProgressBar();
		bar2 = new JProgressBar();
		barThread = new Thread() {
			public void run() {
				while(true) {
					n++;
					setBarValue(bar, n);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		barThread2 = new Thread() {
			public void run() {
				while(true) {
					n2++;
					setBarValue(bar2, n2);
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		//스타일
		la.setPreferredSize(new Dimension(500, 150));
		la.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 90));
		bar.setPreferredSize(new Dimension(500,70));
		bar2.setPreferredSize(new Dimension(500,70));
		setLayout(new FlowLayout());
		add(la);
		add(bar);
		add(bar2);
		
		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		barThread.start();
		barThread2.start();
	}
	
	//바의 값 제어
	public void setBarValue(JProgressBar bar, int n) {
		bar.setValue(n);
		la.setText(Integer.toString(this.n));
	}
	
//	public void setBarValue2() {
//		bar2.setValue(n2);
//		la.setText(Integer.toString(n));
//	}

	public static void main(String[] args) {
		new ProgressApp_my();
	}
}
