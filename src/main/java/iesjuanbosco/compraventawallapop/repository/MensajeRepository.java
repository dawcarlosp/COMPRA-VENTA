package iesjuanbosco.compraventawallapop.repository;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Mensaje;

import iesjuanbosco.compraventawallapop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
   // Recuperar mensajes entre dos usuarios para un anuncio específico
   @Query("SELECT m FROM Mensaje m WHERE m.anuncio = :anuncio AND " +
           "((m.remitente = :remitente AND m.destinatario = :destinatario) OR " +
           "(m.remitente = :destinatario AND m.destinatario = :remitente)) " +
           "ORDER BY m.fechaHora ASC")
   List<Mensaje> findByAnuncioAndUsuarios(@Param("anuncio") Anuncio anuncio,
                                          @Param("remitente") Usuario remitente,
                                          @Param("destinatario") Usuario destinatario);

   // Recuperar todos los mensajes para un propietario en un anuncio
   @Query("SELECT m FROM Mensaje m WHERE m.anuncio = :anuncio AND m.destinatario = :destinatario ORDER BY m.fechaHora ASC")
   List<Mensaje> findByAnuncioAndDestinatario(@Param("anuncio") Anuncio anuncio,
                                              @Param("destinatario") Usuario destinatario);


   @Query("SELECT m FROM Mensaje m WHERE m.anuncio = :anuncio")
   List<Mensaje> findByAnuncio(@Param("anuncio") Anuncio anuncio);

}
