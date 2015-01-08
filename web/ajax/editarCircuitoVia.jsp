<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>

<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idPkInicialCircuito" value="${param.idPkInicialCircuito}"/>

<%--<c:set var="segmentoPk" value="${id}"/>--%>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
         
        <c:set var="circuitoVia" value="${gl.buscarCircuitoViaPorPK(idLinea, idPkInicialCircuito)}"/>
        <fieldset class="dialogoModal">
            <legend class="leg_edit">Editar Circuito de Via de Progresiva ${idPkInicialCircuito}</legend>
            <form method="post" action="AdministrarCircuitoVia" name="formulario">
                
                
                <input type="hidden" name="id_pk_inicial_circuito" value="${idPkInicialCircuito}" >
                <input type="hidden" name="id_linea" value="${idLinea}" >
                Punto Kilometrico Final: <input type="text" name="pk_final_circuito" value="${circuitoVia.pkFinalCircuito}"><br/><br/>
                
                
                                         
                <input type="submit" name="accion" value="Editar">
                <input type="button" value="Cancelar" onclick="cancelarCircuitoVia()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Estacion No Encontrada
    </c:otherwise>
    
</c:choose>
