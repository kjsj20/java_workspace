package day1030.copy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UIFileCopy extends JFrame implements ActionListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JButton bt;
	
	public UIFileCopy() {
		//객체 생성
		la_ori = new JLabel("원본경로");
		t_ori = new JTextField(50);
		la_dest = new JLabel("복사경로");
		t_dest = new JTextField(50);
		bt = new JButton("복사실행");
		
		//스타일 적용 
		la_ori.setPreferredSize(new Dimension(150,30));
		la_dest.setPreferredSize(new Dimension(150,30));
		t_ori.setPreferredSize(new Dimension(500,30));
		t_dest.setPreferredSize(new Dimension(500,30));
		
		//조립
		setLayout(new FlowLayout());
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		bt.addActionListener(this);//버튼 리스너 연결
		
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		
		setSize(740, 180);
		setVisible(true);
		//setDefaultCloseOperation(); //하면 안돼요!! 왜 스트림을 닫을 기회가 없기 때문에..
		//해결책 ) 윈도우창을 닫을때, 이벤트를 구현해야 한다. 즉 WindowListener 구현...
	}
	
	public void copy() {
		//메서드 내의 지역변수는 반드시 개발자가 초기화 해야한다.. 멤버변수가 아니므로..
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String ori = t_ori.getText();
		String dest = t_dest.getText();
		
		try {
			fis = new FileInputStream(ori);
			fos = new FileOutputStream(dest);
			
			int data;
			while(true) {
				data = fis.read();
				if(data==-1)break;
				fos.write(data);				
			}
			JOptionPane.showMessageDialog(this, "복사완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		copy();
	}
	
	public static void main(String[] args) {
		new UIFileCopy();
	}
}
