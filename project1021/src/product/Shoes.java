class Shoes
{	String color ;
	int price;
	
	public Shoes(String color, int price){
		this.color = color;
		this.price = price;
	}


	public static void main(String[] args){
		Shoes s = new Shoes("red", 2000); 
		System.out.println(s.color+","+s.price);
	}
}