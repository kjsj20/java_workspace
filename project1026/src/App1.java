
/*
�����쿡 �� �� �ִ� ���� ������Ʈ �˾ƺ��� 
ex) .��ư, �ؽ�Ʈ�ʵ�, �����ڽ�, üũ�ڽ�, ���̽�, �̹���, textarea....
*/
package gui; 
import java.awt.Frame; //����Ϸ��� Ŭ������ ��ġ ���
						// ��򰡿� .class�� �����ϱ� ������ ��밡���� ��...
						//C:\Program Files\Java\jdk1.8.0_261\jre\lib rt.jar �� ���ԵǾ�����
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Image;

public class App1{
	public static void main(String[] args){
		/*������ ����*/

		/* 
			����ó�� ���� Ŭ������ �������� ��ó�� 
			����,��ó�ڼ�0) "����Ϸ��� Ŭ������ ���� � �������� �����Ǵ°����� �� �뵵 �ľ�"
			����,��ó�ڼ�1) "�ڹ��� ��� ��ü�� �ᱹ 3���� �����ۿ� ����"
			����,��ó�ڼ�2) "Ŭ������ ����� ������̴�, ���� �޸𸮿� �ø��� ���� �˸�ȴ�."
			1)�Ϲ�Ŭ���� : new �ϸ�ȴ�!! new ���� ����������(api������ ����)
			2)�߻�Ŭ���� : new �Ұ��ϹǷ�, �ڽ��� �����ؼ� new�ϰų� �̹� ������
						�ν��Ͻ��� �̿��ϸ� �ȴ�.(api ���� ����)
			3)�������̽� : new �Ұ��ϹǷ�, �ڽ��� �����ؼ� new �ϰų� �̹� ������ �ν��Ͻ�
						�� �̿��ϸ� �ȴ�.(api ���� ����)
		*/
		Frame frame = new Frame();
		frame.setVisible(true); //Window ��ü�κ��� ��ӹ��� �޼���
		//�Ű������δ� ������ ��� �� �� �ִ�..
		//�������� �ʺ�, ���̸� ������ �� �ִ� �޼��� ã��
		frame.setSize(300,400); //api ã�ƺ���!!
	
		//�����찡 �����Ǿ����Ƿ�, ������ �ȿ� ��ġ�� ���� ������Ʈ �÷� ���ƺ���~!!
		//��ư Button(�Ϲ�, �߻�, �������� ��, �Ϲ� new ����������)
		Button bt = new Button("����ư");
		
		//��ư�� �����ϱ� ����, ���̾ƿ� ��Ÿ���� �����ؾ� �ϴµ�, ���̾ƿ��� ���� ���Ŵϱ�
		//�ϴ� FlowLayout�� ��G����
		FlowLayout flow = new FlowLayout(0);
		frame.setLayout(flow); //�����쿡 �÷ο� ����� ��ġ����
		//��ư�� ������ �����̳ʿ� ��������~!!
		frame.add(bt); //add�޼����� �Ű������� Component���̹Ƿ�,
		//Button�� Component �� �ڽ��̱� ������ ���� �ڷ����� �ش��Ͽ�, add()�� 
		//�μ��� �� �� �ֵ�!!

		//html ������ input type = "text"�� �ڹٿ����� TextField �� �Ѵ�.
		//Checkbox 
		//TextArea
		TextField tf = new TextField("hello world~", 10);
		TextArea ta = new TextArea("hello", 2, 10);
		Checkbox cb1 = new Checkbox("one", null, true);
		Checkbox cb2 = new Checkbox("two", null, false);
		frame.add(tf);
		frame.add(ta);
		frame.add(cb1);
		frame.add(cb2);

		//�׳� �ؽ�Ʈ
		Label la = new Label("nameInput");
		frame.add(la);

		// �̹��� �ֱ�!!
		//Image�� �߻� Ŭ�����̸�, �÷���(win, mac, linux)�� ������ ������� ���� �� �ִ�..
		//�÷����� �°� ����������, DefaultToolkit Ŭ�����κ��� �ڿ��� ���� �Ѵ�.
		Toolkit kit = Toolkit.getDefaultToolkit(); //static �޼���!! ���� Ŭ���������� 
												   //������ �� �ִ�.
		//��Ŷ�� �̹����� ���û��� ��η� ���� ���� �� �ִ�..
		//��λ��� ������ ��: �������ô� ������ OS ������ ����ϴ� ǥ���̹Ƿ�,
		//���� �� �ڹ��ڵ尡 ��� OS����Ƿ���, ��� �߸����� ��η� �����Ѵ�..
		Image img = kit.getImage("D:/java_workspace/project1026/res/1.png");
		System.out.println("�̹��� �ּҰ��� : " + img);

		//ȭ�鿡 ����ϴ� ������ ���� �Ұ�..��?? ���ݱ��� html ������ �̹�����
		//html ������ ���ٿ��� ����������, �ڹٿ� ���� �Ϲ����� ������ ����� ���α׷���
		//������ ������(���� �׸��� �۾�)�� �ؾ� �ϱ� �����̴�.

	}
}
