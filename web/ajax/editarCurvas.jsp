<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>

<%@include file="../jslt.jsp" %>
<c:set var="idMatRod" value="${param.idMatRod}"/>
<c:set var="vel" value="${param.vel}"/>

<c:choose>
    <c:when test="${!empty idMatRod}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>         
        <c:set var="curva" value="${gl.buscarCurvaEsfuerzo(idMatRod, vel)}"/>
        <div class="contenedorFormulario">
            <legend><h2>Editar valores ${idMatRod}</h2></legend>
            <form>                
                <input type="hidden" id="id_mat_rod_ed" value="${idMatRod}" >
                <input type="hidden" id="id_vel_ed" value="${vel}" >
                <label> Esfuerzo de tracción</label>
                <input type="text" id="txt_esf_tra_ed" value="${curva.esfuerzoTraccion}">                
                <label> Esfuerzo de frenado</label>
                <input type="text" id="txt_esf_fre_ed" value="${curva.esfuerzoFrenado}">
                <div class="contenedorBoton">
                    <input type="button" value="Editar" onclick="editar()">
                </div>
                <div class="contenedorBoton">
                    <input type="button" value="Cancelar" onclick="cancelarEstacion()">
                </div>                
            </form>
        </div>            
    </c:when>
    <c:otherwise>
        Estacion No Encontrada
    </c:otherwise>
    
</c:choose>
