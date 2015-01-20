$(document).ready(function () {

   

});



function cancelarMaterialRodante() {
    $("#bgVentanaModal").fadeOut()();
    $('#datos').html("");

}

function eliminarMaterialRodante(id) {
    ajaxMaterialRodante(id, "ajax/eliminarMaterialRodante.jsp");
}

function editarMaterialRodante(id) {
    ajaxMaterialRodante(id, "ajax/editarMaterialRodante.jsp");

}
function ajaxMaterialRodante(id, url) {
    var msjEspera = "...:: Consultando Material Rodante::..";
    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
            data: {id: id},
            beforeSend: function () {
                $("#msjajax").html(msjEspera);
                $("#msjajax").slideUp(500);
            },
            complete: function () {
                $("#msjajax").slideDown(2000);
            },
            success: function (data) {

                $('#datos').html(data);

            }
        });


    }
}



function ajaxCircuitoVia(id, id2, url) {
    //var idCircuitoVia= $('#idsegmento').val();
    //alert(id+id2);
    var msjEspera = "...:: Consultando Circuito de Via::..";
//    $("#bgVentanaModal").fadeIn();
//    $("#msjajax").html(msjEspera);
//                $("#msjajax").slideUp(500);
//    $("#datos").load(url, {idLinea:id, idPkInicial: id2});
//    $("#msjajax").slideDown(500);

    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "GET",
            data: {idLinea: id, idPkInicialCircuito: id2},
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
function eliminarCircuitoVia(id, id2) {
    ajaxCircuitoVia(id, id2, "ajax/eliminarCircuitoVia.jsp");
}

function editarCircuitoVia(id, id2) {
    ajaxCircuitoVia(id, id2, "ajax/editarCircuitoVia.jsp");

}
function cancelarCircuitoVia() {
    $("#bgVentanaModal").fadeOut()();
    $('#datos').html("");

}

$("document").ready(function () {
    $("#editarCir").click(function (evento) {
        evento.preventDefault();
        editarCircuitoVia(id, id2);
    });
});
