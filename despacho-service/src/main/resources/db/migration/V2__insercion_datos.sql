INSERT INTO despacho (estado_despacho, direccion_entrega, fecha_estimada, chofer) VALUES

-- Despachos sin chofer asignado (En preparación)
('En preparación', 'Av. Concha y Toro 543, Puente Alto', '2026-05-19', NULL),
('En preparación', 'Camino al Volcán 1234, San José de Maipo', '2026-05-20', NULL),

-- Despachos asignados y en ruta/entregados
('En camino', 'Vicuña Mackenna 456, Santiago', '2026-05-17', 1),
('Entregado', 'Pasaje Las Flores 789, San Bernardo', '2026-05-16', 1),
('En camino', 'Av. Providencia 1100, Providencia', '2026-05-18', 2),
('Entregado', 'Calle El Roble 22, La Florida', '2026-05-15', 3),
('En camino', 'Los Militares 4500, Las Condes', '2026-05-18', 2);