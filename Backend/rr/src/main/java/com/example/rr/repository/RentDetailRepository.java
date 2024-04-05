package com.example.rr.repository;

import com.example.rr.entity.RentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDetailRepository extends JpaRepository<RentDetail, Long> {
}

