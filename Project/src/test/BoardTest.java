package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import project.utill.DBManager;

public class BoardTest {

	private JFrame frame;
	private JTextField title;
	JPanel boardPanel;
	JPanel boardPenel_north;
	JPanel boardPanel_center;
	JPanel writePanel;
	JPanel cmmtPanel;
	JTextArea content, cmmt;
	JLabel labelPagePrev, labelPageNext;
	JScrollPane scrollWrite, scrollCmmt, scrollCmmtPanel;
	public static final int GRIDCOUNT = 10; // 그리드 레이아웃의 층 갯수. 게사판 패널이 들어갈 갯수를 정한다.
	public static int PAGECOUNT = 0, ALLCOUNT = 0, PAGECOUNTSPLIT = 0, PAGECOUNTSPLITNUM = 10;
	int pageForINum = 0;
	int board_id, pagenum = 0;
	int start_pageNum = 1, end_pageNum = 10;

	Connection con;
	DBManager dbCon;
	// boardVO를 담아둘 컬렉션 프레임워크 선언
	ArrayList<boardVO> boardList = new ArrayList<boardVO>();
	// 게시판 리스트 패널을 담아둘 컬렉션 프레임워크 선언
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	// 게시판 라벨 출력을 위해 담아둘 컬렉션 프레임워크 선언
	ArrayList<JLabel> labelListNum = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListTitle = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListUser = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListTime = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListHit = new ArrayList<JLabel>();
	// 게시판 라벨 인덱스를 담아둘 컬렉션 프레임워크 선언
	ArrayList<JLabel> labelPageIndex = new ArrayList<JLabel>();

	// cmmtVO를 담아둘 컬렉션 프레임워크 선언
	ArrayList<cmmtVO> cmmtList = new ArrayList<cmmtVO>();
	// 댓글 리스트 패널을 담아둘 컬렉션 프레임워크 선언
	ArrayList<JPanel> cmmtPanelList = new ArrayList<JPanel>();
	// 댓글 라벨 출력을 위해 담아둘 컬렉션 프레임 워크 선언
	ArrayList<JLabel> cmmtLabelListUser = new ArrayList<JLabel>();
	ArrayList<JLabel> cmmtLabelListTime = new ArrayList<JLabel>();
	ArrayList<JTextArea> cmmtLabelListContent = new ArrayList<JTextArea>();

	JButton registBtn, prevBtn, updBtn, delBtn, cmmtBtn;

