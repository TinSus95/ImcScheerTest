CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `user_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `meta_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `learning_component_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `learning_component` (
  `id` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkhp3qwlfcyls1sjcjbmdc3bw1` (`type_id`),
  CONSTRAINT `FKkhp3qwlfcyls1sjcjbmdc3bw1` FOREIGN KEY (`type_id`) REFERENCES `learning_component_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `component_tag` (
  `component_id` varchar(255) NOT NULL,
  `tag_id` bigint NOT NULL,
  PRIMARY KEY (`component_id`,`tag_id`),
  KEY `FK7sdk8r1hy6glwhvjptcgx3uwi` (`tag_id`),
  CONSTRAINT `FK7sdk8r1hy6glwhvjptcgx3uwi` FOREIGN KEY (`tag_id`) REFERENCES `meta_tag` (`id`),
  CONSTRAINT `FKo8na4xaxfkdbf54cesy31c5tv` FOREIGN KEY (`component_id`) REFERENCES `learning_component` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `user_learning` (
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `component_id` varchar(255) NOT NULL,
  `status_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`component_id`,`user_id`),
  KEY `FKswymhfimi1fk8u0bescbqsjc7` (`status_id`),
  KEY `FKyebn9xb5kt7me25qxm7vahe0` (`user_id`),
  CONSTRAINT `FKa3uphiovg9c4xhk05he4trxaq` FOREIGN KEY (`component_id`) REFERENCES `learning_component` (`id`),
  CONSTRAINT `FKswymhfimi1fk8u0bescbqsjc7` FOREIGN KEY (`status_id`) REFERENCES `user_status` (`id`),
  CONSTRAINT `FKyebn9xb5kt7me25qxm7vahe0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
