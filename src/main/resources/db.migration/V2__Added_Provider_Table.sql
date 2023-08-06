-- create sequence book_id_seq start with 1 increment by 1;
create table book_entity
(
    id        integer GENERATED ALWAYS AS IDENTITY,
    title     varchar(255) not null,
    author    varchar(255) not null,
    year      integer      not null,
    isbn      varchar(255) not null,
    person_id integer,

    primary key (id),
    constraint book_isbn_unique unique (isbn),
    constraint fk_user foreign key (person_id) references user_entity (id)
);

INSERT INTO book_entity (title, author, isbn, year)
VALUES ('Pride and Prejudice', 'Jane Austen', 'ISBN-9780141439518', 1991),
       ('1984', 'George Orwell', 'ISBN-9780451524935', 1991),
       ('To Kill a Mockingbird', 'Harper Lee', 'ISBN-9780061120084', 1991),
       ('The Great Gatsby', 'F. Scott Fitzgerald', 'ISBN-9780743273565', 1991),
       ('The Catcher in the Rye', 'J.D. Salinger', 'ISBN-9780316769488', 1991),
       ('To the Lighthouse', 'Virginia Woolf', 'ISBN-9780156907392', 1991),
       ('Brave New World', 'Aldous Huxley', 'ISBN-9780060850524', 1991),
       ('The Lord of the Rings', 'J.R.R. Tolkien', 'ISBN-9780618640157', 1991),
       ('Fahrenheit 451', 'Ray Bradbury', 'ISBN-9781451673319', 1991),
       ('Jane Eyre', 'Charlotte Bronte', 'ISBN-9780141441146', 1991);