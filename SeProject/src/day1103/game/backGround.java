package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;

public class backGround extends gameObject{

	public backGround(Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
	}

	@Override
	public void tick() {
		this.x += this.velX;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(img, x, y, null);
	}

}
