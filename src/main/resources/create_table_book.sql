-- Creation du table book
CREATE TABLE IF NOT EXISTS "book"(
    "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
    "book_name" VARCHAR(255) NOT NULL,
    "page_numbers" INT CHECK("page_numbers" > 0) NOT NULL,
    "release_date" DATE NOT NULL,
    "topics" "topic"[],
    "id_author" VARCHAR(255) REFERENCES "author"("id")
);

-- Insertion pour le livre 1
INSERT INTO "book" VALUES
    ( 'id1', 'Livre 1', 200, '2023-11-01', '{COMEDY}', 'id1' ),
    ( 'id2', 'Livre 2', 300, '2023-02-11', '{ROMANCE,OTHER}', 'id2'),
    ( 'id3', 'Livre 3', 250, '2023-03-12', '{OTHER}', 'id3');