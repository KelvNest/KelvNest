<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="mr" value="${gl.buscarMaterialRodante(id)}"/>
  <fieldset class="dialogoModal">
             <legend class="leg_edit">Eliminar Material Rodante ${mr.nombreMaterialRodante}</legend>
            <form method="get" action="AdministrarMaterialRodante" name="formulario">
                <input type="hidden" name="id_material_rodante" value="${id}" >
                <p>¿Está seguro que quieres eliminar el Material Rodante ${mr.nombreMaterialRodante}?,
                pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <input type="submit" name="accion" value="Eliminar" >
                <input type="button" value="Cancelar" onclick="cancelarMaterialRodante()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Material Rodante no Encontrado
    </c:otherwise>
    
</c:choose>