package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class LineCanvas extends Canvas {
	
	int x,y,x2,y2;
	
	public LineCanvas(int x, int y, int x2, int y2) {
		this.x = x;
		this.y = y;
		this.x2= x2;
		this.y2 = y2;
		System.out.println(x +"," + y +"," + x2 +"," + y2);
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("제발나와라");
		g.drawLine(x,y,x2,y2);
	}
	
	public static void main(String[] args) {
		//new LineCanvas();
	}
}
