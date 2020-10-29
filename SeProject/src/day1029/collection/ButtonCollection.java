package day1029.collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonCollection extends JFrame implements ActionListener {
	JButton newBt, bgBt;
	JPanel p_north, p_center;
	ArrayList<JButton> btList = new ArrayList<JButton>();
	int num = 1;

	public ButtonCollection() {
		newBt = new JButton("생성");
		bgBt = new JButton("배경색");

		p_north = new JPanel();
		p_center = new JPanel();

		add(p_north, BorderLayout.NORTH);
		add(p_center);
		p_north.add(newBt);
		newBt.addActionListener(this);
		p_north.add(bgBt);
		bgBt.addActionListener(this);
		p_center.setBackground(Color.yellow);

		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ButtonCollection();
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		btClick(obj);
	}

	public void btClick(Object obj) {
		if (newBt == obj) {
			JButton bt = new CustomButton();
			btList.add(bt);
			bt.setText("버튼" + btList.size());
			p_center.add(bt);
			p_center.updateUI();
			num = num + 1;
		} else if (bgBt == obj) {
			for (int i = 0; i < btList.size(); i++) {
				JButton bt = btList.get(i);
				bt.setBackground(Color.CYAN);
			}
		} 
	}
}
