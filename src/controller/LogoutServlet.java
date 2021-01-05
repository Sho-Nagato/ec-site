package controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 全てのセッション情報を削除する
        HttpSession session = request.getSession(false);
        if(Objects.nonNull(session)) {
            session.invalidate();
        }

        // ログイン画面に遷移
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
