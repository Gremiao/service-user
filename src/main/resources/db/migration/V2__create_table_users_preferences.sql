create table users_preferences(
    uuid uuid UNIQUE primary key,
    gender varchar(10) null,
    age_min varchar(2) null,
    age_max varchar(2) null,
    country varchar(10) null,
    state varchar(40) null
);