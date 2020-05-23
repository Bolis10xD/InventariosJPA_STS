$(document).ready(function () {

    $("#id_producto").css("display", "none");

    var id = parseInt($("#id_producto").text());

    $('#btn-r').on('click',function () {

        window.location.href="/productos/baja/"+id;

    });

    $('#btn-ra').on('click',function () {

        window.location.href="/productos/alta/"+id;

    });
});