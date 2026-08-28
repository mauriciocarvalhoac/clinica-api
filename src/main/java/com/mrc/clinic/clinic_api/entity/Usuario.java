package com.mrc.clinic.clinic_api.entity;

import com.mrc.clinic.clinic_api.entity.enums.EnumUserRoles;
import com.mrc.clinic.clinic_api.entity.enums.EnumUserSituacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 250, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EnumUserRoles role;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false, length = 1)
    private EnumUserSituacao situacao;

    @OneToOne
    @JoinColumn(name = "funcionario_id", unique = true)
    private Funcionario funcionario;

    @PrePersist
    @PreUpdate
    private void sincronizarRelacionamento() {
        if (this.funcionario != null) {
            // Garante que o funcionário saiba que este é o usuário dele
            this.funcionario.setUsuario(this);
        }
    }
}
