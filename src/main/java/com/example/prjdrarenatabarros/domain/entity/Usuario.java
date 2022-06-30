package com.example.prjdrarenatabarros.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class Usuario implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(name = "name")
    private String nome;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id",
                    table = "TB_USER"),//cria tabela de acesso do usuario
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id",
                    table = "TB_ROLE"))
    private List<Role> roles;


    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    @Column(name = "specialty_id")
    private Especialidade especialidade;

    @OneToMany(mappedBy = "usuario")
    private List<Atendimento> atendimentos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
