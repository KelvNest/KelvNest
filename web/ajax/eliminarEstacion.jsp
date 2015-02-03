<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idNombreEstacion" value="${param.idNombreEstacion}"/>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="estacion" value="${gl.buscarEstacionPorPK(idLinea, idNombreEstacion)}"/>
        <div class="contenedorFormulario">
             <legend><h2>Eliminar Estacion ${idNombreEstacion}</h2></legend>
            <form>
                <input type="hidden" id="id_nombre_estacion_el" value="${idNombreEstacion}" >
                <input type="hidden" id="id_linea_el" value="${idLinea}" >
                <p>¿Está seguro que quieres eliminar la estacion ${idNombreEstacion}?,
                pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <div class="contenedorBoton">
                    <input type="button" class="botonContinuar" value="Eliminar" onclick="eliminar()">
                </div>                
                <div class="contenedorBoton">
                    <input type="button" class="botonContinuar" value="Cancelar" onclick="cancelarEstacion()">
                </div>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        Estacion No Encontrada
    </c:otherwise>
    
</c:choose>