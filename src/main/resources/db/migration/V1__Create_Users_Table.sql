create table if not exists users
(
    id        bigint         not null
        primary key,
    firstName varchar(255)   not null,
    lastName  varchar(255)   not null,
    password  varchar(255)   not null,
    balance   decimal(10, 2) not null
);