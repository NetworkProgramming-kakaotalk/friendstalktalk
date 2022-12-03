
import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.util.Date;

public class ShowList extends JPanel {
  //private LogIn LogIn = new LogIn();
   private JTextField txtHello;
   private JTextField txtTime;
   public ImageIcon icon2 = new ImageIcon("src/Profile2.png");
   
   public ShowList(String username) {
      setLayout(null);
      
      SimpleDateFormat formatter= new SimpleDateFormat("yyyy.MM.dd. HH'시' mm'분' ss'초'");
      Date date = new Date(System.currentTimeMillis());

      System.out.println(System.currentTimeMillis());
      System.out.println(formatter.format(date));
      
      txtHello = new JTextField();
      txtHello.setText(username);
      txtHello.setHorizontalAlignment(SwingConstants.LEFT);
      txtHello.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      txtHello.setBackground(Color.WHITE);
      txtHello.setBounds(55, 90, 170, 25);
      txtHello.setEditable(false);
      add(txtHello);
      txtHello.setColumns(10);
      
      txtTime = new JTextField();
      txtTime.setText(formatter.format(date));
      txtTime.setBounds(55, 125, 170, 25);
      txtTime.setBackground(Color.WHITE);
      txtTime.setEditable(false);
      add(txtTime);
      txtTime.setColumns(10);
      
      JTextPane textPane = new JTextPane();
      textPane.setText("사용자");
      textPane.setEditable(false);
      textPane.setBounds(0, 90, 55, 25);
      add(textPane);
      
      JTextPane textPane_1 = new JTextPane();
      textPane_1.setText("접속시간");
      textPane_1.setEditable(false);
      textPane_1.setBounds(0, 125, 55, 25);
      add(textPane_1);
      
      JLabel iconLabel = new JLabel(icon2);
      iconLabel.setBounds(70, 10, 80, 70);
      add(iconLabel);
      
   }
}