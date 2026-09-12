package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.*;
import com.mrc.clinic.clinic_api.entity.dto.*;
import com.mrc.clinic.clinic_api.entity.rec.FuncionarioRec;
import org.springframework.beans.BeanUtils;

public class AbstractServiceImpl {
    public Especialidade to(EspecialidadeDTO dto) {
        Especialidade obj = new Especialidade();
        BeanUtils.copyProperties(dto, obj);
        return obj;
    }

    public EspecialidadeDTO to(Especialidade obj) {
        EspecialidadeDTO dto = new EspecialidadeDTO();
        BeanUtils.copyProperties(obj, dto);
        return dto;
    }

    public void to(FuncionarioDTO dto, Funcionario obj) {
        obj.setId(dto.getId());
        obj.setNome(dto.getNome());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCelular(dto.getCelular());
        obj.setPaisOrigem(dto.getPaisOrigem());
        obj.setDataNascimento(dto.getDataNascimento());
        obj.setGenero(dto.getGenero());
        obj.setRg(dto.getRg());

        obj.setFuncao(dto.getFuncao());
        obj.setMatricula(dto.getMatricula());
        obj.setDepartamento(dto.getDepartamento());

        obj.setEndereco((obj.getEndereco() != null ? obj.getEndereco() : new Endereco()));
        obj.getEndereco().setCep(dto.getEndereco().getCep());
        obj.getEndereco().setLogradouro(dto.getEndereco().getLogradouro());
        obj.getEndereco().setNumero(dto.getEndereco().getNumero());
        obj.getEndereco().setBairro(dto.getEndereco().getBairro());
        obj.getEndereco().setCidade(dto.getEndereco().getCidade());
        obj.getEndereco().setEstado(dto.getEndereco().getEstado());
        obj.setMedico(new Medico());

        if (dto.getMedico() != null) {
            obj.setMedico(new Medico());
            obj.getMedico().setCrm(dto.getMedico().getCrm());
            obj.getMedico().setCrmEstado(dto.getMedico().getCrmEstado());
            obj.getMedico().setCrmSituacao(dto.getMedico().getCrmSituacao());
            obj.getMedico().setInstituicaoGraduacao(dto.getMedico().getInstituicaoGraduacao());
            obj.getMedico().setSituacaoPos(dto.getMedico().getSituacaoPos());
            obj.getMedico().setInstituicaoPos(dto.getMedico().getInstituicaoPos());
            obj.getMedico().setSituacaoMestrado(dto.getMedico().getSituacaoMestrado());
            obj.getMedico().setInstituicaoMestrado(dto.getMedico().getInstituicaoMestrado());
            obj.getMedico().setSituacaoDoutorado(dto.getMedico().getSituacaoDoutorado());
            obj.getMedico().setInstituicaoDoutorado(dto.getMedico().getInstituicaoDoutorado());

            for (MedicoEspecialidadeDTO medEspDTO : dto.getMedico().getMedicoEspecialidades()) {
                MedicoEspecialidade medesp = new MedicoEspecialidade();
                medesp.setEspecialidade(new Especialidade());
                medesp.getEspecialidade().setId(medEspDTO.getEspecialidade().getId());
                medesp.getEspecialidade().setDescricao(medEspDTO.getEspecialidade().getDescricao());
                medesp.getEspecialidade().setCbo(medEspDTO.getEspecialidade().getCbo());
                medesp.getEspecialidade().setTiss(medEspDTO.getEspecialidade().getTiss());
                medesp.getEspecialidade().setRqe(medEspDTO.getEspecialidade().getRqe());
                medesp.setPrincipal(medEspDTO.getPrincipal());
                medesp.setSituacao(medEspDTO.getSituacao());

                medesp.setMedico(obj.getMedico());
                obj.getMedico().getMedicoEspecialidades().add(medesp);
            }
        }
    }

    public FuncionarioDTO to(FuncionarioRec rec) {
        FuncionarioDTO dto = new FuncionarioDTO();
        BeanUtils.copyProperties(rec, dto);
        return dto;
    }

    public FuncionarioDTO to(Funcionario obj) {
        FuncionarioDTO dto = new FuncionarioDTO();
        BeanUtils.copyProperties(obj, dto);
        if (dto.getEndereco() != null) {
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            BeanUtils.copyProperties(obj.getEndereco(), enderecoDTO);
            dto.setEndereco(enderecoDTO);
        }
        if (obj.getMedico() != null) {

            MedicoDTO medicoDTO = new MedicoDTO();
            BeanUtils.copyProperties(obj.getMedico(), medicoDTO);
            dto.setMedico(medicoDTO);

            for (MedicoEspecialidade objMedEsp : obj.getMedico().getMedicoEspecialidades()) {
                MedicoEspecialidadeDTO dtoMedEspec = new MedicoEspecialidadeDTO();
                BeanUtils.copyProperties(objMedEsp, dtoMedEspec);
                if (objMedEsp.getEspecialidade() != null) {
                    EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
                    BeanUtils.copyProperties(objMedEsp.getEspecialidade(), especialidadeDTO);
                    dtoMedEspec.setEspecialidade(especialidadeDTO);
                }
                dto.getMedico().getMedicoEspecialidades().add(dtoMedEspec);
            }
        }

        return dto;
    }

    public Funcionario to(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(dto, funcionario);

        if (funcionario.getEndereco() != null) {
            Endereco endereco = new Endereco();
            BeanUtils.copyProperties(dto.getEndereco(), endereco);
            funcionario.setEndereco(endereco);
        }
        if (dto.getMedico() != null) {
            Medico medico = new Medico();
            BeanUtils.copyProperties(dto.getMedico(), medico);
            funcionario.setMedico(medico);

            for (MedicoEspecialidadeDTO medEspDTO : dto.getMedico().getMedicoEspecialidades()) {
                MedicoEspecialidade medEspec = new MedicoEspecialidade();
                BeanUtils.copyProperties(medEspDTO, medEspec);
                if (medEspDTO.getEspecialidade() != null) {
                    Especialidade especialidade = new Especialidade();
                    BeanUtils.copyProperties(medEspDTO.getEspecialidade(), especialidade);
                    medEspec.setEspecialidade(especialidade);
                }
                funcionario.getMedico().getMedicoEspecialidades().add(medEspec);
            }
        }

        return funcionario;
    }
}
