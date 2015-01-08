<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idPkInicial" value="${param.idPkInicial}"/>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="segmento" value="${gl.buscarSegmentoPorPK(idLinea, idPkInicial)}"/>
  <fieldset class="dialogoModal">
             <legend class="leg_edit">Eliminar Segmento de Progresiva ${idPkInicial}</legend>
            <form method="get" action="AdministrarSegmento" name="formulario">
                <input type="hidden" name="progresiva" value="${idPkInicial}" >
                <input type="hidden" name="linea" value="${idLinea}" >
                <p>¿Está seguro que quieres eliminar el segmento de progresiva ${idPkInicial}?,
                pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <input type="submit" name="accion" value="Eliminar" >
                <input type="button" value="Cancelar" onclick="cancelarSegmento()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Segmento No Encontrado
    </c:otherwise>
    
</c:choose>
