package com.clinica.odontologica.service;

import com.clinica.odontologica.entities.Domicilio;
import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DomicilioService {

    private final DomicilioRepository domicilioRepository;

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio guardar(Domicilio domicilio){
        return this.domicilioRepository.save(domicilio);
    }
    public Optional<Domicilio> buscarXId(Long id){
        return this.domicilioRepository.findById(id);
    }

    public Domicilio actualizar(Domicilio domicilio){
        return this.domicilioRepository.save(domicilio);
    }

}
