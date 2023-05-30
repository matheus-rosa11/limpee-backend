package school.sptech.limpee.service.usuario.dto;

import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoNotificacaoDto;

public class UsuarioNotificacaoDto {
    private long id;
    private String nome;
    private EnderecoNotificacaoDto endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoNotificacaoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoNotificacaoDto endereco) {
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
