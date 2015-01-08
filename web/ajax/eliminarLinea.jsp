<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="lin" value="${gl.buscarLinea(id)}"/>
  <fieldset class="dialogoModal">
             <legend class="leg_edit">Eliminar Linea ${lin.nombreLinea}</legend>
            <form method="get" action="AdministrarLinea" name="formulario">
                <input type="hidden" name="nombre_linea" value="${lin.nombreLinea}" >
                <p>¿Está seguro que quieres eliminar La Linea ${lin.nombreLinea}?,
                    pulse Eliminar para continuar, pulse Cancelar para salir.<br/> 
                Al eliminar se eliminaran todos los datos relacionados a esta linea.</p>
                <input type="submit" name="accion" value="Eliminar" >
                <input type="button" value="Cancelar" onclick="cancelarLinea()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Linea no Encontrada
    </c:otherwise>
    
</c:choose>