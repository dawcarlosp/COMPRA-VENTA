package iesjuanbosco.compraventawallapop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //Patron Builder
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles; // Store roles such as "USER" and "ADMIN"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "{usuario.email.invalido}")
    @NotBlank(message = "{usuario.email.notBlank}")
    //En la base de datos solo podra existir el mismo correo una vez
    @Column(unique = true)
    private String email;
    @Length(min = 4, message = "{usuario.password.longitud}")
    private String password;
    private String nombre;
    private String telefono;
    private String poblacion;
    @OneToMany(targetEntity = Anuncio.class, cascade = CascadeType.ALL, mappedBy = "usuario")
    private List anuncios = new ArrayList();
    @ManyToMany(mappedBy = "visitantes") // `mappedBy` hace referencia a la propiedad en la entidad Anuncio
    private List<Anuncio> anunciosV = new ArrayList<>();

    @OneToMany(mappedBy = "remitente", cascade = CascadeType.ALL)
    private List<Mensaje> mensajesEnviados;
    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL)
    private List<Mensaje> mensajesRecibidos;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
