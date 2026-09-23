package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Query("SELECT distinct a FROM Agenda a " +
            "LEFT JOIN fetch a.funcionario fu " +
            "LEFT JOIN fetch a.especialidade es " +
            "LEFT JOIN fetch fu.medico me " +
            "left join a.convenios c " +
            "left join a.atendimentos at " +
            "WHERE a.id = :id")
    Optional<Agenda> findAgenda(@Param("id") Long id);

    @Query("SELECT DISTINCT a FROM Agenda a " +
            "LEFT JOIN FETCH a.funcionario f " +
            "LEFT JOIN FETCH a.atendimentos at")
    List<Agenda> listAll();
}
