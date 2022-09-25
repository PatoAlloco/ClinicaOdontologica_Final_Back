package com.clinica.odontologica.repository;

import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findPacienteByEmail(String email);
}
