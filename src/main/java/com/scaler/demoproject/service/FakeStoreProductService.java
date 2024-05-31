package com.scaler.demoproject.service;


import com.scaler.demoproject.dto.FakeStoreProductDto;
import com.scaler.demoproject.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] lstfakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class);
        List<Product> product_list = new ArrayList<Product>();
        for (FakeStoreProductDto fs : lstfakeStoreProductDto) {
            product_list.add(fs.toProduct());
        }
        return product_list;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fs = product.toFakeStoreDTO();
        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products", fs,
                FakeStoreProductDto.class
        );
        return response.toProduct();
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fs = product.toFakeStoreDTO();
        FakeStoreProductDto response = new FakeStoreProductDto();
        //  Checking for the product which we want to update, weather exist or not
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );
        if (fakeStoreProductDto != null){ //product exist, so we need to use @Patch
            fs = fakeStoreProductDto.merge(fakeStoreProductDto, fs);
            response = restTemplate.patchForObject(
                    "https://fakestoreapi.com/products/" + productId, fs, FakeStoreProductDto.class
            );
        } else{ // product doesn't exist, so we need to create product @Put
            restTemplate.put(
                    "https://fakestoreapi.com/products/" + productId, fs, FakeStoreProductDto.class
            );
            response = fs; // To show the response to user so that user know what is created. As put() has no return type.
        }
        return response.toProduct();
    }

    @Override
    public String deleteProduct(Long productId){
        restTemplate.delete(
                "https://fakestoreapi.com/products/"+productId);
        return "Product Successfully deleted with product id : "+productId;
    }

    public String[] getAllCategories(){
        String[] categories = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class );
        return categories;
    }

    public List<Product> getProductsByCategory(String categoryName){
        FakeStoreProductDto[] arrFakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/"+ categoryName , FakeStoreProductDto[].class
        );
        List<Product> productList = new ArrayList<Product>();
        for (FakeStoreProductDto fspDTO : arrFakeStoreProductDto){
            productList.add(fspDTO.toProduct());
        }
        return productList;
    }

    public List<Product> getLimitedProducts(String limit){
        FakeStoreProductDto[] arrFakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products?limit="+limit, FakeStoreProductDto[].class
        );
        List<Product> productList = new ArrayList<Product>();
        for (FakeStoreProductDto fspDTO : arrFakeStoreProductDto){
            productList.add(fspDTO.toProduct());
        }
        return productList;
    }

    public List<Product> sortedProductSet(String sortingOrder){
        FakeStoreProductDto[] arrFakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products?sort="+sortingOrder, FakeStoreProductDto[].class
        );
        List<Product> productList = new ArrayList<Product>();
        for (FakeStoreProductDto fspDTO : arrFakeStoreProductDto){
            productList.add(fspDTO.toProduct());
        }
        return productList;
    }

}
