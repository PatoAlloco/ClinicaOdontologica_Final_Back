package com.clinica.odontologica.service;

import com.clinica.odontologica.entities.Domicilio;
import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.entities.Paciente;
import com.clinica.odontologica.exceptions.ResourceNotFoundException;
import com.clinica.odontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final DomicilioService domicilioService;
    private final PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, DomicilioService domicilioService) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioService = domicilioService;
    }

    public Paciente guardar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscar(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarXEmail(String email){
        return pacienteRepository.findPacienteByEmail(email);
    }
    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = this.buscar(id);
        if(paciente.isPresent()){
            this.pacienteRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("No se enconro un paciente con el id ingresado");
        }
    }
    public Paciente actualizar(Paciente paciente){
//        String idString = paciente.getDomicilio().getId().toString();
//        Long idDemicilio = Long.parseLong(idString);
//
//        Optional<Domicilio> domicilio = this.domicilioService.buscarXId(idDemicilio);
//        if(domicilio.isPresent()){
//            Domicilio d = domicilio.get();
//
//            d.setCalle(paciente.getDomicilio().getCalle());
//            d.setNumero(paciente.getDomicilio().getNumero());
//            d.setLocalidad(paciente.getDomicilio().getLocalidad());
//            d.setProvincia(paciente.getDomicilio().getProvincia());
//            paciente.setDomicilio(d);
//        }
        return this.pacienteRepository.save(paciente);
    }

}
