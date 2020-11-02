package day1102.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoCopy {
	FileInputStream fis;
	FileOutputStream fos;
	
	FileReader reader; //파일을 대상으로 한 문자기반의 입력스트림
	FileWriter writer; //파일을 대상으로 한 문자기반의 출력스트림
	
	String path = "D:/java_workspace/SeProject/res/data/test.txt";
	String path2 = "D:/java_workspace/SeProject/res/data/test2.txt";
	public MemoCopy() {
		try {
			//fis = new FileInputStream(path);
			//fos = new FileOutputStream(path2);
			
			reader = new FileReader(path);
			writer = new FileWriter(path2);
			int data;
			while(true) {
				data = reader.read();
				if(data==-1)break;
				writer.write(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MemoCopy();
	}

}
