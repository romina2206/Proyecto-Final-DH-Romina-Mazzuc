package com.example.rr.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class RentRequestDTO {
    private Long userId;
    //private List<Long> productIds;
    private Long productId;
    private LocalDate rentDate;
    private LocalDate startDate;
    private LocalDate endDate;

}
