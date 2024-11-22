package iesjuanbosco.compraventawallapop.controller;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Categoria;
import iesjuanbosco.compraventawallapop.entity.FotoAnuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.service.AnuncioService;
import iesjuanbosco.compraventawallapop.service.CategoriaService;
import iesjuanbosco.compraventawallapop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {
    private static final int TAMANIO_PAGINA = 5;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AnuncioService anuncioService;
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/")
    public String inicio(Model model, @RequestParam(defaultValue = "0") int pagina){
        Page<Anuncio> paginaAnuncios = this.anuncioService.listarPaginas(pagina, HomeController.TAMANIO_PAGINA);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(!username.equalsIgnoreCase("anonymousUser")){
        Usuario usuario = this.usuarioService.getUsuarioByUsername(username).get();
        model.addAttribute("usuario", usuario);}
        model.addAttribute("categorias",this.categoriaService.findAll());
        model.addAttribute("anuncios", paginaAnuncios.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaAnuncios.getTotalPages());
        model.addAttribute("hasNext", paginaAnuncios.hasNext());
        model.addAttribute("hasPrevious", paginaAnuncios.hasPrevious());
        model.addAttribute("AnunciosTotales",this.anuncioService.findAll().size());
        return "inicio";
    }
    @GetMapping("/{id}")
    public String inicioCategoria(Model model, @RequestParam(defaultValue = "0") int pagina, @PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(!username.equalsIgnoreCase("anonymousUser")){
            Usuario usuario = this.usuarioService.getUsuarioByUsername(username).get();
            model.addAttribute("usuario", usuario);}

        Page<Anuncio> paginaAnuncios = null;
        try {
          paginaAnuncios = this.anuncioService.listarPaginasCategoria(id, pagina, HomeController.TAMANIO_PAGINA);
        }catch (Exception e){
            paginaAnuncios = this.anuncioService.listarPaginas(pagina, HomeController.TAMANIO_PAGINA);
        }
        if (id.equals(-1L)) {
            return "redirect:/";
        }

        Optional<Categoria> categoriaSeleccionada = this.categoriaService.findById(id);
        if (categoriaSeleccionada.isPresent()) {
            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("selectedCategoriaId", id);
            model.addAttribute("paginaActual", pagina);
            model.addAttribute("totalPaginas", paginaAnuncios.getTotalPages());
            model.addAttribute("hasNext", paginaAnuncios.hasNext());
            model.addAttribute("hasPrevious", paginaAnuncios.hasPrevious());
            model.addAttribute("anuncios", paginaAnuncios.getContent());
            model.addAttribute("AnunciosTotales",this.anuncioService.findAll().size());
            return "inicio";
        }
        return "redirect:/productos";
    }
    @GetMapping("/misAnuncios")
    public String inicioAutenticado(Model model,  @RequestParam(defaultValue = "0") int pagina){
        Page<Anuncio> paginaAnuncios = this.anuncioService.listarPaginasUsuario(this.usuarioService.getAutenticado(),pagina, HomeController.TAMANIO_PAGINA);
        model.addAttribute("anuncios", paginaAnuncios.getContent());
        model.addAttribute("categorias",this.categoriaService.findAll());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaAnuncios.getTotalPages());
        model.addAttribute("hasNext", paginaAnuncios.hasNext());
        model.addAttribute("hasPrevious", paginaAnuncios.hasPrevious());
        model.addAttribute("usuario", this.usuarioService.getAutenticado());
        model.addAttribute("AnunciosTotales",this.anuncioService.findAll().size());
        return "inicio";
    }

}
