$(document).ready(function () {
    $("#btnAgregar").on('click', function (evento) {
        evento.preventDefault();
        alert("intentando");
        agregarLinea($("#nombre_linea").val(), $("#pk_inicial").val(), $("#pk_final").val(), $("#trocha").val());
    });
    $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
});

function agregarLinea(nombre_linea, pk_inicial, pk_final, trocha) {
    $.ajax({
        url: 'AdministrarLinea',
        type: "POST",
        data: {accion: "Agregar", nombre_linea: nombre_linea, pk_inicial: pk_inicial, pk_final: pk_final, trocha: trocha},
        beforeSend: function () {
            $("#msj").html(".:Esperando:.");
        },
        complete: function () {
            $("#msj").html(".:Listo:.");
        },
        success: function (data) {
            $("#msj").fadeOut("slow");
            $('#data').html(data);
            $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
        }
    });
}
function eliminarL(id) {
    if (id !== '') {        
        $.ajax({
            url: 'AdministrarLinea',
            type: "POST",
            data: {accion: 'Eliminar',nombre_linea: id},
            beforeSend: function () {
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#bgVentanaModal").fadeOut();
            },
            success: function (data) {
                $("#msj").fadeOut("slow");
                $('#data').html(data);
                $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
            }
        });
    }
}
function editarL(id_linea,nombre_linea,pk_inicial, pk_final,trocha) {
          
        $.ajax({
            url: 'AdministrarLinea',
            type: "POST",
            data: {accion: 'Editar',id_linea:id_linea,nombre_linea: nombre_linea,pk_inicial:pk_inicial, pk_final:pk_final,trocha:trocha},
            beforeSend: function () {
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#bgVentanaModal").fadeOut();
            },
            success: function (data) {
                $("#msj").fadeOut("slow");
                $('#data').html(data);
                $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
                                
            }
        });
    
}
function ajaxLinea(id, url) {
    //var idSegmento= $('#idsegmento').val();
    var msjEspera = "...:: Consultando Linea::..";
    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
            data: {id: id},
            beforeSend: function () {
                $("#msjajax").html(msjEspera);
                $("#msjajax").slideUp(500);
            },
            complete: function () {
                $("#msjajax").slideDown(2000);
            },
            success: function (data) {

                $('#datos').html(data);

            }
        });


    }
}
function eliminarLinea(id) {
    ajaxLinea(id, "ajax/eliminarLinea.jsp");
}

function editarLinea(id) {
    ajaxLinea(id, "ajax/editarLinea.jsp");

}
function cancelarLinea() {
    $("#bgVentanaModal").fadeOut();
    $('#datos').html("");

}

