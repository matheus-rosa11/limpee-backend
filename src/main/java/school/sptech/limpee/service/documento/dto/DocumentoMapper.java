package school.sptech.limpee.service.documento.dto;

import school.sptech.limpee.domain.documento.Documento;

public class DocumentoMapper {
    public static DocumentoDto of (Documento documento) {
        DocumentoDto d = new DocumentoDto();

        d.setId(documento.getId());
        d.setArquivo(documento.getArquivo());
        d.setNome(documento.getNome());
        d.setType(documento.getType());
        d.setIdUsuario(documento.getId());

        return d;
    }
}
