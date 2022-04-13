insert into category (id, description) values (1001, 'Comic Books');
insert into category (id, description) values (1002, 'Movies');
insert into category (id, description) values (1003, 'Books');

insert into supplier (id, name) values (1001, 'Panini comics');
insert into supplier (id, name) values (1002, 'Amazon');

insert into product (id, name, quantity, fk_category, fk_supplier, created_at) values (1001, 'Warcraft - entre dois mundos',  10, 1002, 1002, CURRENT_TIMESTAMP);
insert into product (id, name, quantity, fk_category, fk_supplier, created_at) values (1002, 'Scrum for all',  3, 1003, 1002, CURRENT_TIMESTAMP);
insert into product (id, name, quantity, fk_category, fk_supplier, created_at) values (1003, 'Cowboy Beebop',  8, 1001, 1001, CURRENT_TIMESTAMP);