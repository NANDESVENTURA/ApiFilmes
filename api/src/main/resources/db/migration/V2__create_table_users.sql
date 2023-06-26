create table users
(

    id       bigint       not null auto_increment,
    user_id  bigint       references   users(id),
    name     varchar(50) not null,
    email    varchar(30) not null unique,
    password varchar(100) not null,


    primary key (id)

);