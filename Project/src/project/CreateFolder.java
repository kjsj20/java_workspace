package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CreateFolder {
	ArrayList<JPanel> panels = new ArrayList<JPanel>();
	 ArrayList<JLabel> labels = new ArrayList<JLabel>();
	  
	 public CreateFolder(JPanel panel, String folderName, int fontSize) {
		 Font font = new Font("HY견고딕", Font.BOLD, fontSize);
			JLabel label = new JLabel(folderName, 10);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(font);
			labels.add(label);
			JPanel panel1 = new JPanel();
			panel1.add(labels.get(labels.size()-1));
			panel1.setBackground(new Color(64, 64, 64));
			panel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			panel1.setPreferredSize(new Dimension(240, 40));
			panel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			MouseAdapter chat_m_adapt = new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					System.out.println("들");
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					System.out.println("나");
				}
			};
			
			if(panels.size()<=0) {
				panel1.setBounds(0, 0, 240, 40);
			}else {
				panel1.setBounds(panels.get(panels.size()-1).getX(), panels.get(panels.size()-1).getY()+40,240,40);
			}
			
			panels.add(panel1);
			for(int i =0; i < panels.size(); i++) {
				panels.get(i).addMouseListener(chat_m_adapt);
			}
			panel.add(panels.get(panels.size()-1));
	}
}
