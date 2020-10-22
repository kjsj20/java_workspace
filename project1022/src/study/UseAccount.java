package use;
//사용하려는 클래스의 위치 알려줍니다.
import bank.Account;
class UseAccount{
	public static void main(String[] args){
		Account acc = new Account();//계좌 클래스 생성(public라 여기까지는 무조건 가능함)

		//bank 은행명은 public 으로 선언되어 있으므로, 무조건 접근이 가능하다.
		System.out.println(acc.bank);
		
		//customer는 protected로 선언되어 있으므로 상속관계에 있거나, 같은 패키지
		// 경우에만 접근이 가능합니다.. 현재 UseAccount는 Account와 상속관계가 없고
		//서로 다른 패키지이므로, 데이터 접근이 불가능할 겁니다. 테스트해보세요
		//System.out.println(acc.customer);

		//계좌번호 num변수는 , 개발자가 아무것도 명시하지 않았는데요, 이러한 접근제한자를 default
		//접근제한자라 하고, 여러분들이 default 라는 키워드를 명시해서는 안됩니다.. 그냥냅두셔야 합니다.
		//default 접근 제한자는 같은 패키지에 있는 클래스 끼리만 접근을 허용해주므로, protected보다
		//한단계더 까다롭습니다. (즉 상속관계에 있어도, 같은 패키지가 아니라면 접근 불가임..)

		//System.out.pritnln(acc.num);

		//balance는 가장 강력한 접근제한자인 private가 적용되어 있으므로, Account 클래스 스스로
		//만 접근이 가능합니다.. 따라서 우리는 Account 자신이 아니라서 , 절대 사용못합니다...
		//완전 폐쇄적이죠...

		//System.out.println(acc.balance);

		//공부할때는 public는 그냥 빼고 공부하자, 퍼블릭 자체는 보안이 없는거닌까...
		//한가지 궁금해집니다..private 는 그럼 아무도 못쓰는데 왜 있는걸까요?

		//acc.balance = 10; // 이 방법은 직접 접근이므로 불가능
		acc.setBalance(10); //10원으로 수정 , 이 방법은 메서드를 통해 접근이 가능
		//이제 잔고가 수정이 됐습니다..
		//그런데 수정된 잔고를 어떻게 확인하나요? 변수에 접근을 막았으니...
		//잔고 수정이 아니라, 잔고에 접근하려는것 또한 메서드를 제공해주셔야 합니다.		
		//마치 리모콘에서 다음 채널 전환 변경 버튼만 있는게 아니라 채널 변경도 가능하게하는겁니다.
		System.out.println(acc.getBalance());
	}
}
