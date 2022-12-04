
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyEvent extends JFrame implements ActionListener{
   JButton b1,b2;
   JPanel p;
   private ShowList showlist1;
   private JTextField textField;
   public EmoticonWindow view;
   
   public MyEvent(String username, String ip_addr, String port_no) {
      setTitle("친구 톡톡");
      setBounds(100,100,340,560);
      
      //사용자 버튼
      Color Gray= new Color(0xECECED);
      p = new JPanel();
      ImageIcon user_icon = new ImageIcon("src/user.png");
      b1 = new JButton(user_icon);
      b1.setBackground(Gray);
      b1.setBounds(12, 10, 80, 50);
      b1.setBorderPainted(false);
      //채팅 버튼
      ImageIcon chat_icon = new ImageIcon("src/chat.png");
      b2 = new JButton(chat_icon);
      b2.setBackground(Gray);
      b2.setBounds(12, 70, 80, 50);
      b2.setBorderPainted(false);
      b1.addActionListener(this);
      b2.addActionListener(this);
      p.setLayout(null);
      
      //b3 = new JButton("대화방 입장하기");
      
      
      b2.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
         if(b2.getText().equals("대화")) {
               
         view= new EmoticonWindow(username, ip_addr, port_no);
         view.setBounds(100,100,460,300);
         b2.setText("화면닫기");

         } else {
            b2.setText("대화");
            view.dispose();
         }
      }});
      
     p.add(b1);
     p.add(b2);
     getContentPane().add(p);
          
      
    
      showlist1 = new ShowList(username);
      showlist1.setBackground(Color.WHITE);
      showlist1.setBounds(10, 50, 210, 364);
     
      
      JPanel panel = new JPanel();
      panel.setBounds(104,10,220,511);
      panel.setBackground(Color.WHITE);
      panel.setLayout(null);
      
      textField = new JTextField();
      textField.setBounds(0, 0, 129, 40);
      textField.setBackground(new Color(255, 255, 255));
      textField.setText("나의 프로필");
      textField.setEditable(false);
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      panel.add(textField);
      textField.setColumns(10);
      panel.add(showlist1);
      showlist1.setLayout(null);
      p.add(panel);
      
      
      setVisible(true);
      
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==b1) {
         textField.setText("나의 프로필");
         textField.setHorizontalAlignment(SwingConstants.CENTER);
         textField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
         textField.setEditable(false);
         showlist1.setVisible(true);

      }else if(e.getSource()==b2) {
         textField.setText("대화창");
         textField.setHorizontalAlignment(SwingConstants.CENTER);
         textField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
         textField.setEditable(false);
         showlist1.setVisible(false);
         

      }
   }
}