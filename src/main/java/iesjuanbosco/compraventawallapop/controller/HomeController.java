package iesjuanbosco.compraventawallapop.controller;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.FotoAnuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.service.AnuncioService;
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

@Controller
public class HomeController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AnuncioService anuncioService;
    @GetMapping("/")
    public String inicio(Model model, @RequestParam(defaultValue = "0") int pagina){
        int tamanioPagina = 5;
        Page<Anuncio> paginaAnuncios = this.anuncioService.listarPaginas(pagina, tamanioPagina);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(!username.equalsIgnoreCase("anonymousUser")){
        Usuario usuario = this.usuarioService.getUsuarioByUsername(username).get();
        model.addAttribute("usuario", usuario);}
        model.addAttribute("anuncios", paginaAnuncios.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaAnuncios.getTotalPages());
        model.addAttribute("hasNext", paginaAnuncios.hasNext());
        model.addAttribute("hasPrevious", paginaAnuncios.hasPrevious());
        return "inicio";
    }
    @GetMapping("/misAnuncios")
    public String inicioAutenticado(Model model,  @RequestParam(defaultValue = "0") int pagina){
        int tamanioPagina = 5;
        Page<Anuncio> paginaAnuncios = this.anuncioService.listarPaginasUsuario(this.usuarioService.getAutenticado(),pagina, tamanioPagina);
        model.addAttribute("anuncios", paginaAnuncios.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaAnuncios.getTotalPages());
        model.addAttribute("hasNext", paginaAnuncios.hasNext());
        model.addAttribute("hasPrevious", paginaAnuncios.hasPrevious());
        model.addAttribute("usuario", this.usuarioService.getAutenticado());
        return "inicio";
    }

}
