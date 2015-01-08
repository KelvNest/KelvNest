<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="lin" value="${gl.buscarLinea(id)}"/>
  <fieldset class="dialogoModal">
             <legend class="leg_edit">Editar Linea ${lin.nombreLinea}</legend>
            <form method="get" action="AdministrarLinea" name="formulario">
                <input type="hidden" name="id_linea" value="${lin.idLinea}" >
                <input type="text" name="nombre_linea" value="${lin.nombreLinea}" >
                Punto Kilométrico Inicial: <input type="text" name="pk_inicial" value="${lin.pkInicial}" >
                Punto Kilométrico Final: <input type="text" name="pk_final" value="${lin.pkFinal}" >
                <br/>
                <br/>
                Trocha: <input type="text" name="trocha" value="${lin.trocha}" ><br/>
                <br/>
                <input type="submit" name="accion" value="Editar" >
                <input type="button" value="Cancelar" onclick="cancelarLinea()">
            </form>
        </fieldset>
    </c:when>
    <c:otherwise>
        Linea no Encontrada
    </c:otherwise>
    
</c:choose>