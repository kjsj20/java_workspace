package day1028.notepad_my;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class notepad_my extends JFrame implements ActionListener{
	JMenuBar Mb;
	JMenu m_file;
	JMenuItem[] List;
	JTextArea area;
	JScrollPane scroll;
	String[] title_List = {"FileOpen", "FileSave"};
	
	FileInputStream fis;
	FileOutputStream fos; 
	String fisName, fosName;
	
	public notepad_my() {
		
		
		Mb = new JMenuBar();
		m_file = new JMenu("FileOpen");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		Mb.add(m_file);
		
		List = new JMenuItem[title_List.length];
		for(int i=0; i<title_List.length; i++) {
			List[i] = new JMenuItem(title_List[i]);
			m_file.add(List[i]);
		}
		
		List[0].addActionListener(this);
		List[1].addActionListener(this);
		
		setJMenuBar(Mb);
		
		add(scroll);
		
		m_file.setPreferredSize(new Dimension(100, 25));
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	} 
	
	public void fileOpen() {
		JFileChooser choose = new JFileChooser();
		choose.showOpenDialog(this);
		String text = "";
//		System.out.println(choose.getSelectedFile());
		try {
			fis = new FileInputStream(choose.getSelectedFile());
			//fos = new FileOutputStream(fosName);
			
			int data; 
			
			while(true) {
				data = fis.read();
				if(data==-1)break;
				text = text + (char)data;
			}
			area.setText(text);
			System.out.println("파일 읽기 성공!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void fileSave() {

		try {
			String text = area.getText();
//			FileInputStream fis = new FileInputStream(area.getText());
			FileOutputStream fos = new FileOutputStream("D:/memo/memo2.txt");
			
			byte[] text_B = text.getBytes();
			
			fos.write(text_B);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == List[0]) {
			fileOpen();
		} else if(obj == List[1]) {
			fileSave();
		}
	}
	
	public static void main(String[] args) {
		new notepad_my();
	}
}
