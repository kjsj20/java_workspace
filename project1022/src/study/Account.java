package bank; 
public class Account{
	public String bank = "�������";//�����
	protected String customer;
	String num = "022-388-85465";
	private int balance = 100000;

	//private���� ����� ������ ���� �ƹ��� �ܺο��� ������ �� �����Ƿ�
	//������ �����Ϸ���, �޼��带 �̿��ؾ� �Ѵ�..
	//�Ʒ��� �ż���� public �̹Ƿ�, ���~~��ü�� ������ �� �ֽ��ϴ�.
	public void setBalance(int balance){
		this.balance = balance;
	}

	//�ܰ� Ȯ�� �޼��� ���� 
	//�̿� ���� private���� ����� ������ ���� �����ϴ� �޼��带 ������ getter �� �մϴ�.
	//�׸��� ���Ͱ��� setBalance ó�� private ������ ���� �����ϴ� �޼��带 ������ setter�� �մϴ�.
	//���Ϳ� ���ʹ� ���� ������ �޼��� ���� ����̿���...
	public int getBalance(){
		return balance;
	}
}
