window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');
    const selectOdontologos = document.querySelector('#selectOdontologos');
    const selectPacientes = document.querySelector('#selectPacientes');

    formulario.addEventListener('submit', function (event) {
        const formData = {
            id: +document.querySelector('#turno_id').value,
            pacienteId: +selectPacientes.value,
            odontologoId: +selectOdontologos.value,
            fecha: document.querySelector('#fecha').value
        };

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
            .then(response => response.json())
    })
 })

function findBy(id) {
    const url = '/turnos/'+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            document.querySelector('#turno_id').value = turno.id;
            document.querySelector('#fecha').value = turno.fecha;

            traerPacientesConSeleccion(turno.pacienteId);            //traigo el turno a editar con el paciente seleccionado
            traerOdontologosConSeleccion(turno.odontologoId);

            document.querySelector('#div_turno_updating').style.display = "block";
        }).catch(error => {
          alert("Error: " + error);
        })
}


function traerPacientesConSeleccion(id) {
    const url = '/pacientes';
    const configuraciones = {
      method: 'GET',
    }

    fetch(url, configuraciones)
        .then(respueta => respueta.json())
        .then(data => {
            selectPacientes.innerHTML = "";    //borro todos
            data.forEach(paciente => {
                if (paciente.id == id) {       //si coincide el id le agrego el selected
                    selectPacientes.innerHTML += ' <option selected value="' + paciente.id + '">'+ paciente.apellido + ' ' + paciente.nombre + '</option>'
                } else {
                    selectPacientes.innerHTML += ' <option value="' + paciente.id + '">'+ paciente.apellido + ' ' + paciente.nombre + '</option>'
                }
            })
        })
};

function traerOdontologosConSeleccion(id) {
    const url = '/odontologos';
    const configuraciones = {
       method: 'GET',
    }

    fetch(url, configuraciones)
        .then(respueta => respueta.json())
        .then(data => {
            selectOdontologos.innerHTML = "";
            data.forEach(odontologo => {
                if (odontologo.id == id) {
                    selectOdontologos.innerHTML += ' <option selected value="' + odontologo.id + '">'+ odontologo.apellido + ' ' + odontologo.nombre + '</option>'
                } else {
                    selectOdontologos.innerHTML += ' <option value="' + odontologo.id + '">'+ odontologo.apellido + ' ' + odontologo.nombre + '</option>'
                }
            })
        })
};



