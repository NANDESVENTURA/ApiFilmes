create table films
(

    id       bigint       not null auto_increment,
    user_id  bigint       references   users(id),
    name     varchar(100) not null,
    platform varchar(100) not null,
    gender   varchar(100) not null,
    note     int          not null,
    watched  tinyint,


    primary key (id)

);
