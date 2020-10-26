public class practice{
	public static void main(String[] args){

		System.out.println("Hello world");
		System.out.println(args.length);

		if(args.length < 3){
			System.out.println("3가지 항목 이상 입력해주세요...");
			return;
		}

		for(int i=0; i < 3; i++){
			System.out.print(args[i]);
		}
	}
}