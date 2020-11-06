package day1106.db;

import javax.swing.table.AbstractTableModel;

public class ProductController extends AbstractTableModel{
	String[][] data = {};
	String[] column = {"product_id", "subcategory_id", "product_name", "brand", "price", "filename"};
	
	public int getColumnCount() {
		return column.length;
	}

	public int getRowCount() {
		return data.length;
	}
	
	//JTable이 getColumnCount()의 갯수만큼 호출하면서 순서대로 0,1,2,3,4,5 를 넘기면서 컬럼 제목을 가져간다..
	public String getColumnName(int col) {
		return column[col];
	}
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
}
