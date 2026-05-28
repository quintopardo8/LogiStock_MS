LogiStock - Microservicios 

Sistema logístico que coordina el flujo de mercancías:
* Entrada: `MS-05 Órdenes Compra` valida proveedores (`MS-03`) y sube el stock en `MS-02 Inventario`.
* Salida: `MS-06 Órdenes Despacho` valida clientes (`MS-04`) y baja el stock en `MS-02 Inventario`.
* Catálogo: `MS-01 Productos` define qué es cada artículo para todo el ecosistema.
