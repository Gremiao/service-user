create table users_join_details
(
    uuid_user uuid REFERENCES users(uuid),
    uuid_details uuid references users_details(uuid)
);