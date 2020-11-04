package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Enemy extends gameObject{
	public Enemy(Image img,int x, int y, int width, int height, int velX, int velY) {
		super(img, x,y,width,height,velX,velY);
	}
	
	public void tick() {
		this.x += this.velX;
	}
	
	//그래픽 처리(화면 그려질 처리)
	//모든 게임 캐릭터는 패널에 그려야 하므로, g2를 패널의 paint() 메서드
	//의 지역변수를 받아오자!!
	public void render(Graphics2D g2) {
		//패널에 그려야 한다!!
			g2.drawImage(img, x, y, null);
	}
}
