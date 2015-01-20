<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="mr" value="${gl.buscarMaterialRodante(id)}"/>
<!--  <fieldset class="dialogoModal">
             <legend class="leg_edit">Editar Material Rodante ${mr.nombreMaterialRodante}</legend>
            <form method="get" action="AdministrarMaterialRodante" name="formulario">
               
                <input type="hidden" name="id_material_Rodante" value="${id}" >
                Nombre: <input type="text" name="nombre_material_rodante" value="${mr.nombreMaterialRodante}">
                Tipo: <input type="text" name="tipo" value="${mr.tipo}" >
                <select name="sub_tipo">
                     <option value="Tren de Viajeros">Tren de Viajeros</option>
                     <option value="Tren de Mercarcias">Tren de Mercancias</option>
                     <option value="Locomotora">Locomotora</option>
                 </select><br/><br/>
                Numero de Vagones: <input type="text" name="numero_vagones" value="${mr.numeroVagones}" >
                Capacidad de Pasajeros: <input type="text" name="capacidad_pasajeros" value="${mr.capacidadPasajeros}" ><br/><br/>
                Kilometraje (Km):<input type="text" name="velocidad_diseño" value="${mr.kilometraje}" >
                Peso (t): <input type="text" name="masa" value="${mr.masa}" ><br/><br/>
                Largo: <input type="text" name="largo" value="${mr.largo}" >
                
                Alto de Cara:  <input type="text" name="alto" value="${mr.altoCara}" ><br/><br/>
                Ancho de Cara: <input type="text" name="ancho" value="${mr.anchoCara}" >
                Aceleracion Maxima(m/s^2): <input type="text" name="aceleracion_maxima" value="${mr.aceleracionMax}" ><br/><br/>                
                Desaceleracion Maxima(m/s^2): <input type="text" name="desaceleracion_maxima" value="${mr.desaceleracionMax}" ><br/><br/>
                Velocidad de Diseño(m/s): <input type="text" name="velocidad_diseño" value="${mr.velocidadDiseño}" >
                Velocidad de operacion (m/s): <input type="text" name="velocidad_operacion" value="${mr.velocidadOperativa}" ><br/><br/>
               
                
                
                <input type="submit" name="accion" value="Editar" >
                <input type="button" value="Cancelar" onclick="cancelarMaterialRodante()">
            </form>
        </fieldset>-->
<div class="contenedorFormulario">
    <legend><h2>Editar Material Rodante ${mr.nombreMaterialRodante}</h2></legend>
            <form>               
                <input type="hidden" id="id_material_Rodante" value="${id}" >
                <label class=""></label>
                Nombre: <input type="text" id="nombre_material_rodante" value="${mr.nombreMaterialRodante}">
                Tipo: <input type="text" id="tipo" value="${mr.tipo}" >
                <select id="sub_tipo">
                     <option value="Tren de Viajeros">Tren de Viajeros</option>
                     <option value="Tren de Mercarcias">Tren de Mercancias</option>
                     <option value="Locomotora">Locomotora</option>
                 </select><br/><br/>
                Numero de Vagones: <input type="text" id="numero_vagones" value="${mr.numeroVagones}" >
                Capacidad de Pasajeros: <input type="text" id="capacidad_pasajeros" value="${mr.capacidadPasajeros}" ><br/><br/>
                Kilometraje (Km):<input type="text" id="velocidad_diseño" value="${mr.kilometraje}" >
                Peso (t): <input type="text" id="masa" value="${mr.masa}" ><br/><br/>
                Largo: <input type="text" id="largo" value="${mr.largo}" >
                
                Alto de Cara:  <input type="text" id="alto" value="${mr.altoCara}" ><br/><br/>
                Ancho de Cara: <input type="text" id="ancho" value="${mr.anchoCara}" >
                Aceleracion Maxima(m/s^2): <input type="text" id="aceleracion_maxima" value="${mr.aceleracionMax}" ><br/><br/>                
                Desaceleracion Maxima(m/s^2): <input type="text" id="desaceleracion_maxima" value="${mr.desaceleracionMax}" ><br/><br/>
                Velocidad de Diseño(m/s): <input type="text" id="velocidad_diseño" value="${mr.velocidadDiseño}" >
                Velocidad de operacion (m/s): <input type="text" id="velocidad_operacion" value="${mr.velocidadOperativa}" ><br/><br/>
               
                
                
                <input type="submit" id="accion" value="Editar" >
                <input type="button" value="Cancelar" onclick="cancelarMaterialRodante()">
            </form>
</div>
    </c:when>
    <c:otherwise>
        Seccion no Encontrada
    </c:otherwise>
    
</c:choose>