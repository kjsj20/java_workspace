class InitialBlock{
	int sum = 0;
	//��������ȿ� {����ȭ}���֤����� ���� �ǹ̴�?
	{	
		for(int i=1; i<=10; i++){
			sum+=i;
		}
		//�� Ŭ������ �ν��Ͻ��� �����ɶ����� �� ������ ȣ���ϰԵ�..
		//�ν��Ͻ� �ʱ�ȭ ���̶� �Ѵ�..
		System.out.println("ȣ��?"+sum);
	}
	//static �ʱ�ȭ ��
	//main() �޼��忡 ���� ���� ������, ����Ǵ� �ʱ�ȭ �� 
	static{
		System.out.println("�����ϱ��� �ʱ�ȭ �ϰڽ��ϴ�.");
		System.out.println("B");
	}
	public static void main(String[] args){
		System.out.println("A");
		//int a = 3;
		/*
		{
			//�׳� ��ȭ ���ѳ��� ����. Ȥ���� �� �ȿ� ������ �����ϸ� �ش����������
			// ��ȣ�ϴ�..
			int b = 5;
		}*/
		//System.out.println(b);
		new InitialBlock();
		new InitialBlock();
		new InitialBlock();
	}
}