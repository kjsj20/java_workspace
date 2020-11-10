package day1110.network.unicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UniServer extends JFrame{
	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port = 7777;
	Thread thread; //메인쓰레드 대신, 접속자를 감지하게될 쓰레드!! (accept() 메서드 때문에..)
	BufferedReader buffr;
	BufferedWriter buffw;
	public UniServer() {
		t_port = new JTextField(Integer.toString(port),10);
		bt = new JButton("서버가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//조립
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//가동버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					public void run() {
						startServer();
					}
				};
				thread.start(); //Runnable 로 진입시키자!!
			}
		});
		
		setVisible(true);
		setBounds(600, 200, 300, 400);
	}
	
	//서버가동
	public void startServer() {
		
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText())); //서버소켓 생성
			area.append("서버 준비\n");
			while(true) {
				Socket socket = server.accept();
				area.append("접속자 발견\n");
				
				//서버는 여러명의 접속자를 감지해야 한다면, 각 접속자 마다 비동기적으로 즉 독립적으로 서로 상관없이
				//대화를 주고 받는 주체는 쓰레드의 인스턴스로 처리하자!!
				MessageThread messageThread = new MessageThread(this, socket);
				messageThread.start();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//메세지 받기(청취)
	public void listen() {
		String msg = null;
		try {
			while(true) {
				msg=buffr.readLine(); //현재로서는 한번만 듣는다..		
				area.append(msg + "\n");
				send(msg);//클라이언트에게 다시 보내야 한다(서버의 의무)
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//클라이언트에게 메시지 보내기
	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UniServer();
	}
}
