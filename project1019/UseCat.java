/*
고양이를 정의하시오
클래스명 : Cat
이름 : 묘
나이 : 3
결혼여부 : 미혼
걷는 동작
우는 동작

UseCat 클래스로 Cat의 정보를 화면에 출력해본다
*/
class UseCat{
	public static void main(String[] args){
		Cat c = new Cat();

		System.out.println("고양이 탄생 성공!");
		System.out.println("고양이 색상: " + c.color);
		System.out.println("고양이 이름: " + c.name);
		System.out.println("고양이 나이: " + c.age);
		System.out.println("결혼 여부: " + c.isMarried);
		c.bark();
		c.run();
	}
}


class Cat
{
	String color = "black";
	String name = "묘";
	String isMarried = "미혼";
	int age = 4;

	public void bark() {
		System.out.println("미우~");
	}

	public void run() {
		System.out.println("고양이가 뛰네요!");
	}
}