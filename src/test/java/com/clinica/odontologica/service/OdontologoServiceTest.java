package com.clinica.odontologica.service;

import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;



    @Test
    @Order(1)
    void guardar() {
        Odontologo o = this.odontologoService.guardar(new Odontologo());
        assertEquals(2L, o.getId());
        //hago la comparacion con el 2L porque ejecuta el test de TurnoServiceTest tambien
    }

    @Test
    @Order(2)
    void buscarXId() {
        Optional<Odontologo> odontologoBuscado = this.odontologoService.buscarXId(1l);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    void buscarTodos() {
        List<Odontologo> odontologos = this.odontologoService.buscarTodos();
        assertEquals(2, odontologos.size());
        //La comparacion es con 2L por el test de turnoServiceTest
    }

    @Test
    @Order(4)
    void actualizar() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("jose");
        odontologo.setId(1L);
        Odontologo odontologoActualizado = this.odontologoService.actualizar(odontologo);
        assertEquals("jose", odontologoActualizado.getNombre());
        assertEquals(1L, odontologoActualizado.getId());
    }

    @Test
    @Order(5)
    void eliminar() throws ResourceNotFoundException {
        this.odontologoService.eliminar(1L);
        List<Odontologo> odontologos = this.odontologoService.buscarTodos();
        assertEquals(1, odontologos.size());
        //Queda un solo objeto por el TurnoServiceTest que habia cargado un odontologo
    }


}