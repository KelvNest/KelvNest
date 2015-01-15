<%--<%@page import="modelo.GestorLista"%>--%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<script src="js/administrarLinea.js" type="text/javascript"></script>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="lin" value="${gl.buscarLinea(id)}"/>       
        <div class="contenedorFormulario">
            <legend><h2>Eliminar Linea ${lin.nombreLinea}</h2></legend>
            <form>
                <input type="hidden" name="nombre_linea" value="${lin.nombreLinea}" >
                <p>¿Está seguro que quieres eliminar La Linea ${lin.nombreLinea}?,
                    pulse Eliminar para continuar, pulse Cancelar para salir.<br/> 
                    Al eliminar se eliminaran todos los datos relacionados a esta linea.</p>
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" id="elimino" value="Eliminar" onclick="eliminarL('${lin.nombreLinea}')">
                </div>                
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" value="Cancelar" onclick="cancelarLinea()">
                </div>                
            </form>
        </div>


    </c:when>
    <c:otherwise>
        Linea no Encontrada
    </c:otherwise>

</c:choose>