/*
 * 다형성에 대해 다시한번 공부해보자!!
 * */

package day1029.poly;

public class Bird {
	String name = "난새";
	String local = "black";
	public void fly() {
		System.out.println("새가 날아요");
	}
	
	public static void main(String[] args) {
		//새들을 대상으로 다형성 공부하기!!
		Bird b1 = new Bird();
		Bird b2 = new Duck(); // b2는 Bird클래스 변수, 메서드 접근
											// 다형성의 특징으로서는 자식메서드를 접근
		Bird b3 = new Sparrow();
		
		b1.fly(); //비록 새지만, 행동이 다양하네 ~~~
		
		Duck d = new Duck(); // 부모꺼 내꺼
	}
}
