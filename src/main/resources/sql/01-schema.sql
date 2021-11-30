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

create table lemon.user_wallet
(
    user_id     int                      not null,
    currency_id smallint                 not null,
    balance     decimal(16, 8) default 0 not null check (balance >= 0),
    constraint user_wallet_currency_id_fk
        foreign key (currency_id) references currency (id),
    constraint user_wallet_user_id_fk
        foreign key (user_id) references user (id)
);

create table lemon.movement
(
    id               bigint auto_increment primary key,
    user_id          int                      not null,
    currency_id      smallint                 not null,
    type             varchar(10)              not null,
    amount           decimal(16, 8) default 0 not null,
    previous_balance decimal(16, 8) default 0 not null,
    created_at       datetime                 not null default now(),
    constraint user_movement_user_id_fk
        foreign key (user_id) references user (id),
    constraint currency_movement_id_fk
        foreign key (currency_id) references currency (id)
);
