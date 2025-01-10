CREATE DATABASE insurance;

CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    policy_holder VARCHAR(255) NOT NULL,
    premium NUMERIC(10, 2) NOT NULL,
    coverage_type VARCHAR(255) NOT NULL
);