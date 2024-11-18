package iesjuanbosco.compraventawallapop.controller;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.AnuncioRepository;
import iesjuanbosco.compraventawallapop.service.AnuncioService;
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
    @GetMapping("/anuncios/view/{id}")
    public String viewAnuncio(Model model,@PathVariable Long id){
        Optional<Anuncio> anuncio = this.anuncioRepository.findById(id);
        if(anuncio.isPresent()){
            model.addAttribute("anuncio", anuncio.get());
            model.addAttribute("fotos", anuncio.get().getFotosAnuncio());
            return "anuncio/anuncio-view";
        }else{
            return "redirect:/";
        }

    }
    @GetMapping("/anuncios/edit/{id}")
    public String editAnuncioView(@PathVariable Long id, Model model) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        Usuario usuario = this.usuarioService.getAutenticado();
        if(anuncio.isPresent() && usuario.getId() == anuncio.get().getUsuario().getId()) {
            model.addAttribute("anuncio", anuncio.get());
            return "anuncio/anuncio-edit";
        }else{
            return "redirect:/";
        }
    }
    @PostMapping("anuncios/edit/{id}")
    public String editAnuncio(@PathVariable Long id,@Valid Anuncio anuncio, BindingResult bindingResult, Model model) {
        Anuncio auxiliar = this.anuncioRepository.findById(id).get();
        anuncio.setFechaCreacion(auxiliar.getFechaCreacion());
        if(bindingResult.hasErrors()) {
            return "anuncio/anuncio-edit";
        }else{
            this.anuncioService.saveAnuncio(anuncio);
            return "redirect:/";
        }
    }

    @GetMapping("/anuncios/new")
    public String newAnuncioView(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        model.addAttribute("usuario", this.usuarioService.getAutenticado());
        return "anuncio/anuncio-new";
    }
    @PostMapping("/anuncios/new")
    public String newAnuncio(@Valid Anuncio anuncio,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam(value = "archivosFotos", required = false) List<MultipartFile> fotos) {
        if (bindingResult.hasErrors()) {
            return "anuncio/anuncio-new";
        }
        //Guardar fotos
        try {
            fotoAnuncioService.guardarFotos(fotos, anuncio);
        }catch (IllegalArgumentException ex) {
            //model.addAttribute("mensaje", ex.getMessage());
            return "anuncio/anuncio-new";
        }
        //Guardar anuncio
        anuncioService.saveAnuncio(anuncio);
        return "redirect:/";
    }
}
