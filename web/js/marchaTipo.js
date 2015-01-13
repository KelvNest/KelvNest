

$(document).ready(function () {
//    $("#cargando").hide();
    $(".cargando").hide();

    $("#cmb_lineas").on("change", function (evento) {
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

    $("#resultadoMarchaTipo").css('display', 'none');

    $("#estaciones.cargando").css("display", "none");

});

function cargaEstaciones(idLinea) {
    $.ajax({
        url: 'ajax/cargaEstaciones.jsp',
        type: "POST",
        data: {idLinea: idLinea},
        beforeSend: function () {
            $(".cargando").fadeIn("slow");
            $(".cargando").css("display", "block");
        },
        complete: function () {
            $(".cargando").fadeOut("slow");
            $(".cargando").css("display", "none");
        },
        success: function (data) {
            $('#estaciones').html(data);
        }});
}


function cargaRestricciones(linea, velocidad) {
    var progEstacionInicial = $("#cmb_est_inicio").val();
    var progEstacionFinal = $("#cmb_est_final").val();
    $("#marcoRestricciones").load("ajax/cargaRestricciones.jsp", {idLinea: linea, progEstacionInicial: progEstacionInicial, progEstacionFinal: progEstacionFinal, velMax: velocidad});
}

function simular() {
    $("#resultadoMarchaTipo").css('display', 'block');
    var idLinea = $("#cmb_lineas").val();
    var vel = $("#velocidad").val();
    var materialRodante = $("#cmb_materiales").val();
    var estInicial = $("#cmb_est_inicio").val();
    var estFinal = $("#cmb_est_final").val();
    var restricciones = [];
//    var sentido = true;
    var a = 0;
    $(".incluir").each(function (i, ele) {
        var chk = $(ele).is(':checked');
        var valor = $(ele).val();
        if (chk === true && (valor !== '')) {
            restricciones[a++] = i;
        }
    }
    );

    $.ajax({
        url: 'MarchaTipo',
        type: "POST",
        data: {idLinea: idLinea, vel: vel, materialRodante: materialRodante, progInicial: estInicial, progFinal: estFinal, restricciones: restricciones},
        beforeSend: function () {
            $("#cargando").show();
        },
        complete: function () {
            $("#cargando").hide();
        },
        success: function (data) {
            $('#resultadoMarchaTipo').html(data);

        }
    });
}



