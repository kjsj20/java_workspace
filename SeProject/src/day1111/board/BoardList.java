/*게시물 목록 페이지*/
package day1111.board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BoardList extends JPanel {
	JTable table;
	JScrollPane scroll;
	JButton bt;
	BoardApp boardApp;
	BoardModel boardModel;
	Connection con;
	
	public BoardList(BoardApp boardApp) {
		this.boardApp = boardApp;
		con=boardApp.getCon();
		table = new JTable(boardModel = new BoardModel());
		scroll = new JScrollPane(table);
		bt = new JButton("글등록");
		
		getList();

		setLayout(new BorderLayout());
		add(scroll);
		add(bt, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(780, 500));
		setVisible(true);

		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//상세보기가 보유한 getDetail() 메서드 호출하기!!
				BoardDetail boardDetail = (BoardDetail) boardApp.getPages(BoardApp.BOARD_DETAIL);
				String board_id = (String)table.getValueAt(table.getSelectedRow(), 0); //board_id
				boardDetail.getDetail(Integer.parseInt(board_id));
				boardDetail.updateHit(Integer.parseInt(board_id));
				boardApp.setPage(BoardApp.BOARD_DETAIL);// 상세보기
			}
		});

		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 글쓰기 폼의 작성자에, 아이디 넣어두기!!
				BoardWrite page = (BoardWrite) boardApp.getPages(BoardApp.BOARD_WRITE);
				page.t_writer.setText(boardApp.getBoardMember().getM_id());
				boardApp.setPage(BoardApp.BOARD_WRITE);// 글쓰기 폼 보여주기
			}
		});
	}
	//게시물 가져오기!
	//rs에 담겨진데이터를 TableModel 이 보유한 이차원배열 data에 대입!!
	public void getList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board order by board_id desc";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery(sql);
			rs.last();
			int total = rs.getRow();
			String[][] records = new String[total][5];
			rs.beforeFirst();
			
			int index = 0;
			while(rs.next()) { 
				String[] arr = new String[5];
				arr[0] = rs.getString("board_id");
				arr[1] = rs.getString("TITLE");
				arr[2] = rs.getString("writer");
				arr[3] = rs.getString("regdate");
				arr[4] = rs.getString("HIT");
				
				records[index++] = arr;
			}
			
			boardModel.data = records;
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
