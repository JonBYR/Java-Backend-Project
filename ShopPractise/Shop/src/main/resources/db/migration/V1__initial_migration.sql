create table users
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table addresses
(
    id       bigint auto_increment
        primary key,
    street   varchar(255) not null,
    city     varchar(255) not null,
    postcode varchar(255) not null,
    userid   bigint       not null,
    constraint addresses_users_id_fk
        foreign key (userid) references users (id)
);