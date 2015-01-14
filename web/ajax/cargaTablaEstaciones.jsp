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
        <div class="contenedor_tabla">
        <table class="tablas">
            <tr>
                <td>Nombre de la Estacion</td>
                <td>Progresiva</td>                              
            </tr>
          
                <jsp:useBean class="modelo.GestorLista" id="gl"/>
            <c:set var="estaciones" value="${gl.buscarEstacion(idLinea)}"/>
                <c:forEach var="e" items="${estaciones}">
            <tr>
                <td>  <a href="#"  onclick="editarEstacion('${e.estacionPK.idLinea}','${e.estacionPK.idNombreEstacion}')" >${e.estacionPK.idNombreEstacion}</a></td>
                 <td>${e.pkEstacion}</td>
                 <td> <a href="#" onclick="eliminarEstacion('${e.estacionPK.idLinea}','${e.estacionPK.idNombreEstacion}')">X</a></td>
                 </tr>
        </c:forEach>
                  
                
         </table>
            </div>           
            
    </c:when>
    <c:otherwise>
         <div class="contenedor_tabla">
        <table class="tablas">
            <tr>
                <td>Nombre de la Estacion</td>
                <td>Progresiva</td>                              
            </tr>
        </table>
             </div>
    </c:otherwise>
</c:choose>
