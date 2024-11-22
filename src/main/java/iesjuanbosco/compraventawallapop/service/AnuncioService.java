package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Categoria;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.AnuncioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class AnuncioService {
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AnuncioRepository anuncioRepository;
    public Page<Anuncio> findByUser(Usuario usuario, Pageable pageable){
        return this.anuncioRepository.findByUsuarioOrderByFechaCreacionDesc(usuario, pageable);
    }
    public void saveAnuncio(Anuncio anuncio){
        anuncio.setUsuario(this.usuarioService.getAutenticado());
        anuncio.setFechaCreacion(LocalDate.now());
        this.anuncioRepository.save(anuncio);
    }
    public void editAnuncio(Anuncio anuncio){
        anuncio.setUsuario(this.usuarioService.getAutenticado());
        this.anuncioRepository.save(anuncio);
    }
    public void deleteAnuncioById(@PathVariable Long id){
        this.anuncioRepository.deleteById(id);
    }
    public Page<Anuncio> listarPaginas(int numeroPagina, int tamanioPagina){
        Pageable pageable =  PageRequest.of(numeroPagina, tamanioPagina);
        return this.anuncioRepository.findAllByOrderByFechaCreacionDesc(pageable);
    }
    public Page<Anuncio> listarPaginasUsuario(Usuario usuario,int numeroPagina, int tamanioPagina){
        Pageable pageable =  PageRequest.of(numeroPagina, tamanioPagina);
        return this.anuncioRepository.findByUsuarioOrderByFechaCreacionDesc(usuario, pageable);
    }
    public Page<Anuncio> listarPaginasCategoria(Long id, int numeroPagina, int tamanioPagina){
        Optional<Categoria> categoria = this.categoriaService.findById(id);
        Pageable pageable =  PageRequest.of(numeroPagina, tamanioPagina);
        return this.anuncioRepository.findByCategoriasOrderByFechaCreacionDesc(categoria.get(), pageable);
    }
    public Page<Anuncio> listarPaginasFiltro(String filtro, int numeroPagina, int tamanioPagina){
        Pageable pageable =  PageRequest.of(numeroPagina, tamanioPagina);
        String filtroF = '%' + filtro + '%';
        return this.anuncioRepository.buscarPorFiltro(filtroF, pageable);
    }
    public List<Anuncio> findAll(){
        return this.anuncioRepository.findAll();
    }
}
