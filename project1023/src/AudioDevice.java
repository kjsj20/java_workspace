package music;

abstract public class AudioDevice{
	//���� ���� ���
	//static�� �Բ� �����ڷ� �Ҹ��� �� �Ѱ���..
	//abstract : �߻�����, �޼��忡 ���� ��� �극�̽� ���� �ҿ����� �޼���
	//�� �ǹ�
	//�ҿ����� �޼��带 �� 1���� �����ϰ� �ִٸ�, �� Ŭ������ �ҿ�����
	//Ŭ�����̸�, �̷��� Ŭ������ �߻�Ŭ������ �Ѵ�!!
	abstract public void setVolume(); //�߻�޼���� �Ѵ�..
	abstract public void playMP3(); //�߻�޼���� ���� (�ڽĵ��� ���������Ϸ���...)
	//MP���� ���
}