package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder1){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder  =passwordEncoder1;
    }
    public Usuario alta(Usuario usuario){
    String encodedPassword = this.passwordEncoder.encode(usuario.getPassword());
    usuario.setPassword(encodedPassword);
    Set<String> roles = Set.of("USER");
    usuario.setRoles(roles);
    return this.usuarioRepository.save(usuario);
    }
    public Optional<Usuario> getUsuarioByUsername(String username){
        return this.usuarioRepository.findByEmail(username);
    }
    public Optional<Usuario> getUsuarioById(Long id){
        return this.usuarioRepository.findById(id);
    }
    public Usuario getAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(username);
            return usuarioOptional.orElse(null);
        }
        return null;
    }
    public void save(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }
    public boolean getAutenticadoEstado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(username.equalsIgnoreCase("anonymousUser")){
            return false;
        }else{
            return true;
        }
    }
}
