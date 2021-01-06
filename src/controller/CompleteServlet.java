package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import dao.SalesDao;
import model.CartModel;
import model.CartProductModel;
import model.SalesModel;

@WebServlet("/CompleteServlet")
public class CompleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッション情報を取得
        HttpSession session = request.getSession();

        // セッション情報からカート情報を取得
        CartModel cartModel = (CartModel)session.getAttribute("cart");

        // 商品毎の在庫数を取得
        ProductDao productDao = new ProductDao();
        List<Integer> productCodeList = cartModel.getCartProductModelList()
                .stream()
                .map(p -> Integer.valueOf(p.getProductCode()))
                .collect(Collectors.toList());
        Map<Integer, Integer> productStockNoMap = productDao.getStockNoMap(productCodeList);

        // 購入テーブルと商品テーブルを更新
        for (CartProductModel cartProduct : cartModel.getCartProductModelList()) {
            SalesModel salesModel = new SalesModel(
                    cartModel.getUserId(),
                    cartProduct.getProductCode(),
                    cartProduct.getProductPrice());
            new SalesDao().insert(salesModel);

            // 更新後在庫数 = 更新前在庫数 - 購入数
            int productCode = cartProduct.getProductCode();
            int stockNo = productStockNoMap.get(productCode) - cartProduct.getPurchasePlanNumber();
            productDao.updateStockNo(productCode, stockNo);
        }

        // セッション情報のカート情報(購入商品リスト、消費税、合計金額)を更新
        cartModel.setCartProductModelList(new ArrayList<CartProductModel>());
        cartModel.setTax(0);
        cartModel.setTotalPrice(0);
        session.setAttribute("cart", cartModel);

        // 購入完了画面に遷移
        request.getRequestDispatcher("WEB-INF/jsp/complete.jsp").forward(request, response);
    }
}
