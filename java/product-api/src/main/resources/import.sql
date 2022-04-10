insert into category (id, description) values (1, 'Comic Books');
insert into category (id, description) values (2, 'Movies');
insert into category (id, description) values (3, 'Books');

insert into supplier (id, name) values (1, 'Panini comics');
insert into supplier (id, name) values (2, 'Amazon');

insert into product (id, name, quantity, fk_category, fk_supplier) values (1, 'Warcraft - entre dois mundos',  10, 2, 2);

insert into product (id, name, quantity, fk_category, fk_supplier) values (2, 'Scrum for all',  3, 3, 2);

insert into product (id, name, quantity, fk_category, fk_supplier) values (3, 'Cowboy Beebop',  8, 1, 1);