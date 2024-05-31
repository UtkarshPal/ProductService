package com.scaler.demoproject.model;

import com.scaler.demoproject.dto.FakeStoreProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    public String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;

    public FakeStoreProductDto toFakeStoreDTO(){
        FakeStoreProductDto fs = new FakeStoreProductDto();
        fs.setId(id);
        fs.setTitle(title);
        fs.setCategory(category.getTitle());
        fs.setImage(imageUrl);
        fs.setDescription(description);
        fs.setPrice(price);
        return fs;
    }
}
