CREATE TABLE `inventario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad_disponible` int NOT NULL,
  `cantidad_reservada` int NOT NULL,
  `productoid` bigint NOT NULL,
  `stock_minimo` int NOT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
