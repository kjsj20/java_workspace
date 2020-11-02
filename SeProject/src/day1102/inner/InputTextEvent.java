package day1102.inner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputTextEvent extends JFrame {
	JTextField text; 
	JTextArea area;
	String stackArea;
	
	public InputTextEvent(JTextArea area) {
		text = new JTextField(20);
		this.area = area;
		
		add(text);
			
		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				
				if(key == 10) {
					stackArea = area.getText() + text.getText() + "\n";
					area.setText(stackArea); 
					text.setText("");
				}
			}
		});
		
		setBounds(420, 0, 200, 100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
