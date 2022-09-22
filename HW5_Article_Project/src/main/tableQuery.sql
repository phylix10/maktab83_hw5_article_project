create database article_project;

create table users
(
    id            serial primary key,
    username      varchar unique,
    national_code varchar(10) unique,
    birthday      date,
    password      text
);

create table article
(
    id           serial primary key,
    title        varchar,
    brief        varchar,
    content      varchar,
    create_date  date,
    is_published bool,
    user_id      int,
    constraint fk_user_id
        foreign key (user_id)
            references users (id)
);