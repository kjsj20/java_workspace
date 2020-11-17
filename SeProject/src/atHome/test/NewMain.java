package atHome.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class NewMain {

   JFrame frame;
   private JTextField textField;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               NewMain window = new NewMain();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public NewMain() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 800, 600);
      frame.setMinimumSize(new Dimension(400,300));
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new BorderLayout(0, 0));
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.LIGHT_GRAY);
      panel.setPreferredSize(new Dimension(150,600));
      frame.getContentPane().add(panel, BorderLayout.WEST);
      panel.setLayout(new BorderLayout(0, 0));
      
      JPanel panel_2 = new JPanel();
      panel_2.setBackground(Color.LIGHT_GRAY);
      panel_2.setPreferredSize(new Dimension(50, 600));
      panel.add(panel_2, BorderLayout.WEST);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBackground(Color.CYAN);
      panel_3.setPreferredSize(new Dimension(100, 600));
      panel.add(panel_3, BorderLayout.CENTER);
      panel_3.setLayout(new BorderLayout(0, 0));
      
      JPanel panel_4 = new JPanel();
      panel_4.setBackground(Color.WHITE);
      panel_4.setPreferredSize(new Dimension(100, 150));
      panel_3.add(panel_4, BorderLayout.NORTH);
      
      JPanel panel_5 = new JPanel();
      panel_5.setBackground(Color.ORANGE);
      panel_5.setPreferredSize(new Dimension(100, 300));
      panel_3.add(panel_5, BorderLayout.CENTER);
      
      JPanel panel_6 = new JPanel();
      panel_6.setBackground(Color.MAGENTA);
      panel_6.setPreferredSize(new Dimension(100, 150));
      panel_3.add(panel_6, BorderLayout.SOUTH);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.GRAY);
      frame.getContentPane().add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new BorderLayout(0, 0));
      
      JPanel panel_7 = new JPanel();
      panel_7.setBackground(Color.LIGHT_GRAY);
      panel_7.setPreferredSize(new Dimension(650, 50));
      panel_1.add(panel_7, BorderLayout.NORTH);
      panel_7.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("#개발팀");
      lblNewLabel.setBounds(12, 10, 60, 15);
      panel_7.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("바보 윤빈");
      lblNewLabel_1.setBounds(12, 25, 72, 25);
      panel_7.add(lblNewLabel_1);
      
      JPanel panel_8 = new JPanel();
      panel_1.add(panel_8, BorderLayout.CENTER);
      panel_8.setPreferredSize(new Dimension(650, 500));
      
      JPanel panel_9 = new JPanel();
      panel_9.setPreferredSize(new Dimension(650, 50));
      panel_1.add(panel_9, BorderLayout.SOUTH);
      panel_9.setLayout(new BorderLayout(0, 0));
      
      textField = new JTextField();
      panel_9.add(textField, BorderLayout.CENTER);
      textField.setColumns(10);
      
      JButton btnNewButton = new JButton("New button");
      panel_9.add(btnNewButton, BorderLayout.EAST);
   }
}