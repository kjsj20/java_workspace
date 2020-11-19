package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

public class test {

	private JFrame frmTestpage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frmTestpage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTestpage = new JFrame();
		frmTestpage.setTitle("testpage");
		frmTestpage.setBounds(100, 100, 1011, 661);
		frmTestpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTestpage.getContentPane().setLayout(null);
	}
}
