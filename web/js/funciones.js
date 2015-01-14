$(document).ready(function () {

    $("#agregarEstacion").on("click", function () {
        alert("Agregando" + $("#select_linea").val() + " " + $("#id_nombre_estacion").val() + " " + $("#pk_estacion").val());
        agregarEstacion($("#select_linea").val(), $("#id_nombre_estacion").val(), $("#pk_estacion").val());
    });
//    $("#eliminar").on("click", function () {
//        $("#msjajax").html("Cambio");
//        $("#msj").html(".:Listo:.");
//        $("#bgVentanaModal").fadeOut();
//        alert("Eliminando");
//        eliminoEstacion($("#id_linea_el").val(), $("#id_nombre_estacion_el").val());
//    });

});


function ajaxSegmento(id, id2, url) {
   
    var msjEspera = "...:: Consultando Segmento::..";

    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
            data: {idLinea: id, idPkInicial: id2},
            beforeSend: function () {
                $("#msjajax").html(msjEspera);
                $("#msjajax").slideUp(500);
            },
            complete: function () {
                $("#msjajax").slideDown(500);
            },
            success: function (data) {

                $('#datos').html(data);

            }
        });


    }
}
function eliminarSegmento(id, id2) {
    ajaxSegmento(id, id2, "ajax/eliminarSegmento.jsp");
}

function editarSegmento(id, id2) {
    ajaxSegmento(id, id2, "ajax/editarSegmento.jsp");

}
function cancelarSegmento() {
    $("#bgVentanaModal").fadeOut()();
    $('#datos').html("");

}
function cancelarMaterialRodante() {
    $("#bgVentanaModal").fadeOut()();
    $('#datos').html("");

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
function eliminarMaterialRodante(id) {
    ajaxMaterialRodante(id, "ajax/eliminarMaterialRodante.jsp");
}

function editarMaterialRodante(id) {
    ajaxMaterialRodante(id, "ajax/editarMaterialRodante.jsp");

}
function ajaxMaterialRodante(id, url) {
    var msjEspera = "...:: Consultando Material Rodante::..";
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
function ajaxRestriccion(id, id2, url) {
    //var idRestriccion= $('#idsegmento').val();
    //alert(id+id2);
    var msjEspera = "...:: Consultando Restriccion::..";
//    $("#bgVentanaModal").fadeIn();
//    $("#msjajax").html(msjEspera);
//                $("#msjajax").slideUp(500);
//    $("#datos").load(url, {idLinea:id, idPkInicial: id2});
//    $("#msjajax").slideDown(500);

    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
            data: {idLinea: id, idRestriccion: id2},
            beforeSend: function () {
                $("#msjajax").html(msjEspera);
                $("#msjajax").slideUp(500);
            },
            complete: function () {
                $("#msjajax").slideDown(500);
            },
            success: function (data) {

                $('#datos').html(data);

            }
        });


    }
}
function eliminarRestriccion(id, id2) {
    ajaxRestriccion(id, id2, "ajax/eliminarRestriccion.jsp");
}

function editarRestriccion(id, id2) {
    ajaxRestriccion(id, id2, "ajax/editarRestriccion.jsp");

}
function cancelarRestriccion() {
    $("#bgVentanaModal").fadeOut()();
    $('#datos').html("");

}


function ajaxCircuitoVia(id, id2, url) {
    //var idCircuitoVia= $('#idsegmento').val();
    //alert(id+id2);
    var msjEspera = "...:: Consultando Circuito de Via::..";
//    $("#bgVentanaModal").fadeIn();
//    $("#msjajax").html(msjEspera);
//                $("#msjajax").slideUp(500);
//    $("#datos").load(url, {idLinea:id, idPkInicial: id2});
//    $("#msjajax").slideDown(500);

    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "GET",
            data: {idLinea: id, idPkInicialCircuito: id2},
            beforeSend: function () {
                $("#msjajax").html(msjEspera);
                $("#msjajax").slideUp(500);
            },
            complete: function () {
                $("#msjajax").slideDown(500);
            },
            success: function (data) {

                $('#datos').html(data);

            }
        });


    }
}
function eliminarCircuitoVia(id, id2) {
    ajaxCircuitoVia(id, id2, "ajax/eliminarCircuitoVia.jsp");
}

function editarCircuitoVia(id, id2) {
    ajaxCircuitoVia(id, id2, "ajax/editarCircuitoVia.jsp");

}
function cancelarCircuitoVia() {
    $("#bgVentanaModal").fadeOut()();
    $('#datos').html("");

}

$("document").ready(function () {
    $("#editarCir").click(function (evento) {
        evento.preventDefault();
        editarCircuitoVia(id, id2);
    })
});
