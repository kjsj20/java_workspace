package day1106.db;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myBtnEdit implements ActionListener {
	ShoppingApp shoppingApp;

	int subId;

	public myBtnEdit(ShoppingApp shoppingApp) {
		this.shoppingApp = shoppingApp;
		subId = 0;
	}

	public void actionPerformed(ActionEvent e) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = shoppingApp.table.getSelectedRow();
//		System.out.println(shoppingApp.ch_sub2.getSelectedItem());
		String subcategory_nm = shoppingApp.ch_sub2.getSelectedItem();
		String sql = "select subcategory_id from subcategory where name = '" + subcategory_nm + "'";
		try {
			pstmt = shoppingApp.con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			rs.next();
			subId = rs.getInt(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		sql = "update product set SUBCATEGORY_ID = ?, PRODUCT_NAME = ?, BRAND = ?, PRICE = ?  WHERE PRODUCT_ID = ?";

		try {
			pstmt = shoppingApp.con.prepareStatement(sql);
			pstmt.setInt(1, subId);
			pstmt.setString(2, shoppingApp.t_name2.getText());
			pstmt.setString(3, shoppingApp.t_brand2.getText());
			pstmt.setString(4, shoppingApp.t_price2.getText());
			pstmt.setString(5, (String) shoppingApp.table.getValueAt(row, 0));
			int result = pstmt.executeUpdate();

			if (result == 0) {
				System.out.println("수정 실패^^");
			} else {
				System.out.println("수정 성공^^");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		shoppingApp.getProductList();
		shoppingApp.table.updateUI();

	}
}
