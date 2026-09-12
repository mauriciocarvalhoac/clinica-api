package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    Optional<Especialidade> findByDescricao(String descricao);

    @Query("SELECT e FROM Especialidade e WHERE e.situacao = 'A' order by e.descricao asc")
    List<Especialidade> listAllAtivas();
}