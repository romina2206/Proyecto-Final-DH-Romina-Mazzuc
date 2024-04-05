package com.example.rr.service;

import com.example.rr.dto.RentRequestDTO;
import com.example.rr.dto.RentResponseDTO;
import com.example.rr.entity.Product;
import com.example.rr.entity.Rent;
import com.example.rr.entity.User;
import com.example.rr.repository.ProductRepository;
import com.example.rr.repository.RentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    @Autowired
    private final EmailService emailService;

    public RentService(RentRepository rentRepository, ModelMapper modelMapper,
                       ProductRepository productRepository, EmailService emailService) {
        this.rentRepository = rentRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    public RentResponseDTO createRent(RentRequestDTO rentRequestDTO, User user, Product product) {
        Rent rent = modelMapper.map(rentRequestDTO, Rent.class);
        rent.setRentDate(LocalDate.now()); // Por defecto, la fecha de alquiler se establece como la actual
        rent.setCreatedDate(LocalDateTime.now()); // Por defecto, la fecha de alquiler se establece como la actual
        rent.setStatus("reservado"); // Por defecto, el alquiler se establece como "reservado"
        rent.setActive(true);
        rent.setUser(user);
        rent.setProduct(product);
        rent.setTotalPrice(this.calculatePrice(rentRequestDTO.getStartDate(), rentRequestDTO.getEndDate(), product.getPrice()));
        Rent savedRent = rentRepository.save(rent);

        // Send confirmation rent email
        emailService.sendRentConfirmationEmail(user.getUsername(), user.getEmail(),
                "http://localhost:3000/mis-reservas/" + user.getId(), product, rent);

        return mapToRentDTO(savedRent);
    }

    public List<RentResponseDTO> getAllRents(){
        // modelMapper.map(rentRepository.findAll(), RentResponseDTO.class);
        List<Rent> rents = rentRepository.findAll();

        return rents.stream()
                .map(this::mapToRentDTO)
                .collect(Collectors.toList());

    }

    public List<RentResponseDTO> getAllRentsByUser(User user){

        List<Rent> rents = rentRepository.findByUser(user);

        return rents.stream()
                .map(this::mapToRentDTO)
                .collect(Collectors.toList());

    }

    private RentResponseDTO mapToRentDTO(Rent rent) {
        Optional<Product> product = productRepository.findById(rent.getProduct().getId());
        RentResponseDTO rentResponseDTO = modelMapper.map(rent, RentResponseDTO.class);
        rentResponseDTO.setProductName(product.orElse(null).getName());
        //rentResponseDTO.setPrice(product.orElse(null).getPrice());
        return rentResponseDTO;
    }

    private Double calculatePrice(LocalDate startDate, LocalDate endDate, Double priceDay){
        return DAYS.between(startDate, endDate) * priceDay;
    }



}