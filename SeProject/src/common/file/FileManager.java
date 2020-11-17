package common.file;

public class FileManager {
	//파일명 구하기 : 매개변수로 파일의 경로를 넘겨받아 파일명만 추출한다.
	public static String getFilename(String path) {
		int lastIndex = path.lastIndexOf("/"); //마지막에 위치한 /의 인덱스 구하기
		return path.substring(lastIndex + 1, path.length());
	}
	
	public static String getExtend(String filename) {
		String[] str = filename.split("\\.");
		return str[1];// 두번째 칸이 확장자임 
	}
	
	public static String getExtend2(String filename) {
		int lastIndex = filename.lastIndexOf(".");
		//마지막점 다음 문자부터 가져와야 하므로 + 1을 더한다!!
		
		return filename.substring(lastIndex+1, filename.length());
	}
	
	public static void main(String[] args) {
		String filename = getFilename("https://www.cartier.co.kr/content/dam/cartier_dam/Communication%20assets/HP/2020/10-oct-1/KR/MP1_hp_oct_1_ballon_bleu_KR.jpg.scale.480.380.high.jpg");
	}
}
