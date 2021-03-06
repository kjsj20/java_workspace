package board;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

public class BoardEvent{
	public BoardEvent() {
		
	};
	public void delPageLabelPrev() {
		boardPenel_north.remove(labelPagePrev);
		boardPenel_north.remove(labelPageNext);
		for (int i = pageForINum; i < PAGECOUNTSPLITNUM; i++) {
			boardPenel_north.remove(labelPageIndex.get(i));
		}
		for (int i = pageForINum; i < labelPageIndex.size();) {
			labelPageIndex.remove(pageForINum);
		}
		boardPenel_north.updateUI();
	}

	// 페이지수 10번째에서 11번째로 넘어갈때.. 삭제하고 다시 만들어줌..
	public void delPageLabelNext() {
		boardPenel_north.remove(labelPagePrev);
		boardPenel_north.remove(labelPageNext);
		for (int i = 0; i < PAGECOUNTSPLITNUM; i++) {
			boardPenel_north.remove(labelPageIndex.get(i));
		}
		boardPenel_north.updateUI();
	}

	// 페이지 목록을 선택했을때..
	public void pageSelect() {
		end_pageNum = (pagenum + 1) * GRIDCOUNT;
		start_pageNum = end_pageNum - 9;
		selectBoard();
		boardPanel_center_content.updateUI();
	}

	// board를 조회한다 !
	public void selectBoard() {

		// 게시판 조회된 목록을 다 지운 뒤 조회 한다.
		boardList.removeAll(boardList);

		String sql = "select * from (SELECT rownum num, aa.*FROM  ";
		sql += " ( SELECT * from board WHERE BOARD_STATUS  = 0 ORDER BY board_id desc) aa ) ";
		sql += " WHERE num >= ? AND num <= ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start_pageNum);
			pstmt.setInt(2, end_pageNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardVO vo = new boardVO();

				vo.setBoard_id(rs.getInt("board_id"));
				vo.setBoard_title(rs.getString("board_title"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setBoard_username(rs.getString("board_username"));
				vo.setBoard_ip(rs.getString("board_ip"));
				vo.setBoard_wtime(rs.getString("board_wtime"));
				vo.setBoard_status(rs.getString("board_status"));
				vo.setBoard_comment_count(rs.getInt("board_comment_count"));
				vo.setBoard_hit(rs.getInt("board_hit"));

				boardList.add(vo);
			}
			;

			// board에 패널추가 method 호출.
			addBoardPanel();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt, rs);
		}
	}

