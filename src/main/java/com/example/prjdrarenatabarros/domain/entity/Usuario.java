package com.example.prjdrarenatabarros.domain.entity;

import com.example.prjdrarenatabarros.domain.Enum.CargoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"login"})})
public class Usuario implements Serializable, UserDetails {
    private static final long serrialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Cargo não pode ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private CargoUsuario cargo;


    @NotEmpty(message = "Login não pode ser vazio")
    @NotNull(message = "Login não pode ser nulo")
    @Size(min = 5, max = 30, message = "Usuario deve conter entre 5 a 30 caracteres")
    @Column()
    private String login;

    @NotEmpty(message = "Senha não pode ser vazio")
    @NotNull(message = "Senha não pode ser nulo")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    @OneToMany(mappedBy = "usuario",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Atendimento> atendimentos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
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
