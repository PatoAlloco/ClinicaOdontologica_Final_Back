package com.clinica.odontologica.controllers;

import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.exceptions.ResourceNotFoundException;
import com.clinica.odontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoCotroller {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoCotroller(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public Odontologo guardar(@RequestBody Odontologo odontologo) {
        return this.odontologoService.guardar(odontologo);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos() {
        return ResponseEntity.ok(this.odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) {
        Optional<Odontologo> odontlogoBuscado = this.odontologoService.buscarXId(id);
        if (odontlogoBuscado.isPresent()) {
            return ResponseEntity.ok(odontlogoBuscado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula){
        Optional<Odontologo> odontlogoBuscado = this.odontologoService.buscarXMatricula(matricula);
        if(odontlogoBuscado.isPresent()){
            return ResponseEntity.ok(odontlogoBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        this.odontologoService.eliminar(id);
        return ResponseEntity.ok("Se elimino el odontologo con id: " + id);
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoBuscado = this.odontologoService.buscarXId(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(this.odontologoService.actualizar(odontologo));
        }
        return ResponseEntity.badRequest().build();
    }
}