	// 글쓰기 등록 시 board content에 패널추가.
	public void addBoardPanel() {
		boardPanel_center_content.removeAll();
		panelList.removeAll(panelList);
		labelListNum.removeAll(labelListNum);
		labelListTitle.removeAll(labelListTitle);
		labelListUser.removeAll(labelListUser);
		labelListTime.removeAll(labelListTime);
		labelListHit.removeAll(labelListHit);

		for (int i = 0; i < boardList.size(); i++) {
			// 해당 num값을 받아놓는다.. 이벤트 처리를 위하여..
			int num = i;

			// 패널 생성
			panelList.add(new JPanel());
			panelList.get(i).setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			panelList.get(i).setBackground(Color.DARK_GRAY);
			panelList.get(i).setLayout(null);
			boardPanel_center_content.add(panelList.get(i));
			panelList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			// 글 번호 라벨
			labelListNum.add(new JLabel(Integer.toString((ALLCOUNT - ((pagenum * GRIDCOUNT) + i)))));
			labelListNum.get(i).setForeground(Color.WHITE);
			labelListNum.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListNum.get(i).setBounds(38, 20, 57, 15);
			panelList.get(i).add(labelListNum.get(i));

			// 글 제목 라벨
			labelListTitle.add(new JLabel(boardList.get(i).getBoard_title()));
			labelListTitle.get(i).setForeground(Color.WHITE);
			labelListTitle.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListTitle.get(i).setBounds(135, 20, 400, 15);
			panelList.get(i).add(labelListTitle.get(i));

			// 글 작성자 라벨
			labelListUser.add(new JLabel(boardList.get(i).getBoard_username()));
			labelListUser.get(i).setForeground(Color.WHITE);
			labelListUser.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListUser.get(i).setBounds(540, 20, 57, 15);
			panelList.get(i).add(labelListUser.get(i));

			// 글 작성시간 라벨
			labelListTime.add(new JLabel(boardList.get(i).getBoard_wtime()));
			labelListTime.get(i).setForeground(Color.WHITE);
			labelListTime.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListTime.get(i).setBounds(620, 20, 150, 15);
			panelList.get(i).add(labelListTime.get(i));

			// 글 조회수 라벨
			labelListHit.add(new JLabel(Integer.toString(boardList.get(i).getBoard_hit())));
			labelListHit.get(i).setForeground(Color.WHITE);
			labelListHit.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListHit.get(i).setBounds(827, 20, 57, 15);
			panelList.get(i).add(labelListHit.get(i));

			// 패널 클릭 이벤트
			panelList.get(i).addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					panelList.get(num).setBackground(Color.black);
				}

				public void mouseExited(MouseEvent e) {
					panelList.get(num).setBackground(Color.DARK_GRAY);
				}

				public void mouseReleased(MouseEvent e) {
					writeBtnEvent();
					scrollWrite.setBounds(112, 88, 686, 311);
					delBtn.setBounds(260, 420, 86, 23);
					prevBtn.setBounds(410, 420, 86, 23);
					updBtn.setBounds(560, 420, 86, 23);
					// 수정, 삭제등에 사용할 board_id를 담아둔다!
					board_id = boardList.get(num).getBoard_id();
					// 조회수 올리기 메서드.
					updHit();
					// 댓글 조회 메서드
					selectCmmt();
					title.setText(boardList.get(num).getBoard_title());
					content.setText(boardList.get(num).getBoard_content());
					// placeholder 기능으로 인해 회색으로 글씨가 보여 하얀색으로 초기화 시킴..
					title.setForeground(Color.white);
					content.setForeground(Color.white);
					// 삭제 수정 버튼을 보여줄지 여부..
					delBtn.setVisible(true);
					updBtn.setVisible(true);
					registBtn.setVisible(false);
					// 클릭 시 검은 배경이 남는 현상 발생으로 색깔 변경..
					panelList.get(num).setBackground(Color.DARK_GRAY);
				}
			});
		}
	}

	// board를 insert한다(작성을 도와줌)!
	public void addBoard() {
		String sql = "insert into board (board_id, board_title, board_content, board_username, board_ip, board_wtime, board_status, board_comment_count, board_hit) ";
		sql = sql + "values(board_seq.nextval, ?, ?, ?, ?, to_char(sysdate, 'yyyy-mm-dd hh24:mm:ss'), 0,  0, 0)";
		PreparedStatement pstmt = null;
		try {
			// ip가지고 오기 ..!!
			local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title.getText());
			pstmt.setString(2, content.getText());
			pstmt.setString(3, "test");
			pstmt.setString(4, ip);
			int result = pstmt.executeUpdate();

			if (result == 0) {
				JOptionPane.showMessageDialog(boardPanel, "등록 오류 발생.");
			} else {
				JOptionPane.showMessageDialog(boardPanel, "등록 성공.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt);
		}
	}

	// board를 delete 한다. board_status만 1로 변경..
	public void delBoard() {
		String sql = "UPDATE board SET BOARD_STATUS = 1 WHERE  BOARD_ID = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			int result = pstmt.executeUpdate();

			if (result == 0) {
				JOptionPane.showMessageDialog(boardPanel, "삭제실패..");
			} else {
				JOptionPane.showMessageDialog(boardPanel, "삭제성공!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt);
		}
	}

	// board를 update(수정) 한다.
	public void updBoard() {
		String sql = "update board set board_title = ?, board_content = ? where board_id = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title.getText());
			pstmt.setString(2, content.getText());
			pstmt.setInt(3, board_id);
			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(boardPanel, "수정실패..");
			} else {
				JOptionPane.showMessageDialog(boardPanel, "수정성공!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt);
		}
	}

	// 선택한 board의 Hit 수를 증가시킨다
	public void updHit() {
		String sql = "UPDATE board SET BOARD_HIT  = NVL(TO_NUMBER(BOARD_HIT), 0) + 1 WHERE  BOARD_ID = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			int result = pstmt.executeUpdate();
			if (result == 0) {
				System.out.println("조회수 증가 오류 발생...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt);
		}
	}

	// 댓글 등록 이벤트..
	public void addCmmt() {
//		cmmt_id, board_id, cmmt_content, cmmt_wtime, cmmt_ip 
		String sql = "INSERT INTO CMMT VALUES(CMMT_SEQ.NEXTVAL, ?, ?, ?,to_char(sysdate, 'yyyy-mm-dd hh24:mm:ss'), ?)";
		PreparedStatement pstmt = null;

		try {
			local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.setString(2, "test");
			pstmt.setString(3, cmmt.getText());
			pstmt.setString(4, ip);

			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(boardPanel, "댓글 등록 실패..");
			} else {
				JOptionPane.showMessageDialog(boardPanel, "댓글 등록 성공..");
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt);
		}
	}

	// 댓글 조회 메소드
	public void selectCmmt() {
		cmmtList.removeAll(cmmtList);
		String sql = "SELECT * FROM CMMT WHERE BOARD_ID = ? ORDER BY CMMT_ID DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cmmtVO vo = new cmmtVO();
				vo.setCmmt_id(rs.getInt("cmmt_id"));
				vo.setBoard_id(rs.getInt("board_id"));
				vo.setCmmt_username(rs.getString("cmmt_username"));
				vo.setCmmt_content(rs.getString("cmmt_content"));
				vo.setCmmt_wtime(rs.getString("cmmt_wtime"));
				vo.setCmmt_ip(rs.getString("cmmt_ip"));
				cmmtList.add(vo);
			}
			addCmmtPanel();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt, rs);
		}
	}

