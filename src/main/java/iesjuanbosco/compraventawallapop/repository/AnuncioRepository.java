package iesjuanbosco.compraventawallapop.repository;
import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository <Anuncio,Long> {
    Page<Anuncio> findAllByOrderByFechaCreacionDesc(Pageable pageable);
    Page<Anuncio> findByUsuarioOrderByFechaCreacionDesc(Usuario usuario, Pageable pageable);
}
