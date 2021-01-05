package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import model.ProductModel;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータ―の文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // 商品検索画面で選択された商品の商品コードを取得
        int productCode = Integer.parseInt(request.getParameter("product_code"));

        // 商品情報を取得
        ProductDao productDao = new ProductDao();
        ProductModel productModel = productDao.findByProductCode(productCode);

        // 商品情報をリクエストスコープに保存
        request.setAttribute("product", productModel);

        // 商品紹介画面に遷移
        request.getRequestDispatcher("WEB-INF/jsp/product_detail.jsp").forward(request, response);
    }
}
