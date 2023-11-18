-- Create author table
CREATE TABLE IF NOT EXISTS "author" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(255) NOT NULL,
    "sex" CHAR(1) CHECK (sex IN ('M', 'F')) NOT NULL
);

INSERT INTO "author" ("id", "name", "sex") VALUES ('f47b63d1-78a6-4d94-9160-48d30d85aa41', 'Author 1', 'M');
INSERT INTO "author" ("id", "name", "sex") VALUES ('3e6e15f7-b42b-4b50-98cb-914a4a7c82e2', 'Author 2', 'F');
INSERT INTO "author" ("id", "name", "sex") VALUES ('7f553c37-95b2-4d67-81f7-25d9f8db0a5e', 'Author 3', 'M');