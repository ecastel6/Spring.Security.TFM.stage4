CREATE TABLE users (
  id BIGINT IDENTITY NOT NULL,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(100) NOT NULL,
  firstname VARCHAR(30) NOT NULL,
  lastname VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  enabled INTEGER NOT NULL
);

CREATE TABLE users_user_roles (
  user_id BIGINT NOT NULL,
  user_role_id BIGINT NOT NULL
);

CREATE TABLE user_roles (
  id BIGINT IDENTITY NOT NULL,
  role VARCHAR(30) NOT NULL
);

CREATE TABLE books (
  id INTEGER IDENTITY NOT NULL,
  author VARCHAR(50),
  title VARCHAR(65),
  price DOUBLE,
  description VARCHAR(400)
);
