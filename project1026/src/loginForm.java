package gui;
import java.awt.*; //나쁜짓이지만 편하다...
class loginForm extends Frame{
	/*has 관계는 멤버변수가 객체형일때를 의미한다!!*/
	Label la_id;
	Label la_pass;
	TextField t_id;
	TextField t_pass;
	Button bt_login;
	Button bt_regist;
	Panel p_center; // 센터에 그리드를 적용할 패널
	Panel p_south; //남쪽에 붙일 패널(여기에 버튼 2개 붙일 예정)

	public loginForm(){
		//부품관계로 보유한 변수들을 모두 초기화 시키자..
		//특히 부품은, 이 객체가 태어날때 같이 함께 태어나야 하므로, 생성자의 타이밍을 놓치지 말자.
		la_id = new Label("ID");
		la_pass = new Label("Password");
		t_id = new TextField(15);
		t_pass = new TextField(15);

		bt_login = new Button("Login");
		bt_regist = new Button("가입");

		p_center = new Panel();
		p_south = new Panel();

		//생성만 한 상태이고, 아직 조립이나 스타일은 부여하지 않았음...
		//Frame은 개발자가 레이아웃을 지정하지 않으면 디폴트가 BorderLayout이다.
		//this.setLayout(new BorderLayout());//필요없다!! 왜? 디폴트가 BorderLayout이닌까~
		p_center.setBackground(new Color(153,255,0));
		this.add(p_center);
		//상수는 직관성을 부여한 데이터이다. final로 더이상 값을 변경할 수 없으며, static으로 인스턴스간
		//공규가 가능하며, public/으로 선언하여, 모든객체가 접근할 수 있도록 접근제한을 풀어놓은 데이터
		p_south.setBackground(Color.YELLOW); //Color를 인간이 사용하기 쉬운 데이터로 사용해보자.
		this.add(p_south, BorderLayout.SOUTH);
		
		//p_center에 그리드 적용
		p_center.setLayout(new GridLayout(2,2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pass);
		p_center.add(t_pass);

		p_south.add(bt_login);
		p_south.add(bt_regist);
		/*this의 정확한 의미 : 레퍼런스 변수이다. 단 나 자신의 인스턴스의 주소값을 가진...
		  해당 인스턴스가 자기 자신을 가리킬때..*/
		this.setSize(400,150);
		this.setVisible(true);
	}

	public static void main(String[] args){
		new loginForm();
	}
}
