--DROP TABLE IF EXISTS liked_items;
--DROP TABLE IF EXISTS items;
--DROP TABLE IF EXISTS users;


CREATE TABLE users (
    id integer GENERATED ALWAYS AS IDENTITY,
    username text NOT NULL UNIQUE,
    email text NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE items (
    id integer GENERATED ALWAYS AS IDENTITY,
    title text NOT NULL,
    price NUMERIC(10, 2),
    user_id integer NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE liked_items (
    item_id integer NOT NULL REFERENCES items (id) ON DELETE CASCADE,
    user_id integer NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY(item_id, user_id)
);


CREATE OR REPLACE FUNCTION get_user_id_by_username(_username text)
RETURNS integer AS '
DECLARE
    user_id integer;
BEGIN
    SELECT id INTO user_id
    FROM users
    WHERE username = _username;

    RETURN user_id;

END; '
LANGUAGE 'plpgsql';