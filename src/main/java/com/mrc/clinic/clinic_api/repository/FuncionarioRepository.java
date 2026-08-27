package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Funcionario;
import com.mrc.clinic.clinic_api.entity.rec.FuncionarioRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);

    @Query("SELECT f FROM Funcionario f")
    List<Funcionario> listAll();

//    @Query("SELECT m FROM Funcionario m LEFT JOIN FETCH m.funcionarioEspecialidades me LEFT JOIN FETCH me.especialidade e  WHERE m.id = :id")
//    Optional<Funcionario> findFuncionarioById(Long id);

    @Query("select new com.mrc.clinic.clinic_api.entity.rec.FuncionarioRec(f.id, f.nome, f.cpf, f.email, f.funcao) from Funcionario f")
    List<FuncionarioRec> list();

    List<Funcionario> findByNomeAndCpf(String nome, String cpf);

}
