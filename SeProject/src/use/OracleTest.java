/*
�ڹٿ��� �����ͺ��̽��� �����ϴ� ����� ������ Java DataBase Connectivity
��� �ϸ� java.sql ��Ű������ api�� �����ȴ�..
�����ͺ��̽��� ���� ������ ����
1) DB������ �˸´� ����̹��� �ε�(oracle, mysql, mssql.. ���� jar�� �ʿ���)
2) ����
3) ���ϴ� ���� ����
4) ��������(Ư�� ��Ʈ�� �� DB�� ��� �� �ݵ�� ��ü����..)
*/
package day1105.db;
import java.sql.ClassNotFoundException;
public class OracleTest{
	public static void main(){
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");//����ϰ����ϴ� ����̹� Ŭ������ ���!(Ŭ���� �ε�)
		//���� ������� os�÷����� Ŭ���� �н��� ��ϵǾ� �־�� �Ѵ�..
		}catch(ClassNotFoundException e){
			System.out.println("������ ����̹��� ã�� �� �����ϴ�");
		}
	}
}
