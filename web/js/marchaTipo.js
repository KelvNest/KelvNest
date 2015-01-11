

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
    var progEstacionInicial = $("#cmb_est_inicio").val();
    var progEstacionFinal = $("#cmb_est_final").val();
    //var sentido=true;
//    $("#marcoRestricciones").load("ajax/cargaRestricciones.jsp", {idLinea: linea, progEstacionInicial: progEstacionInicial, progEstacionFinal: progEstacionFinal, velMax: velocidad, sentido: sentido});
    alert(linea + " " + progEstacionInicial + " " + progEstacionFinal + " " + velocidad);
    $("#marcoRestricciones").load("ajax/cargaRestricciones.jsp", {idLinea: linea, progEstacionInicial: progEstacionInicial, progEstacionFinal: progEstacionFinal, velMax: velocidad});
}

function simular() {

    var idLinea = $("#cmb_lineas").val();
    var vel = $("#velocidad").val();
    var materialRodante = $("#cmb_materiales").val();
    var estInicial = $("#cmb_est_inicio").val();
    var estFinal = $("#cmb_est_final").val();
    var restricciones = [];
    var sentido = true;
    var a = 0;
    $(".incluir").each(function (i, ele) {
        var chk = $(ele).is(':checked');
        var valor = $(ele).val();
//        restricciones[i]=i+ " :"+valor;
        if (chk === true && (valor !== '')) {
//            restricciones[i] = i + " :" + valor;
            restricciones[a++] = i;
        }


    }
    );
//    alert();
//    function estaSent(estInicial, estFinal){
//        if (estInicial < estFinal) {
//            sentido = true;
//        } else {
//            sentido = false;
//        }
//    }
    alert("idLinea: " + idLinea + "  Velocidad: " + vel + "  MatRodan: " + materialRodante + "  estaInicio: " + estInicial + "  estFinal: " + estFinal + "  Restricciones: " + restricciones + " Sentido: " + sentido);
    $("#resultadoMarchaTipo").load("/MarchaTipo", {idLinea: idLinea, vel: vel, materialRodante: materialRodante, estInicial: estInicial, estFinal: estFinal, restricciones: restricciones, sentido: sentido});
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

//function estaSent(estInicial, estFinal){
//        if (estInicial < estFinal) {
//            sentido = true;
//        } else {
//            sentido = false;
//        }
//    }

