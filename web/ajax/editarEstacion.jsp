<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>

<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idNombreEstacion" value="${param.idNombreEstacion}"/>

<%--<c:set var="segmentoPk" value="${id}"/>--%>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
         
        <c:set var="estacion" value="${gl.buscarEstacionPorPK(idLinea, idNombreEstacion)}"/>
        <fieldset class="dialogoModal">
            <legend class="leg_edit">Editar Estacion ${idNombreEstacion}</legend>
            <form method="get" action="AdministrarEstacion" name="formulario">
                
                
<!--                <input type="hidden" name="id_nombre_estacion" value="$ {idNombreEstacion}" >
                <input type="hidden" name="id_linea" value="$ {idLinea}" >-->
                <input type="hidden" id="id_nombre_estacion_ed" value="${idNombreEstacion}" >
                <input type="hidden" id="id_linea_ed" value="${idLinea}" >
                <!--Punto Kilometrico: <input type="text" name="pk_estacion" value="${estacion.pkEstacion}"><br/><br/>-->
                Punto Kilometrico: <input type="text" id="pk_estacion_ed" value="${estacion.pkEstacion}"><br/><br/>
                
                
                                         
                <!--<input type="submit" name="accion" value="Editar">-->
                <!--<input type="button" value="Editar" onclick="editar()">-->
                <!--<input type="button" value="Editar" onclick="editar(${idLinea},${idNombreEstacion},${estacion.pkEstacion})">-->
                <input type="button" value="Editar" onclick="editar()">
                <input type="button" value="Cancelar" onclick="cancelarEstacion()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Estacion No Encontrada
    </c:otherwise>
    
</c:choose>
