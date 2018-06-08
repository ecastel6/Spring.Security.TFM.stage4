INSERT INTO USERS (username, password, firstname, lastname, email) VALUES ('admin', '$2a$10$Q5eMt69cTtZCvSHxYNx7VeEWSZ6edRT73UzaQXzU17G67cTXK2A3i', 'Admin', 'User', 'admin@etsisi.sps.com');
INSERT INTO USERS (username, password, firstname, lastname, email) VALUES ('user', '$2a$10$1W8xvcCIEgDMGCy2su5lBujy0meLU4rObTqOBNCi8pvyHnnyLMUrS', 'Regular', 'User', 'user@etsisi.sps.com');

INSERT INTO USER_ROLES (role) VALUES ('ADMIN');
INSERT INTO USER_ROLES (role) VALUES ('USER');

INSERT INTO USERS_USER_ROLES (USER_ID,USER_ROLES_ID) SELECT u.id,ur.id FROM USERS u, USER_ROLES ur where u.USERNAME='admin' and ur.ROLE='ADMIN';
INSERT INTO USERS_USER_ROLES (USER_ID,USER_ROLES_ID) SELECT u.id,ur.id FROM USERS u, USER_ROLES ur where u.USERNAME='user' and ur.ROLE='USER';

INSERT INTO BOOKS(id, author, title, price, description) VALUES (1, 'John Grisham', 'The Rooster Bar', 13.0, 'Mucho antes de que su nombre se convirtiera en sinónimo del thriller legal moderno.');
INSERT INTO BOOKS(id, author, title, price, description) VALUES (2, 'Daniel Ellsberg', 'The doomsday machine', 19.49, 'Antes de los documentos del Pentágono, antes de Watergate');
INSERT INTO BOOKS(id, author, title, price, description) VALUES (3, 'Nora Roberts', 'Year One', 19.58, 'Una sorprendente nueva novela de la autora número uno del New York Times, Nora Roberts-Year One, es una epopeya de esperanza y horror, caos y magia');
INSERT INTO BOOKS(id, author, title, price, description) VALUES (4, 'David Safier', '¡Muuu!', 21.50, 'Humor en altas dosis');

