package string;
class StringTest2{
	public static void main(String[] args){
		/*String�� �Һ�(immutable)�̴�.*/
		String s1 = "korea";
		System.out.println(s1);
		s1 = s1 + " Fighting..";
		System.out.println(s1);

		/*String str = "";
		for(int i=1; i<=100; i++){
			str += "�ٳѱ� " +i+ " ȸ\n";
		}
		System.out.println(str);*/

		//�ذ�å
		//���������� ���ڿ� ó�� ��ü
		//StringBuilder, StringBuffer
		StringBuilder sb = new StringBuilder();
		sb.append("");
		for(int i=1; i<=100; i++){
			sb.append("�ٳѱ� " +i+ " ȸ\n");
		}
		System.out.println(sb.toString());
	}
}
