drop table if exists booking cascade;
drop table if exists booking_detail cascade;

create table booking
(
    id  bigserial primary key,
    member_id int8,
    booking_status varchar,
    created_at timestamp,
    updated_at timestamp
);

create table booking_detail
(
    id bigserial,
    book_id int8,
    booking_id int8,
    booking_return_date timestamp,
    book_status varchar,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);