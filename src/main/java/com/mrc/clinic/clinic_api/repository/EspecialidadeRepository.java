package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    Optional<Especialidade> findByDescricao(String descricao);
}