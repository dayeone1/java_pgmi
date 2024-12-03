package pgmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JPanel {
    private JTextField idField; 
    private JPasswordField passwordField; 
    private JPasswordField passwordFieldChk; 
    private Database db; // Database 객체 추가
    private ActionListener onRegisterSuccess; 

    public Register(ActionListener onRegisterSuccess) {
        this.onRegisterSuccess = onRegisterSuccess;

        // Database 객체 생성
        db = new Database();

        setLayout(null); // 절대 위치 설정

        // ID 필드
        JLabel idLabel = new JLabel("아이디:");
        idLabel.setBounds(45, 230, 80, 25); // ID 라벨 위치 조정
        add(idLabel);

        idField = new JTextField(); // 기본 ID 필드
        idField.setBounds(52, 252, 240, 40); // ID 필드 위치
        add(idField);

        // 비밀번호 필드
        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setBounds(45, 290, 80, 25); // 비밀번호 라벨 위치 조정
        add(passwordLabel);

        passwordField = new JPasswordField(); // 기본 비밀번호 필드
        passwordField.setBounds(52, 314, 240, 40); // 비밀번호 필드 위치
        add(passwordField);

        // 비밀번호 확인
        JLabel passwordChkLabel = new JLabel("비밀번호 확인:");
        passwordChkLabel.setBounds(45, 350, 120, 25); // 비밀번호 확인 라벨 위치 조정
        add(passwordChkLabel);

        passwordFieldChk = new JPasswordField(); // 기본 비밀번호 확인 필드
        passwordFieldChk.setBounds(52, 373, 240, 40); // 비밀번호 확인 필드 위치
        add(passwordFieldChk);

        ImageIcon button_img_2 = new ImageIcon("C:\\dy\\Img\\login_img/button_2.png");
        ImageIcon button_img_3 = new ImageIcon("C:\\dy\\Img\\login_img/button_3.png");

        JButton registerButton = new JButton(button_img_2);
        registerButton.setRolloverIcon(button_img_3); // 버튼에 마우스가 올라갈 때 이미지 변환
        registerButton.setBorderPainted(false); // 버튼 테두리 설정 해제
        registerButton.setBounds(52, 436, 240, 40);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(passwordFieldChk.getPassword());

                if (id.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.equals(confirmPassword)) {
                    if (db.register(id, password)) {
                        JOptionPane.showMessageDialog(null, "회원가입 성공!");
                        onRegisterSuccess.actionPerformed(e); 
                    } else {
                        JOptionPane.showMessageDialog(null, "회원가입 실패", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(registerButton);
    }
}