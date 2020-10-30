package day1030.chat;

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

public class ChatB extends JFrame implements KeyListener, ActionListener {
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	JButton bt_open;
	String title;
	private ChatA chatA;
	private ChatC chatC;
	
	public ChatB() {
		super("나");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt = new JButton("send");
		bt_open = new JButton("open");
		// 패널 조립
		p_south.add(t_input);
		p_south.add(bt);
		p_south.add(bt_open);

		add(scroll);
		add(p_south, BorderLayout.SOUTH);

		// 스타일 적용
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);

		t_input.setPreferredSize(new Dimension(250, 30));

		// 보여주기 전에 미리 연결해 놓자 (컴포넌트와 리스너 연결)
		t_input.addKeyListener(this); // 프레임이 곧 리스너다!!

		// send 버튼에 리스너 연결
		bt.addActionListener(this);

		// open버튼에 리스너 연결
		bt_open.addActionListener(this);

		//setSize(350, 400);
		setBounds(550, 150, 350, 400);
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

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JButton btn = (JButton) obj; // 하위 자료형으로 변환 down casting

		if (btn == bt) { // 누른버튼과 send버튼의 주소값이 같다면..
			System.out.println("send버튼 눌렀어 ?");
			System.out.println(title);
			send();
		} else if (btn.equals(bt_open)) { // 누른버튼과 열기 버튼이 같은 버튼이라면..
			System.out.println("open버튼 눌렀어 ?");
			open();
		}
	}

	public void send() {
		//나에대한 채팅처리..
		String msg = t_input.getText();
		// 텍스트필드 값을 구하자!!
		area.append( super.getTitle() + ": " + msg + "\n");
		t_input.setText("");
		
		//너에 대한 채팅처리..
		chatA.area.append("친구2: "+msg+"\n");
		chatC.area.append("친구2: "+msg+"\n");
	}
	
	public void open() {

	}
	
	public void setChatA(ChatA chatA) {
		this.chatA = chatA;
	}
	
	public void setChatC(ChatC chatC) {
		this.chatC = chatC;
	}
	
	public static void main(String[] args) {
		new ChatB();
	}

}
