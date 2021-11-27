create table lemon.usuario
(
    id       int auto_increment primary key,
    nombre   varchar(50) not null,
    apellido varchar(50) not null,
    alias    varchar(30) not null,
    email    varchar(60) not null,
    constraint usuario_alias_uindex
        unique (alias),
    constraint usuario_email_uindex
        unique (email),
    constraint usuario_id_uindex
        unique (id)
);

create table lemon.moneda
(
    id          smallint auto_increment
        primary key,
    codigo      varchar(6)         not null,
    descripcion varchar(30)        null,
    decimales   smallint default 0 not null,
    constraint moneda_codigo_uindex
        unique (codigo)
);
