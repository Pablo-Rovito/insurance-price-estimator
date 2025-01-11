package com.insurancepriceestimator.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quote_id", nullable = false)
    private Long quoteId;

    @Column(name = "discount_percentage", nullable = false)
    private double discountPercentage;

    @Column(name = "end_price", nullable = false)
    private double endPrice;

    public Long getId() {
        return id;
    }

    public Discount setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public Discount setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
        return this;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public Discount setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public Discount setEndPrice(double endPrice) {
        this.endPrice = endPrice;
        return this;
    }
}
