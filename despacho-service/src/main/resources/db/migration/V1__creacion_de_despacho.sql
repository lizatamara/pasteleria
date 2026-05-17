CREATE TABLE IF NOT EXISTS despacho (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estado_despacho VARCHAR(50) NOT NULL,
    direccion_entrega VARCHAR(255) NOT NULL,
    fecha_estimada DATE NOT NULL,
    chofer BIGINT NULL
);