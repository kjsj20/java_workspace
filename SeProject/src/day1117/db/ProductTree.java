package day1117.db;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class ProductTree extends JFrame {
	JTree tree;
	JScrollPane scroll;

	String[] category = { "상의", "하의", "엑세서리", "신발" }; // 상위 카테고리 배열
	String[][] product = { { "티셔츠", "점퍼", "니트", "가디건" }, { "청바지", "반바지", "면바지", "핫바지" }, { "귀걸이", "목걸이", "반지", "팔찌" },
			{ "구두", "운동화", "스니커즈", "샌들" } };

	ArrayList<String> topCategory = new ArrayList<String>();
	ArrayList<ArrayList> subCategory = new ArrayList<ArrayList>();

	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
	String user = "user1104";
	String password = "user1104";

	public ProductTree() {
		// DB연동하여 배열들의 데이터를 실제 DB 데이터로 가져오자!!
		// 노드 생성을 외부 메서드로부터 공수받자!!
		connect();
		getTopCategory();
		//서브카테고리 메서드를 상위 카테고리 수만큼 호출하면서 2차원 ArrayList 구조를 생성하자!!
		for(int i =0; i<topCategory.size();i++) {
			String name = topCategory.get(i);
			ArrayList subList = (ArrayList)getSubCategory(name);			
			subCategory.add(subList);
		}
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품정보");
		for (int i = 0; i < category.length; i++) {
			top.add(getCreatedNode(category[i], product[i]));
		}

		tree = new JTree(top);
		scroll = new JScrollPane(tree);

		add(scroll);

		setSize(400, 500);
		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		setLocationRelativeTo(null);
	}

	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			if (con == null) {
				JOptionPane.showMessageDialog(this, "DB연결 실패..");
			} else {
				this.setTitle("환영합니다~");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 상위 카테고리 가져오기
	public void getTopCategory() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from topcategory";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				topCategory.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 하위 카테고리 가져오기
	public List getSubCategory(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList subList = new ArrayList();
		String sql = "select * from subcategory where topcategory_id = (select topcategory_id from topcategory where name = ?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				subList.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return subList;
		}

	}

	// 이 메서드를 호출하는 자가, 배열을 넘기면 배열의 길이만큼 노드를 구성하여 반환해줄 것임
	public DefaultMutableTreeNode getCreatedNode(String parentName, String[] childName) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName); // 부모 생성

		for (int i = 0; i < childName.length; i++) {
			parent.add(new DefaultMutableTreeNode(childName[i]));
		}

		return parent;
	}
	
	//위의 메서드는 배열을 처리하는 용이지만, 이 메서드는 이름은 같지만 List용으로 처리...
	public DefaultMutableTreeNode getCreatedNode(String parentName, ArrayList  childName) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName); // 부모 생성

		for (int i = 0; i < childName.size(); i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}

		return parent;
	}
	
	public static void main(String[] args) {
		new ProductTree();
	}
}