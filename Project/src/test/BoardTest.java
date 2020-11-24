package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import project.utill.DBManager;
import java.awt.Cursor;

public class BoardTest {

	private JFrame frame;
	private JTextField title;
	JPanel boardPanel;
	JPanel boardPenel_north;
	JPanel boardPanel_center;
	JPanel writePanel;
	JTextArea content;
	public static final int GRIDCOUNT = 10; // 그리드 레이아웃의 층 갯수. 게사판 패널이 들어갈 갯수를 정한다.
	public static int PAGECOUNT = 0;
	int board_id;
	int start_pageNum = 1, end_pageNum = 10;

	Connection con;
	DBManager dbCon;
	ArrayList<boardVO> boardList = new ArrayList<boardVO>();
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();

	ArrayList<JLabel> labelListNum = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListTitle = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListUser = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListTime = new ArrayList<JLabel>();
	ArrayList<JLabel> labelListHit = new ArrayList<JLabel>();
	
	ArrayList<JLabel> labelPageIndex = new ArrayList<JLabel>();

	JButton registBtn, prevBtn, updBtn, delBtn;

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
		//뒤로가기에 재조회 기능 추가..
		selectBoard();
	};

	public void compoInit() {
		title.setText("제목을 입력해주세요..");
		title.setForeground(Color.GRAY);
		content.setText("내용을 입력해주세요..");
		content.setForeground(Color.GRAY);
	}
	
	//board의 갯수를 조회한다.
	public void countRowBoard() {
		String sql = "select board_id from board";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last();
			
			PAGECOUNT = (rs.getRow() + (GRIDCOUNT - 1)) / GRIDCOUNT;
			rs.beforeFirst();
			
			addPage();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.close(pstmt, rs);
		}
	}
	
	//10개가 넘어갈때마다 페이지를 생성한다.
	public void addPage() {
		int x = 23;
		for(int i = 0; i < PAGECOUNT; i++) {
			int num = i;
			labelPageIndex.add(new JLabel(Integer.toString(i+1))); 
			labelPageIndex.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelPageIndex.get(i).setForeground(Color.WHITE);
			labelPageIndex.get(i).setBounds(x, 7, 16, 15);
			//page수가 많아지면 x값을 증가시켜 이동시키기 위해..
			x += 17;
			boardPenel_north.add(labelPageIndex.get(i));	
			labelPageIndex.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			labelPageIndex.get(i).addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					end_pageNum = (num + 1) * GRIDCOUNT;
					start_pageNum = end_pageNum-9;
					selectBoard();
					boardPanel_center_content.updateUI();
				}
			});
		}
	}
	
	// board를 조회한다 !
	public void selectBoard() {

		// 게시판 조회된 목록을 다 지운 뒤 조회 한다.
		boardList.removeAll(boardList);

		String sql = "select * from (SELECT rownum num,board_id, board_title, BOARD_CONTENT ";
				sql += ", BOARD_USERNAME, BOARD_IP , BOARD_WTIME , BOARD_STATUS , BOARD_COMMENT_COUNT";
				sql += ", BOARD_HIT FROM board WHERE BOARD_STATUS  = 0 ORDER BY board_id) WHERE num >= ? AND num <= ?";
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
			};

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
			//해당 num값을 받아놓는다.. 이벤트 처리를 위하여..
			int num = i;
			
			//패널 생성
			panelList.add(new JPanel());
			panelList.get(i).setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			panelList.get(i).setBackground(Color.DARK_GRAY);
			panelList.get(i).setLayout(null);
			boardPanel_center_content.add(panelList.get(i));
			panelList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			//글 번호 라벨
//			labelListNum.add(new JLabel(Integer.toString(boardList.get(i).getBoard_id())));
			labelListNum.add(new JLabel(Integer.toString(boardList.size() - i)));
			labelListNum.get(i).setForeground(Color.WHITE);
			labelListNum.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListNum.get(i).setBounds(38, 20, 57, 15);
			panelList.get(i).add(labelListNum.get(i));
			
			
			//글 제목 라벨
			labelListTitle.add(new JLabel(boardList.get(i).getBoard_title()));
			labelListTitle.get(i).setForeground(Color.WHITE);
			labelListTitle.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListTitle.get(i).setBounds(135, 20, 400, 15);
			panelList.get(i).add(labelListTitle.get(i));
			
			//글 작성자 라벨
			labelListUser.add(new JLabel(boardList.get(i).getBoard_username()));
			labelListUser.get(i).setForeground(Color.WHITE);
			labelListUser.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListUser.get(i).setBounds(540, 20, 57, 15);
			panelList.get(i).add(labelListUser.get(i));
			
			//글 작성시간 라벨
			labelListTime.add(new JLabel(boardList.get(i).getBoard_wtime()));
			labelListTime.get(i).setForeground(Color.WHITE);
			labelListTime.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListTime.get(i).setBounds(620, 20, 150, 15);
			panelList.get(i).add(labelListTime.get(i));
			
			//글 조회수 라벨
			labelListHit.add(new JLabel(Integer.toString(boardList.get(i).getBoard_hit())));
			labelListHit.get(i).setForeground(Color.WHITE);
			labelListHit.get(i).setFont(new Font("HY견고딕", Font.PLAIN, 12));
			labelListHit.get(i).setBounds(827, 20, 57, 15);
			panelList.get(i).add(labelListHit.get(i));
			
			//패널 클릭 이벤트
			panelList.get(i).addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					panelList.get(num).setBackground(Color.black);
				}

				public void mouseExited(MouseEvent e) {
					panelList.get(num).setBackground(Color.DARK_GRAY);
				}

				public void mouseReleased(MouseEvent e) {
					writeBtnEvent();
					//수정, 삭제등에 사용할 board_id를 담아둔다!
					board_id = boardList.get(num).getBoard_id();
					//조회수 올리기 메서드.
					updHit();
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
		content.setBorder(new LineBorder(new Color(0, 0, 0)));
		content.setText("내용을 입력해주세요..");
		content.setForeground(Color.GRAY);
		content.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		content.setBounds(112, 88, 686, 511);
		writePanel.add(content);

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
		registBtn.setBounds(468, 627, 86, 23);
		writePanel.add(registBtn);

		prevBtn = new JButton("이전으로");
		prevBtn.setForeground(Color.WHITE);
		prevBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		prevBtn.setBounds(351, 627, 86, 23);
		writePanel.add(prevBtn);

		updBtn = new JButton("수정하기");
		updBtn.setForeground(Color.WHITE);
		updBtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		updBtn.setBounds(468, 627, 86, 23);
//		updBtn.setBounds(586, 627, 86, 23);
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
					// 추가 후 뒤로가기..
					prevBtnEvent();
				}
			}
		});

		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 게시판 삭제
				delBoard();
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
	}
}