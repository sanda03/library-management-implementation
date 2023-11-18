-- Create author table
CREATE TABLE IF NOT EXISTS "author" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(255) NOT NULL,
    "sex" CHAR(1) CHECK (sex IN ('M', 'F')) NOT NULL
);

-- Insertion de données dans la table "author"
INSERT INTO "author" ("name", "sex")
VALUES
    ('Author 1', 'M'),
    ('Author 2', 'F'),
    ('Author 3', 'M');