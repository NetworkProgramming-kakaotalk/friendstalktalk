package ChattingProgram;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EmoticonWindow extends JFrame implements ActionListener {
	   JButton e1,e2,e3,e4,e5,e6,e7,e8;
	   Color Dark_blue= new Color(0xBACEE0);
	   Color White= new Color(0xFFFFFF);
	   Color Gray= new Color(0xECECED);
	   public ChatWindow view;
	   
	public EmoticonWindow(String username, String ip_addr, String port_no) {
	    JFrame fr = new JFrame("이모티콘");
	    fr.setBounds(100,100,460,300);
	    JPanel pn = new JPanel();
	    pn.setBackground(White);
	 
	    ImageIcon emo_image1 = new ImageIcon("src/emoticons/e1.png");
	    e1 = new JButton(emo_image1);
	    e1.setBounds(10, 27, 100, 100);
	    e1.setBorderPainted(false);
	    e1.setBackground(White);
	    
	    ImageIcon emo_image2 = new ImageIcon("src/emoticons/e2.png");
	    e2 = new JButton(emo_image2);
	    e2.setBounds(120, 27, 100, 100);
	    e2.setBorderPainted(false);
	    e2.setBackground(White);
	    
	    ImageIcon emo_image3 = new ImageIcon("src/emoticons/e3.png");
	    e3 = new JButton(emo_image3);
	    e3.setBounds(228, 27, 100, 100);
	    e3.setBorderPainted(false);
	    e3.setBackground(White);
	    
	    ImageIcon emo_image4 = new ImageIcon("src/emoticons/e4.png");
	    e4 = new JButton(emo_image2);
	    e4.setBounds(338, 27, 100, 100);
	    e4.setBorderPainted(false);
	    e4.setBackground(White);
	    
	    ImageIcon emo_image5 = new ImageIcon("src/emoticons/e5.png");
	    e5 = new JButton(emo_image5);
	    e5.setBounds(10, 137, 100, 100);
	    e5.setBorderPainted(false);
	    
	    ImageIcon emo_image6 = new ImageIcon("src/emoticons/e6.png");
	    e6 = new JButton(emo_image6);
	    e6.setBounds(120, 137, 100, 100);
	    e6.setBorderPainted(false);
	    
	    ImageIcon emo_image7 = new ImageIcon("src/emoticons/e7.png");
	    e7 = new JButton(emo_image7);
	    e7.setBounds(228, 137, 100, 100);
	    e7.setBorderPainted(false);
	    
	    ImageIcon emo_image8 = new ImageIcon("src/emoticons/e8.png");
	    e8 = new JButton(emo_image8);
	    e8.setBounds(338, 137, 100, 100);
	    e8.setBorderPainted(false);
	    
	    
	    e1.addActionListener(this);
	    e2.addActionListener(this);
	    pn.setLayout(null);
	    
	    pn.add(e1);
	    pn.add(e2);
	    pn.add(e3);
	    pn.add(e4);
	    pn.add(e5);
	    pn.add(e6);
	    pn.add(e7);
	    pn.add(e8);
	
	    getContentPane().add(pn);
	   // view = new ChatWindow(username, ip_addr, port_no);
	    
	    setVisible(true);
        }
	


	
	@Override
	   public void actionPerformed(ActionEvent e) {
	      if(e.getSource()==e1) {
	    	  //view.EmoticonViewAction("src/emoticons/e1.png");
	      }
	}
}
