$(document).ready(function () {

    $("#btn_agr_curv").on("click", function () {
        agregarCurva($("#cmb_materiales").val(), $("#txt_vel_mat_rod").val(), $("#txt_esf_tra").val(), $("#txt_esf_fre").val());
    });
    $("#cmb_materiales").on("change", function (evento) {
        cargaTablaCurvas($("#cmb_materiales").val());
    });
    $(".cargando").hide();
    $("#tablaCurvas").hide();
    $("#msj").hide();
});

function cargaTablaCurvas(idMatRod) {
    if (idMatRod !== "") {
        $.ajax({
            url: 'ajax/cargaTablaCurvas.jsp',
            type: "POST",
            data: {idMatRod: idMatRod},
            beforeSend: function () {
                $("#tablaCurvas").show();
            },
            complete: function () {
            },
            success: function (data) {
                $('#tablaCurvas').html(data);
            }});
    }
}

function agregarCurva(idMatRod, vel, esfTrac, esfFre) {
    if ((idMatRod !== '') && (vel !== "") && (esfTrac !== "")) {
        alert("agregando");
        $.ajax({
            url: 'AdministrarCurvas',
            type: "POST",
            data: {accion: 'Agregar', idMatRod: idMatRod, vel: vel, esfTrac: esfTrac, esfFre: esfFre},
            beforeSend: function () {
                $("#msj").show();
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {
                $("#msj").html("");
                $("#msj").html(data);
                cargaTablaCurvas($("#cmb_materiales").val());
            }
        });
    } else {
        alert("Datos invalidos");
    }
}
function eliminarC() {
    eliminoCurva($("#id_mat_rod_el").val(), $("#id_vel_el").val());
    cancelarCurva();
}
function eliminarCurva(id, id2) {

    ajaxCurvas(id, id2, "ajax/eliminarCurvas.jsp");
}
function eliminoCurva(idMatRod, vel) {
    if ((idMatRod !== "") && (vel !== "")) {
        $.ajax({
            url: 'AdministrarCurvas',
            type: "POST",
            data: {accion: 'Eliminar', idMatRod: idMatRod, vel: vel},
            beforeSend: function () {
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {
                $("#msj").fadeOut("slow");
                $('#data').html(data);
                cargaTablaCurvas($("#cmb_materiales").val());
            }
        });
    } else {
        alert("Datos invalidos");
    }
}
function editarCurva(id, id2) {

    ajaxCurvas(id, id2, "ajax/editarCurvas.jsp");
}

function editarC() {
    var idMatRod = $("#id_mat_rod_ed").val();
    var idVel = $("#id_vel_ed").val();
    var esfTrac = $("#txt_esf_tra_ed").val();
    var esfFren = $("#txt_esf_fre_ed").val();
    if ((idMatRod !== "") && (idVel !== "") && (esfTrac !== "")) {
        $.ajax({
            url: 'AdministrarCurvas',
            type: "POST",
            data: {accion: 'Editar', idMatRod: idMatRod, idVel: idVel, esfTrac: esfTrac, esfFren: esfFren},
            beforeSend: function () {
                $("#msj").show();
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {
//                $("#msj").fadeOut("slow");
                $("#msj").html(data);
                cancelarCurva();
//                $('#data').html(data);
            }
        });
        cargaTablaCurvas($("#cmb_materiales").val());
    } else {
        alert("Datos invalidos");
    }
}

function cancelarCurva() {
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
