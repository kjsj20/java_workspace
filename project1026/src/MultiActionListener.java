package event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MultiActionListener implements ActionListener{
	//이 메서드로, 눌려진 모든 버튼의 정보가 넘어온다.
	public void actionPerformed(ActionEvent e){
		System.out.println(e.getSource());
		if(e.getSource() == bt1){
			System.out.println("1번버튼누르셨네??!");
		}
	}
}



