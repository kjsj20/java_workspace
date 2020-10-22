class  SportsCar{
   String color="None Color";
   
   public SportsCar(String color){
		this.color = "black";
   }
	
	public String setColor(String color){
		return this.color = color;
	}
   public static void main(String[] args){
		SportsCar s = new SportsCar("black");
		s.setColor("red");
		System.out.println(s.color);
   }
}