package com.joshua.product;

import com.joshua.payload.ProductDTO;
import com.joshua.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDTO addProduct(Integer categoryId, ProductDTO product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Integer categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Integer productId, ProductDTO product);

    ProductDTO deleteProduct(Integer productId);

    ProductDTO updateProductImage(Integer productId, MultipartFile image) throws IOException;
}
