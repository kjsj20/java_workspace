package animal;
public class Duck extends Bird{
	String name = "�� ����";
	public void quack(){
		System.out.println("�в�");
	}
	public static void main(String[] args){
		Duck d1 = new Duck();
		Duck d2 = new Duck();

		Bird b = new Bird();
		
		
		b = d1; // ū <-- �� up casting
		d1 = (Duck)b; // �� <-- ū down casting
	}
}
