$(document).ready(function () {

    $("#agregarEstacion").on("click", function () {
        alert("Agregando" + $("#cmb_lineas").val() + " " + $("#id_nombre_estacion").val() + " " + $("#pk_estacion").val());
        agregarEstacion($("#cmb_lineas").val(), $("#id_nombre_estacion").val(), $("#pk_estacion").val());
    });
    $("#cmb_lineas").on("change", function (evento) {
        cargaTablaEstaciones($("#cmb_lineas").val());
    });
    $(".cargando").hide();
    $("#tablaEstaciones").hide();
});

function cargaTablaEstaciones(idLinea) {
    $.ajax({
        url: 'ajax/cargaTablaEstaciones.jsp',
        type: "POST",
        data: {idLinea: idLinea},
        beforeSend: function () {
            $("#tablaEstaciones").show();
            $(".cargando").fadeIn("slow");
//            $(".cargando").css("display", "block");
        },
        complete: function () {
            $(".cargando").fadeOut("slow");
//            $(".cargando").css("display", "none");
        },
        success: function (data) {
            $('#tablaEstaciones').html(data);
        }});
}

function agregarEstacion(idLinea, idEstacion, progEst) {
    if (idLinea !== '') {
        $.ajax({
            url: 'AdministrarEstacion',
            type: "POST",
            data: {accion: 'Agregar', select_linea: idLinea, id_nombre_estacion: idEstacion, pk_estacion: progEst},
            beforeSend: function () {
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {
                $("#msj").html("El otro");
                $("#msj").fadeOut("slow");

                $('#data').html(data);
                cargaTablaEstaciones($("#cmb_lineas").val());
            }
        });
        
    }
}
function eliminar(){
    eliminoEstacion($("#id_linea_el").val(), $("#id_nombre_estacion_el").val());
    cancelarEstacion();
    
    
}
function eliminarEstacion(id, id2) {
    ajaxEstacion(id, id2, "ajax/eliminarEstacion.jsp");
}
function eliminoEstacion(idLinea,idNombreEstacion){
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
        
    
}
function editarEstacion(id, id2) {
    ajaxEstacion(id, id2, "ajax/editarEstacion.jsp");

}
//function editar(id_linea,id_nombre_estacion,pk_estacion){
function editar(){
    //alert("editando");
  
    var id_linea=$("#id_linea_ed").val();
    var id_nombre_estacion=$("#id_nombre_estacion_ed").val();
    var pk_estacion=$("#pk_estacion_ed").val();
    $.ajax({
            url: 'AdministrarEstacion',
            type: "POST",
//            data: {accion: 'Editar', id_linea: ("#id_linea").val(), id_nombre_estacion: $("#id_nombre_estacion").val(), pk_estacion:$("#pk_estacion").val()},
            data: {accion: 'Editar', id_linea: id_linea, id_nombre_estacion: id_nombre_estacion, pk_estacion:pk_estacion},
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
}
function cancelarEstacion() {
    //editar($("#id_linea").val(),$("#id_nombre_estacion").val(),$("#pk_estacion").val());
    $("#bgVentanaModal").fadeOut();
    $('#datos').html("");

}
function ajaxEstacion(id, id2, url) {

    var msjEspera = "...:: Consultando Estacion::..";

    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
            data: {idLinea: id, idNombreEstacion: id2},
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
