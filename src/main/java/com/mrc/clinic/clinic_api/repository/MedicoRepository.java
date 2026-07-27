package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Medico;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByCpf(String cpf);

    @Nullable List<Medico> findByNomeAndCpf(String nome, String cpf);

}
