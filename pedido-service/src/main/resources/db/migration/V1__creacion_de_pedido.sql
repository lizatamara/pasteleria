CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    monto_total INT NOT NULL,
    fecha_creacion DATE NOT NULL,
    hora_creacion TIME NOT NULL,

    estado_pedido VARCHAR(100) NOT NULL,
    metodo_pago VARCHAR(100) NOT NULL,

    sucursal BIGINT,
    cliente BIGINT,
    despacho BIGINT,
    receta BIGINT,
    evento BIGINT
);