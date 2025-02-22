create table users_locations
(
    uuid uuid UNIQUE primary key,
    country varchar(10) not null,
    state varchar(40) not null ,
    city varchar(40) null,
    postal_code varchar(8) null
);