public class Hero {
	int hp=10;
	boolean fly=false;
	String name="�ް���";
	Bullet bullet;
	
	public void setHp(int hp) { //hp ���� �����ϰ� �ʹ� 
		this.hp = hp; 
	}
	public void setFly(boolean fly) {//fly ���� �����ϰ� �ʹ�
		this.fly = fly;
	}
	public void setName(String name) {//name ���� �����ϰ� �ʹ�		
		this.name = name;
	}
	public void fire(Bullet bullet){//bullet �� �ٸ� ����� �����ϰ� �ʹ�
		this.bullet = bullet;
	}

	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.setHp(500);
		hero.setFly(true);
		hero.setName("��Ʈ��");
		hero.fire(new Bullet());		
	}	
}