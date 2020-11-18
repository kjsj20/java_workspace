package com.swingmall.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.swingmall.admin.board.Board;
import com.swingmall.admin.member.Member;
import com.swingmall.admin.order.Order;
import com.swingmall.admin.product.product;
import com.swingmall.util.db.DBManager;

public class AdminMain extends JFrame {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	public static final int HOME = 0;
	public static final int PRODUCT = 1;
	public static final int MEMBER = 2;
	public static final int ORDER = 3;
	public static final int BOARD = 4;
	
	JPanel user_container;
	JPanel p_content;
	JPanel p_navi; // 웹사이트의 주 메뉴를 포함할 컨테이너 패널
	String[] navi_title = { "Home", "상품관리", "회원정보", "주문관리", "게시판관리" };
	JButton[] navi = new JButton[navi_title.length];

	Page[] page = new Page[5];

	JLabel la_footer; // 윈도우 하단의 카피라이트 영역
	private DBManager dbManager;
	private Connection con;

	public AdminMain() {
		dbManager = new DBManager();
		user_container = new JPanel();
		p_navi = new JPanel();
		la_footer = new JLabel("SwingMall All rights reserved", SwingConstants.CENTER);
		p_content = new JPanel();
		
		user_container.setPreferredSize(new Dimension(WIDTH, HEIGHT - 50));
		la_footer.setPreferredSize(new Dimension(WIDTH, 50));
		la_footer.setBackground(Color.WHITE);
		la_footer.setFont(new Font("Arial Black", Font.BOLD, 19));

		con = dbManager.connect();

		if (con == null) {
			JOptionPane.showMessageDialog(this, "데이터베이스에 접속할 수 없습니다");
			System.exit(0);
		} else {
			this.setTitle("SwingShop 관리로 이용중입니다.");
		}

		// 메인네비게이션 생성
		for (int i = 0; i < navi_title.length; i++) {
			navi[i] = new JButton(navi_title[i]);
			navi[i].setBackground(Color.BLACK);
			navi[i].setForeground(Color.WHITE);
			p_navi.add(navi[i]);
		}

		// 페이지 구성
		page[0] = new Home(this);
		page[1] = new product(this);
		page[2] = new Member(this);
		page[3] = new Order(this);
		page[4] = new Board(this);

		// 조립
		user_container.setLayout(new BorderLayout());
		for(int i =0; i<page.length; i++){
			p_content.add(page[i]);
		}
		
		showPage(HOME);
		
		user_container.add(p_navi, BorderLayout.NORTH);
		user_container.add(p_content);

		this.add(user_container);
		this.add(la_footer, BorderLayout.SOUTH);
		
		
		setSize(1200, 900);
		setVisible(true);
		setLocationRelativeTo(null);

		// 프레임과 리스너연결
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dbManager.disconnect(con);
				System.exit(0);
			}
		});

		// 네비게이션 버튼과 리스너 연결
		for (int i = 0; i < navi.length; i++) {
			navi[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					if (obj == navi[0]) {
						showPage(0);
					} else if (obj == navi[1]) {
						showPage(1);
					} else if (obj == navi[2]) {
						showPage(2);
					} else if (obj == navi[3]) {
						showPage(3);
					} else if (obj == navi[4]) {
						showPage(4);
					}
				}
			});
		}
	}

	// 보여질 페이지와 안보여질 페이지를 설정하는 메서드
	public void showPage(int showIndex) { // 매개변수로는 보여주고 싶은 페이지 넘버
		for (int i = 0; i < page.length; i++) {
			if (i == showIndex) {
				page[i].setVisible(true);
			} else {
				page[i].setVisible(false);
			}
		}
	}
	public DBManager getDbManager() {
		return dbManager;
	}
	
	public Connection getCon(){
		return con;
	}
	
	public static void main(String[] args) {
		new AdminMain();
	}
}
