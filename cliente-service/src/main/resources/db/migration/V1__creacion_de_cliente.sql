CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(150),
    telefono VARCHAR(30),
    direccion VARCHAR(255)
);