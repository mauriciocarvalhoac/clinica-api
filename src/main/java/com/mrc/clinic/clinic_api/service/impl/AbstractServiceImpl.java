package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.*;
import com.mrc.clinic.clinic_api.entity.dto.*;
import com.mrc.clinic.clinic_api.entity.rec.AgendaRec;
import com.mrc.clinic.clinic_api.entity.rec.FuncionarioRec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AbstractServiceImpl {
    @Autowired
    private ObjectMapper objectMapper; // O Spring já fornece essa instância automaticamente

    public void to(AgendaDTO dto, Agenda agenda, HashSet convenios) {
        BeanUtils.copyProperties(dto, agenda);

        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(dto.getFuncionario(), funcionario);
        agenda.setFuncionario(funcionario);

        Especialidade especialidade = new Especialidade();
        BeanUtils.copyProperties(dto.getEspecialidade(), especialidade);
        agenda.setEspecialidade(especialidade);


        if (agenda.getAtendimentos() == null) {
            agenda.setAtendimentos(new ArrayList<>());
        } else {
            agenda.getAtendimentos().clear();
        }
        if (dto.getAtendimentos() != null)
            for (AtendimentoDTO atendimentoDTO : dto.getAtendimentos()) {
                Atendimento atendimento = new Atendimento();
                BeanUtils.copyProperties(atendimentoDTO, atendimento);
                atendimento.setAgenda(agenda);
                agenda.getAtendimentos().add(atendimento);
            }

        if (agenda.getConvenios() == null) {
            agenda.setConvenios(new HashSet<>());
        } else {
            agenda.getConvenios().clear();
        }
        if (convenios != null) {
            agenda.getConvenios().addAll(convenios);
        }

    }

    public AgendaDTO to(AgendaRec rec) {
        AgendaDTO dto = new AgendaDTO();
        dto.setId(rec.id());
        dto.setDescricao(rec.descricao());

        dto.setFuncionario(new FuncionarioDTO());
        dto.getFuncionario().setNome(rec.nome());

        dto.setAtendimentos(new ArrayList<>());
        for (Atendimento atendimento : rec.atendimentos()) {
            AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
            atendimentoDTO.setDia(atendimento.getDia());
            dto.getAtendimentos().add(atendimentoDTO);
        }
        return dto;
    }

    public Agenda to(AgendaDTO dto) {
        Agenda agenda = new Agenda();
        BeanUtils.copyProperties(dto, agenda);

        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(dto.getFuncionario(), funcionario);
        agenda.setFuncionario(funcionario);

        Especialidade especialidade = new Especialidade();
        BeanUtils.copyProperties(dto.getEspecialidade(), especialidade);
        agenda.setEspecialidade(especialidade);

        if (agenda.getAtendimentos() == null) {
            agenda.setAtendimentos(new ArrayList<>());
        }

        for (AtendimentoDTO atendimentoDTO : dto.getAtendimentos()) {
            Atendimento atendimento = new Atendimento();
            BeanUtils.copyProperties(atendimentoDTO, atendimento);
            atendimento.setAgenda(agenda);
            agenda.getAtendimentos().add(atendimento);
        }
        return agenda;
    }

    public AgendaDTO toListAll(Agenda obj) {
        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setId(obj.getId());
        agendaDTO.setDescricao(obj.getDescricao());
        agendaDTO.setDuracao(obj.getDuracao());

        FuncionarioDTO funcinarioDTO = new FuncionarioDTO();
        funcinarioDTO.setNome(obj.getFuncionario().getNome());
        agendaDTO.setFuncionario(funcinarioDTO);

        if (agendaDTO.getAtendimentos() == null) {
            agendaDTO.setAtendimentos(new ArrayList<>());
        }
        agendaDTO.getAtendimentos().clear();

        Set<AtendimentoDTO> listaSet = new HashSet<>();
        obj.getAtendimentos().forEach(atendimento -> {
            AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
            atendimentoDTO.setDia(atendimento.getDia());
            listaSet.add(atendimentoDTO);
        });

        agendaDTO.getAtendimentos().addAll(listaSet);

        return agendaDTO;
    }

    public AgendaDTO to(Agenda obj) {
        AgendaDTO agendaDTO = new AgendaDTO();
        BeanUtils.copyProperties(obj, agendaDTO);

        FuncionarioDTO funcinarioDTO = new FuncionarioDTO();
        BeanUtils.copyProperties(obj.getFuncionario(), funcinarioDTO);
        agendaDTO.setFuncionario(funcinarioDTO);

        EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
        BeanUtils.copyProperties(obj.getEspecialidade(), especialidadeDTO);
        agendaDTO.setEspecialidade(especialidadeDTO);

        if (agendaDTO.getAtendimentos() == null) {
            agendaDTO.setAtendimentos(new ArrayList<>());
        }

        obj.getAtendimentos().forEach(atendimento -> {
            AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
            BeanUtils.copyProperties(atendimento, atendimentoDTO);

            agendaDTO.getAtendimentos().add(atendimentoDTO);
        });
        if (agendaDTO.getConvenios() == null) {
            agendaDTO.setConvenios(new HashSet<>());
        }
        if (obj.getConvenios() != null && !obj.getConvenios().isEmpty()) {
            for (Convenio c : obj.getConvenios()) {
                ConvenioDTO convenioDTO = new ConvenioDTO();
                BeanUtils.copyProperties(c, convenioDTO);
                agendaDTO.getConvenios().add(convenioDTO);
            }
        }

        return agendaDTO;
    }

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
        if (obj.getEndereco() != null) {
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

    public Departamento to(DepartamentoDTO dto) {
        Departamento obj = new Departamento();
        BeanUtils.copyProperties(dto, obj);
        return obj;
    }

    public DepartamentoDTO to(Departamento obj) {
        DepartamentoDTO dto = new DepartamentoDTO();
        BeanUtils.copyProperties(obj, dto);
        return dto;
    }

    public void to(DepartamentoDTO dto, Departamento obj) {
        BeanUtils.copyProperties(dto, obj);
    }

    public void jsonFormatado(Object dto) {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto));
    }
}
