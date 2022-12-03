
// JavaObjClientView.java ObjecStram 기반 Client
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ChatWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInput;
	private String UserName;
	private JButton btnSend;
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel lblUserName;
	private JTextPane textArea;

	private Frame frame;
	private FileDialog fd;
	private JButton emoBtn;
	private JButton imgBtn;
	public ChatWindow view;
	/**
	 * Create the frame.
	 * @throws BadLocationException 
	 */
	
	public ChatWindow(String username, String ip_addr, String port_no)  {
		//emoWind.setBounds(100,100,460,300);
		Color Dark_blue= new Color(0xBACEE0);
		Color White= new Color(0xFFFFFF);
		Color Gray= new Color(0xECECED);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(White);
		
		JPanel panel = new JPanel();
	    panel.setBounds(0,0,380,60);
	    panel.setBackground(Dark_blue);
	    panel.setLayout(null);
	    contentPane.add(panel);
	    
	    JLabel iconLabel = new JLabel(icon1);
	    iconLabel.setBounds(10, 10, 30, 30);
	    panel.add(iconLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 382, 400);
		scrollPane.setBorder(null);
		contentPane.add(scrollPane);

		textArea = new JTextPane();
		textArea.setEditable(true);
		textArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Dark_blue);

		txtInput = new JTextField();
		txtInput.setBounds(10, 468, 360, 30);
		txtInput.setColumns(10);
		txtInput.setBorder(null);
		//txtInput.setHorizontalAlignment(JTextField.NORTH);
		contentPane.add(txtInput);
		
		btnSend = new JButton("전송");
		btnSend.setFont(new Font("굴림", Font.PLAIN, 12));
		btnSend.setBounds(311, 559, 59, 30);
		btnSend.setBackground(Gray);
		contentPane.add(btnSend);

		lblUserName = new JLabel("Name");
		lblUserName.setBorder(null);
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setFont(new Font("굴림", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(50, 10, 62, 40);
		panel.add(lblUserName);
		setVisible(true);

		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		lblUserName.setText(username);
		
		ImageIcon emo_icon = new ImageIcon("src/emoticon.png");
		emoBtn = new JButton(emo_icon);
		emoBtn.setBounds(10, 559, 30, 30);
		contentPane.add(emoBtn);
		emoBtn.setBackground(White);
		emoBtn.setBorder(null);
		emoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmoticonWindow emoWind = new EmoticonWindow(username, ip_addr, port_no);
				emoWind.setBounds(100,100,460,300);
				view.setVisible(false);
				emoWind.setVisible(true);
				ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
			}
		});
		
		ImageIcon clip_icon = new ImageIcon("src/clip.png");
		imgBtn = new JButton(clip_icon);
		imgBtn.setBounds(50, 559, 30, 30);
		contentPane.add(imgBtn);
		imgBtn.setBackground(White);
		imgBtn.setBorder(null);

		ImageIcon out_icon = new ImageIcon("src/logout.png");
		JButton btnExit = new JButton(out_icon);
		btnExit.setFont(new Font("굴림", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				//SendObject(msg);
				System.exit(0);
			}
		});
		btnExit.setBounds(330, 10, 40, 40);
		btnExit.setBackground(Gray);
		panel.add(btnExit);
		
		ImageIcon list_icon = new ImageIcon("src/list.png");
		JButton listBtn = new JButton(list_icon);
		listBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		listBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "200", "/list");
				SendObject(msg);
			}
		});
		listBtn.setBounds(279, 10, 40, 40);
		listBtn.setBackground(Gray);
		panel.add(listBtn);

		view = this;

		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			// SendMessage("/login " + UserName);
			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);

			ListenNetwork net = new ListenNetwork();
			net.start();
			TextSendAction action = new TextSendAction();
			btnSend.addActionListener(action);
			txtInput.addActionListener(action);
			txtInput.requestFocus();
			ImageSendAction action2 = new ImageSendAction();
			imgBtn.addActionListener(action2);
			//EmoticonSendAction action3 = new EmoticonSendAction();
			//emoBtn.addActionListener(action3);
			


		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}
		setVisible(true);
	}

	// Server Message를 수신해서 화면에 표시
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {

					Object obcm = null;
					String msg = null;
					ChatMsg cm;
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					if (obcm == null)
						break;
					if (obcm instanceof ChatMsg) {
						cm = (ChatMsg) obcm;
						msg = String.format("[%s]\n%s", cm.UserName, cm.data);
					} else
						continue;
					switch (cm.code) {
					case "200": // chat message
						if (cm.UserName.equals(UserName))
							AppendTextR(msg); // 내 메세지는 우측에
						else
							AppendText(msg);
						break;
					case "300": // Image 첨부
						if (cm.UserName.equals(UserName)) 
							AppendTextR("[" + cm.UserName + "]");
						else 
							AppendText("[" + cm.UserName + "]");
						AppendImage(cm.img);
						break;
					case "500": //친구 리스트
						
						
						break;
					}
				} catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
