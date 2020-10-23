package animal;
public class Duck extends Bird{
	String name = "³­ ¿À¸®";
	public void quack(){
		System.out.println("²Ð²Ð");
	}
	public static void main(String[] args){
		Duck d1 = new Duck();
		Duck d2 = new Duck();

		Bird b = new Bird();
		
		
		b = d1; // Å« <-- ÀÛ up casting
		d1 = (Duck)b; // ÀÛ <-- Å« down casting
	}
}
