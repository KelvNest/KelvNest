<%-- 
    Document   : estaciones
    Created on : 06/01/2015, 09:16:12 AM
    Author     : seront
--%>
<%@include file="../jslt.jsp" %>
<jsp:useBean class="modelo.GestorLista" id="gl"/>
<c:if test="${gl.listaLinea()!='null'}">
    <div class="contenedor_tabla" style="width: 100%">
            <table class="tablas">
                <tr>
                    <td>Nombre</td>
                    <td>Punto K. Inicial</td>
                    <td>Punto K. Final</td>
                    <td>Trocha</td>
                </tr>                
                    <c:forEach var="l" items="${gl.listaLinea()}">
                        <tr>
                            <td> <input type="button" onclick="editarLinea('${l.idLinea}')" value="${l.nombreLinea}"></td>
                            <td>${l.pkInicial}</td>
                            <td>${l.pkFinal}</td>
                            <td>${l.trocha}</td>
                            <td><input type="button" onclick="eliminarLinea('${l.idLinea}')" value="X"></td>
                        </tr>
                    </c:forEach>
               
            </table>
        </div>
 </c:if>