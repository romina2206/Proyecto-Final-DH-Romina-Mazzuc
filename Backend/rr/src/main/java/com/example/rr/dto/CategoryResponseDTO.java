package com.example.rr.dto;

import com.example.rr.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private Boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private User createdBy;
    private User updatedBy;
}
