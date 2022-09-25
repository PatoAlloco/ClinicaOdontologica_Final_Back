package com.clinica.odontologica.repository;

import com.clinica.odontologica.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
//    @Query("select t from Turno t where t.paciente_id=?1") //puede fallar
//    Optional<List<Turno>> buscarPorPacienteId(Long paciente_id);
    }

