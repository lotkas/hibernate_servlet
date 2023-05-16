create table if not exists managers
(
    id        bigint auto_increment
        primary key,
    firstName varchar(255) not null,
    lastName  varchar(255) not null,
    password  varchar(255) not null
);