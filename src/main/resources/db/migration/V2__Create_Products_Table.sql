create table if not exists product
(
    id        bigint auto_increment
        primary key,
    name      varchar(255) not null,
    price     decimal      not null,
    available bigint       not null
);