<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="mr" value="${gl.buscarMaterialRodante(id)}"/>
       <div class="contenedorFormulario" id="formSeg" style="width: 1020px">
            <legend><h2>Editar Material Rodante ${mr.nombreMaterialRodante}</h2></legend>
            <form>               
                <input type="hidden" id="id_material_Rodante_ed" value="${id}" >
                <div class="columna1">
                    <label class="tituloFormulario">Nombre</label>
                    <input class="campoFormulario" type="text" id="nombre_material_rodante_ed" value="${mr.nombreMaterialRodante}">
                    <label class="tituloFormulario">Tipo</label>
                    <input class="campoFormulario" type="text" id="tipo_ed" value="${mr.tipo}" >
                    <label class="tituloFormulario">Sub-Tipo</label>
                    <select class="campoFormulario" id="sub_tipo_ed">
                        <option value="Tren de Viajeros">Tren de Viajeros</option>
                        <option value="Tren de Mercarcias">Tren de Mercancias</option>
                        <option value="Locomotora">Locomotora</option>
                    </select>
                    <label class="tituloFormulario"> Numero de Vagones</label>
                    <input class="campoFormulario" type="text" id="numero_vagones_ed" value="${mr.numeroVagones}" >
                    <label class="tituloFormulario">Capacidad de Pasajeros</label>
                    <input class="campoFormulario" type="text" id="capacidad_pasajeros_ed" value="${mr.capacidadPasajeros}" >
                    
                </div>
                <div class="columna2">
                    <label class="tituloFormulario">Largo</label>
                    <input class="campoFormulario" type="text" id="largo_ed" value="${mr.largo}" >
                    <label class="tituloFormulario">Alto de Cara</label>
                    <input class="campoFormulario" type="text" id="alto_ed" value="${mr.altoCara}" >
                    <label class="tituloFormulario">Ancho de Cara</label>
                    <input class="campoFormulario" type="text" id="ancho_ed" value="${mr.anchoCara}" >
                    <label class="tituloFormulario">Aceleracion Maxima(m/s^2)</label>
                    <input class="campoFormulario" type="text" id="aceleracion_maxima_ed" value="${mr.aceleracionMax}" >
                    <label class="tituloFormulario">Desaceleracion Maxima(m/s^2)</label>
                    <input class="campoFormulario" type="text" id="desaceleracion_maxima_ed" value="${mr.desaceleracionMax}" >
                    </div>
                <div class="columna1">
                    <label class="tituloFormulario">Kilometraje (Km)</label>
                    <input class="campoFormulario" type="text" id="velocidad_diseño_ed" value="${mr.kilometraje}" >
                    <label class="tituloFormulario"> Peso (t)</label>
                    <input class="campoFormulario" type="text" id="masa_ed" value="${mr.masa}" >
                    <label class="tituloFormulario">Velocidad de Diseño(m/s)</label>
                    <input class="campoFormulario" type="text" id="velocidad_diseño_ed" value="${mr.velocidadDiseño}" >
                    <label class="tituloFormulario">Velocidad de operacion (m/s)</label>
                    <input class="campoFormulario" type="text" id="velocidad_operacion_ed" value="${mr.velocidadOperativa}" >
                
                </div>
                <div class="contenedorBoton">
                    <!--<input type="submit" id="accion" value="Editar" >-->
                    <input class="botonContinuar" type="button" id="accion" value="Editar" onclick="editarMR()">
                    <div>
                        <div class="contenedorBoton">
                            <input class="botonContinuar" type="button" value="Cancelar" onclick="cancelarMaterialRodante()">
                            <div>    
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            Material Rodante no encontrado
                        </c:otherwise>

                    </c:choose>