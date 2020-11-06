package day1106.db;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{

	String[][] flower = {
			{"장미", "50000", "RED", "Korea"},
			{"튤립", "70000", "Purple", "France"},
			{"안개꽃", "35000", "White", "Korea"}
	};
	
	String[][] car = {
			{"BMW", "50000", "RED", "Korea"},
			{"Benz", "50000", "RED", "Korea"},
			{"Audi", "50000", "RED", "Korea"}
	};
	
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int row, int col) {
		System.out.println("row : " + row + " col : " + col);
		return 4;
	}
}
