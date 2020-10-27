package gui;
import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;

class MultiButton extends Frame{
	Button bt1,bt2;
	TextField t;
	public MultiButton(){
		bt1 = new Button("��ư1");
		bt2 = new Button("��ư2");
		t = new TextField(20);
		this.add(bt1);
		this.add(bt2);
		this.add(t);
		//��ư�� ������ ����
		MyActionListener listener = new MyActionListener(bt1,bt2,t);
		bt1.addActionListener(listener);
		bt2.addActionListener(listener);
		t.addActionListener(listener);//�ؽ�Ʈ�ڽ����� Ŭ���̺�Ʈ ���� ����..
		this.setLayout(new FlowLayout());
		this.setSize(300,400);
		this.setVisible(true);
	}
	public static void main(String[] args){
		new MultiButton();
	}
}
