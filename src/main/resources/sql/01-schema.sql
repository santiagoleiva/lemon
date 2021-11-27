create table lemon.user
(
    id        int auto_increment primary key,
    firstname varchar(50) not null,
    lastname  varchar(50) not null,
    alias     varchar(30) not null,
    email     varchar(60) not null,
    constraint user_alias_uindex
        unique (alias),
    constraint user_email_uindex
        unique (email),
    constraint user_id_uindex
        unique (id)
);

create table lemon.currency
(
    id          smallint auto_increment primary key,
    code        varchar(6)         not null,
    description varchar(30) null,
    decimals    smallint default 0 not null,
    constraint currency_code_uindex
        unique (code)
);
