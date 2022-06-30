package com.example.prjdrarenatabarros.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ROLE")
public class Role implements Serializable,GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", unique = true)
    private String nomeRole;

    @Column(name = "description")
    private String Descricao;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
