class HighHeel{
	String color = "None Color";

	public HighHeel(String color){
		this.color = color;
	}

	public static void main(String[] args){
		HighHeel h = new HighHeel("red");
		System.out.println(h.color);
	}
}