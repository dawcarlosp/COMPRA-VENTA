package iesjuanbosco.compraventawallapop.controller;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.FotoAnuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.AnuncioRepository;
import iesjuanbosco.compraventawallapop.service.AnuncioService;
import iesjuanbosco.compraventawallapop.service.CategoriaService;
import iesjuanbosco.compraventawallapop.service.FotoAnuncioService;
import iesjuanbosco.compraventawallapop.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class AnuncioController {
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private AnuncioService anuncioService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FotoAnuncioService fotoAnuncioService;
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/anuncios/del/{id}")
    public String deleteAnuncio(@PathVariable Long id){
        this.anuncioService.deleteAnuncioById(id);
        return "redirect:/";
    }
    @GetMapping("/anuncios/view/{id}")
    public String viewAnuncio(Model model,@PathVariable Long id){
        Optional<Anuncio> anuncio = this.anuncioRepository.findById(id);
        Usuario usuario = this.usuarioService.getAutenticado();
        if(anuncio.isPresent()){
            model.addAttribute("anuncio", anuncio.get());
            model.addAttribute("fotos", anuncio.get().getFotosAnuncio());
            if(usuario != null) {
                model.addAttribute("usuario", usuario);
            }else{
                model.addAttribute("usuario", new Usuario());
            }
            return "anuncio/anuncio-view";
        }else{
            return "redirect:/";
        }

    }
    @GetMapping("/anuncios/edit/{id}")
    public String editAnuncioView(@PathVariable Long id, Model model) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        Usuario usuario = this.usuarioService.getAutenticado();
        List<FotoAnuncio> fotos = anuncioRepository.findById(id).get().getFotosAnuncio();
        if(anuncio.isPresent() && usuario.getId() == anuncio.get().getUsuario().getId()) {
            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("fotoDefault", "/images/galeria.png");
            model.addAttribute("anuncio", anuncio.get());
            model.addAttribute("fotos", fotos);
            return "anuncio/anuncio-edit";
        }else{
            return "redirect:/";
        }
    }
    @PostMapping("anuncios/edit/{id}")
    public String editAnuncio(@PathVariable Long id,@Valid Anuncio anuncio, BindingResult bindingResult, Model model,
                              @RequestParam(value = "categorias", required = false) List<Long> categorias) {
        List<FotoAnuncio> fotos = anuncioRepository.findById(id).get().getFotosAnuncio();
        Anuncio auxiliar = this.anuncioRepository.findById(id).get();
        anuncio.setFechaCreacion(auxiliar.getFechaCreacion());
        if(bindingResult.hasErrors()) {
            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("fotoDefault", "/images/galeria.png");
            model.addAttribute("fotos",fotos);
            model.addAttribute("anuncio", anuncio);
            return "anuncio/anuncio-edit";
        }else{
            if(categorias != null){
                anuncio.setCategorias(this.categoriaService.findByIds(categorias));
            }
            this.anuncioService.editAnuncio(anuncio);
            return "redirect:/";
        }
    }

    @GetMapping("/anuncios/new")
    public String newAnuncioView(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        model.addAttribute("usuario", this.usuarioService.getAutenticado());
        model.addAttribute("categorias", this.categoriaService.findAll());
        model.addAttribute("fotoDefault", "/images/galeria.png");
        return "anuncio/anuncio-new";
    }
    @PostMapping("/anuncios/new")
    public String newAnuncio(@Valid Anuncio anuncio,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam(value = "archivosFotos", required = false) List<MultipartFile> fotos,
                             @RequestParam(value = "categorias", required = false) List<Long> categorias) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("fotoDefault", "/images/galeria.png");
            return "anuncio/anuncio-new";
        }
        //Guardar fotos
        try {
            fotoAnuncioService.guardarFotos(fotos, anuncio);
        }catch (IllegalArgumentException ex) {
            //model.addAttribute("mensaje", ex.getMessage());
            model.addAttribute("categorias", this.categoriaService.findAll());
            return "anuncio/anuncio-new";
        }
        //Guardar anuncio
        if(categorias != null){
            anuncio.setCategorias(this.categoriaService.findByIds(categorias));
        }
        anuncioService.saveAnuncio(anuncio);
        return "redirect:/";
    }
}
