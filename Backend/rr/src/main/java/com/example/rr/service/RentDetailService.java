package com.example.rr.service;

import com.example.rr.dto.RentDetailDTO;
import com.example.rr.entity.RentDetail;
import com.example.rr.repository.RentDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RentDetailService {

    private final RentDetailRepository rentDetailRepository;
    private final ModelMapper modelMapper;

    public RentDetailService(RentDetailRepository rentDetailRepository, ModelMapper modelMapper) {
        this.rentDetailRepository = rentDetailRepository;
        this.modelMapper = modelMapper;
    }

    public RentDetailDTO createRentDetail(RentDetailDTO rentDetailDTO) {
        RentDetail rentDetail = modelMapper.map(rentDetailDTO, RentDetail.class);
        RentDetail savedRentDetail = rentDetailRepository.save(rentDetail);
        return modelMapper.map(savedRentDetail, RentDetailDTO.class);
    }

}
