package pgmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    private JTextField idField; // ID 필드
    private JPasswordField passwordField; // 비밀번호 필드
    private Database db; // Database 객체
    private JButton joinButton; // 회원가입 버튼

    public Login() {
        db = new Database(); // Database 객체 생성

        setLayout(null); // 절대 위치 설정

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(141, 193, 78, 30);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 폰트 설정

        // 사용자 ID 필드
        idField = new JTextField();
        idField.setBounds(52, 251, 250, 40);

        // 비밀번호 필드
        passwordField = new JPasswordField();
        passwordField.setBounds(52, 314, 250, 40);

        // 로그인 버튼
        JButton loginButton = new JButton("로그인");
        loginButton.setBounds(52, 382, 250, 40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText(); // ID 필드에서 텍스트 가져오기
                String password = new String(passwordField.getPassword()); // 비밀번호 필드에서 텍스트 가져오기

                // 로그인 체크
                if (db.logincheck(id, password)) {
                    JOptionPane.showMessageDialog(null, "로그인 성공!");
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패: 잘못된 ID 또는 비밀번호입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 회원가입 버튼
        joinButton = new JButton("회원가입");
        joinButton.setBounds(74, 432, 212, 15);
        
        // 패널에 컴포넌트 추가
        add(loginLabel);
        add(idField);
        add(passwordField);
        add(loginButton);
        add(joinButton);
    }

    // 회원가입 버튼의 ActionListener를 설정하는 메서드
    public void setJoinButtonActionListener(ActionListener listener) {
        joinButton.addActionListener(listener);
    }
}