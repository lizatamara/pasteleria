CREATE TABLE vendedor (
    id BIGINT NOT NULL AUTO_INCREMENT,
    rut VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    sueldo INT NOT NULL,
    fecha_contrato DATE,
    PRIMARY KEY (id)
);