--liquibase formatted sql

--changeset veselint:1
ALTER TABLE USERS
ADD PASSWORD VARCHAR(255) NOT NULL;