$(document).ready(function () {

    var base64 = $("#base64").text();
    var tipo = $("#type").text();



    $("#base64").css("display", "none");
    $("#type").css("display", "none");
    $("#id_producto").css("display", "none");



    $("#imagenPro").append('<img class="img-thumbnail rounded float-left" width="100px" src="data:' + tipo + ';base64,' + base64 + '">');

    $('#btn-info').on('click',function () {


        window.location.href=("/productos/findAltasById/"+ parseInt($("#id_producto").text()));

    });


    $('#btn-actualizar').on('click', function () {
       altaInventarioDto = {};
        altaInventarioDto.cantidad = $("#piezas").val();
        if (altaInventarioDto.cantidad == null || altaInventarioDto.cantidad == "" || altaInventarioDto.cantidad <= 0){
            alert("Ingrese una cantidad valida");
        }else {
            altaInventarioDto.producto = {}
                altaInventarioDto.producto.id =  parseInt($("#id_producto").text());

            console.log(altaInventarioDto);
            $.ajax({
                'type': 'POST',
                'url': '/productos/realizarAlta',
                'contentType': 'application/json',
                'data': JSON.stringify(altaInventarioDto),
                'dataType': 'json',
                'success': function (id) {
                    console.log("Alta guardada ", id);
                    $('#alert').show(500);
                    setTimeout(function () {
                        alert('Stock actualizado')
                        $('#alert').hide(500);
                        window.location.href = "/productos/page/productos";
                    }, 2000)
                },
                'error': function (err) {
                    console.log(err);
                    alert(err)
                }
            });

        }


    });


});