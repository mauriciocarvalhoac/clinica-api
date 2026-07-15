package com.mrc.clinic.clinic_api.entity;

import com.mrc.clinic.clinic_api.entity.dto.PacienteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;

    private String email;
    private String telefone;
    private String celular;

    public static Paciente to(PacienteDTO dto) {
        Paciente obj = new Paciente();
        obj.setId(dto.getId());
        obj.setNome(dto.getNome());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCelular(dto.getCelular());
        return obj;
    }
}
