package school.sptech.limpee.api.controller.imagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.limpee.api.exception.imagem.ImagemNaoEncontradaException;
import school.sptech.limpee.api.repository.imagem.ImagemRepository;
import school.sptech.limpee.domain.documento.message.ResponseMessage;
import school.sptech.limpee.domain.imagem.Imagem;
import school.sptech.limpee.service.imagem.ImagemService;
import school.sptech.limpee.service.imagem.dto.ImagemPrestadorDto;
import school.sptech.limpee.service.usuario.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("imagens")
public class ImagemController {
    @Autowired
    private ImagemService imagemService;

    @PostMapping(value = "/{idPrestador}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadImage(@RequestBody MultipartFile file, @PathVariable long idPrestador) {
        imagemService.uploadImage(file, idPrestador);
        return ResponseEntity.ok(new ResponseMessage("Imagem cadastrada com sucesso!"));
    }

    @GetMapping
    public ResponseEntity<List<ImagemPrestadorDto>> get() {
        List<ImagemPrestadorDto> imagens = imagemService.findAllPrestadorDto();

        return imagens.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(imagens);
    }



    @GetMapping(value = "/foto/{idPrestador}")
    public ResponseEntity<ImagemPrestadorDto> download(@PathVariable Long idPrestador) {

        if (!imagemService.existsByIdByPrestador(idPrestador))
            return ResponseEntity.notFound().build();

        ImagemPrestadorDto imagem = imagemService.getImgPrestadorDtoByIdPrestador(idPrestador);

        return ResponseEntity.status(200).body(imagem);
    }

    /*
    @PostMapping
    public ResponseEntity<Imagem> criar(@RequestBody Imagem novaImagem) {
        imagemRepository.save(novaImagem);
        return ResponseEntity.status(200).body(novaImagem);
    }*/

    // atualiza a foto de uma planta
    // "consumes" indica o tipo de dado que será aceito no corpo da requisição
    // o mime-type indicado no "consumes" é image/*, que indica que qualquer imagem será aceita
    // uma lista dos tipos de mime-type está em https://mimetype.io/all-types/ ou em https://www.sitepoint.com/mime-types-complete-list/
    @CrossOrigin("*")
    @PatchMapping(value = "/foto/{idImagem}", consumes = "image/png")
    public ResponseEntity<Void> patchFoto(@PathVariable Long idImagem, @RequestBody byte[] novaFoto) {
        if (!imagemService.existsById(idImagem))
            return ResponseEntity.notFound().build();

        imagemService.setFoto(idImagem, novaFoto);

        return ResponseEntity.status(200).build();
    }

    // recupera a foto de uma planta
    // "produces" indica o tipo de dado que será entregue no corpo da resposta
    // o mime-type indicado no "produces" é image/jpeg (MediaType.IMAGE_JPEG_VALUE), mas, na prática, qualquer imagem funcionará
    // uma lista dos tipos de mime-type está em https://mimetype.io/all-types/ ou em https://www.sitepoint.com/mime-types-complete-list/

}
