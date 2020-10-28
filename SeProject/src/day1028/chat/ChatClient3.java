package day1028.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient3 extends JFrame implements KeyListener, ActionListener {
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	ChatClient c;
	ChatClient2 c2;

	public ChatClient3(ChatClient c, ChatClient2 c2) {
		super("친구3");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt = new JButton("send");
		this.c = c;
		this.c2 = c2;

		// 패널 조립
		p_south.add(t_input);
		p_south.add(bt);

		add(scroll);
		add(p_south, BorderLayout.SOUTH);

		// 스타일 적용
		area.setBackground(Color.CYAN);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);

		t_input.setPreferredSize(new Dimension(285, 30));

		// 보여주기 전에 미리 연결해 놓자 (컴포넌트와 리스너 연결)
		t_input.addKeyListener(this); // 프레임이 곧 리스너다!!
		bt.addActionListener(this);
		setBounds(850, 150, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 어노테이션이다.. java 5 부터 지원되는 일종의 주석.. 일반적인 주석은 프로그램에 관여하지
	// 않지만, 어노테이션은 프로그래밍에서 어떤 표시를 하기 위함이므로, 자주 사용됨..
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 엔터키를 누르면, area에 입력 데이터를 반영하자~!(누적 시키자)
		int key = e.getKeyCode(); // 키코드 값 반환

		if (key == 10) {
			send();
		}
	}
	
	public void send() {
		String msg = t_input.getText();
		// 텍스트필드 값을 구하자!!
		area.append("나" + ": " + msg + "\n");
		t_input.setText("");

		// 친구 필드
		c.area.append(super.getTitle() + ": " + msg + "\n");
		c2.area.append(super.getTitle() + ": " + msg + "\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		send();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public static void main(String[] args) {
		// new ChatClient2();
	}

}

