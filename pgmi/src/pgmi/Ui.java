package pgmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ui {
    private static Register registerPanel; // Register 패널을 멤버 변수로 선언
    private static Play playPanel; // Play 패널을 멤버 변수로 선언

    public Ui() {
        JFrame jf = new JFrame();
        jf.setBounds(0, 10, 360, 800);
        jf.setTitle("Main");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 애플리케이션 종료
        
        // 로그인 패널 생성
        Login loginPanel = new Login();
        jf.add(loginPanel);

        // Register 패널 초기화
        registerPanel = new Register(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 성공 후 로그인 패널로 돌아가기
                jf.remove(registerPanel); // 기존 회원가입 패널 제거
                jf.add(loginPanel); // 로그인 패널 추가
                jf.revalidate(); // 재배치
                jf.repaint(); // 새로 그리기
            }
        });

        // 회원가입 버튼 클릭 시 Register 패널로 전환
        loginPanel.setJoinButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.remove(loginPanel); // 기존 로그인 패널 제거
                jf.add(registerPanel); // 회원가입 패널 추가
                jf.revalidate(); // 재배치
                jf.repaint(); // 새로 그리기
            }
        });

        // 로그인 성공 시 Play 패널로 전환
        playPanel = new Play(); // Play 패널 초기화
        loginPanel.setLoginSuccessActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.remove(loginPanel); // 기존 로그인 패널 제거
                jf.add(playPanel); // Play 패널 추가
                jf.revalidate(); // 재배치
                jf.repaint(); // 새로 그리기
            }
        });

        jf.setVisible(true);
    }
}
