CREATE TABLE sucursal (
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(255),
    nombre VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    administrador BIGINT,
    PRIMARY KEY (id)
);