window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_turno');
    const selectPacientes = document.querySelector('#selectPacientes');
    const selectOdontologos = document.querySelector('#selectOdontologos');

    formulario.addEventListener('submit', function (event) {

        const formData = {
                    odontologoId: +document.querySelector('#selectOdontologos').value,
                    pacienteId: +document.querySelector('#selectPacientes').value,
                    fecha: document.querySelector('#fecha').value,
        };

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                   '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                   '<strong></strong> Turno agregado </div>'
                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                 '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })

    });

    function resetUploadForm(){
        document.querySelector('#fecha').value = "";
    }

    function traerPacientes() {
                const url = '/pacientes';
                const configuraciones = {
                  method: 'GET',
                }

                fetch(url, configuraciones)
                    .then(respueta => respueta.json())
                    .then(data => {
                        selectPacientes.innerHTML = "";
                        data.forEach(paciente => {
                        selectPacientes.innerHTML += ' <option value="' + paciente.id + '">'+ paciente.apellido + ' ' + paciente.nombre + '</option>'
                        })
                    })
    };
    traerPacientes();

    function traerOdontologos() {
                const url = '/odontologos';
                const configuraciones = {
                   method: 'GET',
                }

                fetch(url, configuraciones)
                    .then(respueta => respueta.json())
                    .then(data => {
                        selectOdontologos.innerHTML = "";
                        data.forEach(odontologo => {
                        selectOdontologos.innerHTML += ' <option value="' + odontologo.id + '">'+ odontologo.apellido + ' ' + odontologo.nombre + '</option>'
                        })
                    })
    };
    traerOdontologos();
})