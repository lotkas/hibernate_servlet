CREATE TABLE if NOT EXISTS managers
(
    id        bigint auto_increment
        PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL
);