CREATE TABLE `ordenes_compra` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` enum('CANCELADA','PENDIENTE','RECIBIDA') NOT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_recepcion` datetime(6) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `proveedor_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
