package gui;
import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;

class MultiButton extends Frame{
	Button bt1,bt2;
	TextField t;
	public MultiButton(){
		bt1 = new Button("버튼1");
		bt2 = new Button("버튼2");
		t = new TextField(20);
		this.add(bt1);
		this.add(bt2);
		this.add(t);
		//버튼과 리스너 연결
		MyActionListener listener = new MyActionListener(bt1,bt2,t);
		bt1.addActionListener(listener);
		bt2.addActionListener(listener);
		t.addActionListener(listener);//텍스트박스에도 클릭이벤트 적용 가능..
		this.setLayout(new FlowLayout());
		this.setSize(300,400);
		this.setVisible(true);
	}
	public static void main(String[] args){
		new MultiButton();
	}
}
