package controller;

import java.io.IOException;
import java.util.HashMap;
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

        // 重複するカート情報をグルーピング
        groupingCartProductModelList(cartModel);

        // 合計金額 (税抜)
        int totalPrice = (int)cartModel.getCartProductModelList()
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

    private void groupingCartProductModelList(CartModel cartModel) {
        // 商品コード毎の購入個数を集計 (key:商品コード value:購入個数)
        Map<Integer, Integer> purchasePlanNumberMap = cartModel.getCartProductModelList().
                stream().
                collect(Collectors.groupingBy(CartProductModel::getProductCode,
                        Collectors.summingInt(CartProductModel::getPurchasePlanNumber)));

        // 商品情報の重複を削除
        Map<Integer, Boolean> cartProductMap = new HashMap<>();
        List<CartProductModel> distinctCartProductList = cartModel.getCartProductModelList().
                stream().
                filter(p -> cartProductMap.putIfAbsent(p.getProductCode(), Boolean.TRUE) == null).
                collect(Collectors.toList());

        // 購入個数を更新
        distinctCartProductList.
            stream().
            forEach(p -> p.setPurchasePlanNumber(purchasePlanNumberMap.get(p.getProductCode())));

        cartModel.setCartProductModelList(distinctCartProductList);
    }
}
