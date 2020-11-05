/*
자바에서 데이터베이스를 연동하는 기술을 가리켜 Java DataBase Connectivity
라고 하며 java.sql 패키지에서 api가 지원된다..
데이터베이스를 연동 업무의 순서
1) DB기종에 알맞는 드라이버를 로드(oracle, mysql, mssql.. 각각 jar가 필요함)
2) 접속
3) 원하는 쿼리 수행
4) 접속해제(특히 스트림 및 DB는 사용 후 반드시 헤체하자..)
*/
package day1105.db;
import java.sql.ClassNotFoundException;
public class OracleTest{
	public static void main(){
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");//사용하고자하는 드라이버 클래스를 등록!(클래스 로드)
		//현재 사용중인 os플랫폼의 클래스 패스에 등록되어 있어야 한다..
		}catch(ClassNotFoundException e){
			System.out.println("지정한 드라이버를 찾을 수 없습니다");
		}
	}
}
