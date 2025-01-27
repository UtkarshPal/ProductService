package com.scaler.demoproject.dto;

import com.scaler.demoproject.model.Category;
import com.scaler.demoproject.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product p = new Product();
        p.setId(id);
        p.setTitle(title);
        p.setPrice(price);
        p.setDescription(description);
        p.setImageUrl(image);

        Category cat = new Category();
        cat.setTitle(category);
        p.setCategory(cat);
        return p;
    }

    @Override
    public String toString() {
        return "FakeStoreProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


    public FakeStoreProductDto merge (FakeStoreProductDto fakeStoreProductDto, FakeStoreProductDto fsDTO){
        if (null != fsDTO.getId()){
            fakeStoreProductDto.setId(fsDTO.getId());
        }
        if (null != fsDTO.getTitle()){
            fakeStoreProductDto.setTitle(fsDTO.getTitle());
        }

        if (null != (Double)fsDTO.getPrice()){
            fakeStoreProductDto.setPrice(fsDTO.getPrice());
        }

        if (null != fsDTO.getDescription()){
            fakeStoreProductDto.setDescription(fsDTO.getDescription());
        }

        if (null != fsDTO.getImage()){
            fakeStoreProductDto.setImage(fsDTO.getImage());
        }

        if (null != fsDTO.getCategory()){
            fakeStoreProductDto.setCategory(fsDTO.getCategory());
        }
        return fakeStoreProductDto;
     }
}
