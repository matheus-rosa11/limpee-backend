package school.sptech.limpee.service.admistrador.dto;

import jakarta.validation.constraints.Size;

public class AdministradorDto {
    @Size(min = 2, max = 100)
    private String nome;

    public AdministradorDto() {
    }

    public AdministradorDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
