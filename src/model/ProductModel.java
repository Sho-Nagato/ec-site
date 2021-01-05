package model;

import java.io.Serializable;

public class ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int productCode;
    private String productName;
    private int stockNo;
    private int productPrice;
    private int categoryId;
    private String categoryName;
    private String productImage;
    private String productMessage;

    public ProductModel(
            int productCode,
            String productName,
            int stockNo,
            int productPrice,
            int categoryId,
            String categoryName,
            String productImage,
            String productMessage) {
        this.productCode = productCode;
        this.productName = productName;
        this.stockNo = stockNo;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productImage = productImage;
        this.productMessage = productMessage;
    }

    public ProductModel(
            int productCode,
            String productName,
            int stockNo,
            int productPrice,
            int categoryId,
            String productImage,
            String productMessage) {
        this.productCode = productCode;
        this.productName = productName;
        this.stockNo = stockNo;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
        this.productImage = productImage;
        this.productMessage = productMessage;
    }

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

    public int getStockNo() {
        return stockNo;
    }

    public void setStockNo(int stockNo) {
        this.stockNo = stockNo;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductMessage() {
        return productMessage;
    }

    public void setProductMessage(String productMessage) {
        this.productMessage = productMessage;
    }
}
