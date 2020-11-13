package day1113.xml;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class URLProgress extends JFrame {
	URLConnection con;
	HttpURLConnection http;
	URL url;
	JTextField t_path;
	JButton bt_down;
	JProgressBar pbar;
	FileOutputStream fos;
	Thread thread;
	int filesize = 0;
	int count = 0;

	public URLProgress() {
		t_path = new JTextField(40);
		bt_down = new JButton("Save");
		bt_down.setPreferredSize(new Dimension(380, 20));
		pbar = new JProgressBar();
		pbar.setPreferredSize(new Dimension(380, 20));

		add(t_path);
		add(bt_down);
		add(pbar);

		bt_down.addActionListener((e) -> {
			imgSize();
			startThread();
		});

		pbar.setValue(count);

		setLayout(new FlowLayout());
		setSize(500, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void imgSize() {
		try {
			url = new URL(t_path.getText());
			con = url.openConnection();
			int size = con.getContentLength();
			if (size < 0) {
				System.out.println("용량 가져오기 실패");
			} else {
				filesize = size;
			}
			con.getInputStream().close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyFile() {
		try {
			pbar.setMinimum(count);
			pbar.setMaximum(filesize);
			url = new URL(t_path.getText());
			con = url.openConnection();
			http = (HttpURLConnection) con;
			http.setRequestMethod("GET");
			InputStream is = http.getInputStream();

			File file = new File("D:/java_workspace/SeProject/res/copy.jpg");

			fos = new FileOutputStream(file);

			int data = -1;
			while (true) {
				data = is.read();
				if (data == -1)
					break;
				fos.write(data);
				count++;
				pbar.setValue(count);
			}
			System.out.println("인터넷상의 파일을 로컬로 저장완료.");
			filesize = 0;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startThread() {
		thread = new Thread() {
			public void run() {
				copyFile();
			}
		};
		thread.start();
	}

	public static void main(String[] args) {
		new URLProgress();
	}
}
