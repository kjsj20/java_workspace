/*
����̸� �����Ͻÿ�
Ŭ������ : Cat
�̸� : ��
���� : 3
��ȥ���� : ��ȥ
�ȴ� ����
��� ����

UseCat Ŭ������ Cat�� ������ ȭ�鿡 ����غ���
*/
class UseCat{
	public static void main(String[] args){
		Cat c = new Cat();

		System.out.println("����� ź�� ����!");
		System.out.println("����� ����: " + c.color);
		System.out.println("����� �̸�: " + c.name);
		System.out.println("����� ����: " + c.age);
		System.out.println("��ȥ ����: " + c.isMarried);
		c.bark();
		c.run();
	}
}


class Cat
{
	String color = "black";
	String name = "��";
	String isMarried = "��ȥ";
	int age = 4;

	public void bark() {
		System.out.println("�̿�~");
	}

	public void run() {
		System.out.println("����̰� �ٳ׿�!");
	}
}