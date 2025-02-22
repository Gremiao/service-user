create table users(
      uuid              uuid         not null primary key,
      password varchar(80) not null,
      active            boolean default false
);
