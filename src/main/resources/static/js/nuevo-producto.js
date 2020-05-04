var imagen = {};
$(document).ready(function () {
    $('#alert').hide();

    document.getElementById('imagen').addEventListener('change', handleFileSelect, false);

    $('#guardar-btn').on('click', function () {
        var nombre = $('#nombre').val();
        var precio = $('#precio').val();

        var productoDTO = {};
        productoDTO.nombre = nombre;
        productoDTO.precio = precio;
        productoDTO.imagen = {};
        productoDTO.imagen.nombre = imagen.nombre;
        productoDTO.imagen.mimeType = imagen.type;
        productoDTO.imagen.dataBase64 = imagen.base64String;

        $.ajax({
            'type': 'POST',
            'url': '/productos/guardar',
            'contentType': 'application/json',
            'data': JSON.stringify(productoDTO),
            'dataType': 'json',
            'success': function (id) {
                console.log("Prodcuto guardado ", id);
                $('#alert').show(500);
                setTimeout(function () {
                    $('#alert').hide(500);
                    window.location.href = "/productos/page/productos";
                }, 2000)
            },
            'error': function (err) {
                console.log(err);
                alert('Ocurrio algun error al guardar el producto')
            }
        });
    });


    function handleFileSelect(evt) {
        var f = evt.target.files[0];
        //nombre del archivo
        imagen.nombre = f.name;
        //tipo del archivo
        imagen.type = f.type;
        var reader = new FileReader();
        reader.onload = (function (theFile) {
            return function (e) {
                //convert a base 64
                var binaryData = e.target.result;
                imagen.base64String = window.btoa(binaryData);
                console.log(imagen.base64String);
                console.log(window.btoa(imagen.base64String));
                $('#img-preview').empty();
                $('#img-preview').append('<img width="250px" src="data:'+imagen.type+';base64,'+imagen.base64String+'">');
            };
        })(f);
        // Read in the image file as a data URL.
        reader.readAsBinaryString(f);
    }
});