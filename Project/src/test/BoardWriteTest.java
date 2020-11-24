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

public class BoardWriteTest {

	private JFrame frame;
	private JTextField title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardWriteTest window = new BoardWriteTest();
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
	public BoardWriteTest() {
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
		
		title = new JTextField();
		title.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		title.setForeground(Color.GRAY);
		title.setText("제목을 입력해주세요..");
		title.setBounds(112, 57, 686, 21);
		writePanel.add(title);
		title.setColumns(10);
		
		title.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(title.getText().equals("제목을 입력해주세요..")) {
					title.setText("");
					title.setForeground(Color.WHITE);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(title.getText().isEmpty()) {
					title.setText("제목을 입력해주세요..");
					title.setForeground(Color.GRAY);
				}
			}
		});
		
		JTextArea content = new JTextArea();
		content.setBorder(new LineBorder(new Color(0, 0, 0)));
		content.setText("내용을 입력해주세요..");
		content.setForeground(Color.GRAY);
		content.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		content.setBounds(112, 88, 686, 511);
		writePanel.add(content);
		
		content.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(content.getText().equals("내용을 입력해주세요..")) {
					content.setText("");
					content.setForeground(Color.WHITE);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(content.getText().isEmpty()) {
					content.setText("내용을 입력해주세요..");
					content.setForeground(Color.GRAY);
				}
			}
		});
		
		JButton registBtn = new JButton("등록하기");
		registBtn.setForeground(Color.WHITE);
		registBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		registBtn.setBounds(591, 627, 86, 23);
		writePanel.add(registBtn);
		
		JButton prevBtn = new JButton("이전으로");
		prevBtn.setForeground(Color.WHITE);
		prevBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		prevBtn.setBounds(351, 627, 86, 23);
		writePanel.add(prevBtn);
		
		JLabel board_title = new JLabel("게시글 작성");
		board_title.setForeground(Color.WHITE);
		board_title.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		board_title.setBounds(406, 22, 241, 15);
		writePanel.add(board_title);
		
		JButton updBtn = new JButton("수정하기");
		updBtn.setForeground(Color.WHITE);
		updBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		updBtn.setBounds(471, 627, 86, 23);
		writePanel.add(updBtn);
		
		JButton delBtn = new JButton("삭제하기");
		delBtn.setForeground(Color.WHITE);
		delBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		delBtn.setFocusable(false);
		delBtn.setBounds(234, 627, 86, 23);
		writePanel.add(delBtn);
		
	}
}
