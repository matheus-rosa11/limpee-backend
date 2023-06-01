package school.sptech.limpee.domain.documento;

import jakarta.persistence.*;
import school.sptech.limpee.domain.usuario.Usuario;

@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String type;
    @Lob
    private byte[] arquivo;
    @OneToOne
    private Usuario usuario;

    public Documento() {
    }

    public Documento(String nome, String type, byte[] arquivo) {
        this.nome = nome;
        this.type = type;
        this.arquivo = arquivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