//	텍스트에어리어.getlinecount

	// 댓글 레코드 수만큼 패널추가 ..
	public void addCmmtPanel() {
		writePanel.remove(scrollCmmtPanel);
		createCmmtScrollPanel();
		cmmtPanelList.removeAll(cmmtPanelList);
		cmmtLabelListUser.removeAll(cmmtLabelListUser);
		cmmtLabelListTime.removeAll(cmmtLabelListTime);
		cmmtLabelListContent.removeAll(cmmtLabelListContent);
		cmmtPanel.updateUI();
		int textAreaSum = 0;
		for (int i = 0; i < cmmtList.size(); i++) {
			cmmtPanelList.add(new JPanel());
			cmmtPanelList.get(i).setPreferredSize(new Dimension(686, 45));
			cmmtPanelList.get(i).setBackground(Color.DARK_GRAY);
			cmmtPanelList.get(i).setLayout(null);
			cmmtPanelList.get(i).setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			cmmtPanel.add(cmmtPanelList.get(i));

			cmmtLabelListUser.add(new JLabel(cmmtList.get(i).getCmmt_username()));
//			cmmtLabelListUser.add(new JLabel(Integer.toString(cmmtList.get(i).getBoard_id())));
			cmmtLabelListUser.get(i).setBounds(20, 0, 100, 10);
			cmmtLabelListUser.get(i).setForeground(Color.WHITE);
			cmmtPanelList.get(i).add(cmmtLabelListUser.get(i));

			cmmtLabelListTime.add(new JLabel(cmmtList.get(i).getCmmt_wtime()));
			cmmtLabelListTime.get(i).setBounds(100, 0, 115, 10);
			cmmtLabelListTime.get(i).setForeground(Color.WHITE);
			cmmtPanelList.get(i).add(cmmtLabelListTime.get(i));

			cmmtLabelListContent.add(new JTextArea(cmmtList.get(i).getCmmt_content()));
			cmmtLabelListContent.get(i).setEnabled(false);
			cmmtLabelListContent.get(i).setDisabledTextColor(Color.WHITE);
			cmmtLabelListContent.get(i).setBackground(Color.DARK_GRAY);

			// 채팅 줄 수에 따라 패널, 에어리어의 크기를 변경해준다..
			if (cmmtLabelListContent.get(i).getLineCount() > 1) {
				cmmtPanelList.get(i)
						.setPreferredSize(new Dimension(686, 40 + (cmmtLabelListContent.get(i).getLineCount() * 15)));
				cmmtLabelListContent.get(i).setBounds(100, 20, 630, cmmtLabelListContent.get(i).getLineCount() * 18);
				textAreaSum = textAreaSum + (cmmtLabelListContent.get(i).getLineCount() * 18);
			} else {
				cmmtLabelListContent.get(i).setBounds(100, 20, 630, 20);
			}

			cmmtLabelListContent.get(i).setForeground(Color.WHITE);
			cmmtPanelList.get(i).add(cmmtLabelListContent.get(i));
		}
		// 리스트가 생기는 만큼 패널의 크기도 조절해준다..
		if ((cmmtList.size() * 40) > cmmtPanel.getHeight()) {
			cmmtPanel.setPreferredSize(new Dimension(240, ((cmmtList.size() + 1) * 45) + textAreaSum));
		}

		// 스크롤바를 맨위로 보내기 위해 사용..
		Runnable doScroll = new Runnable() {
			public void run() {
				scrollCmmtPanel.getVerticalScrollBar().setValue(0);
			}
		};
		SwingUtilities.invokeLater(doScroll);
	}

	public void createCmmtScrollPanel() {
		// 댓글 레이아웃 프레임 패널 추가
		cmmtPanel = new JPanel();
		scrollCmmtPanel = new JScrollPane(cmmtPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCmmtPanel.setBounds(112, 550, 686, 120);
		cmmtPanel.setBackground(Color.DARK_GRAY);
		scrollCmmtPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmmtPanel.setLayout(new FlowLayout());
		writePanel.add(scrollCmmtPanel);
	}
	
	public static void main(String[] args) {
		new BoardEvent();
	}
}
