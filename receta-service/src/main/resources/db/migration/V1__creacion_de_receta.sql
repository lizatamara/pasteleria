CREATE TABLE receta (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            codigo VARCHAR(6),
                            nombre VARCHAR(100),
                            descripcion VARCHAR(300),
                            precio INT,
                            categoria VARCHAR(100)
);

