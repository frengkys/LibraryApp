drop table if exists member cascade;
drop table if exists book cascade;

create table member
(
    id         bigserial primary key,
    name       varchar not null,
    address    varchar not null,
    age        int     not null,
    created_at timestamp,
    updated_at timestamp
);

create table book
(
    id         bigserial primary key,
    book_title varchar not null,
    author     varchar not null,
    created_at timestamp,
    updated_at timestamp
);
