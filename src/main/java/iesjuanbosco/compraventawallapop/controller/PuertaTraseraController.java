package iesjuanbosco.compraventawallapop.controller;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Categoria;
import iesjuanbosco.compraventawallapop.entity.FotoAnuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.CategoriaRepository;

import iesjuanbosco.compraventawallapop.service.AnuncioService;
import iesjuanbosco.compraventawallapop.service.CategoriaService;
import iesjuanbosco.compraventawallapop.service.FotoAnuncioService;
import iesjuanbosco.compraventawallapop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PuertaTraseraController {
    @Autowired
    private CategoriaService  categoriaService;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FotoAnuncioService fotoAnuncioService;
    @Autowired
    private AnuncioService anuncioService;
    @GetMapping("/cargar")
    public String CargarDatos(){
        insertarUsuarios();
        insertarCategorias();
        insertarAnuncios();
        return "redirect:/";
    }
    public void insertarCategorias() {
        List<Categoria> categorias = List.of(
                 Categoria.builder()
                        .nombre("Coches")
                        .descripcion("Vehículos de segunda mano, nuevos o de colección.")
                        .build(),
                 Categoria.builder()
                        .nombre("Motos")
                        .descripcion("Todo tipo de motocicletas, scooters y similares.")
                        .build(),
                 Categoria.builder()
                        .nombre("Electrónica")
                        .descripcion("Artículos electrónicos como móviles, ordenadores y más.")
                        .build(),
                 Categoria.builder()
                        .nombre("Hogar y Jardín")
                        .descripcion("Muebles, decoración, herramientas y artículos para el jardín.")
                        .build(),
                Categoria.builder()
                        .nombre("Deportes y Ocio")
                        .descripcion("Equipamiento deportivo, bicicletas y artículos para actividades recreativas.")
                        .build(),
                Categoria.builder()
                        .nombre("Moda y Accesorios")
                        .descripcion("Ropa, calzado, bolsos y accesorios.")
                        .build(),
                Categoria.builder()
                        .nombre("Bebés y Niños")
                        .descripcion("Juguetes, ropa y artículos para bebés y niños.")
                        .build(),
                Categoria.builder()
                        .nombre("Cine, Libros y Música")
                        .descripcion("Películas, libros, instrumentos musicales y más.")
                        .build(),
                Categoria.builder()
                        .nombre("Videojuegos")
                        .descripcion("Consolas, videojuegos y accesorios relacionados.")
                        .build(),
                Categoria.builder()
                        .nombre("Mascotas")
                        .descripcion("Productos y servicios para animales de compañía.")
                        .build(),
                Categoria.builder()
                        .nombre("Empleo")
                        .descripcion("Ofertas y demandas de empleo.")
                        .build(),
                Categoria.builder()
                        .nombre("Servicios")
                        .descripcion("Servicios profesionales, reparaciones, clases y más.")
                        .build(),
                Categoria.builder()
                        .nombre("Otros")
                        .descripcion("Artículos y servicios que no encajan en las demás categorías.")
                        .build()
        );

        // Guardar las categorías en la base de datos
        categoriaRepository.saveAll(categorias);
    }
    public void insertarUsuarios(){
            Usuario usuario1 = Usuario.builder().email("samuel@gmail.com").password("samuel").nombre("Samuel").poblacion("Alcazar").telefono("123 123 543 345").build();
            Usuario usuario2 = Usuario.builder().email("antonio@example.com").password("antonio").nombre("Antonio").poblacion("Criptana").telefono("123 123 543 345").build();
            Usuario usuario3 = Usuario.builder().email("jairo@example.com").password("jairo").nombre("Jairo").poblacion("Alcazar").telefono("123 123 543 345").build();
            Usuario usuario4 = Usuario.builder().email("perez@example.com").password("perez").nombre("Perez").poblacion("Tomelloso").telefono("123 123 543 345").build();
            Usuario usuario5 = Usuario.builder().email("fermin@example.com").password("fermin").nombre("Fermin").poblacion("Alcazar").telefono("123 123 543 345").build();
            Usuario usuario6 = Usuario.builder().email("felix@example.com").password("felix").nombre("Felix").poblacion("Quero").telefono("123 123 543 345").build();
            Usuario usuario7 = Usuario.builder().email("pedro@example.com").password("pedro").nombre("Pedro").poblacion("Madrid").telefono("123 123 543 345").build();
            Usuario usuario8 = Usuario.builder().email("juan@example.com").password("juan").nombre("Juan").poblacion("Alcazar").telefono("123 123 543 345").build();
            Usuario usuario9 = Usuario.builder().email("andres@example.com").password("andres").nombre("Andres").poblacion("Herencia").telefono("123 123 543 345").build();
            Usuario usuario10 = Usuario.builder().email("alexa@example.com").password("alexa").nombre("Alexa").poblacion("Alcazar").telefono("123 123 543 345").build();

        // Insertar los usuarios
            this.usuarioService.alta(usuario1);
            this.usuarioService.alta(usuario2);
            this.usuarioService.alta(usuario3);
            this.usuarioService.alta(usuario4);
            this.usuarioService.alta(usuario5);
            this.usuarioService.alta(usuario6);
            this.usuarioService.alta(usuario7);
            this.usuarioService.alta(usuario8);
            this.usuarioService.alta(usuario9);
            this.usuarioService.alta(usuario10);
    }
    public void insertarAnuncios(){
        coches();
        motos();
        electronica();
    }
    public void coches(){
        LocalDate date = LocalDate.of(2016, 12, 4);
        LocalDate date2 = LocalDate.of(2020, 4, 7);
        Usuario usuario1 = this.usuarioService.getUsuarioById(1L).get();
        Usuario usuario2 = this.usuarioService.getUsuarioById(2L).get();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        List<Categoria> categorias = this.categoriaService.findByIds(ids);
        Anuncio a = Anuncio.builder().titulo("BMV").descripcion("¡Deportivo y elegante! Motor turbo, interiores de piel, revisiones al día. Perfecto estado, ideal para disfrutar del lujo y la potencia. \uD83D\uDCDE ¡Escríbeme y ven a verlo!\n" +
                "\n").precio(2500.0).fechaCreacion(date).contadorVistas(0).usuario(usuario1).categorias(categorias).build();
        Anuncio a2 = Anuncio.builder().titulo("Kia").descripcion("Súper cuidado, perfecto para ciudad. Bajo consumo, cámara de reversa y conectividad completa. ¡Como nuevo! \uD83D\uDE80 \uD83D\uDCE9 ¡Pregunta sin compromiso!").precio(6000.0).fechaCreacion(date2).contadorVistas(0).usuario(usuario2).categorias(categorias).build();
        List<FotoAnuncio> fotosAnuncio = new ArrayList<>();
        FotoAnuncio f1 = FotoAnuncio.builder().nombre("BMW1.png").anuncio(a).build();
        FotoAnuncio f2 = FotoAnuncio.builder().nombre("BMW2.png").anuncio(a).build();
        FotoAnuncio f3 = FotoAnuncio.builder().nombre("BMW3.png").anuncio(a).build();
        FotoAnuncio f4 = FotoAnuncio.builder().nombre("BMW4.png").anuncio(a).build();
        FotoAnuncio f5 = FotoAnuncio.builder().nombre("BMW5.png").anuncio(a).build();
        fotosAnuncio.add(f1);
        fotosAnuncio.add(f2);
        fotosAnuncio.add(f3);
        fotosAnuncio.add(f4);
        fotosAnuncio.add(f5);
        List<FotoAnuncio> fotosAnuncio2 = new ArrayList<>();
        FotoAnuncio f6 = FotoAnuncio.builder().nombre("KIA1.png").anuncio(a2).build();
        FotoAnuncio f7 = FotoAnuncio.builder().nombre("KIA2.png").anuncio(a2).build();
        FotoAnuncio f8 = FotoAnuncio.builder().nombre("KIA3.png").anuncio(a2).build();
        FotoAnuncio f9 = FotoAnuncio.builder().nombre("KIA4.png").anuncio(a2).build();
        FotoAnuncio f10 = FotoAnuncio.builder().nombre("KIA5.png").anuncio(a2).build();
        fotosAnuncio2.add(f6);
        fotosAnuncio2.add(f7);
        fotosAnuncio2.add(f8);
        fotosAnuncio2.add(f9);
        fotosAnuncio2.add(f10);
        a2.setFotosAnuncio(fotosAnuncio2);
        a.setFotosAnuncio(fotosAnuncio);
        this.anuncioService.saveAnuncio2(a2);
        this.anuncioService.saveAnuncio2(a);
    }
    public void motos(){
        LocalDate date = LocalDate.of(2018, 1, 4);
        LocalDate date2 = LocalDate.of(2023, 7, 7);
        Usuario usuario1 = this.usuarioService.getUsuarioById(3L).get();
        Usuario usuario2 = this.usuarioService.getUsuarioById(4L).get();
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        List<Categoria> categorias = this.categoriaService.findByIds(ids);
        Anuncio a = Anuncio.builder().titulo("TGB").descripcion("¡Deportivo y elegante! Motor turbo, interiores de piel, revisiones al día. Perfecto estado, ideal para disfrutar del lujo y la potencia. \uD83D\uDCDE ¡Escríbeme y ven a verlo!\n" +
                "\n").precio(2500.0).fechaCreacion(date).contadorVistas(0).usuario(usuario1).categorias(categorias).build();
        Anuncio a2 = Anuncio.builder().titulo("KTM").descripcion("Súper cuidado, perfecto para ciudad. Bajo consumo, cámara de reversa y conectividad completa. ¡Como nuevo! \uD83D\uDE80 \uD83D\uDCE9 ¡Pregunta sin compromiso!").precio(6000.0).fechaCreacion(date2).contadorVistas(0).usuario(usuario2).categorias(categorias).build();
        List<FotoAnuncio> fotosAnuncio = new ArrayList<>();
        FotoAnuncio f1 = FotoAnuncio.builder().nombre("TGB1.png").anuncio(a).build();
        FotoAnuncio f2 = FotoAnuncio.builder().nombre("TGB2.png").anuncio(a).build();
        FotoAnuncio f3 = FotoAnuncio.builder().nombre("TGB3.png").anuncio(a).build();
        FotoAnuncio f4 = FotoAnuncio.builder().nombre("TGB4.png").anuncio(a).build();
        FotoAnuncio f5 = FotoAnuncio.builder().nombre("TGB5.png").anuncio(a).build();
        fotosAnuncio.add(f1);
        fotosAnuncio.add(f2);
        fotosAnuncio.add(f3);
        fotosAnuncio.add(f4);
        fotosAnuncio.add(f5);
        List<FotoAnuncio> fotosAnuncio2 = new ArrayList<>();
        FotoAnuncio f6 = FotoAnuncio.builder().nombre("KTM.png").anuncio(a2).build();
        FotoAnuncio f7 = FotoAnuncio.builder().nombre("KTM.png").anuncio(a2).build();
        FotoAnuncio f8 = FotoAnuncio.builder().nombre("KTM.png").anuncio(a2).build();
        FotoAnuncio f9 = FotoAnuncio.builder().nombre("KTM.png").anuncio(a2).build();
        FotoAnuncio f10 = FotoAnuncio.builder().nombre("KTM.png").anuncio(a2).build();
        fotosAnuncio2.add(f6);
        fotosAnuncio2.add(f7);
        fotosAnuncio2.add(f8);
        fotosAnuncio2.add(f9);
        fotosAnuncio2.add(f10);
        a2.setFotosAnuncio(fotosAnuncio2);
        a.setFotosAnuncio(fotosAnuncio);
        this.anuncioService.saveAnuncio2(a2);
        this.anuncioService.saveAnuncio2(a);
    }
    public void electronica(){
        LocalDate date = LocalDate.of(2011, 9, 25);
        LocalDate date2 = LocalDate.of(2024, 3, 7);
        Usuario usuario1 = this.usuarioService.getUsuarioById(5L).get();
        Usuario usuario2 = this.usuarioService.getUsuarioById(6L).get();
        List<Long> ids = new ArrayList<>();
        ids.add(3L);
        List<Categoria> categorias = this.categoriaService.findByIds(ids);
        Anuncio a = Anuncio.builder().titulo("Samsung Galaxy watch 4 Classic").descripcion("Funciona perfectamente, Samsung Galaxy Watch 4 46mm Classic").precio(2500.0).fechaCreacion(date).contadorVistas(0).usuario(usuario1).categorias(categorias).build();
        Anuncio a2 = Anuncio.builder().titulo("Pokémon Oro original (Game Boy Color)").descripcion("Pokémon Oro original de Game Boy. Versión en español. Testeado y funcionando perfectamente. En muy buen estado.").precio(6000.0).fechaCreacion(date2).contadorVistas(0).usuario(usuario2).categorias(categorias).build();
        List<FotoAnuncio> fotosAnuncio = new ArrayList<>();
        FotoAnuncio f1 = FotoAnuncio.builder().nombre("galaxy1.png").anuncio(a).build();
        FotoAnuncio f2 = FotoAnuncio.builder().nombre("galaxy2.png").anuncio(a).build();
        FotoAnuncio f3 = FotoAnuncio.builder().nombre("galaxy3.png").anuncio(a).build();
        FotoAnuncio f4 = FotoAnuncio.builder().nombre("galaxy4.png").anuncio(a).build();
        FotoAnuncio f5 = FotoAnuncio.builder().nombre("galaxy5.png").anuncio(a).build();
        fotosAnuncio.add(f1);
        fotosAnuncio.add(f2);
        fotosAnuncio.add(f3);
        fotosAnuncio.add(f4);
        fotosAnuncio.add(f5);
        List<FotoAnuncio> fotosAnuncio2 = new ArrayList<>();
        FotoAnuncio f6 = FotoAnuncio.builder().nombre("pokemon1.png").anuncio(a2).build();
        FotoAnuncio f7 = FotoAnuncio.builder().nombre("pokemon2.png").anuncio(a2).build();
        FotoAnuncio f8 = FotoAnuncio.builder().nombre("pokemon3.png").anuncio(a2).build();
        fotosAnuncio2.add(f6);
        fotosAnuncio2.add(f7);
        fotosAnuncio2.add(f8);
        a2.setFotosAnuncio(fotosAnuncio2);
        a.setFotosAnuncio(fotosAnuncio);
        this.anuncioService.saveAnuncio2(a2);
        this.anuncioService.saveAnuncio2(a);
    }
}
