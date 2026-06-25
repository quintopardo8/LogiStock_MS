CREATE TABLE `categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `nombre` varchar(100) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

CREATE TABLE `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) COLLATE utf8mb3_unicode_ci NOT NULL,
  `estado` enum('ACTIVO','INACTIVO') COLLATE utf8mb3_unicode_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb3_unicode_ci NOT NULL,
  `precio` int NOT NULL,
  `sku` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

CREATE TABLE `producto_categoria` (
  `producto_id` bigint NOT NULL,
  `categoria_id` bigint NOT NULL,
  KEY `FKioqw5isra2i5o5qybtege3sgt` (`categoria_id`),
  KEY `FKh5teore15e6b0uytoftnh3qnd` (`producto_id`),
  CONSTRAINT `FKh5teore15e6b0uytoftnh3qnd` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKioqw5isra2i5o5qybtege3sgt` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;