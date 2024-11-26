package iesjuanbosco.compraventawallapop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //Patron Builder
@Table(name = "mensajes")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "anuncio_id", nullable = false)
    private Anuncio anuncio;
}
