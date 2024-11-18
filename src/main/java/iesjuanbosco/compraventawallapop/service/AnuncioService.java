package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.AnuncioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class AnuncioService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private FotoAnuncioService fotoAnuncioService;
    public Page<Anuncio> findByUser(Usuario usuario, Pageable pageable){
        return this.anuncioRepository.findByUsuarioOrderByFechaCreacionDesc(usuario, pageable);
    }
    public void saveAnuncio(Anuncio anuncio){
        anuncio.setUsuario(this.usuarioService.getAutenticado());
        anuncio.setFechaCreacion(LocalDate.now());
        this.anuncioRepository.save(anuncio);
    }
    public Page<Anuncio> listarPaginas(int numeroPagina, int tamanioPagina){
        Pageable pageable =  PageRequest.of(numeroPagina, tamanioPagina);
        return this.anuncioRepository.findAllByOrderByFechaCreacionDesc(pageable);
    }
    public Page<Anuncio> listarPaginasUsuario(Usuario usuario,int numeroPagina, int tamanioPagina){
        Pageable pageable =  PageRequest.of(numeroPagina, tamanioPagina);
        return this.anuncioRepository.findByUsuarioOrderByFechaCreacionDesc(usuario, pageable);
    }
}
