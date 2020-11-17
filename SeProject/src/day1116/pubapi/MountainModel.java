package day1116.pubapi;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MountainModel extends AbstractTableModel{
	/*
	 * 이제 이차원배열이 아니라, Vector를 선언해서 , 데이터와 컬럼명을 처리해보겠습니다.
	 * 백터는 컬렉션 프레임웍이니 배열처럼 생성시 크기를 명시하지 않아도 됩니다..그 만큼 유연합니다..
	 * */
	//복사하세요
	Vector<Mountain> data=new Vector<Mountain>();//근데 제너릭타입이 아니라서 뭔가 허전합니다..
											//제너릭타입으로 알맞는 자료형은 뭘까요? 우리는 지금 산을 가져와서 
											//테이블에 출력할 예정에용!!!!! 그럼 산이 들어와야 합니다
	//컬럼 제목 정보를 담는 백터 
	Vector<String> columnName = new Vector<String>(); //n이 빠졌네요
	Mountain mt ;
	//컬럼제목을 구성할 벡터 요소는 생성자에서 채웁시다!!!
	//컬럼명은 한국말로 채웁시다!!
	public MountainModel() {
		columnName.add("ID");//산 아이디 
		columnName.add("산이름");//산 이름 
		columnName.add("소재지");//산 소재지 
		columnName.add("높이");//산 높이 	
		
		mt = new Mountain();
		data.add(mt);
	}
	
	//이제 레코드의 수(행의 수)는 백터의 길이에서 가져오면 됨
	public int getRowCount() {
		return data.size();
	}
	
	//이제 컬럼의 수는 백터의 길이에서 가져오면 됨 
	public int getColumnCount() {
		return columnName.size();
	}
	//컬럼 제목을 출력하기 위한, 메서드 오버라이드 
	public String getColumnName(int col) { //col로 줄여주세요
		return columnName.get(col);//배열이 아니므로 get(인덱스) 로 가져와야 합니다
	}
	
	//2차원배열이나 벡터는 모두 배열구조이므로 지금까지는 [row][col] 형태로 데이터를 추출하였다!!
	//하지만, 이제 벡터에 들어있는 데이터는 VO이므로 
	public Object getValueAt(int row, int col) {
//		System.out.println("col 값 = " + col); //호출시 컬럼값 확인하기 !!
		String obj = null;
		Mountain mt = data.get(row);
		if(col == 0) {
			obj = Integer.toString(mt.getMntnid());
		} else if (col == 1) {
			obj = mt.getMntnnm();
		} else if (col == 2) {
			obj = mt.getMntninfopoflc();
		} else if (col == 3) {
			obj = Integer.toString(mt.getMntninfohght());
		}
		return obj;
	}
	
	
}