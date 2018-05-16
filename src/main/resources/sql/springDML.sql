INSERT INTO users(username, password,firstname,lastname,email, enabled)
  VALUES ('admin', 'admin', 'Admin name', 'Admin lastname','admin@google.com', 1);
INSERT INTO users(username, password,firstname,lastname,email, enabled)
  VALUES ('user', 'user','User name', 'User lastname', 'user@google.com', 1);

INSERT INTO user_roles (role) VALUES ('USER');
INSERT INTO user_roles (role) VALUES ('ADMIN');

INSERT INTO users_user_roles (user_id,user_role_id)
  SELECT u.id,ur.id FROM users u, user_roles ur WHERE u.username="user" and ur.role="USER";
INSERT INTO users_user_roles (user_id,user_role_id)
  SELECT u.id,ur.id FROM users u, user_roles ur WHERE u.username="admin" and ur.role="ADMIN";

INSERT INTO books(id, author, title, price, description) VALUES (1, 'John Grisham', 'The Rooster Bar', 13.0, 'Mucho antes de que su nombre se convirtiera en sinónimo del thriller legal moderno.');
INSERT INTO books(id, author, title, price, description) VALUES (2, 'Daniel Ellsberg', 'The doomsday machine', 19.49, 'Antes de los documentos del Pentágono, antes de Watergate');
INSERT INTO books(id, author, title, price, description) VALUES (3, 'Nora Roberts', 'Year One', 19.58, 'Una sorprendente nueva novela de la autora número uno del New York Times, Nora Roberts-Year One, es una epopeya de esperanza y horror, caos y magia');
INSERT INTO books(id, author, title, price, description) VALUES (4, 'David Safier', '¡Muuu!', 21.50, 'Humor en altas dosis');
