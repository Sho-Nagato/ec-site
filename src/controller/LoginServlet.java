package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.CartModel;
import model.CartProductModel;
import model.UserModel;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ログイン画面に遷移
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータ―の文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // ログイン画面で入力されたログインコードとパスワードを取得
        String loginCd = request.getParameter("login_cd");
        String loginPw = request.getParameter("login_pw");

        // ログインコードとパスワードの入力チェック
        if (loginCd.isEmpty() || loginPw.isEmpty()) {
            request.setAttribute("error_message", "名前又はパスワードが未入力です。");
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        // ユーザー情報取得
        UserDao userDao = new UserDao();
        List<UserModel> userModelList = userDao.find(loginCd, loginPw);

        // ログイン成功 / 失敗チェック
        if (userModelList.size() >= 1) {
            // ログイン成功 (ユーザー情報を取得できた)
            CartModel cartModel = new CartModel();
            cartModel.setUserId(userModelList.get(0).getUserId());
            cartModel.setCartProductModelList(new ArrayList<CartProductModel>());
            request.getSession().setAttribute("cart", cartModel);
            request.getRequestDispatcher("/SearchServlet").forward(request, response);
        } else {
            // ログイン失敗 (ユーザー情報を取得できなかった)
            request.setAttribute("error_message", "名前又はパスワードが間違っています。");
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
