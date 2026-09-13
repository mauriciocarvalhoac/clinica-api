package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Departamento;
import com.mrc.clinic.clinic_api.entity.enums.EnumSituacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findByDescricao(String descricao);

    List<Departamento> findBySituacao(EnumSituacao enumSituacao);
}
