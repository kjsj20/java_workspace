package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class ThumbCanvas_my extends Canvas{
	Toolkit kit = Toolkit.getDefaultToolkit();
	String dir = "D:/java_workspace/SeProject/res/travel2/";
	String src;
	Image img = new Image() {
		
		@Override
		public int getWidth(ImageObserver observer) {
			return 0;
		}
		
		@Override
		public ImageProducer getSource() {
			return null;
		}
		
		@Override
		public Object getProperty(String name, ImageObserver observer) {
			return null;
		}
		
		@Override
		public int getHeight(ImageObserver observer) {
			return 0;
		}
		
		@Override
		public Graphics getGraphics() {
			return null;
		}
	};
	
	public ThumbCanvas_my(String src) {
		img = kit.getImage(dir + src);
		System.out.println(dir + src);
		img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img,0,0,this);
	}
}
