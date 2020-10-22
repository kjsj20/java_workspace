package use;
import fashion.down.Pants; //사용하고픈 클래스의 위치 등록~!!
class UsePants{
	public static void main(String[] args){
		//현재 클래스와는 물리적으로 떨어져 있는, 즉 다른 패키지에 들어있는
		//클래스 사용해보기!!
		Pants p = new Pants(); //찾지 못함..
		System.out.println(p);
	}
}
