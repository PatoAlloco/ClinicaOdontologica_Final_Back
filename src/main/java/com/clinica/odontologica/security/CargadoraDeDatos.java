package com.clinica.odontologica.security;

import com.clinica.odontologica.entities.Domicilio;
import com.clinica.odontologica.entities.Odontologo;
import com.clinica.odontologica.entities.Paciente;
import com.clinica.odontologica.entities.RolUsuario;
import com.clinica.odontologica.entities.Usuario;
import com.clinica.odontologica.repository.PacienteRepository;
import com.clinica.odontologica.repository.UsuarioRepository;
import com.clinica.odontologica.service.OdontologoService;
import com.clinica.odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class CargadoraDeDatos implements ApplicationRunner {

    UsuarioRepository usuarioRepository;
    PacienteService pacienteService;
    OdontologoService odontologoService;

    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository, PacienteService pacienteService,
                            OdontologoService odontologoService) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //cargar un usuario previo al inicio de todo
        //guardar un usuario mediante repository
        //USER
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = "digital";
        String passHash = passwordEncoder.encode(pass);
        Usuario usuario = new Usuario();
        usuario.setNombre("Patricio");
        usuario.setEmail("patoalloco13@gmail.com");
        usuario.setPassword(passHash);
        usuario.setUruarioRol(RolUsuario.ROLE_USER);
        this.usuarioRepository.save(usuario);

        //ADMIN
        String pass2 = "house";
        String passHash2 = passwordEncoder.encode(pass2);
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Oscar");
        usuario2.setEmail("patoalloco13@hotmail.com");
        usuario2.setPassword(passHash2);
        usuario2.setUruarioRol(RolUsuario.ROLE_ADMIN);
        this.usuarioRepository.save(usuario2);


        Odontologo o = new Odontologo();
        o.setNombre("Leo");
        o.setApellido("Messi");
        o.setMatricula("Mat1");
        this.odontologoService.guardar(o);

        Domicilio d = new Domicilio();
        d.setProvincia("Cordoba");
        d.setLocalidad("Bell ville");
        d.setCalle("Da silva");
        d.setNumero(789);

        Paciente p = new Paciente();
        p.setNombre("Pato");
        p.setApellido("Alloco");
        p.setEmail("pato@alloco.com");
        p.setFecha(LocalDate.parse("1986-08-04"));
        p.setDni(123456789);
        p.setDomicilio(d);

        this.pacienteService.guardar(p);
    }
}
