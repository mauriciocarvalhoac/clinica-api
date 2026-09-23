package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.EnumCargo;
import com.mrc.clinic.clinic_api.entity.Funcionario;
import com.mrc.clinic.clinic_api.entity.rec.FuncionarioRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);

    @Query("SELECT f FROM Funcionario f")
    List<Funcionario> listAll();

    @Query("select new com.mrc.clinic.clinic_api.entity.rec.FuncionarioRec(f.id, f.nome, f.cpf, f.email, f.funcao) from Funcionario f")
    List<FuncionarioRec> list();

    List<Funcionario> findByNomeAndCpf(String nome, String cpf);

    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.usuario u WHERE f.id = :id")
    Optional<Funcionario> findUsuarioByFuncionarioId(@Param("id") Long id);

    @Query("select f from Funcionario f " +
            "left join fetch f.medico m " +
            "left join m.medicoEspecialidades me " +
            "left join me.especialidade e " +
            "where f.id = :id")
    Optional<Funcionario> findMedicoById(@Param("id") Long id);

    @Query("select f from Funcionario f " +
            "left join fetch f.medico m " +
            "inner join m.medicoEspecialidades me " +
            "left join me.especialidade e ")
    List<Funcionario> listMedicosAndEspecialidades(EnumCargo enumCargo);

    @Query("select f from Funcionario f " +
            "left join fetch f.medico m " +
            "left join fetch m.medicoEspecialidades me " +
//            "left join fetch me.especialidade e " +
            "where f.id = :id")
    Optional<Funcionario> buscarEspecialidadeByMedico(@Param("id") Long id);
}
