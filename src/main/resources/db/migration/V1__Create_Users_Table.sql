create table if not exists user
(
    id        bigint auto_increment
        primary key,
    firstName varchar(255)   not null,
    lastName  varchar(255)   not null,
    password  varchar(255)   not null,
    balance   decimal(10, 2) not null
);