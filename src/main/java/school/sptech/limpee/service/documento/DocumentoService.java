package school.sptech.limpee.service.documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.limpee.api.repository.documento.DocumentoRepository;
import school.sptech.limpee.domain.documento.Documento;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class DocumentoService {
    @Autowired
    DocumentoRepository documentoRepository;
    public Documento store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento FileDB = new Documento(fileName, file.getContentType(), file.getBytes());

        return documentoRepository.save(FileDB);
    }

    public Documento getFile(Long id) {
        return documentoRepository.findById(id).get();
    }

    public Stream<Documento> getAllFiles() {
        return documentoRepository.findAll().stream();
    }
}
