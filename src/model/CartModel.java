package model;

import java.io.Serializable;
import java.util.List;

public class CartModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private List<CartProductModel> cartProductModelList;
    private int tax;
    private int totalPrice;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartProductModel> getCartProductModelList() {
        return cartProductModelList;
    }

    public void setCartProductModelList(List<CartProductModel> cartProductModelList) {
        this.cartProductModelList = cartProductModelList;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
