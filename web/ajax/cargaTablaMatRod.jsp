<%-- 
    Document   : estaciones
    Created on : 06/01/2015, 09:16:12 AM
    Author     : seront
--%>
<%@include file="../jslt.jsp" %>
<jsp:useBean class="modelo.GestorLista" id="gl"/>

    <div class="contenedor_tabla" id="ingMat">
            <table class="tablas">
                <tr>
                    <td>Nombre</td>
                    <td>Tipo</td>
                    <td>Sub Tipo</td>
                    <td>Vagones</td>
                    <td>Pasajeros</td>
                    <td>Kilometraje</td>
                    <td>Peso</td>
                    <td>Largo</td>
                    <td>Alto</td>
                    <td>Ancho</td>
                    <td>Vel. de Diseño</td>
                    <td>Vel. de Operacion</td>
                    <td>Acel. Max</td>
                    <td>Desacel. Max</td>
                </tr>
                <c:if test="${gl.listaMaterialRodante()!='null'}">
                    <c:forEach var="mr" items="${gl.listaMaterialRodante()}">
                        <tr>
                            <td> <input type="button" onclick="editarMaterialRodante('${mr.idMaterialRodante}')" value="${mr.nombreMaterialRodante}"/></td>
                            <td>${mr.tipo}</td>
                            <td>${mr.subTipo}</td>
                            <td>${mr.numeroVagones}</td>
                            <td>${mr.capacidadPasajeros}</td>
                            <td>${mr.kilometraje}</td>
                            <td>${mr.masa}</td>
                            <td>${mr.largo}</td>
                            <td>${mr.altoCara}</td>
                            <td>${mr.anchoCara}</td>
                            <td>${mr.velocidadDiseño}</td>
                            <td>${mr.velocidadOperativa}</td>
                            <td>${mr.aceleracionMax}</td>
                            <td>${mr.desaceleracionMax}</td>
                            <!--<td><a href="#" onclick="eliminarMaterialRodante('${mr.idMaterialRodante}')">X</a></td>-->
                            <td><input type="button" onclick="eliminarMaterialRodante('${mr.idMaterialRodante}')" value="X"></td>
                        </tr>
                    </c:forEach>
                </c:if>

            </table>
        </div>