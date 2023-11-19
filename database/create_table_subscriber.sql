CREATE TABLE IF NOT EXISTS "subscriber" (
    "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(255) NOT NULL,
    "ref" VARCHAR(255) NOT NULL
);

INSERT INTO "subscriber" values
     ('id1', 'Subscriber 1', 'ref1'),
     ('id2', 'Subscriber 2', 'ref2'),
     ('id3', 'Subscriber 3', 'ref3');