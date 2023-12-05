DROP TABLE IF EXISTS prize_laureates, laureates, prizes;

CREATE TABLE IF NOT EXISTS laureates  (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    share INTEGER,
    motivation TEXT
);

CREATE TABLE IF NOT EXISTS prizes (
    year INTEGER ,
    category VARCHAR(255),
    PRIMARY KEY (year, category)
);

CREATE TABLE prize_laureates (
    prize_year INTEGER,
    prize_category VARCHAR(255),
    laureate_id INTEGER,
    PRIMARY KEY (prize_year, prize_category, laureate_id),
    FOREIGN KEY (prize_year, prize_category) REFERENCES prizes(year, category),
    FOREIGN KEY (laureate_id) REFERENCES laureates(id)
);