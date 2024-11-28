package pgmi;

import java.sql.*;

public class Database {
    private Connection con = null;
    private String url = "jdbc:mysql://localhost/java_test?serverTimezone=Asia/Seoul";
    private String user = "root";
    private String passwd = "1234";

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("DB 연동 성공");
        } catch (Exception e) {
            System.out.println("DB 연동 실패: " + e.toString());
        }
    }

    /* 로그인 정보를 확인 */
    public boolean logincheck(String id, String password) {
        boolean flag = false;

        String query = "SELECT password FROM member WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                // 비밀번호 비교
                if (password.equals(result.getString("password"))) {
                    flag = true;
                    System.out.println("로그인 성공");
                } else {
                    System.out.println("비밀번호가 일치하지 않음");
                }
            } else {
                System.out.println("ID가 존재하지 않음");
            }
        } catch (Exception e) {
            System.out.println("로그인 실패: " + e.toString());
        }

        return flag;
    }

    /* 회원가입 정보 저장 */
    public boolean register(String id, String password) {
        String query = "INSERT INTO member (id, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 성공적으로 추가되었는지 확인
        } catch (SQLException e) {
            System.out.println("회원가입 실패: " + e.toString());
            return false;
        }
    }

    // 데이터베이스 연결 종료 메서드
    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out.println("DB 연결 종료");
            }
        } catch (SQLException e) {
            System.out.println("DB 연결 종료 실패: " + e.toString());
        }
    }
}