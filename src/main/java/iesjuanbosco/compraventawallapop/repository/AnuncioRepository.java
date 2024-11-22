package iesjuanbosco.compraventawallapop.repository;
import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Categoria;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository <Anuncio,Long> {
    Page<Anuncio> findAllByOrderByFechaCreacionDesc(Pageable pageable);
    Page<Anuncio> findByUsuarioOrderByFechaCreacionDesc(Usuario usuario, Pageable pageable);
    Page<Anuncio> findByCategoriasOrderByFechaCreacionDesc(Categoria categoria, Pageable pageable);
    @Query("SELECT a FROM Anuncio a WHERE a.titulo LIKE :filtro OR a.descripcion LIKE :filtro ORDER BY a.fechaCreacion DESC")
    Page<Anuncio> buscarPorFiltro(@Param("filtro") String filtro, Pageable pageable);

}
