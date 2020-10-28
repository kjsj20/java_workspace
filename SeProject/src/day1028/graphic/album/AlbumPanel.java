package day1028.graphic.album;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
//Canvas를 사용하지 않고도, 패널을 이용하면 동일한 효과를 낼 수 있다!
public class AlbumPanel extends JPanel{
	int num = 0;
	Toolkit kit = Toolkit.getDefaultToolkit();
	
	String dir = "D:/java_workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg",
			"ab.jpg",
			"ax.jpg",
			"bm.jpg",
			"et.jpg",
			"kg.jpg",
			"mx.jpg",
			"pk.jpg",
			"ub.jpg",
			"ya.jpg"};
	Image[] img = new Image[src.length];
	
	//생성자에서 이미지를 생성하자!!
	public AlbumPanel() {
		for(int i=0; i<img.length; i++) {
			img[i] = kit.getImage(dir + src[i]);
			img[i] = img[i].getScaledInstance(500, 400, Image.SCALE_SMOOTH);
		}
	}
	
	//우리가 패널을 눈으로 보고 있다는 것은 이미 paint 메서드 호출에 의해, 그림이 완성되었기 때문에
	public void paint(Graphics g) {
		g.drawImage(img[num], 0, 0, this);
	}
}
