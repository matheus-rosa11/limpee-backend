package school.sptech.limpee.service.documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.repository.documento.DocumentoRepository;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.documento.Documento;
import school.sptech.limpee.service.documento.dto.DocumentoDto;
import school.sptech.limpee.service.documento.dto.DocumentoMapper;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class DocumentoService {
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public DocumentoDto getFileByIdPrestador(Long idPrestador) {
        if (!usuarioRepository.existsById(idPrestador))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuário não foi encontrado");

        return DocumentoMapper.of(documentoRepository.findByIdPrestador(idPrestador));
    }
}
