<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>

<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idRestriccion" value="${param.idRestriccion}"/>

<%--<c:set var="segmentoPk" value="${id}"/>--%>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
         
        <c:set var="restriccion" value="${gl.buscarRestriccionPorPK(idLinea, idRestriccion)}"/>
        <fieldset class="dialogoModal">
            <legend class="leg_edit">Editar Restriccion de progresiva ${restriccion.progInicio}</legend>
            <form method="get" action="AdministrarRestriccion" name="formulario">
                
                
                <input type="hidden" name="id_restriccion" value="${restriccion.restriccionPK.idRestriccion}" >
                <input type="hidden" name="id_linea" value="${idLinea}" >
                Velocidad Max. Ascendente: <input type="text" name="vel_max_ascendente" value="${restriccion.velocidadMaxAscendente}"><br/><br/>
                Velocidad Max. Descendente: <input type="text" name="vel_max_descendente"  value="${restriccion.velocidadMaxDescendente}">
                Usuario: <input type="text" name="usuario" value="${restriccion.usuario}"><br/><br/>
                Progresiva Inicio: <input type="text" name="prog_inicio" value="${restriccion.progInicio}">
                Progresiva Final: <input type="text" name="prog_final" value="${restriccion.progFinal}"><br/><br/>
                
                                         
                <input type="submit" name="accion" value="Editar">
                <input type="button" value="Cancelar" onclick="cancelarSegmento()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Restriccion No Encontrada
    </c:otherwise>
    
</c:choose>