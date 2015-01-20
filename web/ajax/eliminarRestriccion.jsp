<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="idLinea" value="${param.idLinea}"/>
<c:set var="idRestriccion" value="${param.idRestriccion}"/>
<c:choose>
    <c:when test="${!empty idLinea}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="restriccion" value="${gl.buscarRestriccionPorPK(idLinea, idRestriccion)}"/>
<!--  <fieldset class="dialogoModal">
             <legend class="leg_edit">Eliminar Restriccion de Progresiva ${idRestriccion}</legend>
            <form method="get" action="AdministrarRestriccion" name="formulario">
                <input type="hidden" name="id_restriccion" value="${idRestriccion}" >
                <input type="hidden" name="id_linea" value="${idLinea}" >
                <p>¿Está seguro que quieres eliminar la restriccion de progresiva ${restriccion.progInicio}?,
                pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <input type="submit" name="accion" value="Eliminar" >
                <input type="button" value="Cancelar" onclick="cancelarRestriccion()">
            </form>
        </fieldset>-->
<div class="contenedorFormulario">
    <legend><h2>Eliminar Restriccion de Progresiva ${idRestriccion}</h2></legend>
            <form>
                <input type="hidden" id="id_restriccion_el" value="${idRestriccion}" >
                <input type="hidden" id="id_linea_el" value="${idLinea}" >
                <p>¿Está seguro que quieres eliminar la restriccion de progresiva ${restriccion.progInicio}?,
                pulse Eliminar para continuar, pulse Cancelar para salir</p>
                <div class="contenedorBoton">
                    <!--<input type="submit" name="accion" value="Eliminar" >-->
                    <input type="button" class="botonContinuar" value="Eliminar" onclick="eliminarRes()">
                </div>
                <div class="contenedorBoton">
                    <input type="button" value="Cancelar" class="botonContinuar" onclick="cancelarRestriccion()">
                </div>
                
            </form>
</div>             
       
    </c:when>
    <c:otherwise>
        Restriccion No Encontrada
    </c:otherwise>
    
</c:choose>
