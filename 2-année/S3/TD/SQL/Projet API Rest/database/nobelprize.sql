DROP TABLE IF EXISTS laureatesPrizes, laureates, prizes;

CREATE TABLE prizes(
    id INT,
    year INT,
    category VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE laureates(
    id INT,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    motivation VARCHAR(255),
    share INT,
    PRIMARY KEY (id)
);

CREATE TABLE laureatesPrizes(
    id INT,
    laureateId INT,
    prizeId INT,
    PRIMARY KEY (id),
    FOREIGN KEY (laureateId) REFERENCES laureates(id),
    FOREIGN KEY (prizeId) REFERENCES prizes(id)
)