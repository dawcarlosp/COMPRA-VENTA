package iesjuanbosco.compraventawallapop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Table(name="categorias")
    @ToString(exclude = "anuncios")
    @Entity
    public class Categoria {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        @Column(length = 1000)
        private String descripcion;
        @ManyToMany(mappedBy = "categorias") // `mappedBy` hace referencia a la propiedad en la entidad Anuncio
        private List<Anuncio> anuncios = new ArrayList<>();

}
