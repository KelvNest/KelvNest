$(document).ready(function () {

    cargaTablaMatRod();
    $("#msj").hide();
    $("#data").hide();
    $("#masjajax").hide();
    $("#agregar").on("click", function () {
        agregarMaterialRodante($("#usuario").val(), $("#cmb_lineas").val(), $("#prog_inicio").val(), $("#prog_final").val(), $("#vel_max_ascendente").val(), $("#vel_max_descendente").val());
    });
    $("#accion_el").on("click", function () {
        alert("tratando de eliminar");
        eliminarMR();
    });
});
function agregarMaterialRodante() {
    var nombre = $("#nombre").val();
    var tipo = $("#tipo").val();
    var sub_tipo = $("#sub_tipo").val();
    var numero_vagones = $("#numero_vagones").val();
    var capacidad_pasajeros = $("#capacidad_pasajeros").val();
    var kilometraje = $("#kilometraje").val();
    var largo = $("#largo").val();
    var ancho = $("#ancho").val();
    var alto = $("#alto").val();
    var velocidad_diseño = $("#velocidad_diseño").val();
    var velocidad_operacion = $("#velocidad_operacion").val();
    var masa = $("#masa").val();
    var aceleracion_maxima = $("#aceleracion_maxima").val();
    var desaceleracion_maxima = $("#desaceleracion_maxima").val();
    if ((nombre !== "") && (tipo !== "") && (sub_tipo !== "") && (numero_vagones !== "") && (capacidad_pasajeros !== "")
            && (kilometraje !== "") && (largo !== "") && (ancho !== "") && (alto !== "") && (velocidad_diseño !== "")
            && (velocidad_operacion !== "") && (masa !== "") && (aceleracion_maxima !== "") && (desaceleracion_maxima !== "")) {
        $.ajax({
            url: 'AdministrarMaterialRodante',
            type: "POST",
            data: {accion: "Agregar", nombre: nombre, tipo: tipo, sub_tipo: sub_tipo, numero_vagones: numero_vagones,
                capacidad_pasajeros: capacidad_pasajeros, kilometraje: kilometraje, largo: largo, ancho: ancho,
                alto: alto, velocidad_diseño: velocidad_diseño, velocidad_operacion: velocidad_operacion, masa: masa,
                aceleracion_maxima: aceleracion_maxima, desaceleracion_maxima: desaceleracion_maxima},
            beforeSend: function () {
//            $("#msj").html(".:Esperando:.");
                $("#msj").show();
            },
            complete: function () {
//            $("#msj").html(".:Listo:.");
            },
            success: function (data) {
                $("#msj").fadeOut("slow");
                $('#data').html(data);
                $('#data').show();
                cargaTablaMatRod();
            }
        });
    }else{
        alert("Falta un parametro");
    }
}
//function editarRes(id_restriccion,id_linea,vel_max_ascendente,vel_max_descendente,usuario,prog_inicio,prog_final){
function editarMR() {
    var nombre = $("#nombre_material_rodante_ed").val();
    var tipo = $("#tipo_ed").val();
    var sub_tipo = $("#sub_tipo_ed").val();
    var numero_vagones = $("#numero_vagones_ed").val();
    var capacidad_pasajeros = $("#capacidad_pasajeros_ed").val();
    var kilometraje = $("#kilometraje_ed").val();
    var largo = $("#largo_ed").val();
    var ancho = $("#ancho_ed").val();
    var alto = $("#alto_ed").val();
    var velocidad_diseño = $("#velocidad_diseño_ed").val();
    var velocidad_operacion = $("#velocidad_operacion_ed").val();
    var masa = $("#masa_ed").val();
    var aceleracion_maxima = $("#aceleracion_maxima_ed").val();
    var desaceleracion_maxima = $("#desaceleracion_maxima_ed").val();
    var id_material_rodante = $("#id_material_Rodante_ed").val();
    $.ajax({
        url: 'AdministrarMaterialRodante',
        type: "POST",
        data: {accion: "Editar", nombre: nombre, tipo: tipo, sub_tipo: sub_tipo, numero_vagones: numero_vagones,
            capacidad_pasajeros: capacidad_pasajeros, kilometraje: kilometraje, largo: largo, ancho: ancho,
            alto: alto, velocidad_diseño: velocidad_diseño, velocidad_operacion: velocidad_operacion, masa: masa,
            aceleracion_maxima: aceleracion_maxima, desaceleracion_maxima: desaceleracion_maxima,
            id_material_rodante: id_material_rodante},
        beforeSend: function () {
//            $("#msj").html(".:Esperando:.");
            $("#msj").show();
        },
        complete: function () {
//            $("#msj").html(".:Listo:.");
        },
        success: function (data) {
            $("#msj").fadeOut("slow");
            $('#data').html(data);
            $('#data').show();
            cargaTablaMatRod();
            cancelarMaterialRodante();
        }
    });
}
function eliminarMR() {
    var id_material_rodante = $("#id_material_rodante_el").val();

    $.ajax({
        url: 'AdministrarMaterialRodante',
        type: "POST",
        data: {accion: "Eliminar", id_material_rodante: id_material_rodante},
        beforeSend: function () {
            $("#msj").html(".:Esperando:.");
            $("#msj").show();
        },
        complete: function () {
            $("#msj").html(".:Listo:.");
        },
        success: function (data) {
            $("#msj").fadeOut("slow");
            $('#data').html(data);
            $('#data').show();
            cargaTablaMatRod();
            cancelarMaterialRodante();
        }
    });
}
function cargaTablaMatRod() {
    $("#marcoMR").load("ajax/cargaTablaMatRod.jsp");

}
function eliminarMaterialRodante(id) {
    ajaxMaterialRodante(id, "ajax/eliminarMaterialRodante.jsp");
}

function editarMaterialRodante(id) {
    ajaxMaterialRodante(id, "ajax/editarMaterialRodante.jsp");

}
function ajaxMaterialRodante(id, url) {
//    var msjEspera = "...:: Consultando Material Rodante::..";
    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
            data: {id: id},
            beforeSend: function () {
//                $("#msjajax").html(msjEspera);
//                $("#msjajax").slideUp(500);
                $("#msjajax").show();
            },
            complete: function () {
//                $("#msjajax").slideDown(2000);
                $("#msjajax").hide();
            },
            success: function (data) {

                $('#datos').html(data);

            }
        });
    }
}

function cancelarMaterialRodante() {
    $('#datos').html("");
    $("#bgVentanaModal").fadeOut();
}