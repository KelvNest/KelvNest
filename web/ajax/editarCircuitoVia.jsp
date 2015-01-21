<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>

<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idPkInicialCircuito" value="${param.idPkInicialCircuito}"/>

<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>   
        <c:set var="circuitoVia" value="${gl.buscarCircuitoViaPorPK(idLinea, idPkInicialCircuito)}"/>
        <div class="contenedorFormulario">
            <!--<legend>Editar Circuito de Via de Progresiva ${idPkInicialCircuito}</legend>-->
            <h2>Editar Circuito de Via de Progresiva ${idPkInicialCircuito}</h2>
            <form>        
                <input type="hidden" id="hdd_prog_ini_cir_ed" value="${idPkInicialCircuito}" >
                <input type="hidden" id="hdd_id_linea_ed" value="${idLinea}" >
                <label class="tituloFormulario">Punto Kilometrico Final</label>
                <input type="text" class="campoFormulario" id="txt_prog_fin_cir_ed" value="${circuitoVia.pkFinalCircuito}">
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" id="btn_edi" value="Editar" onclick="editarCirVia()">
                </div>

                <div class="contenedorBoton">
                    <input type="button" class="botonContinuar" value="Cancelar" onclick="cancelarCircuitoVia()">
                </div>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        Circuito de vía no encontrado
    </c:otherwise>

</c:choose>
