package com.clinica.odontologica.service;

import com.clinica.odontologica.dto.TurnoDTO;
import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.entities.Paciente;
import com.clinica.odontologica.entities.Turno;
import com.clinica.odontologica.exceptions.ResourceNotFoundException;
import com.clinica.odontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Optional<Turno> buscar(Long id){
        return this.turnoRepository.findById(id);
    }
    public List<Turno> buscarTodos(){
        return this.turnoRepository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Turno> turno = this.buscar(id);
        if(turno.isPresent()){
            this.turnoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No existe un turno con el id ingresado");
        }
    }

    public Optional<TurnoDTO> buscarXIdDTO(Long id){
        Optional<Turno> t = this.turnoRepository.findById(id);
        if(t.isPresent()){
            return Optional.of(new TurnoDTO(t.get())); //el of me crea un optional
        }
        return Optional.empty();//optional vacio
    }

    public TurnoDTO buscarDTOxID(Long id) throws Exception {
        Optional<Turno> t = this.turnoRepository.findById(id);
        if(t.isPresent()){
            return new TurnoDTO(t.get());
        }
        throw new Exception("Turno no encontrado");
    }

    public List<TurnoDTO> buscarTodosDTO() {
        List<Turno> turnos = this.buscarTodos();
        if (turnos.isEmpty()) {
            return new ArrayList<>();
        }
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for (Turno t : turnos){
            turnosDTO.add(new TurnoDTO(t));
        }
        return turnosDTO;
    }

    public TurnoDTO guardarDTO(TurnoDTO turnoDTO){
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        Turno turno = new Turno();

        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return new TurnoDTO(this.turnoRepository.save(turno));
    }

    public TurnoDTO actualizarDTO(TurnoDTO turnoDTO) throws Exception {
        Optional<Turno> turnoBase = this.turnoRepository.findById(turnoDTO.getId());
        if(turnoBase.isPresent()) {
            Paciente paciente = new Paciente();
            Odontologo odontologo = new Odontologo();
            Turno t = turnoBase.get();

            paciente.setId(turnoDTO.getPacienteId());
            odontologo.setId(turnoDTO.getOdontologoId());
            t.setFecha(turnoDTO.getFecha());
            t.setOdontologo(odontologo);
            t.setPaciente(paciente);
            return new TurnoDTO(this.turnoRepository.save(t));
        }
        throw new Exception("Turno no encontrado");
    }
}
