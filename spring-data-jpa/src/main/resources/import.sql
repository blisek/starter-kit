insert into library(id, name) values (1, 'Roosevelt National Library');
insert into library(id, name) values (2, 'Biblioteka Narodowa');

insert into book (id, title, library_id) values (1, 'Pierwsza książka', 1);
insert into book (id, title, library_id) values (2, 'Druga książka', 2);
insert into book (id, title, library_id) values (3, 'Trzecia książka', 1);

insert into author(id, first_name, last_name) values (1, 'Jan', 'Kowalski');
insert into author(id, first_name, last_name) values (2, 'Zbigniew', 'Nowak');
insert into author(id, first_name, last_name) values (3, 'Janusz', 'Jankowski');
insert into author(id, first_name, last_name) values (4, 'Sebastian', 'Bono');

insert into book_author(book_id, author_id) values (1, 1);
insert into book_author(book_id, author_id) values (2, 2);
insert into book_author(book_id, author_id) values (3, 3);
insert into book_author(book_id, author_id) values (3, 4);
