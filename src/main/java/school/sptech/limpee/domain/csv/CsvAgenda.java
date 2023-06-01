package school.sptech.limpee.domain.csv;

import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.dto.UsuarioCsvDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvAgenda {
    private List<UsuarioCsvDto> usuarios;
    private String dataInsercao;

    public CsvAgenda(List<UsuarioCsvDto> usuarios) {
        this.usuarios = usuarios;
        this.dataInsercao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
    public List<UsuarioCsvDto> getUsuarios() {
        return usuarios;
    }
    public String getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(String dataInsercao) {
        this.dataInsercao = dataInsercao;
    }
}
