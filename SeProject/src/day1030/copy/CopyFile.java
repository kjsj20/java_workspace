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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CopyFile extends JFrame implements ActionListener{
	JLabel l_ori, l_copy; 
	JTextField t_ori, t_copy;
	JButton bt; 
	FileInputStream fis;
	FileOutputStream fos;
	
	public CopyFile() {
		l_ori = new JLabel("원본");
		l_copy = new JLabel("복사본");
		t_ori = new JTextField();
		t_copy = new JTextField();
		bt = new JButton("Copy!");
		
		l_ori.setPreferredSize(new Dimension(80,30));
		t_ori.setPreferredSize(new Dimension(120,30));
		l_copy.setPreferredSize(new Dimension(80,30));
		t_copy.setPreferredSize(new Dimension(120,30));
		
		add(l_ori);
		add(t_ori);
		add(l_copy);
		add(t_copy);
		add(bt);
		
		bt.addActionListener(this);
		
		setSize(300,300);
		setVisible(true);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void copyBtnClick() {
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_copy.getText());
			
			int data; 
			while(true) {
				data = fis.read();
				if(data==-1)break;
				fos.write(data);
			}			
			System.out.println("복사가 완료 되었습니다~");
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
		copyBtnClick();
	}
	
	public static void main(String[] args) {
		new CopyFile();
	}
}
