CREATE TABLE if NOT EXISTS sales
(
    id        bigint auto_increment
        PRIMARY KEY,
    userId    bigint   NOT NULL,
    productId bigint   NOT NULL,
    addDate   datetime NULL DEFAULT NOW(),
    CONSTRAINT product_sale_id
        FOREIGN KEY (productId) REFERENCES products (id),
    constraint user_sale_id
    FOREIGN KEY (userId) REFERENCES users (id)
);