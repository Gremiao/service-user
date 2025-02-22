create table users_access(
      uuid              uuid         not null primary key,
      registration_date timestamp,
      last_acess        timestamp
);