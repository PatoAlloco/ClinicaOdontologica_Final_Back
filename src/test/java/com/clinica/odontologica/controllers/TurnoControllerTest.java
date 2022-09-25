package com.clinica.odontologica.controllers;

import com.clinica.odontologica.dto.TurnoDTO;
import com.clinica.odontologica.entities.Domicilio;
import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.entities.Paciente;
import com.clinica.odontologica.service.OdontologoService;
import com.clinica.odontologica.service.PacienteService;
import com.clinica.odontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TurnoControllerTest {

    //TEST DE INTEGRACION
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void cargarDatos(){
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

        TurnoDTO turno= new TurnoDTO();
        turno.setFecha(LocalDate.of(2022,9,19));
        turno.setOdontologoId(1L);
        turno.setPacienteId(1L);
        TurnoDTO turnoGuardado = turnoService.guardarDTO(turno);
        assertEquals(1,turnoGuardado.getId());
    }

    @Test
    public void listarTurnosTest() throws Exception {
        this.cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders
                        .get("/turnos")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}