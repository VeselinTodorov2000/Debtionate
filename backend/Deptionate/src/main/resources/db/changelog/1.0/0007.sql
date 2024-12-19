--liquibase formatted sql

--changeset veselint:1
ALTER TABLE PAYMENTS
ALTER COLUMN payment_date TYPE DATE;
