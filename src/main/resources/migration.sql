CREATE DATABASE IF NOT EXISTS library_management;

\c library_management;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create author table
CREATE TABLE IF NOT EXISTS "author" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(255) NOT NULL,
    "sex" CHAR(1) CHECK (sex IN ('M', 'F')) NOT NULL,
);

-- Create book table
CREATE TABLE IF NOT EXISTS "book"(
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "book_name" VARCHAR(255) NOT NULL,
    "page_numbers" INT CHECK("page_numbers" > 0) NOT NULL,
    "releaseDate" TIMESTAMP NOT NULL,
);