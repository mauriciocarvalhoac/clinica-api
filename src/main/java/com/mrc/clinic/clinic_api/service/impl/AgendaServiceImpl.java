package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Agenda;
import com.mrc.clinic.clinic_api.entity.Atendimento;
import com.mrc.clinic.clinic_api.entity.Convenio;
import com.mrc.clinic.clinic_api.entity.dto.*;
import com.mrc.clinic.clinic_api.exceptionConfig.exceptions.ObjectNotFoundException;
import com.mrc.clinic.clinic_api.repository.AgendaRepository;
import com.mrc.clinic.clinic_api.repository.ConveinoRepository;
import com.mrc.clinic.clinic_api.service.AgendaService;
import com.mrc.clinic.clinic_api.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl extends AbstractServiceImpl implements AgendaService {

    @Autowired
    private AgendaRepository repo;
    @Autowired
    private ConveinoRepository repoConvenio;

    @Override
    @Transactional
    public AgendaDTO save(AgendaDTO dto) {
        Agenda callback = repo.save(to(dto));
        return to(callback);
    }

    @Override
    public List<AgendaDTO> listAll() {
        return repo.listAll().stream().map(this::toListAll).toList();
    }

    @Override
    public List<AgendaDTO> filterBy(String nome) {
        return List.of();
    }

    @Override
    public AgendaDTO findAgenda(Long id) {
        AgendaDTO dto = repo.findAgenda(id).map(this::tofindAgenda).orElseThrow(() -> new ObjectNotFoundException(MsgUtil.ITEM_NAO_ENCONTRADO));
        return dto;
    }

    @Override
    public Long delete(Long id) {
        Agenda agenda = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(MsgUtil.ITEM_NAO_ENCONTRADO));
        repo.delete(agenda);
        return id;
    }

    @Transactional
    @Override
    public AgendaDTO update(Long id, AgendaDTO dto) {
        Agenda agenda = repo.findAgenda(id).orElseThrow(() -> new ObjectNotFoundException(MsgUtil.ITEM_NAO_ENCONTRADO));

        Set<Long> idsConvenio = dto.getConvenios().stream().map(ConvenioDTO::getId).collect(Collectors.toSet());
        List<Convenio> convenios = repoConvenio.findAllById(idsConvenio);

        to(dto, agenda, new HashSet(convenios));

        return to(repo.save(agenda));
    }

    private AgendaDTO tofindAgenda(Agenda agenda) {
        AgendaDTO dto = new AgendaDTO();
        dto.setId(agenda.getId());
        dto.setDescricao(agenda.getDescricao());
        dto.setDuracao(agenda.getDuracao());
        dto.setSala(agenda.getSala());
        dto.setModalidadeParticular(agenda.getModalidadeParticular());
        dto.setValorConsultaParticular(agenda.getValorConsultaParticular());
        dto.setModalidadeConvenio(agenda.getModalidadeConvenio());

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(agenda.getFuncionario().getId());
        funcionarioDTO.setNome(agenda.getFuncionario().getNome());
        dto.setFuncionario(funcionarioDTO);

        EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
        especialidadeDTO.setId(agenda.getEspecialidade().getId());
        especialidadeDTO.setDescricao(agenda.getEspecialidade().getDescricao());
        dto.setEspecialidade(especialidadeDTO);

        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(agenda.getFuncionario().getMedico().getId());
        dto.getFuncionario().setMedico(medicoDTO);

        Set<ConvenioDTO> convenios = new HashSet<>();
        for (Convenio c : agenda.getConvenios()) {
            ConvenioDTO convenioDTO = new ConvenioDTO();
            convenioDTO.setId(c.getId());
            convenioDTO.setNomeFantasia(c.getNomeFantasia());
            convenios.add(convenioDTO);
        }
        dto.setConvenios(convenios);

        List<AtendimentoDTO> atendimentos = new ArrayList<>();
        for (Atendimento a : agenda.getAtendimentos()) {
            AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
            atendimentoDTO.setId(a.getId());
            atendimentoDTO.setDia(a.getDia());
            atendimentoDTO.setHoraIni(a.getHoraIni());
            atendimentoDTO.setIntervaloIni(a.getIntervaloIni());
            atendimentoDTO.setIntervaloFim(a.getIntervaloFim());
            atendimentoDTO.setHoraFim(a.getHoraFim());
            atendimentos.add(atendimentoDTO);
        }
        dto.setAtendimentos(atendimentos);
        jsonFormatado(dto);
        return dto;
    }


}
