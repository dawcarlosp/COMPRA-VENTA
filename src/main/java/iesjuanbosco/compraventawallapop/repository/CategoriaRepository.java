package iesjuanbosco.compraventawallapop.repository;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
