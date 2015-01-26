$(document).ready(function () {
    $("#btn_agr_lin").on('click', function (evento) {
        evento.preventDefault();
        
        agregarLinea();
    });
    $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
    $("#msj").hide();

//    $("#tablaLineas").hide();
});

//function agregarLinea(nombre_linea, pk_inicial, pk_final, trocha) {
function agregarLinea() {
    var nombre_linea = $("#txt_nom_lin").val();
    var pk_inicial = $("#num_prog_ini").val();
    var pk_final = $("#num_prog_fin").val();
    var trocha = $("#num_tro").val();
    if ((nombre_linea !== "") && (pk_inicial !== "") && (pk_final !== "") && (trocha !== "")) {
        $.ajax({
            url: 'AdministrarLinea',
            type: "POST",
            data: {accion: "Agregar", nombre_linea: nombre_linea, pk_inicial: pk_inicial, pk_final: pk_final, trocha: trocha},
            beforeSend: function () {
                $("#msj").html("<p><image class='cargando' src='img/ajax-loader.gif'/></p>");
                $("#msj").fadeIn("slow");
            },
            complete: function () {

            },
            success: function (data) {
                $("#msj").html("");
                $("#msj").html(data);
                $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
            }
        });
    } else {
        alert("Faltan datos para agregar la línea");
    }
}
function eliminarL(id) {
    if (id !== '') {
        $.ajax({
            url: 'AdministrarLinea',
            type: "POST",
            data: {accion: 'Eliminar', nombre_linea: id},
            beforeSend: function () {
                $("#msj").html("<p><image class='cargando' src='img/ajax-loader.gif'/></p>");
                $("#msj").show();
            },
            complete: function () {
                
            },
            success: function (data) {
                $("#msj").html("");
                $("#msj").html(data);
                $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
                cancelarLinea();
            }
        });
    }
}
function editarL() {
    var id_linea = $("#hdd_id_linea_ed").val();
    var nombre_linea = $("#txt_nom_lin_ed").val();
    var pk_inicial = $("#num_prog_ini_ed").val();
    var pk_final = $("#num_prog_fin_ed").val();
    var trocha = $("#num_tro_ed").val();

    if ((id_linea !== "") && (nombre_linea !== "") && (pk_inicial !== "") && (pk_final !== "") && (trocha !== "")) {
        $.ajax({
            url: 'AdministrarLinea',
            type: "POST",
            data: {accion: 'Editar', id_linea: id_linea, nombre_linea: nombre_linea, pk_inicial: pk_inicial, pk_final: pk_final, trocha: trocha},
            beforeSend: function () {
                $("#msj").html("<p><image class='cargando' src='img/ajax-loader.gif'/></p>");
                $("#msj").fadeIn("slow");
            },
            complete: function () {

            },
            success: function (data) {
                $("#msj").html("");
                $("#msj").html(data);
                $('#tablaLineas').load('ajax/cargaTablaLineas.jsp');
                cancelarLinea();

            }
        });
    } else {
        alert("Faltan parametros para editar la linea");
    }

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

