public class practice{
	public static void main(String[] args){

		System.out.println("Hello world");
		System.out.println(args.length);

		if(args.length < 3){
			System.out.println("3���� �׸� �̻� �Է����ּ���...");
			return;
		}

		for(int i=0; i < 3; i++){
			System.out.print(args[i]);
		}
	}
}