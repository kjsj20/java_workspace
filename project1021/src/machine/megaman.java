public class Hero {
	int hp=10;
	boolean fly=false;
	String name="메가맨";
	Bullet bullet;
	
	public void setHp(int hp) { //hp 값을 변경하고 싶다 
		this.hp = hp; 
	}
	public void setFly(boolean fly) {//fly 값을 변경하고 싶다
		this.fly = fly;
	}
	public void setName(String name) {//name 값을 변경하고 싶다		
		this.name = name;
	}
	public void fire(Bullet bullet){//bullet 을 다른 무기로 변경하고 싶다
		this.bullet = bullet;
	}

	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.setHp(500);
		hero.setFly(true);
		hero.setName("앤트맨");
		hero.fire(new Bullet());		
	}	
}