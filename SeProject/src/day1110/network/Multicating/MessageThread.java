package day1110.network.Multicating;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//이 쓰레드는, 대화용 쓰레드 이므로 입출력 스트림을 각각의 인스턴스가 보유해야 한다!!
//아래의 클래스를 쓰레드로 선언하는 순간부터, 이 인스턴스들은 서로 비동기적(Asyncronous)으로 동작할 수 있다!!
public class MessageThread extends Thread{
	Socket socket; //각각의 쓰레드는 대화용 소켓을 보유해야 스트림을 뽑을 수 있으므로, 접속자가 감지되면
	//해당 소켓을 인수로 넘겨받자
	BufferedReader buffr;//듣기
	BufferedWriter buffw;//말하기
	MultiServer MultiServer;
	boolean flag = true;
	public MessageThread(MultiServer MultiServer, Socket socket) {
		this.MultiServer=MultiServer;
		this.socket=socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void run() {
		listen();
	}
	
	//메세지 받기(청취)
	public void listen() {
		String msg=null;
		try {
			while(flag) {
				msg=buffr.readLine(); //현재로서는 한번만 듣는다..
				
				if(msg.equals("exit")) {
					//백터명단에서 제거, flag도 false : 쓰레드 Dead 
					MultiServer.clientList.remove(this);
					flag = false;
					MultiServer.area.append("현재까지 접속자 수" + MultiServer.clientList.size() + "\n");
				}else {
					MultiServer.area.append(msg+"\n");
					send(msg);//클라이언트에게 다시 보내야 한다(서버의 의무)
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//클라이언트에게 메시지 보내기 
	public void send(String msg) {
		try {
			//백터에 들어있는 메시지 쓰레드 만큼 반복문 수행하면서 .write, flush 수행(멀티 캐스팅)
			for(int i = 0; i < MultiServer.clientList.size(); i++) {
				MessageThread messageThread = MultiServer.clientList.get(i); //백터에 들어있는 메시지 쓰레드를 하나씩 꺼낸다.
				messageThread.buffw.write(msg+"\n");
				messageThread.buffw.flush();				
			}
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}







