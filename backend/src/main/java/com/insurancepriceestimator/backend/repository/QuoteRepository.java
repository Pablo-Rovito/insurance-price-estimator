package com.insurancepriceestimator.backend.repository;

import com.insurancepriceestimator.backend.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
