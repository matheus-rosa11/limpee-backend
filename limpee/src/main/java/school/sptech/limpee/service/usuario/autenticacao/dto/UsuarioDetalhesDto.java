package school.sptech.limpee.service.usuario.autenticacao.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.limpee.domain.usuario.Usuario;

import java.util.Collection;

//UserDetails é uma interface do Spring Security que representa informações de um usuário que é autenticado no sistema.
// Ela contém informações como nome de usuário, senha, permissões (ou "papéis") e outras informações de identificação e autorização.
//
// Essas informações são usadas pelo Spring Security para autenticar o usuário e verificar se ele tem as permissões
//  necessárias para acessar determinados recursos ou funcionalidades do sistema.

public class UsuarioDetalhesDto implements UserDetails {
    private final String nome;
    private final String email;
    private final String senha;

    public UsuarioDetalhesDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public String getNome() {
        return nome;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
    }

    @Override
    public String getPassword(){
        return senha;
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
