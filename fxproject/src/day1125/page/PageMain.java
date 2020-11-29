
/*FX에서의 페이지 전환, 화면전환*/
package day1125.page;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PageMain extends Application {
	String[] naviTitle = { "MAIN", "SHOPPING", "MEMBER", "LOGIN" };
	public static final int MAIN = 0;
//	public static final int SHOPPING = 1;
	public static final int MEMBER = 2;
	public static final int LOGIN = 3;
	Pane[] page = new Pane[naviTitle.length];
	Button[] btn = new Button[naviTitle.length];
	ToolBar bar; // 네비게이션을 포함하는 메뉴바
	BorderPane border; // 전체 레이아웃

	public void start(Stage stage) throws Exception {
		border = new BorderPane();

		// xml로 디자인된 파일을 자바객체로 변환하기!!(안드로으디에서는 이 과정을 inflation 이라고함)
		for (int i = 0; i < page.length; i++) {
			page[i] = FXMLLoader.load(this.getClass().getClassLoader()
					.getResource("day1125/page/" + naviTitle[i].toLowerCase() + ".fxml"));// main
		}

		// 네비게이션 만들기
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new Button(naviTitle[i]);

			btn[i].setId(Integer.toString(i));
			// 버튼에 액션이벤트 연결
			btn[i].setOnAction((e) -> {
				Button bt = (Button) e.getSource();
				System.out.println(bt.getId());
				
				showPage(page[Integer.parseInt(bt.getId())]);
			});
		}

		// 툴바에 버튼 올리기
		bar = new ToolBar(btn[Pages.MAIN.ordinal()], btn[Pages.SHOPPING.ordinal()], btn[Pages.MEMBER.ordinal()],
				btn[Pages.LOGIN.ordinal()]);
		bar.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		border.setTop(bar);// 생성된 바를 top 영역에 부착!!

		showPage(page[Pages.MEMBER.ordinal()]);
		showWindow(stage, border);
	}

	// 페이지 전환 메서드 정의 : swing 에서 우리가 정의한 showPage() 역할 메서드 정의
	public void showPage(Pane p) {
		// 스윙에서는 미리 부착한 컴포넌트(주로 패널) 를 보이거나 않보이게 하면 되지만,
		// FX에서는 물리적으로 부착하고, 제거하는 처리로 제어함..
		// 기존에 border에 붙여지 컴포넌트가 존재한다면 모두 제거!!
		border.getChildren().removeAll(border.getChildren());
		border.setTop(bar);
		border.setCenter(p);
	}

	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);
		stage.setScene(s);
		stage.setWidth(800);
		stage.setHeight(700);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
