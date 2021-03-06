package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{
	XCanvas can;
	
	public MyListener(XCanvas can) {
		this.can = can;
	}
	
	public void actionPerformed(ActionEvent e) {
		//켄버스에 선을 그리되, LineMaker 클래스의 JTextField 의 값을 이용하여..
		//paint() 메서드는 개발자가 직접호출할수도 없고, 호출해서도 안된다!!
		//paint() 메서드는 그림이 그려질 준비가 되었을때 시스템, 즉 .JVM에 의해 호출된다
		//따라서 개발자가 원하는 타임에, 그림을 갱신하게 하려면, paint() 메서드를 직접 호출해서는 아니되고
		//갱신을 할것을 시스템에 요청!! repaint() 다시 그려주세요~~ - >update() 화면지우기
		// -> paint()
		can.repaint();
		
		//xcanvas의 paint(); 불가능
	}
	
}
