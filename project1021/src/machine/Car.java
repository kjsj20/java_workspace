class Car
{
	String color = "red";
	String brend = "benz";
	int price = 50000000;
	String kinds = "sedan";
	Wheel wheel; 
	
	//클래스명과 동일한 이름의 메서드를 가리켜 생성자라 하고,
	//생성자는 이름에서도 알 수 있듯이, 객체의 생성 타임에 무언가 초기화 작업이 있을때,
	//작업을 수행하는 용도의 메서드이다..
	public Car(){
		this.wheel = new Wheel();
	}

	public static void main(String[] args){
		Car c = new Car();
		//c.wheel = new Wheel();
		System.out.println(c.brend);
		System.out.println(c.wheel.brend);
	}
} 
