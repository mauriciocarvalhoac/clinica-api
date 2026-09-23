package com.mrc.clinic.clinic_api.entity.rec;

import com.mrc.clinic.clinic_api.entity.Agenda;
import com.mrc.clinic.clinic_api.entity.Atendimento;

import java.util.List;

public record AgendaRec(Long id, String descricao, String nome, List<Atendimento> atendimentos) {

//    public AgendaRec(Agenda agenda) {
//        this(agenda.getId(), agenda.getDescricao(), agenda.getFuncionario().getNome(), agenda.getAtendimentos());
//    }

    public AgendaRec(Agenda agenda) {
        this(agenda.getId(), agenda.getDescricao(), agenda.getFuncionario().getNome(), agenda.getAtendimentos());
    }
}
