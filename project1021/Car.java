class Car
{
	String color = "red";
	String brend = "benz";
	int price = 50000000;
	String kinds = "sedan";
	Wheel wheel; 
	
	//Ŭ������� ������ �̸��� �޼��带 ������ �����ڶ� �ϰ�,
	//�����ڴ� �̸������� �� �� �ֵ���, ��ü�� ���� Ÿ�ӿ� ���� �ʱ�ȭ �۾��� ������,
	//�۾��� �����ϴ� �뵵�� �޼����̴�..
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
