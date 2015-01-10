

$(document).ready(function () {
    $("#cmb_lineas").change(
            function (evento) {
                //alert($("#cmb_lineas").val());
                cargaEstaciones($("#cmb_lineas").val());
            });

    $("#continuar").click(
            function (evento) {
                if (($("#cmb_lineas").val() !== "") & ($("#velocidad").val() > 0)) {
                    cargaRestricciones($("#cmb_lineas").val(), $("#velocidad").val());
                } else {
                    alert("Seleccione una linea y una velocidad para la simulacion");
                }
            });

});

function cargaEstaciones(linea) {
    $("#estaciones").load("ajax/cargaEstaciones.jsp", {idLinea: linea});
}

function cargaRestricciones(linea, velocidad) {
//    $("#marcoRestricciones").load("ajax/cargaRestricciones.jsp", {idLinea: linea, velMax: velocidad});
var progEstacionInicial= $("#cmb_est_inicio").val();
var progEstacionFinal= $("#cmb_est_final").val();
    $("#marcoRestricciones").load("ajax/cargaRestricciones.jsp", {idLinea: linea, progEstacionInicial: progEstacionInicial, progEstacionFinal: progEstacionFinal, velMax: velocidad});
}

function simular() {
    var idLinea = $("#cmb_lineas").val();
    var vel = $("#velocidad").val();
    var materialRodante = $("#cmb_materiales").val();
    var estInicial = $("#cmb_est_inicio").val();
    var estFinal = $("#cmb_est_final").val();
    var restricciones = [];

    $(".incluir").each(function (i, ele) {
        var chk = $(ele).is(':checked');
        var valor = $(ele).val();
//        restricciones[i]=i+ " :"+valor;
        if (chk === true && (valor !== '')) {
            restricciones[i] = i + " :" + valor;
        }

    });

    //alert("idLinea: "+idLinea+"  Velocidad: "+vel+"  MatRodan: "+materialRodante+"  estaInicio: "+ estInicial+"  estFinal: "+estFinal+"  Restricciones: "+restricciones);
    $("#resultadoMarchaTipo").load("/MarchaTipo", {idLinea: idLinea, vel: vel, materialRodante: materialRodante, estInicial: estInicial, estFinal: estFinal, restricciones: restricciones});
//    $.ajax({
//            url:"/MarchaTipo", 
//            type: "POST",
//            data: {idLinea:idLinea, vel:vel,materialRodante:materialRodante,estInicial:estInicial, estFinal:estFinal, restricciones: restricciones},
//            beforeSend: function () {
//                $("#msjajax").html(msjEspera);
//                $("#msjajax").slideUp(500);
//            },
//            complete: function (){
//                $("#msjajax").slideDown(500);
//            },
//            success: function (data) {
//                
//                $('#datos').html(data);
//                
//            }
//        });
}

