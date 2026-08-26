package com.mrc.clinic.clinic_api.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Temporal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MedicoDTO {
    private Long id;
    @NotNull(message = "O campo Nome é obrigatório.")
    @Size(max = 250, message = "O valor maximo do campo Nome é 250 caracteres.")
    private String nome;
    @NotNull(message = "O campo CPF é obrigatório.")
    @Size(max = 11, message = "O valor maximo do campo CPF é 11 caracteres.")
    private String cpf;
    @Email(message = "O campo Email está inválido")
    @Size(max = 100, message = "O valor maximo do campo Email é 100 caracteres.")
    @NotNull(message = "O campo Email é obrigatório.")
    private String email;
    @NotNull(message = "O campo Celular é obrigatório.")
    @Size(max = 15, message = "O valor maximo do campo Celular é 15 caracteres.")
    private String celular;
    @Size(max = 15, message = "O valor maximo do campo Telefone é 15 caracteres.")
    private String telefone;
    @Temporal
    @NotNull(message = "O campo Data de Nascimento é obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    @Size(max = 15, message = "O valor maximo do campo Telefone é 15 caracteres.")
    private String rg;
    @Size(max = 1, message = "O valor maximo do campo Telefone é 1 caracteres.")
    private String genero;
    private String paisOrigem;

    private String crm;
    private String crmEstado;
    private String instituicaoGraduacao;
    private String statusPos;
    private String instituicaoPos;
    private String statusMestrado;
    private String instituicaoMestrado;
    private String statusDoutorado;
    private String instituicaoDoutorado;


    @Embedded
    private EnderecoDTO endereco = new EnderecoDTO();

    private List<MedicoEspecialidadeDTO> medicoEspecialidades = new ArrayList<>();


}
