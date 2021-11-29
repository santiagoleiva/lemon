insert into lemon.user(firstname, lastname, alias, email)
values ('Alan', 'Castillo', 'acastillo', 'alan.castillo@lemon.me'),
       ('Melanie', 'Villalobos', 'mvillalobos', 'melanie.villalobos@lemon.me'),
       ('Mauro', 'Gonzalez', 'mgonzalez', 'mauro.gonzalez@lemon.me');

insert into lemon.currency(code, description, decimals)
values ('ARS', 'Peso Argentino', 2),
       ('USDT', 'Tether', 2),
       ('BTC', 'Bitcoin', 8);

insert into lemon.user_wallet(user_id, currency_id, balance)
select u.id, c.id, 0
from lemon.currency as c,
     lemon.user as u