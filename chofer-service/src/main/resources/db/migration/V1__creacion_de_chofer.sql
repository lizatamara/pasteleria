CREATE TABLE chofer (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            rut VARCHAR(11),
                            nombre VARCHAR(100),
                            apellido VARCHAR(100),
                            tipo_licencia VARCHAR(2),
                            telefono VARCHAR(12),
                            vehiculo BIGINT
);