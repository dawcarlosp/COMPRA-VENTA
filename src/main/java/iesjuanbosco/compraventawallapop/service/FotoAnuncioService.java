package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.FotoAnuncio;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.AnuncioRepository;
import iesjuanbosco.compraventawallapop.repository.FotoAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FotoAnuncioService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AnuncioRepository anuncioRepository;
    private static final List<String> TIPOS_PERMITIDOS = List.of("image/jpeg","image/png","image/gif","image/avif","image/webp","image/jpg");
    private static final long MAX_FILE_SIZE = 10000000;
    private static final String UPLOADS_DIRECTORY = "uploads/imagesAnuncios";
    @Autowired
    private FotoAnuncioRepository fotoAnuncioRepository;

    public void validarArchivo(MultipartFile archivo) {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("Alguno de los archivos no ha sido seleccionado o está vacío");
        }
        if (!TIPOS_PERMITIDOS.contains(archivo.getContentType())) {
            throw new IllegalArgumentException("El tipo de archivo no es permitido: " + archivo.getContentType());
        }
        if (archivo.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Archivo demasiado grande. Solo se admiten archivos < 10MB");
        }
    }

    public String generarNombreUnico(MultipartFile file) {
        UUID nombreUnico = UUID.randomUUID();
        String extension;
        if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
            extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        } else {
            throw new IllegalArgumentException("El archivo seleccionado no es una imagen.");
        }
        return nombreUnico + extension;
    }

    public void guardarImagen(MultipartFile archivo, String nuevoNombreFoto) {
        Path ruta = Paths.get(UPLOADS_DIRECTORY + File.separator + nuevoNombreFoto);
        try {
            byte[] contenido = archivo.getBytes();
            byte[] contenidoRedimensionado = imageService.resizeImage(contenido, 1000);
            Files.write(ruta, contenidoRedimensionado);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo: " + archivo.getOriginalFilename(), e);
        }
    }
    public List<FotoAnuncio> guardarFotos(List<MultipartFile> fotos, Anuncio anuncio) {

        List<FotoAnuncio> listaFotos = new ArrayList<>();

        for (MultipartFile foto : fotos) {
            if (!foto.isEmpty()) {
                validarArchivo(foto);
                String nombreFoto = generarNombreUnico(foto);
                guardarImagen(foto, nombreFoto);

                FotoAnuncio fotoProducto = FotoAnuncio.builder()
                        .nombre(nombreFoto)
                        .anuncio(anuncio).build();

                listaFotos.add(fotoProducto);
            }
        }
        anuncio.setFotosAnuncio(listaFotos);
        return listaFotos;
    }

    public void addFoto(MultipartFile foto, Anuncio anuncio) {
        if (!foto.isEmpty()) {
            validarArchivo(foto);
            String nombreFoto = generarNombreUnico(foto);
            guardarImagen(foto, nombreFoto);

            FotoAnuncio fotoAnuncio = FotoAnuncio.builder()
                    .nombre(nombreFoto)
                    .anuncio(anuncio).build();


            anuncio.getFotosAnuncio().add(fotoAnuncio);
            anuncioRepository.save(anuncio);
        }
    }

    public void deleteFoto(Long idFoto) {
        Optional<FotoAnuncio> fotoAnuncioOptional = fotoAnuncioRepository.findById(idFoto);
        if(fotoAnuncioOptional.isPresent()){
            FotoAnuncio fotoAnuncio = fotoAnuncioOptional.get();
            Path archivoFoto = Paths.get(fotoAnuncio.getNombre());
            try {
                Files.deleteIfExists(archivoFoto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fotoAnuncioRepository.deleteById(idFoto);
        }
    }
}
