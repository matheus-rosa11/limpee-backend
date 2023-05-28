package school.sptech.limpee.api.controller.documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.sptech.limpee.api.repository.imagem.ImagemRepository;
import school.sptech.limpee.domain.documento.Documento;
import school.sptech.limpee.domain.documento.message.ResponseFile;
import school.sptech.limpee.domain.documento.message.ResponseMessage;
import school.sptech.limpee.domain.imagem.Imagem;
import school.sptech.limpee.service.documento.DocumentoService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            documentoService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @PostMapping(value = "/teste", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> teste(@RequestBody MultipartFile file){
        try {
            String nome = file.getOriginalFilename();
            byte[] conteudo = file.getBytes();

            Imagem imagem = new Imagem();
            imagem.setNome(nome);
            imagem.setFoto(conteudo);

            imagemRepository.save(imagem);

            return ResponseEntity.ok(new ResponseMessage("Imagem enviada com sucesso!"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Falha ao enviar a imagem."));
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = documentoService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getNome(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getArquivo().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Documento documento = documentoService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getNome() + "\"")
                .body(documento.getArquivo());
    }
}
