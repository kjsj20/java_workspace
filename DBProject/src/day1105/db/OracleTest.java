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
//import java.sql.ClassNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class OracleTest{
	public static void main(String[] args){
	Connection con = null; //�޼����� ���������� �ݵ�� �ʱ�ȭ�ؾ� ��(��� ����ó�� �ڵ����� ���ִ��Ͼ���!!)
	PreparedStatement pstmt = null;

	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");//����ϰ����ϴ� ����̹� Ŭ������ ���!(Ŭ���� �ε�)
		//���� ������� os�÷����� Ŭ���� �н��� ��ϵǾ� �־�� �Ѵ�..
		System.out.println("����̺� �ε� ����");

		//DB������ url, id, password
		String url="jdbc:oracle:thin:@localhost:1521:ORCL";
		String user = "user1104";
		String password = "user1104";
		
		//���� �õ���, ������ �����Ǹ� Connection �̶�� �������̽��� �ν��Ͻ��� ��ȯ����!!
		//Connection���� ������ ���� ������ ����ִ�!!
		//���� ���� ���δ� Connection ��ü�� null ���η� �Ǵ��Ѵ�!
			con = DriverManager.getConnection(url, user, password); //���ӽõ�!!
		if(con==null){
			System.out.println("���ӽ���");
		}else{
			System.out.println("���Ӽ���");

			//������ ���������Ƿ�, �������� ������ �� �ִ�..
			//�������� �����ϴ� ��ü�� PreparedStatement �������̽� �̴�.
			String sql = "insert into member(member_id, name, age, phone)";
			sql += " values(seq_member.nextval, '������', '16', '01029408805')";

			//������ �����Ǿ��, �������� ������ �� �����Ƿ�, Connection ��ü�� ���� �ν��Ͻ��� ���� �Ѵ�!!
			pstmt = con.prepareStatement(sql); //���������� �غ�!!

			//���� ���� �� �������θ� �Ǵ��ϱ� ���ؼ��� executeUpdate() �޼����� ��ȯ���� �̿��Ѵ�
			//��ȯ���� int���̸�, ������ ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ�Ѵ�..
			//���� insert�� ��� ��ȯ���� 1, update, delete�� ����� ���� �޾Ҵ��� �˼��� ����.. ���ǿ� ���� Ʋ��
			//��ġ�� insert, update, delete�� ��� 0�̶�� ���з� �����ϸ� �ȴ�!
			int result = pstmt.executeUpdate(); //DML(insert, update, delete) ��������� �� �޼��� �����
			if(result != 1){
				System.out.println("�Է½���");
			} else {
				System.out.println("�Է¼���");
			} 
		}
		}catch(ClassNotFoundException e){
			System.out.println("������ ����̹��� ã�� �� �����ϴ�");
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
				//db ������ ���Ǿ��� ��� ��ü�� �ݾƾ� �Ѵ�..
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}	
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
				
		}
	}
}
