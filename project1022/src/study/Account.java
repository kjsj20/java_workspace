package bank; 
public class Account{
	public String bank = "기업은행";//은행명
	protected String customer;
	String num = "022-388-85465";
	private int balance = 100000;

	//private으로 선언된 변수는 절대 아무도 외부에서 접근할 수 없으므로
	//변수에 접근하려면, 메서드를 이용해야 한다..
	//아래의 매서드는 public 이므로, 모든~~객체가 접근할 수 있습니다.
	public void setBalance(int balance){
		this.balance = balance;
	}

	//잔고 확인 메서드 정의 
	//이와 같이 private으로 선언된 변수의 값을 리턴하는 메서드를 가리켜 getter 라 합니다.
	//그리고 위와같이 setBalance 처럼 private 변수의 값을 변경하는 메서드를 가리켜 setter라 합니다.
	//게터와 세터는 아주 유명한 메서드 정의 기법이에요...
	public int getBalance(){
		return balance;
	}
}
