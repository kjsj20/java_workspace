package event;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.Choice;

class MyWin extends Frame{
	Button bt; //MyWin has a bt
	TextField t;
	Choice ch; //html에서의 .select 박스와 동일

	public MyWin(){
		bt = new Button("나 눌러봐");	
		t = new TextField(15);
		ch = new Choice();
		//ch 의 아이템 채우기
		ch.add("Java Programming");
		ch.add("JSP Programming");
		ch.add("Android Programming");
		ch.add("Spring Programming");
		ch.add("Mybatis Programming");
		
		ch.addItemListener(new MyItem());
		this.addWindowListener(new MyWindowListener());

		this.setLayout(new FlowLayout());
		//버튼을 윈도우에 부착!!
		this.add(bt); // 프레임은 디폴트가 BorderLayout이므로, 센터영역에 크게
		//붙을것임
		this.add(t);
		this.add(ch);
		//버튼과 리스너 연결 !!!
		bt.addActionListener(new MyListener());

		//텍스트박스와 리스너 연결
		t.addKeyListener(new MyKey());
		
		this.addMouseListener(new MyMouse());

		this.setSize(300,400);
		this.setVisible(true);
	}

	public static void main(String[] args){
		new MyWin();
	}
}
