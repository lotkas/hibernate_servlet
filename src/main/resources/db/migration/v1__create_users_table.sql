CREATE TABLE if NOT EXISTS user
(
    id        bigint auto_increment
        PRIMARY KEY,
    firstName VARCHAR(255)   NOT NULL,
    lastName  VARCHAR(255)   NOT NULL,
    password  VARCHAR(255)   NOT NULL,
    balance   DECIMAl(10, 2) NOT NULL DEFAULT 0
);