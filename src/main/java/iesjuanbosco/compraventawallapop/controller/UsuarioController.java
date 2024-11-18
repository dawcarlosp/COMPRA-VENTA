package iesjuanbosco.compraventawallapop.controller;

import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/usuarios/new")
    public String newUserView(Model model){
        model.addAttribute("usuario", new Usuario());
        return "/usuario/usuario-new";
    }
    @PostMapping("/usuarios/new")
    public String newUser(@Valid Usuario usuario, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            this.usuarioService.alta(usuario);
            return "redirect:/";
        }else{
            return "/usuario/usuario-new";
        }
    }

}
