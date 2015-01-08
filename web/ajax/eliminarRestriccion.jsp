<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idRestriccion" value="${param.idRestriccion}"/>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="restriccion" value="${gl.buscarRestriccionPorPK(idLinea, idRestriccion)}"/>
  <fieldset class="dialogoModal">
             <legend class="leg_edit">Eliminar Restriccion de Progresiva ${idRestriccion}</legend>
            <form method="get" action="AdministrarRestriccion" name="formulario">
                <input type="hidden" name="id_restriccion" value="${idRestriccion}" >
                <input type="hidden" name="id_linea" value="${idLinea}" >
                <p>¿Está seguro que quieres eliminar la restriccion de progresiva ${restriccion.progInicio}?,
                pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <input type="submit" name="accion" value="Eliminar" >
                <input type="button" value="Cancelar" onclick="cancelarRestriccion()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Restriccion No Encontrada
    </c:otherwise>
    
</c:choose>
