create table users_join_access
(
    uuid_user uuid REFERENCES users(uuid),
    uuid_access uuid references users_access (uuid)
);