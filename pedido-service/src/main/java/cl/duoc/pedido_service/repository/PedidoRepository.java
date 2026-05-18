package cl.duoc.pedido_service.repository;

import cl.duoc.pedido_service.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    @Query("SELECT p FROM Pedido p WHERE p.estado_pedido = :estado")
    List<Pedido> buscarPorEstado(@Param("estado") String estado);

    // 2. Reporte por Monto Mínimo
    @Query("SELECT p FROM Pedido p WHERE p.monto_total >= :montoMinimo")
    List<Pedido> buscarPorMontoMinimo(@Param("montoMinimo") Integer montoMinimo);

    // 3. Reporte por Método de Pago
    @Query("SELECT p FROM Pedido p WHERE p.metodo_pago = :metodoPago")
    List<Pedido> buscarPorMetodoPago(@Param("metodoPago") String metodoPago);

    // 4. Reporte de Pedidos Críticos (Estado y Monto Mínimo)
    @Query("SELECT p FROM Pedido p WHERE p.estado_pedido = :estado AND p.monto_total >= :monto")
    List<Pedido> buscarPedidosCriticos(@Param("estado") String estado, @Param("monto") Integer monto);

    // 5. Historial Comercial por Rango de Fechas
    @Query("SELECT p FROM Pedido p WHERE p.fecha_creacion BETWEEN :inicio AND :fin")
    List<Pedido> buscarPorRangoFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
}
