package com.mrc.clinic.clinic_api.repository;

import com.mrc.clinic.clinic_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String usuario);

    @Query("SELECT u FROM Usuario u left join fetch u.funcionario f")
    List<Usuario> list();
}
