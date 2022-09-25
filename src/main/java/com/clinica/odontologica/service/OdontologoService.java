package com.clinica.odontologica.service;

import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.exceptions.ResourceNotFoundException;
import com.clinica.odontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private final OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardar(Odontologo odontologo){
        return this.odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarXId(Long id){
        return this.odontologoRepository.findById(id);
    }
    public Optional<Odontologo> buscarXMatricula(String matricula){
        return this.odontologoRepository.findOdontologoByMatriculaIgnoreCase(matricula);
    }
    public List<Odontologo> buscarTodos(){
        return this.odontologoRepository.findAll();
    }
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = this.buscarXId(id);
        if(odontologo.isPresent()){
            this.odontologoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No existe un Odontólogo con el id ingresado");
        }
    }

    public Odontologo actualizar(Odontologo odontologo){
        return this.odontologoRepository.save(odontologo);
    }


}
