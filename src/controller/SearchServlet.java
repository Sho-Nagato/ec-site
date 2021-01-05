package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.ProductDao;
import model.CategoryModel;
import model.ProductModel;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    private final int CATEGORY_ALL = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータ―の文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // 検索画面で入力された商品名とカテゴリーを取得
        String productName = request.getParameter("product_name");
        int categoryId = Integer.parseInt(request.getParameter("category"));

        List<ProductModel> productModelList = new ArrayList<>();
        ProductDao productDao = new ProductDao();

        // 商品情報一覧を取得
        if (productName.isEmpty() && categoryId == CATEGORY_ALL) {
            // 商品名：空白、カテゴリー：すべて
            productModelList = productDao.findAll();
        } else if (productName.isEmpty() && categoryId != CATEGORY_ALL) {
            // 商品名：空白、カテゴリー：すべて以外
            productModelList = productDao.findByCategory(categoryId);
        } else if (!productName.isEmpty() && categoryId == CATEGORY_ALL) {
            // 商品名：空白以外、カテゴリー：すべて
            productModelList = productDao.findByProductName(productName);
        } else if (!productName.isEmpty() && categoryId != CATEGORY_ALL) {
            // 商品名：空白以外、カテゴリー：すべて以外
            productModelList = productDao.find(categoryId, productName);
        }

        if (productModelList.size() == 0) {
            request.setAttribute("search_result_message", "検索結果が0件です。");
        } else {
            // 商品情報一覧をリクエストスコープに保存
            request.setAttribute("product_list", productModelList);
        }

        // 検索画面に遷移
        request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // カテゴリー情報取得
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryModel> categoryModelList = categoryDao.findAll();

        // カテゴリー情報をセッションスコープに保存
        request.getSession().setAttribute("category_list", categoryModelList);

        // 検索画面に遷移
        request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
    }
}
