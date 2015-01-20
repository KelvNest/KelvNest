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
        <div class="contenedorFormulario">
            <legend><h2>Editar Restriccion de progresiva ${restriccion.progInicio}</h2></legend>            
            <form>
                <input type="hidden" id="id_restriccion_ed" value="${restriccion.restriccionPK.idRestriccion}" >
                <input type="hidden" id="id_linea_ed" value="${idLinea}" >
                <label class="tituloFormulario">Velocidad Max. Ascendente</label>
                <input class="campoFormulario" type="text" id="vel_max_ascendente_ed" value="${restriccion.velocidadMaxAscendente}">
                <label class="tituloFormulario">Velocidad Max. Descendente</label>
                <input class="campoFormulario" type="text" id="vel_max_descendente_ed"  value="${restriccion.velocidadMaxDescendente}">
                <label class="tituloFormulario">Usuario</label>
                <input class="campoFormulario" type="text" id="usuario_ed" value="${restriccion.usuario}">
                <label class="tituloFormulario">Progresiva Inicio</label>
                <input class="campoFormulario" type="text" id="prog_inicio_ed" value="${restriccion.progInicio}">
                <label class="tituloFormulario">Progresiva Final</label>
                <input class="campoFormulario" type="text" id="prog_final_ed" value="${restriccion.progFinal}">
                <div class="contenedorBoton">
                    <!--<input class="botonContinuar" type="button" onclick="editarRes('${restriccion.restriccionPK.idRestriccion}', '${idLinea}', '${restriccion.velocidadMaxAscendente}', '${restriccion.velocidadMaxDescendente}', '${restriccion.usuario}', '${restriccion.progInicio}', '${restriccion.progFinal}')" value="Editar">-->
                    <input class="botonContinuar" type="button" onclick="editarRes()" value="Editar">
                </div>
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" value="Cancelar" onclick="cancelarRestriccion()">
                </div>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        Restriccion No Encontrada
    </c:otherwise>

</c:choose>