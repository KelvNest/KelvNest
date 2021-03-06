<%-- 
    Document   : estaciones
    Created on : 06/01/2015, 09:16:12 AM
    Author     : seront
--%>
<%@include file="../jslt.jsp" %>
<link href="css/estilo1.css" type="text/css" rel="stylesheet"/>

<c:set var="idLinea" value="${param.idLinea}"></c:set>
<c:choose>
    <c:when test="${!empty idLinea}">
        <label class="tituloFormulario">Estacion inicial </label> 
        <select id="cmb_est_inicio" class="campoFormulario">
            <option value=""> Seleccione...</option>
            <jsp:useBean class="modelo.GestorLista" id="gl"/>
            <c:set var="estaciones" value="${gl.buscarEstacion(idLinea)}"/>
            <c:forEach var="estacion" items="${estaciones}">                       
                <option value="${estacion.pkEstacion}"> ${estacion.getEstacionPK().idNombreEstacion} </option>                        
            </c:forEach>
        </select>

        <label class="tituloFormulario">Estacion final </label> 
        <select id="cmb_est_final" class="campoFormulario">
            <option value=""> Seleccione...</option>    
            <c:forEach var="estacion" items="${estaciones}">                       
                <option value="${estacion.pkEstacion}"> ${estacion.getEstacionPK().idNombreEstacion} </option>                        
            </c:forEach>
        </select>
    </c:when>
    <c:otherwise>
        <label class="tituloFormulario">Estacion inicial </label> 
        <select id="cmb_est_inicio" class="campoFormulario">
            <option value=""> Seleccione...</option>
        </select>
        <label class="tituloFormulario">Estacion final </label> 
        <select id="cmb_est_final" class="campoFormulario">
            <option value=""> Seleccione...</option>   
        </select>
    </c:otherwise>
</c:choose>
