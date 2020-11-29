package day1125.chart;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarCharApp extends Application{
	BarChart bar;
	CategoryAxis x; //x축으로 사용할 객체(유럽, 북미, 아시아..)
	NumberAxis y; //y축으로 사용할 객체(코로나 감염자 수)
	public void start(Stage stage) throws Exception {
		x = new CategoryAxis();
		y = new NumberAxis();
		
		//x,y축에 라벨 달기
		x.setLabel("Area");
		y.setLabel("People");
		
		//데이터 구성!!
		XYChart.Series s1 = new XYChart.Series();
		s1.setName("아시아");
		s1.getData().add(new XYChart.Data("20만명",20));
		
		XYChart.Series s2 = new XYChart.Series();
		s2.setName("유럽");
		s2.getData().add(new XYChart.Data("68만명",68));
		
		XYChart.Series s3 = new XYChart.Series();
		s3.setName("북미");
		s3.getData().add(new XYChart.Data("300만명",300));
		
		
		bar = new BarChart(x, y);
		bar.setLegendSide(Side.RIGHT);
		
		//bar차트에 데이터 적용
		bar.getData().addAll(s1, s2, s3);
		
		showWindow(stage, bar);
	}
	
	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);
		stage.setScene(s);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
