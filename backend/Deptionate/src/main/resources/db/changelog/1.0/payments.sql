--liquibase formatted sql

--changeset veselint:1
CREATE TABLE PAYMENTS
(
    id           BIGSERIAL PRIMARY KEY,
    debt_id      BIGINT         NOT NULL,
    amount       NUMERIC(10, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_debt FOREIGN KEY (debt_id) REFERENCES debts (id) ON DELETE CASCADE
);
