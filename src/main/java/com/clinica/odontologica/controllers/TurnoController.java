package com.clinica.odontologica.controllers;

import com.clinica.odontologica.dto.TurnoDTO;
import com.clinica.odontologica.exceptions.BadRequestException;
import com.clinica.odontologica.exceptions.ResourceNotFoundException;
import com.clinica.odontologica.service.OdontologoService;
import com.clinica.odontologica.service.PacienteService;
import com.clinica.odontologica.service.TurnoService;
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
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurnoDTO(@RequestBody TurnoDTO turnoDTO) throws BadRequestException {
        ResponseEntity<TurnoDTO> respuesta;
        if (odontologoService.buscarXId(turnoDTO.getOdontologoId()).isPresent() &&
                pacienteService.buscar(turnoDTO.getPacienteId()).isPresent()) {
            respuesta = ResponseEntity.ok(turnoService.guardarDTO(turnoDTO));
        } else{
            //respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            throw new BadRequestException("No se encuentra el odontólogo o el paciente con el id ingresado");
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
            this.turnoService.eliminar(id);
            return ResponseEntity.ok("Se elimino el turno correctamente");
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurnoDTO(@RequestBody TurnoDTO turno){
        try {
            ResponseEntity<TurnoDTO> respuesta;
            if (this.turnoService.buscar(turno.getId()).isPresent()) {
                if (this.odontologoService.buscarXId(turno.getOdontologoId()).isPresent() &&
                        this.pacienteService.buscar(turno.getPacienteId()).isPresent()) {
                    respuesta = ResponseEntity.ok(this.turnoService.actualizarDTO(turno));
                } else {
                    respuesta = ResponseEntity.badRequest().build();
                }
            } else {
                respuesta = ResponseEntity.badRequest().build();
            }
            return respuesta;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarxIddto1(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.turnoService.buscarDTOxID(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<TurnoDTO> buscarXIdDTO2(Long id){
        Optional<TurnoDTO> tdto = this.turnoService.buscarXIdDTO(id);
        if(tdto.isPresent()){
            return ResponseEntity.ok(tdto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodoDTO(){
        return ResponseEntity.ok(this.turnoService.buscarTodosDTO());
    }
}
