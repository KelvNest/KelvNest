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
        <div class="contenedorFormulario" id="formSeg">
            <legend class="tituloFormulario">
                <h2>Editar Segmento de progresiva ${segmento.segmentoPK.idPkInicial}</h2>
            </legend>
            <form>
                <div class="columna1">
                    <input type="hidden" id="pk_inicio_ed" value="${segmento.segmentoPK.idPkInicial}" >
                    <input type="hidden" id="id_linea_ed" value="${segmento.segmentoPK.idLinea}" >
                    <label class="tituloFormulario">Tipo</label>
                    <div class="grp_rbt" class="campoFormulario">
                        <c:choose>
                            <c:when test="${segmento.recta==true}">
                                <label class="tituloFormulario">Recta </label>
                                <input class="campoFormulario" type="radio" id="tipo_segmento_ed" name="tipo_segmento" value="true" checked="checked" required>               
                                <label class="tituloFormulario">Curva </label>
                                <input class="campoFormulario"type="radio" id="tipo_segmento_ed" name="tipo_segmento" value="false" required>
                            </c:when>
                            <c:otherwise>
                                <label class="tituloFormulario">Recta </label>
                                <input class="campoFormulario" type="radio" id="tipo_segmento_ed" name="tipo_segmento" value="true"  required>               
                                <label class="tituloFormulario">Curva </label>
                                <input class="campoFormulario"type="radio" id="tipo_segmento_ed" name="tipo_segmento" value="false" checked="checked" required>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <label class="tituloFormulario">Radio</label>
                    <input class="campoFormulario" type="text" id="radio_ed" value="${segmento.radioCurvatura}">
                    <label class="tituloFormulario">Punto Kilometrico Final</label>
                    <input class="campoFormulario" type="text" id="pk_final_ed" value="${segmento.pkFinal}" >
                    <label class="tituloFormulario">Gradiente</label>
                    <input class="campoFormulario" type="text" id="gradiente_ed"  value="${segmento.gradiente}">                    
                </div>
                <div class="columna2">
                    <label class="tituloFormulario">Velocidad Max. Ascendente</label>
                    <input class="campoFormulario" type="text" id="velocidad_max_ascendente_ed" value="${segmento.velocidadMaxAscendente}">
                    <label class="tituloFormulario">Velocidad Max. Descendente</label>
                    <input class="campoFormulario" type="text" id="velocidad_max_descendente_ed"  value="${segmento.velocidadMaxDescendente}">
                    <label class="tituloFormulario">Tunel</label>
                    <div class="grp_rbt">
                        <c:choose>
                            <c:when test="${segmento.tunel==true}">
                                 <label for="si">Si</label>
                                 <input class="campoFormulario" type="radio" id="tunel_ed" name="tunel" value="true" checked="checked" id="si">
                        <label for="no">No</label> 
                        <input class="campoFormulario" type="radio" id="tunel_ed" name="tunel" value="false" id="no">
                            </c:when>
                            <c:otherwise>
                                <label for="si">Si</label>
                                 <input class="campoFormulario" type="radio" id="tunel_ed" name="tunel" value="true"  id="si">
                        <label for="no">No</label> 
                        <input class="campoFormulario" type="radio" id="tunel_ed" name="tunel" value="false" checked="checked" id="no">
                            </c:otherwise>
                        </c:choose>
                       
                    </div>
                </div>
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" value="Editar" onclick="editarS()">
                </div>
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" value="Cancelar" onclick="cancelarSegmento()">
                </div>               
            </form>
        </div>
    </c:when>
    <c:otherwise>
        Segmento no Encontrado
    </c:otherwise>

</c:choose>
