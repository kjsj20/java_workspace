package test1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Test1_1 {

	List<DataVO> infoList;
	List<String> valList;
	HashMap<String, String> codeMap;
	public Test1_1() {
		String src = "D:\\첨부1. Hoa_data_20 11.txt"; // 읽기

		try {
			File file = new File(src); // 읽기

			OutputStream output = new FileOutputStream("D:\\dd.txt"); // 빼기

			// System.out.println(file.getName()); // 읽은 파일명

			FileReader filereader = new FileReader(file); // 읽기

			BufferedReader bufReader = new BufferedReader(filereader); // 읽기

			String line = "";
			String out = "";

			List<String> list = new ArrayList();

			while ((line = bufReader.readLine()) != null) {
				// System.out.println(line);

				list.add(line);
				// 리스트에 line 담
			}

			infoList = new ArrayList<DataVO>();

			String Time = "";
			String TimeP = "";

			for (int i = 0; i < list.size(); i++) { // vo list에 한값씩 담기

				DataVO vo = new DataVO();
				String str = (String) list.get(i);

				Time = str.substring(0, 8);

				String[] TimeArr = Time.split(":");

				for (int j = 0; j < TimeArr.length; j++) {
					TimeP += TimeArr[j];
				}
				vo.setCode(str);
				vo.setTime(TimeP);
				vo.setNameCode(str.substring(15, 21));
				vo.setSerial(str.substring(21, 26));
				vo.setPrice(str.substring(26, 35));
				vo.setCount(str.substring(35, 47));

				infoList.add(vo);
				TimeP = "";

			}

			Collections.sort(infoList, new ListComparator());
			codeMap = new HashMap<String, String>();
			for (int i = 0; i < infoList.size(); i++) {
//				System.out.println(infoList.get(i).getCode());
//				if(infoList.get(i).getNameCode() == "771002") {
//				valList.add(infoList.get(i).getTime()+infoList.get(i).getPrice());
				codeMap.put(infoList.get(i).getNameCode(), infoList.get(i).getPrice());
//				System.out.println(infoList.get(i).getNameCode());
//					System.out.print("코드 :" + infoList.get(i).getNameCode() + " ");
//					System.out.print("시간 :" + infoList.get(i).getTime() + " ");
//					System.out.print("일련번호 :" + infoList.get(i).getSerial() + " ");
//					System.out.println("가격 :" + infoList.get(i).getPrice() + " ");
//					System.out.println("수량 :" + infoList.get(i).getCount());
//				}
//				splitList.add(infoList.get(i).getTime());
				System.out.println(codeMap.get(infoList.get(i).getNameCode()));
			}
			System.out.println(codeMap.size());
			// String nameCode = "infoList.get(0).getNameCode()";
			//
			// for(int i=1; i < infoList.size(); i++) {
			//
			// if(nameCode.equals(infoList.get(i).getNameCode())) {
			// System.out.println();
			// }
			// }
			byte[] by = out.getBytes();
			output.write(by);
			// .readLine()은 끝에 개행문자를 읽지 않는다.
			bufReader.close();
			
//			new splitCode();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Test1_1();
	}

}

class ListComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		String nameCode1 = ((DataVO) o1).getNameCode();
		String nameCode2 = ((DataVO) o2).getNameCode();
		return nameCode1.compareTo(nameCode2);
	}
}

class DataVO {
	private String code;
	private String time;
	private String nameCode;
	private String serial;
	private String price;
	private String Count;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNameCode() {
		return nameCode;
	}
	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}


}
