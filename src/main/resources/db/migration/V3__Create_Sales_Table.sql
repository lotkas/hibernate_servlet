create table if not exists sales
(
    id        bigint auto_increment
        primary key,
    userId    bigint   not null,
    productId bigint   not null,
    addDate   datetime null,
    constraint product_sale_id
        foreign key (productId) references products (id),
    constraint user_sale_id
        foreign key (userId) references users (id)
);