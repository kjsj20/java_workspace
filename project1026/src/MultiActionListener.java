package event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MultiActionListener implements ActionListener{
	//�� �޼����, ������ ��� ��ư�� ������ �Ѿ�´�.
	public void actionPerformed(ActionEvent e){
		System.out.println(e.getSource());
		if(e.getSource() == bt1){
			System.out.println("1����ư�����̳�??!");
		}
	}
}



