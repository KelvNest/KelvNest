$(document).ready(function (){
    $("#cmb_lineas").on("change", function (evento) {
        cargaTablaSegmentos($("#cmb_lineas").val());
    });
    $("#msj").hide();
    $("#data").hide();
    
});
function cargaTablaSegmentos(idLinea) {
    alert(idLinea);
    $.ajax({
        url: 'ajax/cargaTablaSegmentos.jsp',
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
            $('#tablaSegmentos').html(data);
        }});
}

function agregar(){
    var cmb_linea= $("#cmb_lineas").val();
    var tipo_segmento=$("#tipo_segmento").val();
    var pk_inicio=$("#pk_inicio").val();
    var pk_final=$("#pk_final").val();
    var radio=$("#radio").val();
    var gradiente=$("#gradiente").val();
    var tunel=$("#tunel").val();
    var velocidad_max_ascendente=$("#velocidad_max_ascendente").val();
    var velocidad_max_descendente=$("#velocidad_max_descendente").val();
    
    $.ajax({
        url: 'AdministrarSegmento',
        type: "POST",
        data: {accion: "Agregar", cmb_linea: cmb_linea, tipo_segmento: tipo_segmento,pk_inicio:pk_inicio, pk_final: pk_final, radio: radio,gradiente:gradiente, tunel:tunel, velocidad_max_ascendente:velocidad_max_ascendente, velocidad_max_descendente:velocidad_max_descendente},
        beforeSend: function () {
            $("#msj").show();
            $("#msj").html(".:Esperando:.");
        },
        complete: function () {
            $("#msj").html(".:Listo:.");
        },
        success: function (data) {
            $("#msj").fadeOut("slow");
            $('#data').html(data);
//            $('#data').fadeIn("slow");
            $('#data').show();
            $('#tablaSegmentos').load('ajax/cargaTablaSegmentos.jsp',{idLinea:cmb_linea});
        }
    });
}

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

function eliminarS(progresiva,linea){
     if ((progresiva !== '')&&(linea!== '')) {        
        $.ajax({
            url: 'AdministrarSegmento',
            type: "POST",
            data: {accion: 'Eliminar',progresiva: progresiva,linea:linea},
            beforeSend: function () {
                $("#msj").show();
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#bgVentanaModal").fadeOut();
            },
            success: function (data) {
//                $("#msj").fadeOut("slow");
                $("#msj").hide();
                $('#data').html(data);
                $('#data').show();
                cargaTablaSegmentos($("#cmb_lineas").val());
            }
        });
}
}

function editarSegmento(id, id2) {
    ajaxSegmento(id, id2, "ajax/editarSegmento.jsp");
}
function editarS(){
   
    var id_linea_ed=$("#id_linea_ed").val();
    var tipo_segmento_ed=$("#tipo_segmento_ed").val();
    var pk_inicio_ed=$("#pk_inicio_ed").val();
    var pk_final_ed=$("#pk_final_ed").val();
    var radio_ed=$("#radio_ed").val();
    var gradiente_ed=$("#gradiente_ed").val();
    var tunel_ed=$("#tunel_ed").val();
    var velocidad_max_ascendente_ed=$("#velocidad_max_ascendente_ed").val();
    var velocidad_max_descendente_ed=$("#velocidad_max_descendente_ed").val();
    $.ajax({
            url: 'AdministrarSegmento',
            type: "POST",
//            data: {accion: 'Editar', id_linea: ("#id_linea").val(), id_nombre_estacion: $("#id_nombre_estacion").val(), pk_estacion:$("#pk_estacion").val()},
            data: {accion: 'Editar', id_linea_ed:id_linea_ed, tipo_segmento_ed:tipo_segmento_ed,pk_inicio_ed:pk_inicio_ed,pk_final_ed:pk_final_ed, radio_ed:radio_ed,gradiente_ed:gradiente_ed,tunel_ed:tunel_ed,velocidad_max_ascendente_ed:velocidad_max_ascendente_ed,velocidad_max_descendente_ed:velocidad_max_descendente_ed},
            beforeSend: function () {
                $("#msj").show();
                $("#msj").html(".:Esperando:.");
            },
            complete: function () {
                $("#msj").html(".:Listo:.");
            },
            success: function (data) {                 
                $("#msj").fadeOut("slow");    
                $('#data').html(data);
                $('#data').show();
                cargaTablaSegmentos($("#cmb_lineas").val());
                cancelarSegmento();
                
            }
        });
          
}
function cancelarSegmento() {
    $("#bgVentanaModal").fadeOut()();
    $('#datos').html("");

}

