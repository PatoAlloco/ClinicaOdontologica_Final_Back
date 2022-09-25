 function buscarPorMatricula() {                                           //funcion que llamo en el boton del serch
     const matricula = document.querySelector('#matriculaBuscar').value;   //id del input

      const url = '/odontologos/matricula/' + matricula;
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)                                                  //pegada para buscar por matricula
      .then(response => response.json())
      .then(data => {
        if (data){                                                         //si trae algo lo mando como objeto a renderizar
             renderizarOdontologos([data]);
        }
      }).catch(error => {                                                  //si no tira la alerta
          alert("No hay concidencias");
      })
     }

 function renderizarOdontologos(data){
      var table = document.getElementById("odontologoTable");             //asigno el resultado a table
      while(table.rows.length > 1) {
        table.deleteRow(1);               //borro todas las lineas mayores a 1 para quedarme con un solo resultado renderizado
      }

      for(odontologo of data){
         var odontologoRow = table.insertRow();
         let tr_id = 'tr_' + odontologo.id;
         odontologoRow.id = tr_id;

         let deleteButton = '<button' + ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                            ' type="button" onclick="deleteBy('+ odontologo.id +')" class="btn btn-danger btn_delete">' +
                            '&times</button>';

         let updateButton = '<button' +' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                            ' type="button" onclick="findBy('+ odontologo.id +')" class="btn btn-info btn_id">' +
                            odontologo.id + '</button>';

         odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                 '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                 '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>'+
                 '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                 '<td>' + deleteButton + '</td>';
      };
     }
