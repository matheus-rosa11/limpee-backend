package school.sptech.limpee.service.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeCriacaoDto;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeDto;

import java.util.List;

public class UsuarioCriacaoDto {
    private String nome;
    @Email
    private String email;
    @Size(min = 6)
    private String senha;
    private String genero;
    private String tipoUsuario;
    private EnderecoDTO enderecoDTO;
    private List<String> especialidades;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }
}
