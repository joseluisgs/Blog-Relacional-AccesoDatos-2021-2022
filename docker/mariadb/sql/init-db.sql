-- Adminer 4.8.1 MySQL 5.5.5-10.5.11-MariaDB dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `blog`;
CREATE DATABASE `blog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blog`;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `texto` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Base de Datos de Categoría';

INSERT INTO `category` (`id`, `texto`) VALUES
(1,	'General'),
(2,	'Dudas'),
(3,	'Evaluación'),
(4,	'Pruebas');

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `texto` text NOT NULL,
  `fechaPublicacion` datetime NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `post_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_user_FK` (`user_id`),
  KEY `comment_post_FK` (`post_id`),
  CONSTRAINT `comment_post_FK` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `comment_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de comentarios';

INSERT INTO `comment` (`id`, `texto`, `fechaPublicacion`, `user_id`, `post_id`) VALUES
(1,	'Comentario 01',	'2021-10-01 19:26:08',	1,	1),
(2,	'Comentario 02',	'2021-10-01 19:26:22',	2,	2),
(3,	'Comentario 03',	'2021-10-01 19:26:36',	3,	2),
(4,	'Comentario 04',	'2021-10-01 19:26:54',	1,	2),
(6,	'Comentario 05',	'2021-10-01 19:27:24',	4,	4),
(7,	'Comentario 03',	'2021-10-01 19:27:39',	4,	4);

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `user_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `login_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de login';


DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(250) NOT NULL,
  `url` varchar(250) DEFAULT NULL,
  `contenido` text NOT NULL,
  `fecha_publicacion` datetime NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `category_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_user_FK` (`user_id`),
  KEY `post_category_FK` (`category_id`),
  CONSTRAINT `post_category_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `post_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de posts';

INSERT INTO `post` (`id`, `titulo`, `url`, `contenido`, `fecha_publicacion`, `user_id`, `category_id`) VALUES
(1,	'Post num 1',	'http://post1.com',	'Este es el post num 1',	'2021-10-01 16:12:03',	1,	1),
(2,	'Posy num 2',	'http://post2.com',	'Este es el post num 2',	'2021-10-01 16:12:28',	2,	2),
(3,	'Post num 3',	'http://post3.com',	'Este es el post num 3',	'2021-10-01 16:13:00',	3,	3),
(4,	'Post num 4',	'http://post4.com',	'Esto es el post num 4',	'2021-10-01 16:13:40',	1,	1),
(5,	'Post num 5',	'http://post5.com',	'Esto es el post num 5',	'2021-10-01 16:14:14',	2,	3);

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `nombre` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `test` (`nombre`, `email`) VALUES
('Jose Luis',	'joseluis@docker.com'),
('Soraya',	'soraya@docker.com'),
('Victor',	'victor@docker.com');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fecha_registro` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de usuarios';

INSERT INTO `user` (`id`, `nombre`, `email`, `password`, `fecha_registro`) VALUES
(1,	'Pepe Perez',	'pepe@pepe.es',	'7110eda4d09e062aa5e4a390b0a572ac0d2c0220',	'2021-09-30'),
(2,	'Ana Anaya',	'ana@anaya.es',	'7110eda4d09e062aa5e4a390b0a572ac0d2c0220',	'2021-09-30'),
(3,	'Paco Perez',	'paco@perez.es',	'7110eda4d09e062aa5e4a390b0a572ac0d2c0220',	'2021-09-30'),
(4,	'Son Goku',	'goku@dragonball.es',	'7110eda4d09e062aa5e4a390b0a572ac0d2c0220',	'2021-09-30'),
(5,	'Chuck Norris',	'chuck@norris.es',	'7110eda4d09e062aa5e4a390b0a572ac0d2c0220',	'2021-10-01');

-- 2021-10-01 19:28:28