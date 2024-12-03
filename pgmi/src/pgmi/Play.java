package pgmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Play extends JPanel {
    private JLabel timeLabel; // 시간 라벨
    private JLabel characterLabel; // 캐릭터 라벨

    private int characterNum = 0;
    
    private int hunger = 50, health = 50, energy = 50;

    private JProgressBar hungerBar;
    private JProgressBar healthBar;
    private JProgressBar energyBar;

    public Play() {
        setLayout(null); // 절대 위치 설정

        // 배경색 설정
        setBackground(new Color(135, 206, 250)); // 하늘색

        // 메뉴 버튼
        JButton menuButton = new JButton("☰");
        menuButton.setBounds(285, 15, 50, 50);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 이벤트는 비워둠
            }
        });
        add(menuButton);

        // 체크리스트 버튼
        JButton checklistButton = new JButton("체크리스트");
        checklistButton.setBounds(15, 15, 50, 50);
        checklistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 체크리스트 버튼 클릭 시 이벤트 처리
                JOptionPane.showMessageDialog(null, "체크리스트가 열립니다."); // 임시 메시지
            }
        });
        add(checklistButton);
        
        // 시간 표시
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 80)); // 글자 크기를 키움
        timeLabel.setBounds(70, 80, 300, 100);
        add(timeLabel);

        // 캐릭터 이미지 추가
        ImageIcon characterIcon[] = {
            new ImageIcon("C:\\dy\\Img\\play_img/pgm_0.png"), // 캐릭터 이미지 경로
            new ImageIcon("C:\\dy\\Img\\play_img/pgm_2.png"),
            new ImageIcon("C:\\dy\\Img\\play_img/pgm_3.png")
        };
        characterLabel = new JLabel(characterIcon[characterNum]);
        characterLabel.setBounds(115, 350, characterIcon[characterNum].getIconWidth(), characterIcon[characterNum].getIconHeight());
        add(characterLabel);

     // 상태창 아이콘
        ImageIcon statsIcon[] = {
            new ImageIcon("C:\\dy\\Img\\play_img/icon_0.png"),
            new ImageIcon("C:\\dy\\Img\\play_img/icon_1.png"),
            new ImageIcon("C:\\dy\\Img\\play_img/icon_2.png")
        };
        
        for(int i = 0; i < statsIcon.length; i++) {
            characterLabel = new JLabel(statsIcon[i]);
            characterLabel.setBounds(15, 560 + 42 * i, statsIcon[i].getIconWidth(), statsIcon[i].getIconHeight());
            add(characterLabel);
        }
        
     // 상태바 (체력, 배고픔, 에너지 표시)
        hungerBar = new JProgressBar(0, 100);
        hungerBar.setValue(hunger);
        hungerBar.setBounds(60, 560, 270, 25);
        add(hungerBar);

        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(health);
        healthBar.setBounds(60, 602, 270, 25);
        add(healthBar);

        energyBar = new JProgressBar(0, 100);
        energyBar.setValue(energy);
        energyBar.setBounds(60, 644, 270, 25);
        add(energyBar);
        
        
        // 버튼
        JButton feedButton = new JButton("밥주기");
        feedButton.setBounds(35, 700, 115, 50);
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedCharacter();
            }
        });
        add(feedButton);

        JButton playButton = new JButton("놀아주기");
        playButton.setBounds(200, 700, 115, 50);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playWithCharacter();
            }
        });
        add(playButton);

        // Timer 설정하여 매 초마다 현재 시간 업데이트
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start(); // Timer 시작
        updateTime(); // 초기 시간 설정
    }

    // 현재 시간을 업데이트하는 메서드
    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        timeLabel.setText(currentTime);
    }

    // 캐릭터에게 밥을 주는 메서드
    private void feedCharacter() {
        health += 30;
        hunger += 50;

        // 상태값 제한
        if (health > 100) health = 100;
        if (hunger > 100) hunger = 100;

        // 상태바 업데이트
        healthBar.setValue(health);
        hungerBar.setValue(hunger);
    }

    // 캐릭터와 놀아주는 메서드
    private void playWithCharacter() {
        health -= 10;
        hunger -= 10;
        energy -= 10;

        // 상태값 제한
        if (health < 0) health = 0;
        if (hunger < 0) hunger = 0;
        if (energy < 0) energy = 0;

        // 상태바 업데이트
        healthBar.setValue(health);
        hungerBar.setValue(hunger);
        energyBar.setValue(energy);
    }
}
