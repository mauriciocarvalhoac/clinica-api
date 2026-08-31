package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Convenio;
import com.mrc.clinic.clinic_api.entity.rec.ConvenioRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConveinoRepository extends JpaRepository<Convenio, Long> {
    @Query("select new com.mrc.clinic.clinic_api.entity.rec.ConvenioRec(c.id, c.descricao, c.situacao) from Convenio c")
    List<ConvenioRec> listAll();
}