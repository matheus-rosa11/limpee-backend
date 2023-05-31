package school.sptech.limpee.service.imagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.repository.imagem.ImagemRepository;
import school.sptech.limpee.domain.imagem.Imagem;
import school.sptech.limpee.service.imagem.dto.ImagemMapper;
import school.sptech.limpee.service.imagem.dto.ImagemPrestadorDto;
import school.sptech.limpee.service.usuario.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemService {
    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private UsuarioService usuarioService;

    public void uploadImage(MultipartFile file, long idPrestador) {

        if (!usuarioService.existsById(idPrestador))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um usuário com o ID especificado.");

        String nome = file.getOriginalFilename();
        byte[] conteudo;

        try {
            conteudo = file.getBytes();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Houve um erro ao processar a imagem.");
        }

        Imagem imagem = new Imagem();
        imagem.setNome(nome);
        imagem.setFoto(conteudo);
        imagem.setPrestador(usuarioService.findById(idPrestador).get());

        imagemRepository.save(imagem);
    }

    public List<Imagem> findAll() {
        return imagemRepository.findAll();
    }

    public boolean existsById(Long idImagem) {
        return imagemRepository.existsById(idImagem);
    }

    public void setFoto(Long idImagem, byte[] novaFoto) {
        imagemRepository.setFoto(idImagem, novaFoto);
    }

    public byte[] getFoto(Long idImagem) {
        return imagemRepository.getFoto(idImagem);
    }

    public boolean existsByIdByPrestador(Long idPrestador) {
        return usuarioService.existsById(idPrestador);
    }

    public byte[] getFotoByIdPrestador(Long idPrestador) {
        return imagemRepository.getFotoByIdPrestador(idPrestador)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "O usuário não possui imagem."));
    }

    public List<ImagemPrestadorDto> findAllPrestadorDto() {
        return imagemRepository.findAll().stream().map(ImagemMapper::of).toList();
    }
}
