package use;
import car.Truck;
import car.Bus;
import car.Taxi;

class UseCar{
	public static void main(String[] args){
		Taxi t = new Taxi(); //택시의 인스턴스 생성, 얼핏보기에는 택시만 생성될 것 같지만.
							 //택시보다 앞선 부모인 Car가 존재해야, 택시도 존재할 수 있기때문에
							 //js 시절의 원리가 그대로 적용되어 Car인스턴스도 생성된다.
		System.out.println(t.brand);

		t.pass();

		t.move();
	}
}
