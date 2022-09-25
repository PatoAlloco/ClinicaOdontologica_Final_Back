 function buscarPorMail() {
    const mail = document.querySelector('#mailBuscar').value;

    const url = '/pacientes/email/' + mail;
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
      .then(response => response.json())
      .then(data => {
        if (data){
             renderizarPacientes([data]);
        }
      }).catch(error => {
          alert("No hay concidencias");
      })
 }

 function renderizarPacientes(data){
    var table = document.getElementById("pacienteTable");
    while(table.rows.length > 1) {
      table.deleteRow(1);
    }

    for(paciente of data){
        var pacienteRow = table.insertRow();
        let tr_id = 'tr_' + paciente.id;
        pacienteRow.id = tr_id;

        let deleteButton = '<button' + ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                           ' type="button" onclick="deleteBy('+ paciente.id +')" class="btn btn-danger btn_delete">' +
                           '&times</button>';

        let updateButton = '<button' +' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                           ' type="button" onclick="findBy('+ paciente.id +')" class="btn btn-info btn_id">' +
                           paciente.id + '</button>';

    pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
            '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
            '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>'+
            '<td class=\"td_email\">' + paciente.email + '</td>' +
            '<td class=\"td_dni\">' + paciente.dni + '</td>' +
            '<td class=\"td_fecha\">' + paciente.fecha + '</td>' +
            '<td class=\"td_calle\">' + paciente.domicilio.calle.toUpperCase() + '</td>' +
            '<td class=\"td_numero\">' + paciente.domicilio.numero + '</td>' +
            '<td class=\"td_localidad\">' + paciente.domicilio.localidad.toUpperCase()  + '</td>' +
            '<td class=\"td_provincia\">' + paciente.domicilio.provincia.toUpperCase()  + '</td>' +
            '<td>' + deleteButton + '</td>';
    };
 }
