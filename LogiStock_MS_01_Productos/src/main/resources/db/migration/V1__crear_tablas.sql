
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK35t4wyxqrevf09uwx9e9p6o75` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `estado` tinyint(4) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio` int(11) NOT NULL,
  `sku` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKclpng6f2m2r9i1y5g2yxajyuq` (`sku`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `producto_categoria` (
  `producto_id` bigint(20) NOT NULL,
  `categoria_id` bigint(20) NOT NULL,
  KEY `FKioqw5isra2i5o5qybtege3sgt` (`categoria_id`),
  KEY `FKh5teore15e6b0uytoftnh3qnd` (`producto_id`),
  CONSTRAINT `FKh5teore15e6b0uytoftnh3qnd` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKioqw5isra2i5o5qybtege3sgt` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
