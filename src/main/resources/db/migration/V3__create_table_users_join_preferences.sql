create table users_join_preferences
(
    uuid_user uuid REFERENCES users(uuid),
    uuid_preferences uuid references users_preferences (uuid)
);