package use;
import car.Truck;
import car.Bus;
import car.Taxi;

class UseCar{
	public static void main(String[] args){
		Taxi t = new Taxi(); //�ý��� �ν��Ͻ� ����, ���ͺ��⿡�� �ýø� ������ �� ������.
							 //�ýú��� �ռ� �θ��� Car�� �����ؾ�, �ýõ� ������ �� �ֱ⶧����
							 //js ������ ������ �״�� ����Ǿ� Car�ν��Ͻ��� �����ȴ�.
		System.out.println(t.brand);

		t.pass();

		t.move();
	}
}
