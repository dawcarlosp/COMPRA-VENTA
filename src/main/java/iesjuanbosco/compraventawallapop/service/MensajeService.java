package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Anuncio;
import iesjuanbosco.compraventawallapop.entity.Mensaje;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    public List<Mensaje> chat(Anuncio anuncio, Usuario remitente, Usuario destinatario) {
        return mensajeRepository.findByAnuncioAndUsuarios(anuncio, remitente, destinatario);
    }
    public List<Mensaje> getMensajesParaPropietario(Anuncio anuncio, Usuario propietario) {
        return mensajeRepository.findByAnuncioAndDestinatario(anuncio, propietario);
    }
    public Map<Usuario, List<Mensaje>> getMensajesPorConversacion(Anuncio anuncio, Usuario propietario) {
        List<Mensaje> mensajes = mensajeRepository.findByAnuncio(anuncio);
        return mensajes.stream()
                .filter(m -> m.getDestinatario().equals(propietario) || m.getRemitente().equals(propietario))
                .collect(Collectors.groupingBy(m -> m.getRemitente().equals(propietario) ? m.getDestinatario() : m.getRemitente()));
    }

}
