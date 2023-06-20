create table films(

                        id bigint not null auto_increment,
                        name varchar(100) not null,
                        platform varchar(100) not null,
                        gender varchar(100) not null,
                        note varchar(100) not null,
                        watched tinyint,




                        primary key(id)

);
