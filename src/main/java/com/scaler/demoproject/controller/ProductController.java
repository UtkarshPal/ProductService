package com.scaler.demoproject.controller;

import com.scaler.demoproject.model.Product;
import com.scaler.demoproject.service.FakeStoreProductService;
import com.scaler.demoproject.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    /*Sample input in JSON format for createProduct()*/
    /*{
        "title":"Apple airpods",
            "price":25000,
            "description":"Best airpods ever",
            "image":"https://i.pravatar.cc",
            "category":{
        "id":null,
                "title":"electronic"
    }
    }*/

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        // Whenever someone is doing a post request on /product
        // Plz execute this method
        Product postRequestResponse = productService.createProduct(product);
        return postRequestResponse;
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) {
        // Whenever someone is doing a get request on /product/{id}
        // Plz execute this method
        Product currentProduct = productService.getSingleProduct(productId);
        return currentProduct;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> postRequestResponse = productService.getAllProducts();
        return postRequestResponse;

    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(productId, product);
        return updatedProduct;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        return productService.deleteProduct(productId);
    }

    @GetMapping("/Categories")
    public String[] getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/productsByCategory/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String categoryName){
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/ProductsbyLimit/{limit}")
    public List<Product> getLimitedProducts(@PathVariable("limit") String limit){
        return productService.getLimitedProducts(limit);
    }

    @GetMapping("/sortProducts/{sortingOrder}")
    public List<Product> sortedProductSet(@PathVariable("sortingOrder") String sortingOrder){
        return productService.sortedProductSet(sortingOrder);
    }
}
