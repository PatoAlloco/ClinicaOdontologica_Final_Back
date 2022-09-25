package com.clinica.odontologica.repository;

import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findOdontologoByMatriculaIgnoreCase(String matricula);

//    @Query("select o from Odontologo o where o.matricula=?1")
//    Optional<Odontologo> buscarPorMatricula(String matricula);
}
