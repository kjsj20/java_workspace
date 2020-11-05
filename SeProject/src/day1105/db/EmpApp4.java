package day1105.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmpApp4 extends JFrame{
	JButton btn_con, btn_load;
	JTextArea area;
	JScrollPane scroll;
	
	String url = "jdbc:mariadb://localhost/db1105";
	String user = "root";
	String password = "1234";
	
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public EmpApp4() {
		btn_con = new JButton("Connect");
		btn_load = new JButton("Load");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setPreferredSize(new Dimension(650, 500));
		
		add(btn_con);
		add(btn_load);
		add(scroll);
		
		btn_con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		btn_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		setLayout(new FlowLayout());
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			area.append("로드성공!\n");
			
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				area.append("연결성공!\n");
			}else {
				area.append("연결실패!\n");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		String sql = "select * from emp";
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int col = rmd.getColumnCount();
			System.out.println(col);
			while(rs.next()) {
				for(int i = 1; i<=col; i++) {
					area.append(rs.getString(i) + " ");
				}
				area.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EmpApp4();
	}
}
