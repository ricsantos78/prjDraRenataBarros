package com.example.prjdrarenatabarros.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role", uniqueConstraints={@UniqueConstraint(columnNames={"nomeRole"})})
public class Role implements Serializable,GrantedAuthority {

    private static final long serrialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeRole;

    private String Descricao;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
