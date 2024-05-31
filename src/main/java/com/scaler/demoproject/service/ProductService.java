package com.scaler.demoproject.service;

import com.scaler.demoproject.model.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
    String deleteProduct (Long productId);
    String[] getAllCategories();
    List<Product> getProductsByCategory(String categoryName);
    List<Product> getLimitedProducts(String limit);
    List<Product> sortedProductSet(String sortingOrder);
}
