package model;

import java.io.Serializable;

public class CartProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int productCode;
    private String productName;
    private int productPrice;
    private int purchasePlanNumber;

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getPurchasePlanNumber() {
        return purchasePlanNumber;
    }

    public void setPurchasePlanNumber(int purchasePlanNumber) {
        this.purchasePlanNumber = purchasePlanNumber;
    }
}
