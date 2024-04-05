package com.example.rr.controller;

import com.example.rr.dto.ImageRequestDTO;
import com.example.rr.dto.ImageResponseDTO;
import com.example.rr.entity.Product;
import com.example.rr.repository.ProductRepository;
import com.example.rr.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;
    private final ProductRepository productRepository;

    public ImageController(ImageService imageService, ProductRepository productRepository) {
        this.imageService = imageService;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<ImageResponseDTO> createImage(@RequestBody ImageRequestDTO imageRequestDTO) {
        ImageResponseDTO createdImage = imageService.createImage(imageRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageResponseDTO> getImage(@PathVariable Long imageId) {
        ImageResponseDTO image = imageService.getImageById(imageId);
        if (image != null) {
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDTO>> getAllImages() {
        List<ImageResponseDTO> images = imageService.getAllImages();
        if (!images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<ImageResponseDTO>> getAllImagesByProduct(@PathVariable Long productId) {

        //Optional<Product> product = productRepository.findById(productId);
        Product p = new Product();
        p.setId(productId);
        List<ImageResponseDTO> images = imageService.getAllImagesByProduct(p);  // en caso de usar el optional de arriba, sustituir la variable 'p' por esta linea: product.orElse(null)
        if (!images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}

