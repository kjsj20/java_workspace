/*
�ڹٵ� �ٸ� ���� ���������� �迭�� �����Ѵ�...
��, �ڹٴ� ���������͹���� �ƴ� ������ ����� �������α׷����μ�,
�迭 ����� �ݵ�� �ڷ����� �����ؾ� �Ѵ�...
*/
class ArrayTest1{
	public static void main(String[] args){
		//int�� �迭 ����
		//var arr = new Array();
		//�ڹٽ�ũ��Ʈ�ʹ� �޸�, �ڹٿ����� �迭�� ���������� ���� ���� �� ����
		//�ݵ�� �� ������ ������ Ÿ�Ը� ���� �� �ִ�...
		int[] arr = new int[3]; //�ݵ�� �ڷ���(must) �� ũ�� ����ؾ� ��!!
		arr[0] = 7;
		arr[1] = 23;
		arr[2] = 9;
		
		for(int i=0; i<arr.length; i++){
			System.out.println(arr[i]);
		}
		
		//var arr = ["���", "�ٳ���", "����"];
		String[] fruit = {"���", "�ٳ���", "����"};
		System.out.println("������ ����" + fruit.length);
		for(int i=0; i<fruit.length; i++){
			System.out.println(fruit[i]);
		}

	}
}