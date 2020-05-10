$(document).ready(function () {
    printProductos();
});

function printProductos() {
    $.get('/productos/all/for-table', function (productos) {
        $('#t-productos tbody').empty();
        $.each(productos, function (i, producto) {
            var fila = crearFila();

            appendColContent(fila,crearColId(producto.id));
            appendColContent(fila, producto.nombre);
            appendColContent(fila, producto.stock);
            appendColContent(fila, '$' + producto.precio);
            appendColContent(fila, producto.fechaAlta);
            appendColIconContent(fila,icons.Alta,producto, function (producto) {
                $.get('/productos/alta/'+ producto.id, function () {
                    window.location.href='/productos/alta/'+ producto.id;
                });
            });

            appendColIconContent(fila,icons.Baja,producto, function (producto) {

            });

            appendColImgContent(fila, producto.imagen);
            appendColIconContent(fila, icons.edit, producto, function (producto) {
                console.log(producto);
            });

            appendColIconContent(fila, icons.delete, producto, function (producto) {
                console.log("borrar", producto);
                $.get('/productos/delete/' + producto.id, function (response) {
                    alert(response);
                    printProductos();
                }).fail(function() {
                    alert( "Error al borrar el producto" );
                })
            });

            $('#t-productos tbody').append($(fila));

        })
    })
}


function appendColContent(fila, contenido) {
    var col = crearColumna();
    $(col).append(contenido);
    $(fila).append(col);
}


function appendColImgContent(fila, imagen) {
    var col = crearColumna();
    $(col).append('<img width="100px" src="data:' + imagen.mimeType + ';base64,' + imagen.dataBase64 + '">');
    $(fila).append(col);
}

function appendColIconContent(fila, icono, producto, onclick) {
    var col = crearColumna();
    $(col).append(icono);
    $(fila).append(col);

    $(col).on('click', function () {
        onclick(producto);
    });
}




function crearFila() {
    return $('<tr></tr>');
}

function crearFAlta(ID) {
    return $('<form><input type="button" name="Alta_btn" value="Alta" id="'+ID+'" class="btn-success" role="link" onclick="window.location = '+"'/productos/page/alta-producto'"+'" /></form>')
    }

function crearFBaja() {
    return $('<a id="btn-baja" class="btn btn-danger" role="button">Baja</a>')
}

function crearColId(ID) {
    var ii = ID;
    return $(' <div class="auto" id="'+ii+'">' +ii+
        '</div>');
}


function crearColumna() {
    return $('<td></td>');
}


icons = {
    delete: '<svg class="bi Guardarbi-trash-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
        '  <path fill-rule="evenodd" d="M2.5 1a1 1 0 00-1 1v1a1 1 0 001 1H3v9a2 2 0 002 2h6a2 2 0 002-2V4h.5a1 1 0 001-1V2a1 1 0 00-1-1H10a1 1 0 00-1-1H7a1 1 0 00-1 1H2.5zm3 4a.5.5 0 01.5.5v7a.5.5 0 01-1 0v-7a.5.5 0 01.5-.5zM8 5a.5.5 0 01.5.5v7a.5.5 0 01-1 0v-7A.5.5 0 018 5zm3 .5a.5.5 0 00-1 0v7a.5.5 0 001 0v-7z" clip-rule="evenodd"/>\n' +
        '</svg>',
    edit: '<svg class="bi bi-pencil" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
        '  <path fill-rule="evenodd" d="M11.293 1.293a1 1 0 011.414 0l2 2a1 1 0 010 1.414l-9 9a1 1 0 01-.39.242l-3 1a1 1 0 01-1.266-1.265l1-3a1 1 0 01.242-.391l9-9zM12 2l2 2-9 9-3 1 1-3 9-9z" clip-rule="evenodd"/>\n' +
        '  <path fill-rule="evenodd" d="M12.146 6.354l-2.5-2.5.708-.708 2.5 2.5-.707.708zM3 10v.5a.5.5 0 00.5.5H4v.5a.5.5 0 00.5.5H5v.5a.5.5 0 00.5.5H6v-1.5a.5.5 0 00-.5-.5H5v-.5a.5.5 0 00-.5-.5H3z" clip-rule="evenodd"/>\n' +
        '</svg>',
    Alta: '<svg class="btn-success rounded" width="1em" height="1em" viewBox="0 0 20 20">\n' +
            '<path fill-rule="none" d="M8.416,3.943l1.12-1.12v9.031c0,0.257,0.208,0.464,0.464,0.464c0.256,0,0.464-0.207,0.464-0.464V2.823l1.12,1.12c0.182,0.182,0.476,0.182,0.656,0c0.182-0.181,0.182-0.475,0-0.656l-1.744-1.745c-0.018-0.081-0.048-0.16-0.112-0.224C10.279,1.214,10.137,1.177,10,1.194c-0.137-0.017-0.279,0.02-0.384,0.125C9.551,1.384,9.518,1.465,9.499,1.548L7.76,3.288c-0.182,0.181-0.182,0.475,0,0.656C7.941,4.125,8.234,4.125,8.416,3.943z M15.569,6.286h-2.32v0.928h2.32c0.512,0,0.928,0.416,0.928,0.928v8.817c0,0.513-0.416,0.929-0.928,0.929H4.432c-0.513,0-0.928-0.416-0.928-0.929V8.142c0-0.513,0.416-0.928,0.928-0.928h2.32V6.286h-2.32c-1.025,0-1.856,0.831-1.856,1.856v8.817c0,1.025,0.832,1.856,1.856,1.856h11.138c1.024,0,1.855-0.831,1.855-1.856V8.142C17.425,7.117,16.594,6.286,15.569,6.286z"></path>' +
           '</svg>',
    Baja: '<svg class="btn-danger rounded" width="1em" height="1em" viewBox="0 0 20 20">\n' +
            '<path fill-rule="none" d="M15.608,6.262h-2.338v0.935h2.338c0.516,0,0.934,0.418,0.934,0.935v8.879c0,0.517-0.418,0.935-0.934,0.935H4.392c-0.516,0-0.935-0.418-0.935-0.935V8.131c0-0.516,0.419-0.935,0.935-0.935h2.336V6.262H4.392c-1.032,0-1.869,0.837-1.869,1.869v8.879c0,1.031,0.837,1.869,1.869,1.869h11.216c1.031,0,1.869-0.838,1.869-1.869V8.131C17.478,7.099,16.64,6.262,15.608,6.262z M9.513,11.973c0.017,0.082,0.047,0.162,0.109,0.226c0.104,0.106,0.243,0.143,0.378,0.126c0.135,0.017,0.274-0.02,0.377-0.126c0.064-0.065,0.097-0.147,0.115-0.231l1.708-1.751c0.178-0.183,0.178-0.479,0-0.662c-0.178-0.182-0.467-0.182-0.645,0l-1.101,1.129V1.588c0-0.258-0.204-0.467-0.456-0.467c-0.252,0-0.456,0.209-0.456,0.467v9.094L8.443,9.553c-0.178-0.182-0.467-0.182-0.645,0c-0.178,0.184-0.178,0.479,0,0.662L9.513,11.973z"></path>' +
          '</svg>'

}