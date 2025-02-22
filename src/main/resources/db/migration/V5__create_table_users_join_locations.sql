create table users_join_locations
(
    uuid_user uuid REFERENCES users(uuid),
    uuid_location uuid references users_locations (uuid)
);