	InetAddress local;
	private JPanel boardPanel_center_header;
	private JPanel boardPanel_center_content;
	private JLabel contentNum;
	private JLabel contentTitle;
	private JLabel writerName;
	private JLabel writerTime;
	private JLabel writerHit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardTest window = new BoardTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BoardTest() {
		dbCon = new DBManager();
		con = dbCon.connect();
		initialize();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbCon.disConnect(con);
				System.exit(0);
			}
		});
	}

	public void writeBtnEvent() {
		boardPanel.removeAll();
		boardPanel.updateUI();
		boardPanel.add(writePanel, BorderLayout.CENTER);
		scrollWrite.setBounds(112, 88, 686, 511);
		registBtn.setBounds(468, 627, 86, 23);
		prevBtn.setBounds(351, 627, 86, 23);
		scrollCmmtPanel.setVisible(false);
		compoInit();
	}

	public void prevBtnEvent() {
		boardPanel.removeAll();
		boardPanel.updateUI();
		boardPanel.add(boardPenel_north, BorderLayout.NORTH);
		boardPanel.add(boardPanel_center, BorderLayout.CENTER);
		delBtn.setVisible(false);
		updBtn.setVisible(false);
		registBtn.setVisible(true);
		// 뒤로가기에 재조회 기능 추가..
		selectBoard();
	};

	public void compoInit() {
		title.setText("제목을 입력해주세요..");
		title.setForeground(Color.GRAY);
		content.setText("내용을 입력해주세요..");
		content.setForeground(Color.GRAY);
		cmmt.setText("댓글을 입력해주세요..");
		cmmt.setForeground(Color.GRAY);
	}

	// board의 갯수를 조회한다.
	public void countRowBoard() {
		String sql = "select board_id from board where board_status = 0";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last();
			ALLCOUNT = rs.getRow();
			PAGECOUNT = (rs.getRow() + (GRIDCOUNT - 1)) / GRIDCOUNT;
			// page 갯수 계산을 위해 넣어둔다.. 10개 이상이될때 분리 시키는 작업..
			PAGECOUNTSPLIT = PAGECOUNT;
			rs.beforeFirst();

			addPage();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt, rs);
		}
	}

	// 게시판 레코드가 10개가 넘어갈때마다 페이지를 생성한다.
	public void addPage() {
		int x = 33;
		// 화살표 라벨을 만들어줌
		labelPagePrev = new JLabel("◀");
		labelPagePrev.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		labelPagePrev.setForeground(Color.WHITE);
		labelPagePrev.setBounds(10, 7, 16, 15);
		labelPagePrev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		boardPenel_north.add(labelPagePrev);

		for (int i = pageForINum; i < PAGECOUNTSPLITNUM; i++) {
			int num = i;
			labelPageIndex.add(new JLabel(Integer.toString(i + 1)));
			labelPageIndex.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelPageIndex.get(i).setForeground(Color.WHITE);
			labelPageIndex.get(i).setBounds(x, 7, 16, 15);
			// page수가 많아지면 x값을 증가시켜 이동시키기 위해..
			x += 20;
			boardPenel_north.add(labelPageIndex.get(i));
			labelPageIndex.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			// page쪽수 라벨을 누르면 작동.. 페이지 이동
			labelPageIndex.get(i).addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					labelPageIndex.get(pagenum).setForeground(Color.white);
					pagenum = num;
					labelPageIndex.get(num).setForeground(Color.black);
					pageSelect();
				}
			});
		}
		labelPageNext = new JLabel("▶");
		labelPageNext.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		labelPageNext.setForeground(Color.WHITE);
		labelPageNext.setBounds(x + 10, 7, 16, 15);
		labelPageNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		boardPenel_north.add(labelPageNext);

		labelPagePrev.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				// 최소 페이지 수를 넘기면 안넘어 가도록 조정
				if (pagenum > 0) {
					if (pagenum % 10 == 0) {
						pageForINum = (pageForINum - 10);
						delPageLabelPrev();
						if (PAGECOUNTSPLITNUM == PAGECOUNT) {
							PAGECOUNTSPLITNUM -= PAGECOUNT - (pageForINum + 10);
						} else {
							PAGECOUNTSPLITNUM -= 10;
						}
						addPage();
					}
					if (labelPageIndex.size() != pagenum) {
						labelPageIndex.get(pagenum).setForeground(Color.white);
					}
					pagenum = pagenum - 1;
					labelPageIndex.get(pagenum).setForeground(Color.black);
					pageSelect();
				}
			}
		});

		labelPageNext.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				// 최대 페이지 수를 넘기면 안넘어 가도록 조정
				if (PAGECOUNT - 1 > pagenum) {
					if (pagenum == labelPageIndex.size() - 1) {
						pageForINum = (pageForINum + 10);
						delPageLabelNext();
						PAGECOUNTSPLITNUM += 10;
						if (PAGECOUNTSPLITNUM > PAGECOUNT) {
							PAGECOUNTSPLITNUM = PAGECOUNT;
						}
						addPage();
					}
					labelPageIndex.get(pagenum).setForeground(Color.white);
					pagenum = pagenum + 1;
					labelPageIndex.get(pagenum).setForeground(Color.black);
					pageSelect();
				}
			}
		});
	}

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
			int num = i;
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
			cmmtPanel.setPreferredSize(new Dimension(240,  ((cmmtList.size() + 1) * 45) + textAreaSum));
		}
		
		//스크롤바를 맨위로 보내기 위해 사용..
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
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		boardPanel = new JPanel();
		frame.getContentPane().add(boardPanel);
		boardPanel.setLayout(new BorderLayout(0, 0));

		boardPenel_north = new JPanel();
		boardPenel_north.setBackground(Color.DARK_GRAY);
		boardPenel_north.setPreferredSize(new Dimension(900, 30));
		boardPanel.add(boardPenel_north, BorderLayout.NORTH);
		boardPenel_north.setLayout(null);

		JButton writeBtn = new JButton("Write");
		writeBtn.setFocusable(false);
		writeBtn.setPreferredSize(new Dimension(59, 30));
		writeBtn.setFont(new Font("HY견고딕", Font.PLAIN, 11));
		writeBtn.setBounds(799, 5, 75, 21);
		boardPenel_north.add(writeBtn);

		boardPanel_center = new JPanel();
		boardPanel_center.setBackground(Color.DARK_GRAY);
		boardPanel_center.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		boardPanel_center.setPreferredSize(new Dimension(900, 690));
		boardPanel.add(boardPanel_center, BorderLayout.CENTER);
		boardPanel_center.setLayout(new BorderLayout(0, 0));

		boardPanel_center_header = new JPanel();
		boardPanel_center_header.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		boardPanel_center_header.setBackground(Color.DARK_GRAY);
		boardPanel_center_header.setPreferredSize(new Dimension(900, 30));
		boardPanel_center.add(boardPanel_center_header, BorderLayout.NORTH);
		boardPanel_center_header.setLayout(null);

		contentNum = new JLabel("글번호");
		contentNum.setForeground(Color.WHITE);
		contentNum.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		contentNum.setBounds(30, 5, 57, 15);
		boardPanel_center_header.add(contentNum);

		contentTitle = new JLabel("제목");
		contentTitle.setForeground(Color.WHITE);
		contentTitle.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		contentTitle.setBounds(278, 5, 57, 15);
		boardPanel_center_header.add(contentTitle);

		writerName = new JLabel("글쓴이");
		writerName.setForeground(Color.WHITE);
		writerName.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		writerName.setBounds(535, 5, 57, 15);
		boardPanel_center_header.add(writerName);

		writerTime = new JLabel("작성일");
		writerTime.setForeground(Color.WHITE);
		writerTime.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		writerTime.setBounds(670, 5, 57, 15);
		boardPanel_center_header.add(writerTime);

		writerHit = new JLabel("조회수");
		writerHit.setForeground(Color.WHITE);
		writerHit.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		writerHit.setBounds(815, 5, 57, 15);
		boardPanel_center_header.add(writerHit);

		boardPanel_center_content = new JPanel();
		boardPanel_center_content.setBackground(Color.DARK_GRAY);
		boardPanel_center.add(boardPanel_center_content, BorderLayout.CENTER);
		boardPanel_center_content.setLayout(new GridLayout(GRIDCOUNT, 0, 0, 0));
		// 리스트 갯수를 조회한다..
		countRowBoard();
		// 처음 실행시 1번의 글자색이 검은색으로 변경해야됨..
		labelPageIndex.get(0).setForeground(Color.black);
		// 리스트 조회 후 boardPanel_center 에 넣기위해 아래 쪽에선언한다.
		selectBoard();

		// 게시판 글쓰기 페이지
		writePanel = new JPanel();
		writePanel.setBounds(0, 0, 884, 681);
		writePanel.setBackground(Color.DARK_GRAY);
		writePanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		writePanel.setPreferredSize(new Dimension(900, 690));
		writePanel.setLayout(null);

		title = new JTextField();
		title.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		title.setForeground(Color.GRAY);
		title.setText("제목을 입력해주세요..");
		title.setBounds(112, 57, 686, 21);
		writePanel.add(title);
		title.setColumns(10);

		// 제목 Placeholder
		title.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (title.getText().equals("제목을 입력해주세요..")) {
					title.setText("");
					title.setForeground(Color.WHITE);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (title.getText().isEmpty()) {
					title.setText("제목을 입력해주세요..");
					title.setForeground(Color.GRAY);
				}
			}
		});

		content = new JTextArea();
		scrollWrite = new JScrollPane(content);
		scrollWrite.setBorder(new LineBorder(new Color(0, 0, 0)));
		content.setText("내용을 입력해주세요..");
		scrollWrite.setForeground(Color.GRAY);
		scrollWrite.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		writePanel.add(scrollWrite);

		createCmmtScrollPanel();

		// 댓글 텍스트 에어리어 추가
		cmmt = new JTextArea();
		scrollCmmt = new JScrollPane(cmmt);
		scrollCmmt.setBorder(new LineBorder(new Color(0, 0, 0)));
		cmmt.setText("댓글을 입력해주세요..");
		cmmt.setForeground(Color.GRAY);
		scrollCmmt.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		scrollCmmt.setBounds(112, 460, 686, 40);
		writePanel.add(scrollCmmt);

		// 댓글등록 버튼 추가..
		cmmtBtn = new JButton("댓글등록");
		cmmtBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		cmmtBtn.setBounds(707, 510, 90, 25);
		writePanel.add(cmmtBtn);

		// 댓글 Placeholder
		cmmt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (cmmt.getText().equals("댓글을 입력해주세요..")) {
					cmmt.setText("");
					cmmt.setForeground(Color.WHITE);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (cmmt.getText().isEmpty()) {
					cmmt.setText("댓글을 입력해주세요..");
					cmmt.setForeground(Color.GRAY);
				}
			}
		});

		content.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (content.getText().equals("내용을 입력해주세요..")) {
					content.setText("");
					content.setForeground(Color.WHITE);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (content.getText().isEmpty()) {
					content.setText("내용을 입력해주세요..");
					content.setForeground(Color.GRAY);
				}
			}
		});

		registBtn = new JButton("등록하기");
		registBtn.setForeground(Color.WHITE);
		registBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		writePanel.add(registBtn);

		prevBtn = new JButton("이전으로");
		prevBtn.setForeground(Color.WHITE);
		prevBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		writePanel.add(prevBtn);

		updBtn = new JButton("수정하기");
		updBtn.setForeground(Color.WHITE);
		updBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		updBtn.setBounds(468, 627, 86, 23);
		updBtn.setVisible(false);
		writePanel.add(updBtn);

		delBtn = new JButton("삭제하기");
		delBtn.setForeground(Color.WHITE);
		delBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		delBtn.setFocusable(false);
		delBtn.setBounds(234, 627, 86, 23);
		delBtn.setVisible(false);
		writePanel.add(delBtn);

		JLabel board_title = new JLabel("게시글 작성");
		board_title.setForeground(Color.WHITE);
		board_title.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		board_title.setBounds(406, 22, 241, 15);
		writePanel.add(board_title);

		writeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeBtnEvent();
			}
		});

		prevBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prevBtnEvent();
			}
		});

		// 글쓰기폼 등록 이벤트
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (title.getText() == " " || title.getText().equals("제목을 입력해주세요..")) {
					JOptionPane.showMessageDialog(writePanel, "제목을 입력해주세요...");
				} else if (content.getText() == " " || content.getText().equals("내용을 입력해주세요..")) {
					JOptionPane.showMessageDialog(writePanel, "내용을 입력해주세요...");
				} else {
					// 게시판 추가
					addBoard();
					// 등록 후 번호 초기화
					countRowBoard();
					// 추가 후 뒤로가기..
					prevBtnEvent();
				}
			}
		});

		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 게시판 삭제
				delBoard();
				// 등록 후 번호 초기화
				countRowBoard();
				// 삭제 후 뒤로가기..
				prevBtnEvent();
			}
		});

		updBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (title.getText() == " " || title.getText().equals("제목을 입력해주세요..")) {
					JOptionPane.showMessageDialog(writePanel, "제목을 입력해주세요...");
				} else if (content.getText() == " " || content.getText().equals("내용을 입력해주세요..")) {
					JOptionPane.showMessageDialog(writePanel, "내용을 입력해주세요...");
				} else {
					// 게시판 수정
					updBoard();
				}
			}
		});

		// 댓글 등록 이벤트
		cmmtBtn.addActionListener((e) -> {
			if (cmmt.getText() == " " || cmmt.getText().equals("댓글을 입력해주세요..")) {
				JOptionPane.showMessageDialog(boardPanel, "댓글을 입력해주세요...");
			} else {
				addCmmt();
				cmmtPanel.removeAll();
				selectCmmt();
				addCmmtPanel();
				cmmtPanel.updateUI();
				cmmt.setText("댓글을 입력해주세요..");
				cmmt.setForeground(Color.GRAY);
			}
		});
	}
}