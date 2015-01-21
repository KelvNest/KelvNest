$(document).ready(function () {
    $("#cmb_lineas").on("change load", function (){
        cargarTablaCV();
    });
   

});

function ajaxCircuitoVia(id, id2, url) {
    var msjEspera = "...:: Consultando Circuito de Via::..";
    if (id !== '') {
        $("#bgVentanaModal").fadeIn();
        $.ajax({
            url: url,
            type: "POST",
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
function cargarTablaCV(){
    var idLinea=$("#cmb_lineas").val();
    $("#marcoCV").load("ajax/cargaTablaCircuitosVia.jsp",{idLinea:idLinea});
}

