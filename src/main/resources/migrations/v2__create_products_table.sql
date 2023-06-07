CREATE TABLE if NOT EXISTS products
(
    id        bigint auto_increment
        PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    price     DECIMAL      NOT NULL,
    available bigint       NOT NULL DEFAULT 0
);