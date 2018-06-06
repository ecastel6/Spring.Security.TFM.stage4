CREATE DATABASE `sps.stage4` IF NOT EXISTS ;
USE sps.stage4;
CREATE TABLE `USERS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `PERSISTENT_LOGINS` (
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `series` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `USER_ROLES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `USERS_USER_ROLES` (
  `user_id` bigint(20) NOT NULL,
  `user_roles_id` bigint(20) NOT NULL,
PRIMARY KEY (`user_id`,`user_roles_id`),
KEY `FK_USER_ROLES` (`user_roles_id`),
CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`),
CONSTRAINT `FK_USER_ROLES` FOREIGN KEY (`user_roles_id`) REFERENCES `USER_ROLES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `sps.stage4`.`USERS` (`username`, `password`, `firstname`, `lastname`, `email`) VALUES ('admin', '$2a$10$Q5eMt69cTtZCvSHxYNx7VeEWSZ6edRT73UzaQXzU17G67cTXK2A3i', 'Admin', 'User', 'admin@etsisi.sps.com');
INSERT INTO `sps.stage4`.`USERS` (`username`, `password`, `firstname`, `lastname`, `email`) VALUES ('user', '$2a$10$1W8xvcCIEgDMGCy2su5lBujy0meLU4rObTqOBNCi8pvyHnnyLMUrS', 'Regular', 'User', 'user@etsisi.sps.com');

INSERT INTO `sps.stage4`.`USER_ROLES` (`role`) VALUES ('ADMIN');
INSERT INTO `sps.stage4`.`USER_ROLES` (`role`) VALUES ('USER');

INSERT INTO USERS_USER_ROLES (user_id, user_roles_id)
  SELECT u.id, ur.id FROM USERS u, USER_ROLES ur
  where u.username='admin' and ur.role='ADMIN';

INSERT INTO USERS_USER_ROLES (user_id, user_roles_id)
  SELECT u.id, ur.id FROM USERS u, USER_ROLES ur
  where u.username='user' and ur.role='USER';

CREATE TABLE BOOKS (
  id INTEGER NOT NULL AUTO_INCREMENT,
  author VARCHAR(50),
  title VARCHAR(65),
  price DOUBLE,
  description VARCHAR(400),
  PRIMARY KEY (`id`)
);

INSERT INTO BOOKS(id, author, title, price, description) VALUES (1, 'John Grisham', 'The Rooster Bar', 13.0, 'Mucho antes de que su nombre se convirtiera en sinónimo del thriller legal moderno.');
INSERT INTO BOOKS(id, author, title, price, description) VALUES (2, 'Daniel Ellsberg', 'The doomsday machine', 19.49, 'Antes de los documentos del Pentágono, antes de Watergate');
INSERT INTO BOOKS(id, author, title, price, description) VALUES (3, 'Nora Roberts', 'Year One', 19.58, 'Una sorprendente nueva novela de la autora número uno del New York Times, Nora Roberts-Year One, es una epopeya de esperanza y horror, caos y magia');
INSERT INTO BOOKS(id, author, title, price, description) VALUES (4, 'David Safier', '¡Muuu!', 21.50, 'Humor en altas dosis');

