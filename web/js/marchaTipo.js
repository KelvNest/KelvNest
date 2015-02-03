$(document).ready(function () {
    $(".cargando").hide();

    $("#cmb_lineas").on("change", function (evento) {
        cargaEstaciones($("#cmb_lineas").val());
    });

    $("#continuar").click(
            function (evento) {
                if (($("#cmb_lineas").val() !== "") && ($("#velocidad").val() > 0) && ($("#cmb_est_final").val() !== "") && ($("#cmb_est_inicio").val() !== "")) {
                    cargaRestricciones($("#cmb_lineas").val(), $("#velocidad").val());
                } else {
                    alert("Uno De Los Datos Ingresados Es Invalido");
                }
            });

    $("#resultadoMarchaTipo").css('display', 'none');
//    $("#car1").hide();
    $(".cargando").hide();
    $(".marcoRestricciones").hide();

});

function cargaEstaciones(idLinea) {
    $.ajax({
        url: 'ajax/cargaEstaciones.jsp',
        type: "POST",
        data: {idLinea: idLinea},
        beforeSend: function () {
            $("#car1").fadeIn("slow");
        },
        complete: function () {
            $("#car1").fadeOut("slow");
        },
        success: function (data) {
            $('#estaciones').html(data);
        }});
}


function cargaRestricciones(linea, velocidad) {
    var progEstacionInicial = $("#cmb_est_inicio").val();
    var progEstacionFinal = $("#cmb_est_final").val();
    if(progEstacionInicial!==progEstacionFinal){
        $.ajax({
                url: 'ajax/cargaRestricciones.jsp',
                type: "POST",
                data: {idLinea: linea, progEstacionInicial: progEstacionInicial, progEstacionFinal: progEstacionFinal, velMax: velocidad},
                beforeSend: function () {
                    $(".marcoRestricciones").show();
                    $("#car2").fadeIn("slow");
                },
                complete: function () {
                    $("#car2").fadeOut("800");
                },
                success: function (data) {
                    $("#contRestricciones").html(data);
                    $("#contMarTip").css('float', 'left');
                    $("#contMarTip").css('display', 'inline-block');
                    $("#contMarTip").css('margin', '0px 20px');
                    $("#contRestricciones").css('display', 'inline-block');
                }
            });
        }else{
            alert("Las estaciones de inicio y fin no pueden ser iguales");
        }
}

function simular() {

    var idLinea = $("#cmb_lineas").val();
    var vel = $("#velocidad").val();
    var materialRodante = $("#cmb_materiales").val();
    var estInicial = $("#cmb_est_inicio").val();
    var estFinal = $("#cmb_est_final").val();
    //var restricciones = [];
    var restricciones = "";
    $(".incluir").each(function (i, ele) {
        var chk = $(ele).is(':checked');
        var valor = $(ele).val();
        if (chk === true && (valor !== '')) {
            restricciones += valor + " ";
        }
    });
    if (estFinal !== estInicial) {
        if ((idLinea !== "") && (vel !== "") && (materialRodante !== "") && (estFinal !== "") && (estInicial !== "")) {
            $.ajax({
                url: 'MarchaTipo',
                type: "POST",
                data: {idLinea: idLinea, vel: vel, materialRodante: materialRodante, progInicial: estInicial, progFinal: estFinal, restricciones: restricciones},
                beforeSend: function () {
                    $("#resultadoMarchaTipo").css('display', 'block');
//                    $("#cargando").show();
                    $("#car3").fadeIn("slow");
                },
                complete: function () {
                    $("#car3").fadeOut("slow");
                },
                success: function (data) {
                    $('#resultadoMarchaTipo').html(data);
                    graficarMarchaTipo();
                }
            });
        } else {
            alert("Uno de los valores no es correcto");
        }
    } else {
        alert("Las estaciones de inicio y fin no pueden ser iguales");
    }
}



