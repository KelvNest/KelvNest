<%-- 
    Document   : estaciones
    Created on : 06/01/2015, 09:16:12 AM
    Author     : seront
--%>
<%@include file="../jslt.jsp" %>
<!--<link href="css/estilo1.css" type="text/css" rel="stylesheet"/>-->

<c:set var="idLinea" value="${param.idLinea}"></c:set>
<c:set var="progEstacionInicial" value="${param.progEstacionInicial}"></c:set>
<c:set var="progEstacionFinal" value="${param.progEstacionFinal}"></c:set>
<c:set var="velMax" value="${param.velMax}"></c:set>
<%--<c:set var="sentido" value="${param.sentido}"></c:set>--%>
 
<!--<fieldset>-->
<!--    <legend>-->
<div class="contenedorFormulario" id="contRestricciones">
<h2>Restricciones</h2>
<h3> ${idLinea}+ ${progEstacionInicial}+ ${progEstacionFinal}+${velMax}</h3>
    <!--</legend>-->
    <table class="tablas" >
       <tbody>
          
           <tr >
                    <td>Incluir en la simulacion</td>
                    <td>Progresiva inicio</td>
                    <td>Progresiva final</td>
                    <td>Registrada por</td>                              
                    <td>Velocidad maxima ascendente</td>
                    <td>Velocidad maxima descendente</td>
                </tr>
                <jsp:useBean class="modelo.GestorLista" id="gl"/>
                <%--<c:set var="restricciones" value="${gl.buscarRestriccionVel(idLinea, velMax)}"/>--%>
                <c:set var="restricciones" value="${gl.buscarRestriccionEntreEstacionesAscendente(idlinea, progEstacionInicial, progEstacionFinal, velMax)}"/>
                <%--<c:choose>--%>                    
                    <%--<c:when test="${progEstacionInicial<progEstacionFinal}">--%>
                        <%--<c:set var="restricciones" value="${gl.buscarRestriccionEntreEstacionesAscendente(idlinea, progEstacionInicial, progEstacionFinal, velMax)}"/>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<c:set var="restricciones" value="${gl.buscarRestriccionEntreEstacionesDescendente(idlinea, progEstacionInicial, progEstacionFinal, velMax)}"/>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                
                <c:forEach var="restriccion" items="${restricciones}">
                    <tr class="campoFormulario">
                    <!--<tr >-->
                        <!--<td><input type="checkbox" class="incluir" value="${restriccion.restriccionPK.idRestriccion}" onclick="clic(${restriccion.restriccionPK.idRestriccion})"></td>-->
                        <td><input type="checkbox" class="incluir" value="${restriccion.restriccionPK.idRestriccion}"></td>
                        <td>${restriccion.progInicio}</td>                              
                        <td>${restriccion.progFinal}</td>                              
                        <td>${restriccion.getUsuario()}</td> 
                        <td>${restriccion.getVelocidadMaxAscendente()}</td>
                        <td>${restriccion.getVelocidadMaxDescendente()}</td>
                    </tr>
                </c:forEach>
            </tbody>
    </table>
                <div class="contenedorBoton">
                    <input type="button" id="simular" class="botonContinuar" value="Simular" onclick="simular()"/> 
                </div>
</div>
<!--</fieldset>-->
           