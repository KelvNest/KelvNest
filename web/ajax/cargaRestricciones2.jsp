<%-- 
    Document   : estaciones
    Created on : 06/01/2015, 09:16:12 AM
    Author     : seront
--%>
<%@include file="../jslt.jsp" %>

<c:set var="idLinea" value="${param.idLinea}"></c:set>

<!--<div class="contenedorFormulario" id="contRestricciones">-->
<!--<div id="contRestricciones">-->

    <h2>Restricciones</h2>
<c:choose>
    <c:when test="${!empty idLinea}">
        <table class="tablas" >
        <tbody>          
            <tr >
                <td>Editar</td>
                <td>Progresiva inicio</td>
                <td>Progresiva final</td>
                <td>Registrada por</td>                              
                <td>Velocidad maxima ascendente</td>
                <td>Velocidad maxima descendente</td>
            </tr>
            <jsp:useBean class="modelo.GestorLista" id="gl"/>            
                    <c:set var="restricciones" value="${gl.buscarRestriccion(idLinea)}"/>               
            <c:forEach var="restriccion" items="${restricciones}">
                <tr class="campoFormulario">
                    <!--<td>${restriccion.restriccionPK.idRestriccion}</td>-->
                    <td><input type="button" onclick="editarRestriccion('${idLinea}','${restriccion.restriccionPK.idRestriccion}')" value="Editar"></td>
                    <td>${restriccion.progInicio}</td>                              
                    <td>${restriccion.progFinal}</td>                              
                    <td>${restriccion.getUsuario()}</td> 
                    <td>${restriccion.getVelocidadMaxAscendente()}</td>
                    <td>${restriccion.getVelocidadMaxDescendente()}</td>
                    <td><input type="button" onclick="eliminarRestriccion('${idLinea}','${restriccion.restriccionPK.idRestriccion}')" value="X"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </c:when>
    <c:otherwise>
        <h3>Restricciones no encontradas</h3>
    </c:otherwise>
</c:choose>
    
    
<!--</div>-->

