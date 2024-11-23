package iesjuanbosco.compraventawallapop.repository;

import iesjuanbosco.compraventawallapop.entity.Mensaje;

import iesjuanbosco.compraventawallapop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
   List<Mensaje> findAllByRemitenteAndAndDestinatarioOrderByIdDesc(Usuario remitente, Usuario destinatario);
}
