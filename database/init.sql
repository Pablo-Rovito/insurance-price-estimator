CREATE DATABASE insurance;
\c insurance;

CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(100) NOT NULL,
    coverage_amount NUMERIC NOT NULL,
    monthly_rate NUMERIC NOT NULL
);