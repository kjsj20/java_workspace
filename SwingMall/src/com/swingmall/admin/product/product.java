package com.swingmall.admin.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.swingmall.admin.AdminMain;
import com.swingmall.admin.Page;

public class product extends Page {
	JPanel p_west;
	JPanel p_center;
	JTree tree;
	JTable table;
	JScrollPane s1, s2;
	JButton bt_regist;
	ArrayList<String> topList;
	ArrayList<ArrayList> subList = new ArrayList<ArrayList>();
	ProductModel model;

	public product(AdminMain adminMain) {
		super(adminMain);
		// 카테고리 가져오기
		getTopList();
		for (int i = 0; i < topList.size(); i++) {
			String name = topList.get(i);
			getSubList(name);
		}

		// 노드 만들기
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품목록");
		for (int i = 0; i < topList.size(); i++) {
			top.add(getCreateNode(topList.get(i), subList.get(i)));
		}

		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree(top);
		table = new JTable();
		s1 = new JScrollPane(tree);
		s2 = new JScrollPane(table);
		bt_regist = new JButton("등록하기");

		// 스타일 적용
		s1.setPreferredSize(new Dimension(200, adminMain.HEIGHT - 100));
		p_west.setBackground(Color.WHITE);
		s2.setPreferredSize(new Dimension(AdminMain.WIDTH - 300, adminMain.HEIGHT - 200));
		// 조립
		setLayout(new BorderLayout());
		p_west.add(s1);
		p_center.add(s2);
		p_center.add(bt_regist);
		
		add(p_west, BorderLayout.WEST);
		add(p_center);

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(selectedNode.toString().equals("상품목록")) {
					getProductList(null);
				} else {
					getProductList(selectedNode.toString());					
				}
			}
		});

		getProductList(null);
	}

	// 상위 카테고리 가져오기
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from topcategory";

		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 배열은 유연하지 못하므로, ArrayList 에 담자
			topList = new ArrayList();
			while (rs.next()) {
				topList.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}

	// 하위 카테고리 가져오기
	public void getSubList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from subcategory where topcategory_id = (select topcategory_id from topcategory where name = ?)";

		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			ArrayList list = new ArrayList();
			while (rs.next()) {
				list.add(rs.getString("name"));
			}
			subList.add(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}

	// 트리노드 생성하기
	public DefaultMutableTreeNode getCreateNode(String parentName, ArrayList childName) {
		// 부모노드 생성하기
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);

		// 넘겨받은 매개변수인 ArrayList 만큼 반복하여 부모노드에 자식노드 부착!!
		for (int i = 0; i < childName.size(); i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}

		return parent;
	}
	
	
	
	// 상품 가져오기
	public void getProductList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = null;
		if (name == null) {
			sql = "select * from product";
		} else {
			sql = "select * from product where subcategory_id =(select subcategory_id from subcategory where name =?)";
		}

		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();
			ArrayList<String> columnNames = new ArrayList<String>();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String colName = meta.getColumnName(i);
				columnNames.add(colName);
			}
			ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
			while (rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setSubcategory_id(rs.getInt("subcategory_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				productList.add(vo);
			}
			model = new ProductModel();
			model.column = columnNames;
			model.record = productList;
			table.setModel(model);
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
}
