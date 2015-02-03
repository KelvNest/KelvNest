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
        <div class="contenedorFormulario">
            <legend>
                <h2>
                    Editar Estacion ${idNombreEstacion}
                </h2>
            </legend>
            <form>
                <input type="hidden" id="id_nombre_estacion_ed" value="${idNombreEstacion}" >
                <input type="hidden" id="id_linea_ed" value="${idLinea}" >
                Punto Kilometrico: <input class="campoFormulario" type="text" id="pk_estacion_ed" value="${estacion.pkEstacion}"><br/><br/>

                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" value="Editar" onclick="editar()">
                </div>

                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" value="Cancelar" onclick="cancelarEstacion()">
                </div>

            </form>
        </div>

    </fieldset>
</c:when>
<c:otherwise>
    Estacion No Encontrada
</c:otherwise>

</c:choose>
