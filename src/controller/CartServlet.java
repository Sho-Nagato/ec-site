package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import model.CartModel;
import model.CartProductModel;
import model.ProductModel;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // カート画面に遷移
        request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータ―の文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // 商品紹介画面で選択された商品の商品コードを取得
        int productCode = Integer.parseInt(request.getParameter("product_code"));

        // 商品情報を取得
        ProductDao productDao = new ProductDao();
        ProductModel productModel = productDao.findByProductCode(productCode);

        // 商品紹介画面で選択された商品の購入予定個数を取得
        int purchasePlanNumber = Integer.parseInt(request.getParameter("number"));

        // セッション情報を取得
        HttpSession session = request.getSession();

        // セッション情報からカート情報を取得
        CartModel cartModel = (CartModel)session.getAttribute("cart");

        // カート情報を更新
        List<CartProductModel> cartProductModelList = cartModel.getCartProductModelList();
        CartProductModel cartProductModel = new CartProductModel();
        cartProductModel.setProductCode(productModel.getProductCode());
        cartProductModel.setProductName(productModel.getProductName());
        cartProductModel.setProductPrice(productModel.getProductPrice());
        cartProductModel.setPurchasePlanNumber(purchasePlanNumber);
        cartProductModelList.add(cartProductModel);
        cartModel.setCartProductModelList(cartProductModelList);

        // TODO ここにカート情報のグルーピング処理が入る

        // 合計金額 (税抜)
        int totalPrice = (int)cartProductModelList
                .stream()
                .mapToInt(c -> (c.getProductPrice() * c.getPurchasePlanNumber())).sum();

        // 合計金額 (税込)
        int taxIncludedTotalPrice = (int) (totalPrice * 1.1);
        cartModel.setTotalPrice(taxIncludedTotalPrice);

        // 消費税 = 合計金額 (税込) - 合計金額 (税抜)
        cartModel.setTax(taxIncludedTotalPrice - totalPrice);

        // カート情報をセッションに保存
        session.setAttribute("cart", cartModel);

        // カート画面に遷移
        response.sendRedirect("/ec-site/CartServlet");
    }
}
