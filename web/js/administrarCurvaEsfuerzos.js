$(document).ready(function () {

    $("#agregarEstacion").on("click", function () {
        agregarCurva($("#cmb_materiales").val(), $("#txt_vel_mat_rod").val(), $("#txt_esf_tra").val(),$("#txt_esf_fre").val());
    });
    $("#cmb_materiales").on("change", function (evento) {
//        cargaTablaEstaciones($("#cmb_lineas").val());
        cargaTablaCurvas($("#cmb_materiales").val());
    });
    $(".cargando").hide();
    $("#tablaCurvas").hide();
    $("#msj").hide();
});

function cargaTablaCurva(idMatRod) {
    if (idMatRod !== "") {
        $.ajax({
            url: 'ajax/cargaTablaCurvas.jsp',
            type: "POST",
            data: {idMatRod: idMatRod},
            beforeSend: function () {
                $("#tablaCurvas").show();
//                $(".cargando").fadeIn("slow");
            },
            complete: function () {
//                $(".cargando").fadeOut("slow");
            },
            success: function (data) {
                $('#tablaCurvas').html(data);
            }});
    }

}

function agregarCurva(idMatRod, vel, esfTrac,esfFre) {
    if ((idMatRod !== '') && (vel !== "") && (esfTrac !== "")) {
        $.ajax({
            url: 'AdministrarEstacion',
            type: "POST",
            data: {accion: 'Agregar', idMatRod: idMatRod, vel: vel, esfTrac: esfTrac,esfFre:esfFre},
            beforeSend: function () {
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {
                $("#msj").html(data);
                cargaTablaEstaciones($("#cmb_lineas").val());
            }
        });
    } else {
        alert("Datos invalidos");
    }
}
function eliminar() {
    eliminoEstacion($("#id_linea_el").val(), $("#id_nombre_estacion_el").val());
    cancelarEstacion();
}
function eliminarEstacion(id, id2) {
    ajaxEstacion(id, id2, "ajax/eliminarEstacion.jsp");
}
function eliminoEstacion(idLinea, idNombreEstacion) {
    if ((idLinea !== "") && (idNombreEstacion !== "")) {
        $.ajax({
            url: 'AdministrarEstacion',
            type: "POST",
            data: {accion: 'Eliminar', id_linea: idLinea, id_nombre_estacion: idNombreEstacion},
            beforeSend: function () {
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {
                $("#msj").fadeOut("slow");
                $('#data').html(data);
                cargaTablaEstaciones($("#cmb_lineas").val());
            }
        });
    } else {
        alert("Datos invalidos");
    }

}
function editarEstacion(id, id2) {
    ajaxEstacion(id, id2, "ajax/editarEstacion.jsp");
}

function editar() {
    var id_linea = $("#id_linea_ed").val();
    var id_nombre_estacion = $("#id_nombre_estacion_ed").val();
    var pk_estacion = $("#pk_estacion_ed").val();
    if ((id_linea !== "") && (id_nombre_estacion !== "") && (pk_estacion !== "")) {
        $.ajax({
            url: 'AdministrarEstacion',
            type: "POST",
            data: {accion: 'Editar', id_linea: id_linea, id_nombre_estacion: id_nombre_estacion, pk_estacion: pk_estacion},
            beforeSend: function () {
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {
                $("#msj").fadeOut("slow");
                cancelarEstacion();
                $('#data').html(data);
            }
        });
        cargaTablaEstaciones($("#cmb_lineas").val());
    } else {
        alert("Datos invalidos");
    }
}

function cancelarEstacion() {
    $("#bgVentanaModal").fadeOut();
    $('#datos').html("");
}

function ajaxCurvas(id, id2, url) {

    var msjEspera = "...:: Consultando::..";

    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
            data: {idMatRod: id, vel: id2},
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
