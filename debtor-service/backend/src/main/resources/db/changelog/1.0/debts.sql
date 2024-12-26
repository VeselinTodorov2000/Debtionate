--liquibase formatted sql

--changeset veselint:1
CREATE TABLE DEBTS (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       amount NUMERIC(10, 2) NOT NULL,
                       due_date DATE NOT NULL,
                       status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
