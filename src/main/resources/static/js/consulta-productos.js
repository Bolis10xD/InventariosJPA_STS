$(document).ready(function () {
    printProductos();
});

function printProductos() {
    $.get('/productos/all/for-table', function (productos) {
        $('#t-productos tbody').empty();
        $.each(productos, function (i, producto) {
            var fila = crearFila();

            appendColContent(fila, producto.nombre);
            appendColContent(fila, producto.stock);
            appendColContent(fila, '$' + producto.precio);
            appendColContent(fila, producto.fechaAlta);
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

function crearColumna() {
    return $('<td></td>');
}

icons = {
    delete: '<svg class="bi bi-trash-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
        '  <path fill-rule="evenodd" d="M2.5 1a1 1 0 00-1 1v1a1 1 0 001 1H3v9a2 2 0 002 2h6a2 2 0 002-2V4h.5a1 1 0 001-1V2a1 1 0 00-1-1H10a1 1 0 00-1-1H7a1 1 0 00-1 1H2.5zm3 4a.5.5 0 01.5.5v7a.5.5 0 01-1 0v-7a.5.5 0 01.5-.5zM8 5a.5.5 0 01.5.5v7a.5.5 0 01-1 0v-7A.5.5 0 018 5zm3 .5a.5.5 0 00-1 0v7a.5.5 0 001 0v-7z" clip-rule="evenodd"/>\n' +
        '</svg>',
    edit: '<svg class="bi bi-pencil" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
        '  <path fill-rule="evenodd" d="M11.293 1.293a1 1 0 011.414 0l2 2a1 1 0 010 1.414l-9 9a1 1 0 01-.39.242l-3 1a1 1 0 01-1.266-1.265l1-3a1 1 0 01.242-.391l9-9zM12 2l2 2-9 9-3 1 1-3 9-9z" clip-rule="evenodd"/>\n' +
        '  <path fill-rule="evenodd" d="M12.146 6.354l-2.5-2.5.708-.708 2.5 2.5-.707.708zM3 10v.5a.5.5 0 00.5.5H4v.5a.5.5 0 00.5.5H5v.5a.5.5 0 00.5.5H6v-1.5a.5.5 0 00-.5-.5H5v-.5a.5.5 0 00-.5-.5H3z" clip-rule="evenodd"/>\n' +
        '</svg>'
}