//						dos.close();
//						dis.close();
						ois.close();
						oos.close();
						socket.close();

						break;
					} catch (Exception ee) {
						break;
					} // catch문 끝
				} // 바깥 catch문끝

			}
		}
	}
	

	// keyboard enter key 치면 서버로 전송
	class TextSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Send button을 누르거나 메시지 입력하고 Enter key 치면
			if (e.getSource() == btnSend || e.getSource() == txtInput) {
				String msg = null;
				// msg = String.format("[%s] %s\n", UserName, txtInput.getText());
				msg = txtInput.getText();
				SendMessage(msg);
				txtInput.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
				txtInput.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
				if (msg.contains("/exit")) // 종료 처리
					System.exit(0);
			}
		}
	}

	class ImageSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == imgBtn) {
				frame = new Frame("이미지첨부");
				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
				// frame.setVisible(true);
				// fd.setDirectory(".\\");
				fd.setVisible(true);
				// System.out.println(fd.getDirectory() + fd.getFile());
				if (fd.getDirectory().length() > 0 && fd.getFile().length() > 0) {
					ChatMsg obcm = new ChatMsg(UserName, "300", "IMG");
					ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile());
					obcm.img = img;
					SendObject(obcm);
				}
			}
		}
	}
	

	public void EmoticonViewAction(String emo_image) {
		// TODO Auto-generated method stub
		ChatMsg obcm = new ChatMsg(UserName, "300", "IMG");
		ImageIcon img = new ImageIcon(emo_image);
		obcm.img = img;
		SendObject(obcm);
	}
	

	ImageIcon icon1 = new ImageIcon("src/Profile.png");
	
	public void AppendIcon(ImageIcon icon) {
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		textArea.setCaretPosition(len);
		textArea.insertIcon(icon);
	}

	// 화면에 출력
	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		AppendIcon(icon1);
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		//textArea.setCaretPosition(len);
		//textArea.replaceSelection(msg + "\n");
		
		StyledDocument doc = textArea.getStyledDocument();
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
		len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);


	}
	// 화면 우측에 출력
	public void AppendTextR(String msg) {
		AppendIcon(icon1);
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.	
		StyledDocument doc = textArea.getStyledDocument();
		SimpleAttributeSet right = new SimpleAttributeSet();
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setForeground(right, Color.BLUE);
	    doc.setParagraphAttributes(doc.getLength(), 1, right, false);
		try {
			doc.insertString(doc.getLength(),msg+"\n", right );
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);

	}
	
	public void AppendImage(ImageIcon ori_icon) {
		
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		Image ori_img = ori_icon.getImage();
		Image new_img;
		ImageIcon new_icon;
		int width, height;
		double ratio;
		width = ori_icon.getIconWidth();
		height = ori_icon.getIconHeight();
		// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
		if (width > 200 || height > 200) {
			if (width > height) { // 가로 사진
				ratio = (double) height / width;
				width = 80;
				height = (int) (width * ratio);
			} else { // 세로 사진
				ratio = (double) width / height;
				height = 80;
				width = (int) (height * ratio);
			}
			new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			new_icon = new ImageIcon(new_img);
			textArea.insertIcon(new_icon);
		} else {
			textArea.insertIcon(ori_icon);
			new_img = ori_img;
		}
		len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);
		textArea.replaceSelection("\n");
		
	}

	// Windows 처럼 message 제외한 나머지 부분은 NULL 로 만들기 위한 함수
	public byte[] MakePacket(String msg) {
		byte[] packet = new byte[BUF_LEN];
		byte[] bb = null;
		int i;
		for (i = 0; i < BUF_LEN; i++)
			packet[i] = 0;
		try {
			bb = msg.getBytes("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}

	// Server에게 network으로 전송
	public void SendMessage(String msg) {
		try {
			// dos.writeUTF(msg);
//			byte[] bb;
//			bb = MakePacket(msg);
//			dos.write(bb, 0, bb.length);
			ChatMsg obcm = new ChatMsg(UserName, "200", msg);
			oos.writeObject(obcm);
		} catch (IOException e) {
			// AppendText("dos.write() error");
			AppendText("oos.writeObject() error");
			try {
//				dos.close();
//				dis.close();
				ois.close();
				oos.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
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
}