package school.sptech.limpee.service.imagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.imagem.ImagemRepository;

@Service
public class ImagemService {
    @Autowired
    ImagemRepository imagemRepository;
}
