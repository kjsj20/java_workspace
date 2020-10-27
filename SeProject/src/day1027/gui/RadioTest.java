/*
 * 1)�۾� ũ�� ���� ctrl + (-,+Ű)
 * 2)window > Preferences > General > Appearance > Colors and Fonts > Basic >. Text Font ����
 *   Verdana ü ����
 * 3) ����Ű ��� ���� ctrl + shift + L
 * 4) �ڵ� ����Ʈ : �ش� Ŭ������ Ŀ�� �÷����� ctrl + shift + o
 * 5) �ڵ� �ڵ� ���� : ctrl + shift + f
 * 6) �ش� ��ü�� api���� �ٷΰ��� : �ش� Ŭ���� Ŀ�� �ø� �� shift + F2(���Ű) ���ͳ� ������ ������..
 * 7) ��� sout �̶�� ����ܾ� �Է°� ���ÿ� ctrl+space
 * 8) ������ �� Alt + �� �Ʒ� �̵� ����
 * 9) ������ �� ctrl + alt �� �Ʒ� ���� ��� 
 * */

package day1027.gui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

public class RadioTest extends Frame {
	Checkbox ch;
	
	CheckboxGroup group = new CheckboxGroup();
	
	public RadioTest() {
		group = new CheckboxGroup();
		setLayout(new FlowLayout());
		this.add(new Checkbox("컴퓨터", group, false));
		this.add(new Checkbox("독서", group, false));
		this.add(new Checkbox("코딩", group, false));
		this.add(new Checkbox("노래", group, false));
		this.add(new Checkbox("운동", group, false));
		this.add(new Checkbox("요리", group, false));
		setSize(300,400);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("겁나빠름");
		new RadioTest();
	}

}
