package com.clinica.odontologica.dto;

import com.clinica.odontologica.entities.Turno;

import java.time.LocalDate;

public class TurnoDTO {
    private Long id;
    private String paciente;
    private String odontologo;
    private LocalDate fecha;

    private Long pacienteId;
    private Long odontologoId;

    public TurnoDTO(Turno turno){
        this.id = turno.getId();
        this.paciente = turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido();
        this.odontologo = turno.getOdontologo().getNombre() + " " + turno.getOdontologo().getApellido();
        this.fecha = turno.getFecha();
        this.pacienteId = turno.getPaciente().getId();
        this.odontologoId = turno.getOdontologo().getId();
    }

    public TurnoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(String odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }
}
