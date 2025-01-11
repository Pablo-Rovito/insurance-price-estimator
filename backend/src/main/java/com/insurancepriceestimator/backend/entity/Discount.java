package com.insurancepriceestimator.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    @Column(nullable = false)
    private double discountPercentage;

    @Column(nullable = false)
    private double endPrice;

    public Long getId() {
        return id;
    }

    public Discount setId(Long id) {
        this.id = id;
        return this;
    }

    public Quote getQuote() {
        return quote;
    }

    public Discount setQuote(Quote quote) {
        this.quote = quote;
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
