package board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class BoardDesign extends BoardMain{
	JTextField title;
	private JPanel boardPanel_center_header;
	JPanel boardPanel_center_content;
	private JLabel contentNum;
	private JLabel contentTitle;
	private JLabel writerName;
	private JLabel writerTime;
	private JLabel writerHit;
	JTextArea content, cmmt;
	JPanel boardPanel;
	JPanel boardPenel_north;
	JPanel boardPanel_center;
	JPanel writePanel;
	JPanel cmmtPanel;
	JLabel labelPagePrev, labelPageNext;
	JScrollPane scrollWrite, scrollCmmt, scrollCmmtPanel;
	public static final int GRIDCOUNT = 10; // 그리드 레이아웃의 층 갯수. 게사판 패널이 들어갈 갯수를 정한다.
	public static int PAGECOUNT = 0, ALLCOUNT = 0, PAGECOUNTSPLIT = 0, PAGECOUNTSPLITNUM = 10;
	int pageForINum = 0;
	int board_id, pagenum = 0;
	int start_pageNum = 1, end_pageNum = 10;

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

	public BoardDesign() {
		boardPanel = new JPanel();
		containerPanel.add(boardPanel);
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
		boardEvent.selectBoard();

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

		boardEvent.createCmmtScrollPanel();

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
					boardEvent.addBoard();
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
				boardEvent.delBoard();
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
					boardEvent.updBoard();
				}
			}
		});

		// 댓글 등록 이벤트
		cmmtBtn.addActionListener((e) -> {
			if (cmmt.getText() == " " || cmmt.getText().equals("댓글을 입력해주세요..")) {
				JOptionPane.showMessageDialog(boardPanel, "댓글을 입력해주세요...");
			} else {
				boardEvent.addCmmt();
				cmmtPanel.removeAll();
				boardEvent.selectCmmt();
				boardEvent.addCmmtPanel();
				cmmtPanel.updateUI();
				cmmt.setText("댓글을 입력해주세요..");
				cmmt.setForeground(Color.GRAY);
			}
		});
		
		
		containerPanel.updateUI();
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
		boardEvent.selectBoard();
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
					boardEvent.pageSelect();
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
						boardEvent.delPageLabelPrev();
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
					boardEvent.pageSelect();
				}
			}
		});

		labelPageNext.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				// 최대 페이지 수를 넘기면 안넘어 가도록 조정
				if (PAGECOUNT - 1 > pagenum) {
					if (pagenum == labelPageIndex.size() - 1) {
						pageForINum = (pageForINum + 10);
						boardEvent.delPageLabelNext();
						PAGECOUNTSPLITNUM += 10;
						if (PAGECOUNTSPLITNUM > PAGECOUNT) {
							PAGECOUNTSPLITNUM = PAGECOUNT;
						}
						addPage();
					}
					labelPageIndex.get(pagenum).setForeground(Color.white);
					pagenum = pagenum + 1;
					labelPageIndex.get(pagenum).setForeground(Color.black);
					boardEvent.pageSelect();
				}
			}
		});
	}
}
