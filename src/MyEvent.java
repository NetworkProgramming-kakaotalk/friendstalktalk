import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class MyEvent extends JFrame implements ActionListener{
   JButton b1,b2;
   JPanel p;
   private ShowList showlist1;
   private JTextField textField;
   private JTextPane listArea;
   private FriendsList friendslist;
   
   public MyEvent(String username,String ip_addr,String port_no) {
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
      
      p.add(b1);
      p.add(b2);
      getContentPane().add(p);
          
      
      int y=50; // 클릭할때마다 y값 60씩 증가
      	showlist1 = new ShowList(username);
      	showlist1.setBackground(Color.WHITE);
      	showlist1.setBounds(10, y, 210, 49);
      	
      	friendslist = new FriendsList(username, ip_addr, port_no);
      	friendslist.setBackground(Color.WHITE);
      	friendslist.setBounds(10, y, 210, 49);
      
//          showlist2 = new ShowList(username);
//          showlist2.setBackground(Color.WHITE);
//          showlist2.setBounds(10, 110, 210, 49);
//          
//          showlist3 = new ShowList(username);
//          showlist3.setBackground(Color.WHITE);
//          showlist3.setBounds(10, 170, 210, 49);
      	
      JPanel panel = new JPanel();
      panel.setBounds(104,10,220,120);
      panel.setBackground(Color.WHITE);
      panel.setLayout(null);
    
      
      //listArea = new JTextPane();
      //listArea.setEditable(true);
      //listArea.setFont(new Font("굴림체", Font.PLAIN, 14));
      //scrollPane.setViewportView(listArea);
      //listArea.setBackground(Color.WHITE);
      //listArea.setBorder(null);
      
      textField = new JTextField();
      textField.setBounds(0, 0, 80, 40);
      textField.setBackground(new Color(255, 255, 255));
      textField.setText("목록");
      textField.setEditable(false);
      textField.setHorizontalAlignment(SwingConstants.CENTER);
	  textField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      panel.add(textField);
      textField.setColumns(10);
      panel.add(showlist1);
      showlist1.setLayout(null);
      p.add(panel);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(104, 130, 220, 390);
      scrollPane.setBorder(null);
      p.add(scrollPane);
      	
      JPanel listPanel = new JPanel();
      scrollPane.setViewportView(listPanel);
      //listPanel.setBounds(104,10,220,120);
      listPanel.setBackground(Color.WHITE);
      listPanel.setLayout(null);
      listPanel.setBorder(null);
      listPanel.add(friendslist);
      
      setVisible(true);
      
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==b1) {
    	  textField.setText("목록");
    	  textField.setHorizontalAlignment(SwingConstants.CENTER);
    	  textField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    	  textField.setEditable(false);
    	  showlist1.setVisible(true);
    	  friendslist.setVisible(true);

      }else if(e.getSource()==b2) {
    	  textField.setText("대화창");
    	  textField.setHorizontalAlignment(SwingConstants.CENTER);
    	  textField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    	  textField.setEditable(false);
    	  showlist1.setVisible(false);
    	  friendslist.setVisible(false);
      }
   }
}