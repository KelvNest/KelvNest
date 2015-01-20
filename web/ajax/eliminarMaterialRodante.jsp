<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="mr" value="${gl.buscarMaterialRodante(id)}"/>
       
        <div class="contenedorFormulario">
            <legend><h2>Eliminar Material Rodante ${mr.nombreMaterialRodante}</h2></legend>
            <form>
                <input type="hidden" id="id_material_rodante_el" value="${id}" >
                <p>¿Está seguro que quieres eliminar el Material Rodante ${mr.nombreMaterialRodante}?,
                    pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" id="accion_el" value="Eliminar" onclick="eliminarMR()">
                </div>                
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" value="Cancelar" onclick="cancelarMaterialRodante()">
                </div>

            </form>
        </div>

    </c:when>
    <c:otherwise>
        Material Rodante no Encontrado
    </c:otherwise>

</c:choose>