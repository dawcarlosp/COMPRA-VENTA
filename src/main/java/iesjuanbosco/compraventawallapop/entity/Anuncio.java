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
    public FotoAnuncio getPrimeraFoto(){
        return  fotosAnuncio.get(0);
    }
}
