INSERT INTO pedido (
    monto_total,
    fecha_creacion,
    hora_creacion,
    estado_pedido,
    metodo_pago,
    sucursal,
    cliente,
    despacho,
    receta,
    evento
) VALUES
(12000, '2026-05-01', '10:30:00', 'pendiente', 'debito', 1, 1, 1, 1, 1),

(8500, '2026-05-02', '11:15:00', 'en_preparacion', 'credito', 2, 2, 2, 2, 2),

(15000, '2026-05-03', '12:45:00', 'entregado', 'efectivo', 1, 3, 3, 3, 1),

(9900, '2026-05-04', '13:20:00', 'pendiente', 'transferencia', 3, 4, 4, 1, 2),

(20000, '2026-05-05', '14:10:00', 'en_preparacion', 'debito', 2, 5, 5, 4, 3),

(17500, '2026-05-06', '15:00:00', 'entregado', 'credito', 1, 6, 6, 2, 1),

(7300, '2026-05-07', '16:25:00', 'pendiente', 'efectivo', 3, 7, 7, 5, 2),

(13400, '2026-05-08', '17:40:00', 'en_preparacion', 'transferencia', 2, 8, 5, 3, 3);