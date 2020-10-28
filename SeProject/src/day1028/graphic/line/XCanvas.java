package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas extends Canvas{
	LIneMaker2 lineMaker;
	int x1,y1,x2,y2;
	//켄버스는 개발자가 직접 그림을 그릵수 있는 빈 도화지이다!!
	//따라서 paint() 메서드를 재정의 하면 된다.
	public void setLineMaker(LIneMaker2 lineMaker) {
		this.lineMaker= lineMaker;
	}
	public void paint(Graphics g) {
		x1 =Integer.parseInt(lineMaker.t_x1.getText());
		y1 = Integer.parseInt(lineMaker.t_y1.getText());
		x2 = Integer.parseInt(lineMaker.t_x2.getText());
		y2= Integer.parseInt(lineMaker.t_y2.getText());
		g.drawLine(x1, y1, x2, y2);
	}
}
