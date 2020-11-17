/*
 * 산 1개를 담게될 클래스 정의
 * */
package day1116.pubapi;

public class Mountain {
	// 멤버변수로는 오픈 데이터 포털의 산정보에 있는 모든 데이터를 다 넣기엔 너무 많으닌까
	// 원하는것 골라서 담자.
	private int mntnid; //산코드
	private String mntnnm; //산이름 담게될 변수
	private String mntninfopoflc; //산정보소재지
	private int mntninfohght; //산높이
	public int getMntnid() {
		return mntnid;
	}
	public void setMntnid(int mntnid) {
		this.mntnid = mntnid;
	}
	public String getMntnnm() {
		return mntnnm;
	}
	public void setMntnnm(String mntnnm) {
		this.mntnnm = mntnnm;
	}
	public String getMntninfopoflc() {
		return mntninfopoflc;
	}
	public void setMntninfopoflc(String mntninfopoflc) {
		this.mntninfopoflc = mntninfopoflc;
	}
	public int getMntninfohght() {
		return mntninfohght;
	}
	public void setMntninfohght(int mntninfohght) {
		this.mntninfohght = mntninfohght;
	}
}
