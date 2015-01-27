<%-- 
    Document   : estaciones
    Created on : 06/01/2015, 09:16:12 AM
    Author     : seront
--%>
<%@include file="../jslt.jsp" %>
<link href="css/estilo1.css" type="text/css" rel="stylesheet"/>

<%--<c:set var="idLinea" value="${param.idLinea}"></c:set>--%>
<c:set var="idMatRod" value="${param.idMatRod}"></c:set>

<c:choose>
    <c:when test="${!empty idMatRod}">
        <div class="contenedor_tabla">
            <table class="tablas">
                <tr>
                    <td>Velocidad</td>
                    <td>Esfuerzo de Tracción</td>                              
                    <td>Esfuerzo de Frenado</td>                              
                    <td>Eliminar</td>                              
                </tr>
                <jsp:useBean class="modelo.GestorLista" id="gl"/>
                <c:set var="curvas" value="${gl.listaEsfuerzos(idMatRod)}"/>
                <c:forEach var="cur" items="${curvas}">
                    <tr>
                        <td>  <input type="button"  onclick="editarCurva('${cur.curvaEsfuerzoPK.idMaterialRodante}', '${cur.curvaEsfuerzoPK.idVelocidadCurvaEsfuerzo}')" value="${cur.curvaEsfuerzoPK.idVelocidadCurvaEsfuerzo}"></td>
                        <td>${cur.esfuerzoTraccion}</td>
                        <td>${cur.esfuerzoFrenado}</td>
                        <td> <input type="button" onclick="eliminarEstacion('${cur.curvaEsfuerzoPK.idMaterialRodante}', '${cur.curvaEsfuerzoPK.idVelocidadCurvaEsfuerzo}')" value="X"></td>
                    </tr>
                </c:forEach>
            </table>
        </div>           
    </c:when>
    <c:otherwise>
        <div class="contenedor_tabla">
            <table class="tablas">
                <tr>
                    <td>Velocidad</td>
                    <td>Esfuerzo de Tracción</td>                              
                    <td>Esfuerzo de Frenado</td>                              
                    <td>Eliminar</td>                              
                </tr>
            </table>
        </div>
    </c:otherwise>
</c:choose>
