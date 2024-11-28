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

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(30, 30, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(120, 30, 150, 25);
        add(idField);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setBounds(30, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 150, 25);
        add(passwordField);

        JLabel passwordChkLabel = new JLabel("비밀번호 확인:");
        passwordChkLabel.setBounds(30, 110, 120, 25);
        add(passwordChkLabel);

        passwordFieldChk = new JPasswordField();
        passwordFieldChk.setBounds(120, 110, 150, 25);
        add(passwordFieldChk);

        JButton registerButton = new JButton("회원가입");
        registerButton.setBounds(120, 150, 120, 25);
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