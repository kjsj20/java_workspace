package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Bullet extends gameObject{
	GamePanel gamePanel;
	public Bullet(GamePanel gamePanel, Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
		this.gamePanel = gamePanel;
	}
	
	//총알에 알맞는 물리량변화 코드 작성
	public void tick() {
		this.x += this.velX;
		
		//화면밖으로 나가면, 화살을 BulletArray에서 제거해야, 그려질 대상이 되지 않음..
		//또한 BulletArray의 크기를 줄여놓아야, 추후 충돌검사시 반복문의 횟수를 줄일 수 있음..
		if(this.x > GamePanel.WIDTH - 100) {
			gamePanel.bulletList.remove(this);
		} 
	}

	//총알에 맞는 그래픽 처리..
	public void render(Graphics2D g2) {
		if(img == null) {
			g2.setColor(Color.BLACK);
			g2.fillOval(x, y, width, height);			
		} else {
			g2.drawImage(img,x,y,null);
		}
	}
	
}
