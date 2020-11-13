package day1113.xml.down;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import common.file.FileManager;

public class DownLoader extends JFrame {
	JButton bt_down;
	JProgressBar bar;
	MovieHandler movieHandler;
	Thread parsingThread;

	public DownLoader() {
		bt_down = new JButton("시작");
		bar = new JProgressBar();
		bar.setForeground(Color.CYAN);
		bar.setBackground(Color.BLACK);

		bar.setFont(new Font("Verdana", Font.BOLD, 25));
		bar.setStringPainted(true);
		bar.setValue(35);

		// 스타일
		bar.setPreferredSize(new Dimension(580, 45));

		setLayout(new FlowLayout());
		add(bt_down);
		add(bar);

		parsingThread = new Thread() {
			public void run() {
				parseData();
				//총 몇건이 존재하는지 출력
				System.out.println(movieHandler.movieList.size());
				for(int i=0; i<movieHandler.movieList.size(); i++) {
					Movie movie = movieHandler.movieList.get(i);
					download(movie.getUrl());
				}
			}
		};

		// 다운로드 버튼과 리스너연결
		bt_down.addActionListener((e) -> {
			parsingThread.start();
		});

		setSize(600, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public void parseData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser(); // 파서객체생성
			URL url = this.getClass().getClassLoader().getResource("res/marvel.xml");
			File file = new File(url.toURI());
			saxParser.parse(file, movieHandler = new MovieHandler()); // 파싱시작.
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 인터넷상의 자원과 연결한 후, 스트림으로 데이터를 읽어와 로컬 하드 경로에 저장하기!!
	public void download(String path) {
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(path);
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;

			http.setRequestMethod("GET");
			is = http.getInputStream();
			long time = System.currentTimeMillis();
			String ext = FileManager.getExtend(path);
			String filename = time+"."+ext;
			fos = new FileOutputStream("D:/java_workspace/SeProject/res/download/" + filename);
			int data = -1;
			while (true) {
				data = is.read();
				if(data == -1)break;
				fos.write(data);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new DownLoader();
	}
}