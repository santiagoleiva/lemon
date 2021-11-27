create table lemon.user
(
    id       int auto_increment primary key,
    nombre   varchar(50) not null,
    apellido varchar(50) not null,
    alias    varchar(30) not null,
    email    varchar(60) not null,
    constraint user_alias_uindex
        unique (alias),
    constraint user_email_uindex
        unique (email),
    constraint user_id_uindex
        unique (id)
);
