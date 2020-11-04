package day1103.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import common.image.ImageUtil;

//사실상 모든~게임의 그래픽처리는  패널이 담당하게 됨!!
public class GamePanel extends JPanel{
	Thread loopThread; //게임 루프를 무한루프로 실행할 쓰레드!! 
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	
	Hero hero;
	Bullet bullet;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Block> blockList = new ArrayList<Block>();
	ArrayList<backGround> bg = new ArrayList<backGround>();
	ArrayList<heart> hp = new ArrayList<heart>();
	Image bgImg;
	boolean flag = false;
	boolean over = false;
	int score = 0; //점수
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		createBg();
		createBlock();
		createHero();//주인공 생성
		createEnemy();
		createHeart();
		
		loopThread = new Thread() {
			public void run() {
				while(true) {
					if(flag)gameLoop();
					try {
						Thread.sleep(10); // 1/1000 초
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		loopThread.start();
	}

	public void paint(Graphics g) {
		Graphics2D g2=(Graphics2D)g; //2D에 더 적합한 그래픽스 객체로 형변환!!
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, WIDTH, HEIGHT); //패널의 크기만큼 사각형이 채워진다(화면을 깨끗이 닦는 효과)
		
		render(g2);
	}
	
	public void createHero() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/plane.png", 100, 65).getImage();
		hero = new Hero(this,img,200, 200, 100, 65, 0, 0);
	}
	
	//게임 윈도우로부터 어떤 방향키가 눌렸는지를 전달받자!!
	public void moveKey(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT:hero.velX=-5;break;
			case KeyEvent.VK_RIGHT:hero.velX=5;break;
			case KeyEvent.VK_UP:hero.velY=-5;break;
			case KeyEvent.VK_DOWN:hero.velY=5;break;
			case KeyEvent.VK_SPACE:fire();break;
		}
	}
	
	public void stopKey(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT:hero.velX=0;break;
			case KeyEvent.VK_RIGHT:hero.velX=0;break;
			case KeyEvent.VK_UP:hero.velY=0;break;
			case KeyEvent.VK_DOWN:hero.velY=0;break;
		}
	}
	
	// 1)플랫폼에 종속된 경로 : Toolkit
	// 2)클래스패스 : 클래스로더.getResources()
	
	public void fire() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/ball.png", 20, 20).getImage();
		Bullet bullet = new Bullet(this,img,hero.x + (hero.width), hero.y + (hero.height/2), 20, 20, 10, 0); 
		bulletList.add(bullet);//생성된 총알을 bulletList에 담자
	}
	
	//배경이미지 생성
	public void createBg() {
		bgImg = ImageUtil.getIcon(this.getClass(), "res/game/bg.jpg", WIDTH, HEIGHT).getImage();
		bg.add(new backGround(bgImg, 0, 0, WIDTH, HEIGHT, -5, 0));
		bg.add(new backGround(bgImg, WIDTH, 0, WIDTH, HEIGHT, -5, 0));
	}
	
	//적군 생성
	public void createEnemy() {
		String[] path = {"e1.png", "e2.png", "e3.png", "e4.png" , "e5.png"};
	
		for(int i=0; i<20; i++) {
			double r= Math.random();
			int n = (int)(r * path.length);
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/"+path[n], 80, 60).getImage();			
			Enemy enemy = new Enemy(img, WIDTH-50, 50 + (80 * i), 80, 60, -1, 0);
			enemyList.add(enemy); //적군 목록에 추가!!
		}
	}
	
	//블락 생성
	public void createBlock() {
		for(int i=0; i<20; i++) {
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/block.png", 80, 60).getImage();			
			Block block = new Block(img, 300 + (i *60), 600, 60, 60, 0, 0);
			blockList.add(block); //적군 목록에 추가!!
		}
	}
	
	public void createHeart() {
		for(int i =0; i<4; i++) {
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/e1.png",50, 50).getImage();
			heart ht = new heart(img, 50 + (i * 50), 85, 30, 30, 0, 0);
			hp.add(ht);
		}
	}
	
	public void showGameOver(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 200));
		StringBuffer sb = new StringBuffer(); 
		sb.append("Game Over"); 
		g2.drawString(sb.toString(), 50, 400);
	}
	
	//게임의 상황, 정보 출력
	public void printData(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 25));
		
		StringBuffer sb = new StringBuffer(); 
		sb.append("Bullet: " + bulletList.size()); 
		sb.append("  Enemy: " + enemyList.size());
		sb.append("  Score: " + score);
		g2.drawString(sb.toString(), 100, 50);
		
		if(over)showGameOver(g2);
	}
	
	//물리량 변경
	public void tick() {
		hero.tick();
		for(int i=0; i<bg.size(); i++) {
			backGround bgSet = bg.get(i); 
			bgSet.tick();
//			System.out.println(bgSet.x);
			if(bgSet.x == -(bgSet.width)) {
				bg.remove(bgSet);
				bg.add(new backGround(bgImg, WIDTH, 0, WIDTH, HEIGHT, -5, 0));
			}
		}
		for(int i=0; i<bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			bullet.tick();			
		}
		
		for(int i=0; i<enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.tick();
		}
		
		for(int i=0; i<blockList.size(); i++) {
			Block block = blockList.get(i);
			block.tick();
		}
		
		for(int i=0; i<hp.size(); i++) {
			heart ht = hp.get(i);
			ht.tick();
		}
	}
	public void render(Graphics2D g2) {
		for(int i=0; i<bg.size(); i++) {
			backGround bgSet = bg.get(i); 
			bgSet.render(g2);
		}
		
		hero.render(g2);
		
		for(int i=0; i<hp.size(); i++) {
			heart ht = hp.get(i);
			ht.render(g2);
		}
		
		for(int i=0; i<bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			bullet.render(g2);			
		}
		
		printData(g2);
		
		for(int i=0; i<enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.render(g2);
		}
		
		for(int i=0; i<blockList.size(); i++) {
			Block block = blockList.get(i);
			block.render(g2);
		}
	}
	
	//모든 게임의  tick(), render() 를 호출! 즉 게임엔진!!
	public void gameLoop() {
		tick();
		this.repaint();
	}
}







