import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class FriendsList extends JPanel {
	private String UserName;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JTextPane txtHello;
	private Socket socket; // 연결소켓
	
	public FriendsList(String username, String ip_addr, String port_no){
		setLayout(null);
		
		txtHello = new JTextPane();
		txtHello.setText(username);
		//txtHello.setHorizontalAlignment(SwingConstants.LEFT);
		txtHello.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		txtHello.setBackground(Color.WHITE);
		txtHello.setBounds(50, 0, 170, 25);
		txtHello.setEditable(false);
		add(txtHello);
		//txtHello.setColumns(10);
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

		
			ChatMsg msg = new ChatMsg(UserName, "200", "/list");
			SendObject(msg);
		}catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}
	}
	public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			// textArea.append("메세지 송신 에러!!\n");
			AppendText("SendObject Error");
		}
	}
	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		//AppendIcon(icon1);
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = txtHello.getDocument().getLength();
		// 끝으로 이동
		//textArea.setCaretPosition(len);
		//textArea.replaceSelection(msg + "\n");
		
		StyledDocument doc = txtHello.getStyledDocument();
		SimpleAttributeSet left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(left, Color.BLACK);
	    doc.setParagraphAttributes(doc.getLength(), 1, left, false);
		try {
			doc.insertString(doc.getLength(), msg+"\n", left );
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		len = txtHello.getDocument().getLength();
		txtHello.setCaretPosition(len);
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
