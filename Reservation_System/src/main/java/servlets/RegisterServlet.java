package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

//sqlに入るためのURL,USER,PASSを設定
    private static final String URL = "jdbc:mysql://localhost:3306/reservation_system";
    private static final String USER = "root";
    private static final String PASS = "root";

//使うSQL文をSQ_SELECT,SQL＿INSERTに格納
    private static final String SQL_SELECT = "SELECT COUNT(*) AS count FROM user WHERE USER_ID = ?";
    private static final String SQL_INSERT =
        "INSERT INTO user (USER_ID, NAME, EMAIL, PASSWORD, GENDER, AUTHORITY) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


//習得した各値を変数に格納
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String authorityStr = request.getParameter("authority");
 //ArrayListの文字列を設定。
        List<String> validationMsgs = new ArrayList<>();
        int userId = 0;
        int authority = 0;


        // --- 入力チェック ---
        
        
        if (isEmpty(idStr)) {
            validationMsgs.add("ID入力は必須です");
        } else if (idStr.length() != 4) {
            validationMsgs.add("IDは4桁の整数で入力してください。");
        } else {
            try {
                userId = Integer.parseInt(idStr);
            } catch (NumberFormatException ex) {
                validationMsgs.add("IDは4桁の整数で入力してください。");
            }
        }
        if (isEmpty(name)) {
            validationMsgs.add("名前入力は必須です");
        }
        if (isEmpty(password)) {
            validationMsgs.add("パスワード設定は必須です");
        }
        if (isEmpty(email)) {
            validationMsgs.add("メールアドレス設定は必須です");
        }
        if (isEmpty(gender)) {
            validationMsgs.add("性別の設定は必須です");
        } else if (!"M".equals(gender) && !"F".equals(gender)) {
            validationMsgs.add("性別の値が不正です");
        }
      
        try {
            authority = Integer.parseInt(authorityStr);
            if (authority < 1 || authority > 3) {
                validationMsgs.add("権限の値が不正です");
            }
        } catch (NumberFormatException ex) {
            validationMsgs.add("権限設定は必須です");
        }

        String jspPath = "/homepages/register.jsp"; // 入力画面(実際のパスに合わせてください)
        boolean isRedirect = false;

        // --- DB処理(入力チェックOKのときだけ) ---
        if (validationMsgs.isEmpty()) {
            try (Connection con = getConnection()) {

                // 重複チェック
                int count = 0;
                try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT)) {
                    stmt.setInt(1, userId);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            count = rs.getInt("count");
                        }
                    }
                }

                if (count > 0) {
                    validationMsgs.add("そのIDは使用されています。別のものを指定してください。");
                } else {
                    // 登録(本番ではパスワードをハッシュ化すること)
                    try (PreparedStatement stmt = con.prepareStatement(SQL_INSERT)) {
                        stmt.setInt(1, userId);
                        stmt.setString(2, name);
                        stmt.setString(3, email);
                        stmt.setString(4, password);
                        stmt.setString(5, gender);
                        stmt.setInt(6, authority);

                        if (stmt.executeUpdate() < 1) {
                            jspPath = "/homepages/ERROR_JSP.jsp";
                            request.setAttribute("errorMsg", "情報登録に失敗しました。もう一度初めからやり直してください");
                        } else {
                            isRedirect = true;
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                jspPath = "/homepages/ERROR_JSP.jsp";
                request.setAttribute("errorMsg", "もう一度最初からの操作をお願いします。");
            }
        }

        // --- 画面遷移 ---
        if (isRedirect) {
            response.sendRedirect(request.getContextPath() + "/pdservlet"); // 遷移先に合わせて変更
            return;
        }

        if (!validationMsgs.isEmpty()) {
            request.setAttribute("validationMsgs", validationMsgs);
            request.setAttribute("id", idStr);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("authority", authority);
            // パスワードは戻さない
        }
        request.getRequestDispatcher(jspPath).forward(request, response);
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBCドライバが見つかりません", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // GETでの登録は不可。フォームへ戻す
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/pdservlet");
    }
}
