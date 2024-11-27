package iesjuanbosco.compraventawallapop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //Patron Builder
@Table(name = "anuncios")
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaCreacion;
    @NotBlank(message = "{anuncio.titulo.notBlank}")
    private String titulo;
    private String descripcion;
    @NotNull(message = "{anuncio.precio.notNull}")
    private Double precio;
    @OneToMany(targetEntity =  FotoAnuncio.class, cascade = CascadeType.ALL, mappedBy = "anuncio")
    private List<FotoAnuncio> fotosAnuncio = new ArrayList<>();
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;
    @ManyToMany
    @JoinTable(
            name = "anuncio_categoria", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "anuncio_id"), // Columna que referencia a la tabla Anuncio
            inverseJoinColumns = @JoinColumn(name = "categoria_id") // Columna que referencia a la tabla Categoria
    )
    private List<Categoria> categorias = new ArrayList<>();
    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;
    @ManyToMany
    @JoinTable(
            name = "anuncio_visitante", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "anuncio_id"), // Columna que referencia a la tabla Anuncio
            inverseJoinColumns = @JoinColumn(name = "usuario_id") // Columna que referencia a la tabla Usuario
    )
    private List<Usuario> visitantes = new ArrayList<>();
    public FotoAnuncio getPrimeraFoto(){
        FotoAnuncio fotoAnuncio = new FotoAnuncio();
        fotoAnuncio.setNombre("default.png");
        if(!fotosAnuncio.isEmpty()){
            return  fotosAnuncio.get(0);
        }else{
            return fotoAnuncio;
        }
    }
}
