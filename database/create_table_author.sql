-- Create author table
CREATE TABLE IF NOT EXISTS "author" (
    "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(255) NOT NULL,
    "sex" CHAR(1) CHECK (sex IN ('M', 'F')) NOT NULL
);

INSERT INTO "author" VALUES
    ('id1', 'Auteur 1', 'M'),
    ('id2', 'Auteur 2', 'F'),
    ('id3', 'Auteur 3', 'M');