<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>

<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idPkInicial" value="${param.idPkInicial}"/>

<%--<c:set var="segmentoPk" value="${id}"/>--%>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
         
        <c:set var="segmento" value="${gl.buscarSegmentoPorPK(idLinea, idPkInicial)}"/>
        <fieldset class="dialogoModal">
            <legend class="leg_edit">Editar Segmento de progresiva ${segmento.segmentoPK.idPkInicial}</legend>
            <form method="get" action="AdministrarSegmento" name="formulario">
                
                Tipo: Recta<input type="radio" name="tipo_segmento" value="true"  >               
                Curva<input type="radio" name="tipo_segmento" value="false">               
                <input type="hidden" name="pk_inicio" value="${segmento.segmentoPK.idPkInicial}" >
                <input type="hidden" name="id_linea" value="${segmento.segmentoPK.idLinea}" >
                Punto Kilometrico Final: <input type="text" name="pk_final" value="${segmento.pkFinal}" ><br/><br/>
                Gradiente: <input type="text" name="gradiente"  value="${segmento.gradiente}">
                Radio: <input type="text" name="radio" value="${segmento.radioCurvatura}"><br/><br/>
                Velocidad Max. Ascendente: <input type="text" name="velocidad_max_ascendente" value="${segmento.velocidadMaxAscendente}"><br/><br/>
                Velocidad Max. Descendente: <input type="text" name="velocidad_max_descendente"  value="${segmento.velocidadMaxDescendente}">
                Tunel
                No<input type="radio" name="tunel" value="false">
                Si<input type="radio" name="tunel" value="true"><br/><br/>
                <input type="submit" name="accion" value="Editar">
                <input type="button" value="Cancelar" onclick="cancelarSegmento()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Segmento no Encontrado
    </c:otherwise>
    
</c:choose>
