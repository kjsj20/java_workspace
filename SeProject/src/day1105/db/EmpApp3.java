package day1105.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmpApp3 extends JFrame{
	JButton btn_con, btn_load;
	JTextArea area; 
	JScrollPane scroll;
	
	String url = "jdbc:oracle:thin:@localhost:1521:ORCL"; 
	String user = "user1104";
	String password = "user1104";
	
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public EmpApp3() {
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
		setSize(750,600);
		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
	}
	
	public void connect(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			area.append("로드 성공!\n");
		
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				area.append("연결 성공!\n");
			} else {
				area.append("연결 실패!\n");
			}
		} catch (ClassNotFoundException e) {
			area.append("로드 실패!\n");
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
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			
			while(rs.next()) {
				for(int i=1; i<=col; i++) {
					area.append(rs.getString(i) + " ");
				}
				area.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EmpApp3();
	}
}
