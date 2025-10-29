package com.joshua.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int productId;
    private String productName;
    private String image;
    private int quantity;
    private double price;
    private double discount;
    private double specialPrice;

}
