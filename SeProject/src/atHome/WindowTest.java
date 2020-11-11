package atHome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import common.image.ImageUtil;

public class WindowTest {
   ImageIcon maginfier;
   boolean flag = false;

   private JFrame frame;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               WindowTest window = new WindowTest();
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
   public WindowTest() {
      
      initialize();
      //public static ImageIcon getIcon(Class target, String path, int width, int height) {
      
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      maginfier = ImageUtil.getIcon(this.getClass(), "res/maginfier.png", 40   , 50);
      Image maginfierimg = ImageUtil.getCustomSizedImage(maginfier.getImage(), 40, 50);
      
      
      frame = new JFrame();
      frame.setBounds(100, 100, 800, 600);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JMenuBar menuBar = new JMenuBar();
      frame.setJMenuBar(menuBar);
      
      JMenu file = new JMenu("file");
      menuBar.add(file);
      
      JMenuItem mntmNewMenuItem_1 = new JMenuItem("save");
      file.add(mntmNewMenuItem_1);
      frame.getContentPane().setLayout(new BorderLayout(0, 0));
      
      JPanel panel_North = new JPanel();
      panel_North.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      panel_North.setBackground(UIManager.getColor("Button.darkShadow"));
      frame.getContentPane().add(panel_North, BorderLayout.NORTH);
      panel_North.setLayout(new BorderLayout(0, 0));
      panel_North.setPreferredSize(new Dimension(800, 50));
      
      JPanel panel_1 = new JPanel() {
         public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(maginfierimg, 0, 0, this);
         }
      };
      panel_1.setBackground(UIManager.getColor("CheckBox.shadow"));
      panel_North.add(panel_1, BorderLayout.WEST);
      panel_1.setPreferredSize(new Dimension(40, 50));
      panel_1.setLayout(null);
      panel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
      //textfield가 들어간 panel 만들기
      JPanel popupPanel = new JPanel();
      JTextField popuptext =  new JTextField(10);
      popupPanel.setPreferredSize(new Dimension(200,100));
      popupPanel.add(popuptext);
      popupPanel.setBackground(Color.BLUE);
      
      //popupfactory 생성
      PopupFactory pf = new PopupFactory();
      Popup p = pf.getPopup(frame, popupPanel, 150, 200);
      
      
      //popupfactory호출 문제되는부분
      panel_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if(flag == false) {
               p.show();
               flag = true;
            }else {
               p.hide();
               flag = false;
               PopupFactory pf = new PopupFactory();
               Popup p = pf.getPopup(frame, popupPanel, 150, 200);
            }
         }
      });
      frame.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if(flag == true)
            p.hide();
         }
      });
      
      
      
      JPanel panel_2 = new JPanel();
      panel_North.add(panel_2, BorderLayout.CENTER);
      
      JPanel panel_3 = new JPanel();
      panel_North.add(panel_3, BorderLayout.EAST);
      
      
      JPanel panel_West = new JPanel();
      frame.getContentPane().add(panel_West, BorderLayout.WEST);
      panel_West.setPreferredSize(new Dimension(40, 600));
      
      JPanel panel_Center = new JPanel();
      frame.getContentPane().add(panel_Center, BorderLayout.CENTER);
      panel_Center.setLayout(null);
      
      JTextArea textArea = new JTextArea();
      textArea.setBounds(0, 0, 746, 490);
      panel_Center.add(textArea);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.ORANGE);
      panel.setBounds(0, 0, 100, 40);
   }
}