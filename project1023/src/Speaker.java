package music;
import riding.Wing;

//����Ŀ�� ����� ����̽�����, ���̴�.
//���ΰ� ���� �ڷ����̴�..
//���ΰ� ����ȯ�� �����ϴ�(�ڡڡڡڡ�)
public class Speaker extends AudioDevice implements Wing{
					/* is a					is a */
					/* ����̶� ? �θ��� ��� ����� �ڽ��� �����޴� ����
							�θ�Ŭ������ �ҿ����� �߻�Ŭ������ ���, �̰� �����
							�ƴ϶� �״��̴�!! �� �θ��� �߻����� �޼����� �ϼ���
							�ڽĿ��� ���ѱ� ���̴�..
							���� �ڽ� Ŭ����������, �θ��� �ҿ����� �޼��带
							������ �������ؾ�, �� Ŭ������ ������ Ŭ������ �ȴ�.
							�̷� ���� ������ ������ ���������� �Ѵ�...
					
					*/
	boolean ooper; //�������� ����
	String color = "red";
	
	public void sound(){
		
	}

	public void setVolume(){//�극�̽��� �����ϱ⸸ �ص�, �����Ǹ� �ϼ��Ѱ���
		System.out.println("������ �����ؿ�");
	}

	public void playMP3(){
		System.out.println("Mp3 ���� ����!");
	}

	public void fly(){
		System.out.println("ġŲ�� ���� ������~");
	}
}
