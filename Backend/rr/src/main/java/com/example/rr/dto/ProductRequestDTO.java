package com.example.rr.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private String category;
    private Double price;
  //  private Set<Long> featureIds;
    //private Set<Long> relatedProductIds;
    private Boolean active;
    private String imageUrl;
}

