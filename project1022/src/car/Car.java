package car;
public class Car{
	public String brand;
	

	public Car(String brand){
		//System.out.println("자식의 super()호출에 의해 난 호출됩니다.");
		this.brand = brand;
	}

	public void move(){
		System.out.println("자동차가 갑니다");
	}
}
