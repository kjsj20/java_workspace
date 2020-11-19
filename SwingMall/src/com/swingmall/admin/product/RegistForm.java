package com.swingmall.admin.product;

import java.awt.Choice;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegistForm extends JPanel{
	JPanel p_container;
	JLabel[] la_title = new JLabel[7];
	String[] title = {"상위카테고리","하위카테고리","상품명","가격","이미지","상세설명"};
	Choice ch_top;
	Choice ch_sub;
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_filename;
	JTextArea t_detail;w
	
	public RegistForm(){
	}
	
}
