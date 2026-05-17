CREATE TABLE evento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_evento VARCHAR(100),
    fecha DATETIME,
    cantidad_personas INT
);