package event;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

/*������â�� ������� �߻��� �� �ִ� �̺�Ʈ�� �����ϴ� ������ �����ϱ�!!*/
public class MyWindowListener implements WindowListener{

	public void	windowActivated(WindowEvent e){
		System.out.println("windowActivated ȣ��");
	}
	public void	windowClosed(WindowEvent e){
		System.out.println("windowClosed ȣ��");
	}
	public void	windowClosing(WindowEvent e){
		System.out.println("windowClosing ȣ��");
	}
	public void	windowDeactivated(WindowEvent e){
		System.out.println("windowDeactivated ȣ��");
	}
	public void	windowDeiconified(WindowEvent e){
		System.out.println("windowDeiconified ȣ��");
	}
	public void	windowIconified(WindowEvent e){
		System.out.println("windowIconified ȣ��");
	}
	public void	windowOpened(WindowEvent e){
		System.out.println("windowOpened ȣ��");
	}
}
