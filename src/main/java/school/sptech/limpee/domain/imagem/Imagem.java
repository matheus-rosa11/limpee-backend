package school.sptech.limpee.domain.imagem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import school.sptech.limpee.domain.usuario.Usuario;

@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne
    private Usuario prestador;

    //@JsonIgnore
    @Column(length = 50 * 1024 * 1024)
    private byte[] foto;

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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Usuario getPrestador() {
        return prestador;
    }

    public void setPrestador(Usuario prestador) {
        this.prestador = prestador;
    }
}
