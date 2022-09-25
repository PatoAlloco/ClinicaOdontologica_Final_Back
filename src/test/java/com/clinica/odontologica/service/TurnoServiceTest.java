package com.clinica.odontologica.service;

import com.clinica.odontologica.dto.TurnoDTO;
import com.clinica.odontologica.entities.Domicilio;
import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.entities.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void precargarDatos(){
        //me voy encargar de preparar un paciente y un odontologo
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle x");
        domicilio.setNumero(584);
        domicilio.setLocalidad("Salta");
        domicilio.setProvincia("Salta");
        Paciente paciente= new Paciente();
        paciente.setNombre("Rodolfo");
        paciente.setApellido("Baspineiro");
        paciente.setDomicilio(domicilio);
        pacienteService.guardar(paciente);

        Odontologo odontologo=new Odontologo();
        odontologo.setNombre("Colmillo");
        odontologo.setApellido("Cariado");
        odontologoService.guardar(odontologo);
    }

    @Test
    void eliminar() {
    }

    @Test
    void buscarXIdDTO() {
    }

    @Test
    @Order(2)
    void buscarDTOxID() throws Exception {
//        Optional<TurnoDTO> turnoBuscado = this.turnoService.buscarXIdDTO(1L);
//        TurnoDTO turnoBuscadoFinal = turnoBuscado.get();
//        assertNotNull(turnoBuscadoFinal);
    }

    @Test
    void buscarTodosDTO() {
    }

    @Test
    @Order(1)
    void guardarDTO() {
        precargarDatos();
        //preparar un turno para su guardado
        TurnoDTO turno = new TurnoDTO();
        turno.setFecha(LocalDate.of(2022,9,19));
        turno.setOdontologoId(1L);
        turno.setPacienteId(1L);
        TurnoDTO turnoGuardado = turnoService.guardarDTO(turno);
        assertEquals(1,turnoGuardado.getId());
    }

    @Test
    void actualizarDTO() {
    }
}