package com.insurancepriceestimator.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String policyHolder;

    @Column(nullable = false)
    private Double premium;

    @Column(nullable = false)
    private String coverageType;

    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    private Discount discount;

    public Long getId() {
        return id;
    }

    public Quote setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public Quote setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
        return this;
    }

    public Double getPremium() {
        return premium;
    }

    public Quote setPremium(Double premium) {
        this.premium = premium;
        return this;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public Quote setCoverageType(String coverageType) {
        this.coverageType = coverageType;
        return this;
    }

    public Discount getDiscount() {
        return discount;
    }

    public Quote setDiscount(Discount discount) {
        this.discount = discount;
        return this;
    }
}
