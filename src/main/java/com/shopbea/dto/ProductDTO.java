package com.shopbea.dto;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private long categoryId;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
