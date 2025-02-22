create table users_details(
      uuid              uuid         not null primary key,
      first_name        varchar(20)  not null,
      last_name         varchar(40)  not null,
      age               varchar(2)   not null,
      gender            varchar(10)  not null,
      email             varchar(100) not null,
      phone_number      varchar(15)  not null
);