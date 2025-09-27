package com.joshua.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity(name="Categories")
public class Category {
    @Id
    private int categoryId;
    private String categoryName;
    public Category(int categoryId, String categoryName) {
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
