package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JSeparator;

public class BoardDetailTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardDetailTest window = new BoardDetailTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BoardDetailTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel boardPanel = new JPanel();
		frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel writePanel = new JPanel();
		writePanel.setBackground(Color.DARK_GRAY);
		writePanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		writePanel.setPreferredSize(new Dimension(900, 690));
		boardPanel.add(writePanel, BorderLayout.CENTER);
		writePanel.setLayout(null);
		
		JButton prevBtn = new JButton("이전으로");
		prevBtn.setForeground(Color.WHITE);
		prevBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		prevBtn.setBounds(394, 481, 86, 23);
		writePanel.add(prevBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(104, 78, 686, 15);
		writePanel.add(separator);
		
		JLabel detailTitle = new JLabel("New label");
		detailTitle.setForeground(Color.WHITE);
		detailTitle.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		detailTitle.setBounds(104, 52, 637, 15);
		writePanel.add(detailTitle);
		
		JTextArea detailContent = new JTextArea();
		detailContent.setDisabledTextColor(Color.WHITE);
		detailContent.setEnabled(false);
		detailContent.setForeground(Color.WHITE);
		detailContent.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		detailContent.setBorder(new LineBorder(new Color(0, 0, 0)));
		detailContent.setBounds(104, 93, 686, 354);
		writePanel.add(detailContent);
		
		JButton detailDelBtn = new JButton("삭제하기");
		detailDelBtn.setFocusable(false);
		detailDelBtn.setForeground(Color.WHITE);
		detailDelBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		detailDelBtn.setBounds(266, 481, 86, 23);
		writePanel.add(detailDelBtn);
		
		JButton prevBtn_2 = new JButton("수정하기");
		prevBtn_2.setForeground(Color.WHITE);
		prevBtn_2.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		prevBtn_2.setBounds(523, 481, 86, 23);
		writePanel.add(prevBtn_2);
		
	}
}
