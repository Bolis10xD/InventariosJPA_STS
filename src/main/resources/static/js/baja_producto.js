$(document).ready(function () {

    var base64 = $("#base64").text();
    var tipo = $("#type").text();



    $("#base64").css("display", "none");
    $("#type").css("display", "none");
    $("#id_producto").css("display", "none");

    $("#imagenPro").append('<img class="img-thumbnail rounded float-left" width="100px" src="data:' + tipo + ';base64,' + base64 + '">');

    $('#btn-info').on('click',function () {


        window.location.href=("/productos/findBajasById/"+ parseInt($("#id_producto").text()));

    });

    $('#btn-actualizar').on('click', function () {
        bajaInventarioDto = {};
        bajaInventarioDto.cantidad = $("#piezas").val();
        if (bajaInventarioDto.cantidad == null || bajaInventarioDto.cantidad == "" || bajaInventarioDto.cantidad <= 0 ){
            alert("Ingrese una cantidad valida");
        }else if (bajaInventarioDto.cantidad > parseFloat($("#piezasStock").text())){
            alert("La cantidad de piezas ingresadas es mayor al numero actual del stock del producto");
        } else {
            bajaInventarioDto.producto = {}
            bajaInventarioDto.producto.id =  parseInt($("#id_producto").text());
            console.log(bajaInventarioDto);
            $.ajax({
                'type': 'POST',
                'url': '/productos/realizarBaja',
                'contentType': 'application/json',
                'data': JSON.stringify(bajaInventarioDto),
                'dataType': 'json',
                'success': function (id) {
                    console.log("Baja guardada ", id);
                    $('#alert').show(500);
                    setTimeout(function () {
                        alert('Stock actualizado')
                        $('#alert').hide(500);
                        window.location.href = "/productos/page/productos";
                    }, 2000)
                },
                'error': function (err) {
                    console.log(err);
                    alert(err);
                }
            });

        }


    });


});