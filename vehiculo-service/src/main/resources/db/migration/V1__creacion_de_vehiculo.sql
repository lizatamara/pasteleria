CREATE TABLE vehiculo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patente VARCHAR(20) NOT NULL,
    capacidad_kilos DOUBLE,
    marca VARCHAR(100),
    modelo VARCHAR(100)
);