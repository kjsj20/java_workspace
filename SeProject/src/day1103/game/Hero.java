/*
 * 주인공을 정의한다!!
 * */
package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JOptionPane;

public class Hero extends gameObject{
	GamePanel gamePanel; //enemyList가 있는 클래스라서
	public Hero(GamePanel gamePanel,Image img,int x, int y, int width, int height, int velX, int velY) {
		super(img, x,y,width,height,velX,velY);
		this.gamePanel = gamePanel;
	}
	
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		rect.x=x;
		rect.y=y;
		
		collisionCheck();
	}
	
	public void collisionCheck() {
		//적군과 나의 충돌여부를 판단하고, 만일 충돌하면 나죽고 너죽고, HP죽이고..
		for(int i=0; i<gamePanel.enemyList.size(); i++) {
			Enemy enemy = gamePanel.enemyList.get(i);
			if(this.rect.intersects(enemy.rect)) { //충돌..
				gamePanel.hp.remove(gamePanel.hp.size() - 1);
				gamePanel.enemyList.remove(enemy);
				
				gamePanel.score += 20;
				
				if(gamePanel.hp.size() == 0) {
					gamePanel.over = true;
					gamePanel.flag = false;
//					JOptionPane.showMessageDialog(gamePanel, "Game Over....");
//					System.exit(0);
				}
				System.out.println(gamePanel.hp.size());
				break;
			}
		}
	}
	
	
	//그래픽 처리(화면 그려질 처리)
	//모든 게임 캐릭터는 패널에 그려야 하므로, g2를 패널의 paint() 메서드
	//의 지역변수를 받아오자!!
	public void render(Graphics2D g2) {
		//패널에 그려야 한다!!

			//g2.setColor(Color.RED);
			g2.drawRect(rect.x, rect.y, rect.width, rect.height);
			//우리가 이미 보유하고 있는 사각형을 사각화 시켜보자!!

			g2.drawImage(img, rect.x, rect.y, null);
	}
}
