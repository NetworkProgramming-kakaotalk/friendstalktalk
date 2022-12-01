	import java.awt.*;
	import java.text.SimpleDateFormat;
	import javax.swing.*;
	import java.util.Date;
	import java.util.Vector;
	import javax.swing.text.StyledDocument;
	
	public class ShowList extends JPanel {
		private LogIn LogIn = new LogIn();
		private JTextField txtHello;
		private JTextField txtTime;
		
		
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
			txtHello.setBounds(50, 0, 170, 25);
			txtHello.setEditable(false);
			add(txtHello);
			txtHello.setColumns(10);
			
			txtTime = new JTextField();
			txtTime.setText(formatter.format(date));
			txtTime.setBounds(50, 25, 170, 25);
			txtTime.setBackground(Color.WHITE);
			txtTime.setEditable(false);
			add(txtTime);
			txtTime.setColumns(10);
			
		}
		/*
		public Vector<Friend> FriendVector = new Vector<friend>();
		
		public void AddFriend(ImageIcon icon, String username, String userstatus, String statusmsg) {
	        int len = textPanelFriendList.getDocumetn().getLength();
	        textPanelFriendList.setCaretPosition(len);
	        Friend f = new Friend(mainview, icon, username,userstatus, statusmsg);
	        f.UserStatusMsg = statusmsg;
	        textPaneFriendList.insertComponent(f);
	        if (username.equals(UserName) ) {
	           f.setBackground(new Color(240,240,240));
	           f.SetStatusChangeActive();
	        }
	        f.SetProfileButtonActive();
	        FriendVector.add(f);
	        textPaneFriendList.setCaretPosition(0);
	        repaint();
	     }*/
		
	}