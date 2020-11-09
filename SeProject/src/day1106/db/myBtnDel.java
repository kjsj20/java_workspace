package day1106.db;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myBtnDel implements ActionListener {
	ShoppingApp shoppingApp;

	public myBtnDel(ShoppingApp shoppingApp) {
		this.shoppingApp = shoppingApp;
	}

	public void actionPerformed(ActionEvent e) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = shoppingApp.table.getSelectedRow();
		String sql = "delete from product where product_id = " + shoppingApp.table.getValueAt(row, 0);

		try {
			pstmt = shoppingApp.con.prepareStatement(sql);
			int result = pstmt.executeUpdate();

			if (result == 0) {
				System.out.println("삭제 실패 ㅠㅠ");
			} else {
				System.out.println("삭제 성공^^");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		shoppingApp.getProductList();
		shoppingApp.table.updateUI();
	}

}
