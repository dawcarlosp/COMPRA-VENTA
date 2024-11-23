package iesjuanbosco.compraventawallapop.service;

import iesjuanbosco.compraventawallapop.entity.Mensaje;
import iesjuanbosco.compraventawallapop.entity.Usuario;
import iesjuanbosco.compraventawallapop.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    public List<Mensaje> chat(Usuario remitente, Usuario destinatario){
        return this.mensajeRepository.findAllByRemitenteAndAndDestinatarioOrderByIdDesc(remitente, destinatario);
    }
}
