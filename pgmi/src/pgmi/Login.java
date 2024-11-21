package pgmi;

import java.awt.event.*;
import javax.swing.*;

public class Login extends JPanel {
	
	public Login() {
		 JLabel login_label = new JLabel("Login");
		 login_label.setBounds(141, 193, 71, 36);
		 JTextField id = new JTextField();
		 id.setBounds(52, 252, 257, 40);
		 
		 JTextField password = new JTextField();
		 password.setBounds(52, 314, 257, 40);
		 
	     setLayout(null); // 패널의 레이아웃을 null로 설정하여 절대 위치 지정
	        
	     // 패널에 컴포넌트 추가
	     add(login_label);
	     add(password);
	     add(id);
	}
   
}
