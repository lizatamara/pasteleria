-- =====================================================================
-- SCRIPT DE CREACIÓN DE BASES DE DATOS - PROYECTO PASTELERÍA
-- ESTRUCTURA DESACOPLADA PARA ARQUITECTURA DE MICROSERVICIOS
-- =====================================================================

-- 1. Base de datos para Administrador Service
CREATE DATABASE IF NOT EXISTS `bd_administrador` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 2. Base de datos para Chofer Service
CREATE DATABASE IF NOT EXISTS `bd_chofer` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 3. Base de datos para Cliente Service
CREATE DATABASE IF NOT EXISTS `bd_cliente` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 4. Base de datos para Despacho Service
CREATE DATABASE IF NOT EXISTS `bd_despacho` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 5. Base de datos para Evento Service
CREATE DATABASE IF NOT EXISTS `bd_evento` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 6. Base de datos para Pedido Service
CREATE DATABASE IF NOT EXISTS `bd_pedido` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 7. Base de datos para Receta Service
CREATE DATABASE IF NOT EXISTS `bd_receta` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 8. Base de datos para Sucursal Service
CREATE DATABASE IF NOT EXISTS `bd_sucursal` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 9. Base de datos para Vehiculo Service
CREATE DATABASE IF NOT EXISTS `bd_vehiculo` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 10. Base de datos para Vendedor Service
CREATE DATABASE IF NOT EXISTS `bd_vendedor` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- =====================================================================
-- FIN DEL SCRIPT
-- Todas las bases de datos han sido creadas de forma independiente.
-- Flyway se encargará de crear las tablas automáticamente al iniciar.
-- =====================================================================