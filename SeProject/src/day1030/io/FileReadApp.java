package day1030.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReadApp {

	FileInputStream fis;
	FileOutputStream fos;

	public FileReadApp(){
		File file = new File("D:/java_workspace/SeProject/res/data/memo.txt");
		
		try{
			fis = new FileInputStream(file);
			
			int data;

			while(true){
				data=fis.read();
				if(data==-1)break;
				System.out.print((char)data);
			}	
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			//이 영역은 실행부가 try문을 수행하던, catch문을 수행하던 무조건 거쳐서 가야하는 영역이므로,
			//이 영역에 자원을 닫는 코드를 작성해야 한다!!
			//주로 데이터베이스와의 연결끊기, 스트림 연결 끊기
			if(fis != null) {//메모리에 존재할때만..
				try {
					fis.close();//스트림을 닫음 Alt + Shift + Z
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new FileReadApp();
	}
}
