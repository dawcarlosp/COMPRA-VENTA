package iesjuanbosco.compraventawallapop.controller;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.repository.AnuncioRepository;
import iesjuanbosco.compraventawallapop.repository.FotoAnuncioRepository;
import iesjuanbosco.compraventawallapop.service.AnuncioService;
import iesjuanbosco.compraventawallapop.service.FotoAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class FotoAnuncioController {
    @Autowired
    private FotoAnuncioRepository fotoAnuncioRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private FotoAnuncioService fotoAnuncioService;
    @GetMapping("anuncios/{id1}/fotos/{id2}/delete")
    public String deleteFoto(@PathVariable("id1") Long idAnuncio,
                             @PathVariable("id2") Long idFoto){
        fotoAnuncioService.deleteFoto(idFoto);
        return "redirect:/anuncios/edit/" + idAnuncio ;
    }

    @PostMapping("/anuncios/edit/{id}/addfoto")
    public String addFoto(@PathVariable("id") Long id, Model model,
                          @RequestParam(value = "archivoFoto") MultipartFile archivoFoto) {
        Optional<Anuncio> productoOptional = anuncioRepository.findById(id);
        if(productoOptional.isPresent()){
            fotoAnuncioService.addFoto(archivoFoto, productoOptional.get());
        }
        return "redirect:/anuncios/edit/" + id;
    }
}
