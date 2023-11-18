-- Create topic type
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'topic') THEN
        CREATE TYPE "topic" AS ENUM ('COMEDY', 'ROMANCE', 'OTHER');
    END IF;
END $$;

-- Create book table
CREATE TABLE IF NOT EXISTS "book"(
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "book_name" VARCHAR(255) NOT NULL,
    "page_numbers" INT CHECK("page_numbers" > 0) NOT NULL,
    "release_date" DATE NOT NULL,
    "topics" "topic"[],
    "id_author" UUID REFERENCES "author"("id")
);

-- Insertion pour le livre 1
INSERT INTO "book" VALUES ( 'a0b5aa7d-9c95-4a7b-8b4f-d6b3459c1a4b', 'Book 1', 200, '2023-01-01', ARRAY['COMEDY'::"topic"], 'f47b63d1-78a6-4d94-9160-48d30d85aa41' );
INSERT INTO "book" VALUES ( '6d02bcad-558b-4f9c-bb9a-682f28b7b582', 'Book 2', 300, '2023-02-01', ARRAY['ROMANCE'::"topic", 'OTHER'::"topic"], '3e6e15f7-b42b-4b50-98cb-914a4a7c82e2');
INSERT INTO "book" VALUES ( '72b9a3d0-793a-4a7d-8314-79f8b94008f2', 'Book 3', 250, '2023-03-01', ARRAY['OTHER'::"topic"], '7f553c37-95b2-4d67-81f7-25d9f8db0a5e');