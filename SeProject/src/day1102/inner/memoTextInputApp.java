package day1102.inner;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class memoTextInputApp extends JFrame implements ActionListener{
	JMenuBar bar;
	JMenu menu;
	JMenuItem m_open, m_save;
	JTextArea area;
	JScrollPane scroll;
	InputStream is;
	FileReader reader; 
	FileWriter writer;
	BufferedReader buffr; 
	JFileChooser choose;
	File selectFile;
	
	public memoTextInputApp() {
		bar = new JMenuBar();
		menu = new JMenu("파일");
		m_open = new JMenuItem("열기");
		m_save = new JMenuItem("저장");
		area = new JTextArea(); 
		scroll = new JScrollPane(area);
		choose = new JFileChooser("D:/java_workspace/SeProject/res/");
		
		menu.add(m_open);
		menu.add(m_save);
		bar.add(menu);
		setJMenuBar(bar);
		add(scroll);
		
		m_open.addActionListener(this);
		m_save.addActionListener(this);
		
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		new InputTextEvent(area);
	}
	
	public void textOpen() {
		int result = choose.showOpenDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			selectFile = choose.getSelectedFile();
			this.setTitle(selectFile.getAbsolutePath());
			
			try {
				reader = new FileReader(selectFile);
				buffr = new BufferedReader(reader);
				String str = null;
				while(true) {
					str = buffr.readLine();
					if(str == null)break;
					area.append(str + "\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void textSave(){
		try {
			writer = new FileWriter(selectFile.getAbsoluteFile());
			writer.write(area.getText());
			JOptionPane.showMessageDialog(this, "저장완료!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}  
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == m_open) {
			textOpen();
		} else if (obj == m_save) {
			textSave();			
		}
	}
	
	public static void main(String[] args) {
		new memoTextInputApp();
	}	
}
