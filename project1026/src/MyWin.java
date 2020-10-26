package event;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.Choice;

class MyWin extends Frame{
	Button bt; //MyWin has a bt
	TextField t;
	Choice ch; //html������ .select �ڽ��� ����

	public MyWin(){
		bt = new Button("�� ������");	
		t = new TextField(15);
		ch = new Choice();
		//ch �� ������ ä���
		ch.add("Java Programming");
		ch.add("JSP Programming");
		ch.add("Android Programming");
		ch.add("Spring Programming");
		ch.add("Mybatis Programming");
		
		ch.addItemListener(new MyItem());
		this.addWindowListener(new MyWindowListener());

		this.setLayout(new FlowLayout());
		//��ư�� �����쿡 ����!!
		this.add(bt); // �������� ����Ʈ�� BorderLayout�̹Ƿ�, ���Ϳ����� ũ��
		//��������
		this.add(t);
		this.add(ch);
		//��ư�� ������ ���� !!!
		bt.addActionListener(new MyListener());

		//�ؽ�Ʈ�ڽ��� ������ ����
		t.addKeyListener(new MyKey());
		
		this.addMouseListener(new MyMouse());

		this.setSize(300,400);
		this.setVisible(true);
	}

	public static void main(String[] args){
		new MyWin();
	}
}
