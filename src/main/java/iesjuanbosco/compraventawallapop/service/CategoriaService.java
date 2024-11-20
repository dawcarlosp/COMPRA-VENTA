package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Categoria;
import iesjuanbosco.compraventawallapop.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> findAll(){
        return this.categoriaRepository.findAll();
    }
    public Optional<Categoria> findById(Long id){return categoriaRepository.findById(id);}
}
