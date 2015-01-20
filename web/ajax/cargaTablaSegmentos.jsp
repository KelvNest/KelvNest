<%-- 
    Document   : estaciones
    Created on : 06/01/2015, 09:16:12 AM
    Author     : seront
--%>
<%@include file="../jslt.jsp" %>
<!--<link href="css/estilo1.css" type="text/css" rel="stylesheet"/>-->

<c:set var="idLinea" value="${param.idLinea}"></c:set>
<jsp:useBean class="modelo.GestorLista" id="gl"/>
<c:choose>
    <c:when test="${!empty idLinea}">
        <div class="contenedor_tabla" style="width: 100%">
            <table class="tablas">
                <tr>
                    <td>Progresiva Inicial</td>
                    <td>Progresiva Final</td>
                    <td>Curva/Recta</td>
                    <td>Gradiente</td>
                    <td>Radio</td>
                    <td>Velocidad Max. Ascendente</td>
                    <td>Velocidad Max. Descendente</td>
                    <td>Tunel</td>
                </tr>
                <c:choose>
                    <c:when test="${gl.listaSegmento(idLinea)!='null'}">
                        <c:forEach var="a" items="${gl.listaSegmento(idLinea)}">
                            <tr>
                                <!--<td ><a href="#" onclick="editarSegmento('${a.segmentoPK.idLinea}', '${a.segmentoPK.idPkInicial}')">${a.segmentoPK.idPkInicial}</a></td>-->
                                <td ><input type="button" onclick="editarSegmento('${a.segmentoPK.idLinea}', '${a.segmentoPK.idPkInicial}')" value="${a.segmentoPK.idPkInicial}"></td>
                                <td>${a.pkFinal}</td>

                                <c:choose>
                                    <c:when  test="${a.recta=='true'}">
                                        <td>Recta</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Curva</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${a.gradiente}</td>

                                <td>${a.radioCurvatura}</td>

                                <td>${a.velocidadMaxAscendente}</td>

                                <td>${a.velocidadMaxDescendente}</td>


                                <c:choose>
                                    <c:when  test="${a.tunel=='true'}">
                                        <td>Si</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>No</td>
                                    </c:otherwise>
                                </c:choose>
                                <!--<td><a href="#" onclick="eliminarSegmento('${a.segmentoPK.idLinea}', '${a.segmentoPK.idPkInicial}')">X</a></td>-->
                                <td><input type="button" onclick="eliminarSegmento('${a.segmentoPK.idLinea}', '${a.segmentoPK.idPkInicial}')" value="X"></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                </c:choose>

            </table>
        </div>           

    </c:when>
    <c:otherwise>
        <div class="contenedor_tabla" style="width: 100%">
            <table class="tablas">
                <tr>
                    <td>Progresiva Inicial</td>
                    <td>Progresiva Final</td>
                    <td>Curva/Recta</td>
                    <td>Gradiente</td>
                    <td>Radio</td>
                    <td>Velocidad Max. Ascendente</td>
                    <td>Velocidad Max. Descendente</td>
                    <td>Tunel</td>
                </tr>
            </table>
        </div>
    </c:otherwise>
</c:choose>
