$(document).ready(function(){
    
    cargaTablaMatRod();
    $("#msj").hide();
    $("#data").hide();
    $("#masjajax").hide();
    $("#agregar").on("click", function(){
        agregarMaterialRodante($("#usuario").val(),$("#cmb_lineas").val(),$("#prog_inicio").val(),$("#prog_final").val(),$("#vel_max_ascendente").val(),$("#vel_max_descendente").val());
    });
});
function agregarMaterialRodante(){
    var nombre= $("#nombre").val();
    var tipo= $("#tipo").val();
    var sub_tipo= $("#sub_tipo").val();
    var numero_vagones= $("#numero_vagones").val();
    var capacidad_pasajeros= $("#capacidad_pasajeros").val();
    var kilometraje= $("#kilometraje").val();
    var largo= $("#largo").val();
    var ancho= $("#ancho").val();
    var alto= $("#alto").val();
    var velocidad_diseño= $("#velocidad_diseño").val();
    var velocidad_operacion= $("#velocidad_operacion").val();
    var masa= $("#masa").val();
    var aceleracion_maxima= $("#aceleracion_maxima").val();
    var desaceleracion_maxima= $("#desaceleracion_maxima").val();
   if((nombre!=="")&&(tipo!=="")&&(sub_tipo!=="")&&(numero_vagones!=="")&&(capacidad_pasajeros!=="")
           &&(kilometraje!=="")&&(largo!=="")&&(ancho!=="")&&(alto!=="")&&(velocidad_diseño!=="")
           &&(velocidad_operacion!=="")&&( masa!=="")&&(aceleracion_maxima!=="")&&(desaceleracion_maxima!=="")){
    $.ajax({
        url: 'AdministrarMaterialRodante',
        type: "POST",
        data: {accion: "Agregar",nombre:nombre, tipo:tipo, sub_tipo: sub_tipo,numero_vagones:numero_vagones,
            capacidad_pasajeros: capacidad_pasajeros, kilometraje: kilometraje,largo:largo,ancho:ancho,
            alto:alto,velocidad_diseño:velocidad_diseño,velocidad_operacion:velocidad_operacion,masa:masa,
            aceleracion_maxima:aceleracion_maxima,desaceleracion_maxima:desaceleracion_maxima},
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
   }
}
//function editarRes(id_restriccion,id_linea,vel_max_ascendente,vel_max_descendente,usuario,prog_inicio,prog_final){
function editarRes(){
    var id_restriccion=$("#id_restriccion_ed").val();
    var id_linea=$("#id_linea_ed").val();
    var vel_max_ascendente=$("#vel_max_ascendente_ed").val();
    var vel_max_descendente=$("#vel_max_descendente_ed").val();
    var usuario=$("#usuario_ed").val();
    var prog_inicio=$("#prog_inicio_ed").val();
    var prog_final=$("#prog_final_ed").val();
    $.ajax({
        url: 'AdministrarRestriccion',
        type: "POST",
        data: {accion: "Editar",usuario:usuario,id_restriccion:id_restriccion, id_linea: id_linea, prog_inicio: prog_inicio,prog_final:prog_final, vel_max_ascendente: vel_max_ascendente, vel_max_descendente: vel_max_descendente},
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
            cargaRestricciones($("#cmb_lineas").val());
            cancelarRestriccion();
        }
    });
}
function eliminarRes(){
    var id_restriccion=$("#id_restriccion_el").val();
    var id_linea=$("#id_linea_el").val();
    
    $.ajax({
        url: 'AdministrarRestriccion',
        type: "POST",
        data: {accion: "Eliminar",id_restriccion:id_restriccion, id_linea: id_linea},
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
            cargaRestricciones($("#cmb_lineas").val());
            cancelarRestriccion();
        }
    });
}
function cargaTablaMatRod() {    
    $("#marcoMR").load("ajax/cargaTablaMatRod.jsp");
    
}
function ajaxRestriccion(id, id2, url) {
    //var idRestriccion= $('#idsegmento').val();
    //alert(id+id2);
   // var msjEspera = "...:: Consultando Restriccion::..";
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
//                $("#msjajax").html(msjEspera);
//                $("#msjajax").slideUp(500);
                    $("#msjajax").fadeIn("slow");
            },
            complete: function () {
//                $("#msjajax").slideDown(500);
                 $("#msjajax").fadeOut("slow");
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
    $('#datos').html("");
    $("#bgVentanaModal").fadeOut();
}