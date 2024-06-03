-- V1__CreateUsersTable.sql - 03/06/2024 Diego L. F. S.

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    document VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance DECIMAL(19, 2) NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    CONSTRAINT chk_user_type CHECK (user_type IN ('COMMON', 'MERCHANT'))
);

