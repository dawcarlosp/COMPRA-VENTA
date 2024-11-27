package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Categoria;
import iesjuanbosco.compraventawallapop.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Categoria> findByName(String name){return categoriaRepository.findAllByNombre(name);}
    public List<Categoria> findByIds(List<Long> ids){
        List<Categoria> categorias = new ArrayList<>();
        for (Long id : ids){
            Optional<Categoria> categoria = categoriaRepository.findById(id);
            if(categoria.isPresent()){
                categorias.add(categoria.get());
            }else{
                categorias = null;
                categorias.add(this.categoriaRepository.findById(19L).get());
            }
        }
        return categorias;
    }
}
