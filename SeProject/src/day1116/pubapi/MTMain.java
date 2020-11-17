package day1116.pubapi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MTMain extends JFrame{
	//이차원 배열이 아니라, Vector를 선언해서 , 데이터와 컬럼명을 처리.
	//벡터는 컬렉션 프레임웍이니 배열처럼 생성시 크기를 명시하지 않아도 됨
		Vector data = new Vector();

		// 컬럼 제목 정보를 담는 벡터
		Vector<String> columnName = new Vector<String>();
	//서쪽영역
	JPanel p_west;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
	
	JTable table;
	MountainModel model;
	JScrollPane scroll;
	
	
	public MTMain() {
		init();
		p_west = new JPanel();
		t_name = new JTextField();
		t_op1 = new JTextField();
		t_op2 = new JTextField();
		t_op3 = new JTextField();
		bt = new JButton("검색하기");
		
		table = new JTable(data,columnName);
		scroll = new JScrollPane(table);
		
		
		//스타일 적용 
		p_west.setPreferredSize(new Dimension(200, 600));
		p_west.setBackground(Color.WHITE);
		t_name.setPreferredSize(new Dimension(190, 30));
		t_op1.setPreferredSize(new Dimension(190, 30));
		t_op2.setPreferredSize(new Dimension(190, 30));
		t_op3.setPreferredSize(new Dimension(190, 30));
		
		p_west.add(t_name);
		p_west.add(t_op1);
		p_west.add(t_op2);
		p_west.add(t_op3);
		p_west.add(bt);
		
		add(p_west, BorderLayout.WEST);
		add(scroll);
		
		setSize(900,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	//벡터값을 초기화 하자
	public void init() {
		//데이터 1건 넣어보기
		/*JTable에 벡터를 매개변수로 넣는 방식은 객체지향적인 요즘 개발방식에 맞지 않음..
		 * 이유) 백터안에 백터를 넣어야 하므로, 이차원 배열 방식과 다를바 없음..
		 * 따라서 아래와 같이 벡터안에 또 하나의 벡터를 추가해야 함..
		 * */
		Vector v= new Vector();
		v.add("1");
		v.add("설악산");
		v.add("강원도");
		v.add("3000");
		
		data.add(v);
		//컬럼정보 채우고
		columnName.add("ID");
		columnName.add("산이름");
		columnName.add("소재지");
		columnName.add("높이");
	}
	
	public static void main(String[] args) {
		new MTMain();
	}
}
