package model;

import java.io.Serializable;

public class CategoryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int categoryId;
    private String categoryName;

    public CategoryModel(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
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
}
