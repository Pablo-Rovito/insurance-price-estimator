CREATE DATABASE insurance;

CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    policy_holder VARCHAR(255) NOT NULL,
    premium NUMERIC(10, 2) NOT NULL,
    coverage_type VARCHAR(255) NOT NULL
);

CREATE TABLE discounts (
    id SERIAL PRIMARY KEY,
    quote_id BIGINT UNIQUE NOT NULL,
    discount_percentage DOUBLE PRECISION NOT NULL,
    end_price DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_quote FOREIGN KEY (quote_id) REFERENCES quotes (id)
);