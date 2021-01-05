package model;

import java.io.Serializable;

public class SalesModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private int productCode;
    private int salesPrice;

    public SalesModel(int userId, int productCode, int salesPrice) {
        this.userId = userId;
        this.productCode = productCode;
        this.salesPrice = salesPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(int salesPrice) {
        this.salesPrice = salesPrice;
    }
}
