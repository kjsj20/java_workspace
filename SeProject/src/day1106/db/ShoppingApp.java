/*
 * 쇼핑몰 상품관리 프로그램을 제작하기
 * */
package day1106.db;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ShoppingApp extends JFrame{
	JPanel p_west; //전체 중 서쪽
	JPanel p_center; //전체 중 가운데
	JPanel c_north; // 가운데 중 북쪽 
	JPanel c_center; //가운데 중 센터
	JPanel p_east;//전체 중 동쪽
	
	//등록폼 관련 
	Choice ch_top;
	Choice ch_sub;
	JTextField t_name;
	JTextField t_brand;
	JTextField t_price;
	JButton bt_find; //이미지 찾아보기 
	JPanel can;//이미지 미리보기 그려질 곳
	JButton bt_regist; 
	
	//센터영역 - 검색관련
	Choice ch_category;//검색 카테고리
	JTextField t_keyword; //검색어
	JButton bt_search; //검색버튼 
	JTable table;
	JScrollPane scroll;
	
	//동쪽영역 
	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_name2;
	JTextField t_brand2;
	JTextField t_price2;
	JButton bt_find2; //이미지 찾아보기 
	JPanel can2;//이미지 미리보기 그려질 곳
	JButton bt_edit; //수정버튼 
	JButton bt_del; //삭제버튼
	
	public ShoppingApp() {
		//서쪽영역 생성 
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		bt_find = new JButton("이미지찾기");
		can = new JPanel();
		bt_regist = new JButton("등록");
		
		//서쪽 조립 
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(bt_find);
		p_west.add(can);
		p_west.add(bt_regist);
		
		//스타일적용 
		ch_top.setPreferredSize(new Dimension(135, 30));
		ch_sub.setPreferredSize(new Dimension(135, 30));
		t_name.setPreferredSize(new Dimension(135, 30));
		t_brand.setPreferredSize(new Dimension(135, 30));
		t_price.setPreferredSize(new Dimension(135, 30));
		bt_find.setPreferredSize(new Dimension(135, 30));
		can.setPreferredSize(new Dimension(135, 115));
		
		p_west.setPreferredSize(new Dimension(150, 600));
//		p_west.setBackground(Color.YELLOW);
		
		//프레임에 서쪽 영역 붙이기 
		add(p_west, BorderLayout.WEST);
		
		
		//가운데 영역 생성 
		c_north = new JPanel();
		c_center = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("검색");
		table = new JTable();
		scroll = new JScrollPane(table);
		
		//스타일 적용
		c_north.setBackground(Color.PINK);
		ch_category.setPreferredSize(new Dimension(130, 30));
		t_keyword.setPreferredSize(new Dimension(500, 30));
		bt_search.setPreferredSize(new Dimension(120, 30));
		
		//가운데-검색영역 조립 
		c_north.add(ch_category);
		c_north.add(t_keyword);
		c_north.add(bt_search);
		
		
		//가운데-테이블영역 조립 
		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);
		
		//가운데 영역의 전체 패널에 두패널 부착 
		p_center=new JPanel();
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north, BorderLayout.NORTH);
		p_center.add(c_center);
		
		//가운데 전체패널을 프레임에 부착 
		add(p_center);
		
		
		//동쪽영역 생성 
		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_name2 = new JTextField();
		t_brand2 = new JTextField();
		t_price2 = new JTextField();
		bt_find2 = new JButton("이미지찾기");
		can2 = new JPanel();
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		//동쪽 조립 
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(bt_find2);
		p_east.add(can2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		
		//스타일적용 
		ch_top2.setPreferredSize(new Dimension(135, 30));
		ch_sub2.setPreferredSize(new Dimension(135, 30));
		t_name2.setPreferredSize(new Dimension(135, 30));
		t_brand2.setPreferredSize(new Dimension(135, 30));
		t_price2.setPreferredSize(new Dimension(135, 30));
		can2.setPreferredSize(new Dimension(135, 115));
		bt_find2.setPreferredSize(new Dimension(135, 30));
		bt_edit.setPreferredSize(new Dimension(135, 30));
		bt_del.setPreferredSize(new Dimension(135, 30));
		
		p_east.setPreferredSize(new Dimension(150, 600));
//		p_east.setBackground(Color.YELLOW);
		
		//프레임에 서쪽 영역 붙이기 
		add(p_east, BorderLayout.EAST);
		
		setSize(1000,600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//db연동시 하면 안됨..
	}
	
	public static void main(String[] args) {
		new ShoppingApp();
	}
}














