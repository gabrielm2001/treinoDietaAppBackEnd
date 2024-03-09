package treinoDieta.api.physicalEntities.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@EqualsAndHashCode(of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String password;
    private String username;

    public Usuario(DadosResgistroUsuario dados){
        this.userRole = dados.userRole();
        this.username = dados.username();
        this.password = dados.password();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (UserRole.ROLE_ADMIN == userRole){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_PROFESSOR"), new SimpleGrantedAuthority("ROLE_ALUNO"));
        }else if (UserRole.ROLE_ALUNO == userRole){
            return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
        }else if (UserRole.ROLE_PROFESSOR == userRole){
            return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        }

        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
