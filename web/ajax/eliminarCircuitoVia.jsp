<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idPkInicialCircuito" value="${param.idPkInicialCircuito}"/>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="circuitoVia" value="${gl.buscarCircuitoViaPorPK(idLinea, idPkInicialCircuito)}"/>
  <fieldset class="dialogoModal">
             <legend class="leg_edit">Eliminar Circuito de Via con Progresiva Inicial ${idPkInicialCircuito}</legend>
            <form method="get" action="AdministrarCircuitoVia" name="formulario">
                <input type="hidden" name="id_pk_inicial_circuito" value="${idPkInicialCircuito}" >
                <input type="hidden" name="id_linea" value="${idLinea}" >
                <p>¿Está seguro que quieres eliminar el circuito de via con progresiva inicial ${idPkInicialCircuito}?,
                pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <input type="submit" name="accion" value="Eliminar" >
                <input type="button" value="Cancelar" onclick="cancelarCircuitoVia()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Circuito de Via No Encontrado
    </c:otherwise>
    
</c:choose>