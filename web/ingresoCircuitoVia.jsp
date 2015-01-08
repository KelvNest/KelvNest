<%-- 
    Document   : ingresoCircuitoVia
    Created on : 05/01/2015, 10:12:11 AM
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilo.jsp" %>
<%@include file="jslt.jsp" %>
<%@include file="labelbetter.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/funciones.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_lb.css" type="text/css" rel="stylesheet">
        <title>S.I.P.T.R.A.F</title>
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
    </head>
    <body >
        <header>
            <p class="titulo">Administrar Circuitos de Via</p>
        </header>
        <div id="bgVentanaModal">
            <div id="msjajax"></div>
            <div id="datos">
                
            </div>
            
        </div>
        
        <fieldset class="formulario">
            <legend class="leg">DATOS DEL CIRCUITO DE VIA</legend>
            <p style="font-size: 20;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form method="get" action="AdministrarCircuitoVia" name="formulario">
                Linea:<select name="select_linea" value="${b.idLinea}">
                    <option value="">Seleccione la Linea</option>
                    <c:forEach var="b" items="${gl.listaLinea()}">
                        <option  name="select_linea" value="${b.idLinea}" onchange="">${b.nombreLinea}</option>
                    </c:forEach>
                </select>
                <input type="text" name="id_pk_inicial_circuito" class="label_better" data-new-placeholder="Punto Kilometrico Inicial" placeholder="Punto Kilometrico Inicial" required>
                <input type="text" name="pk_final_circuito" class="label_better" data-new-placeholder="Punto Kilometrico Final" placeholder="Punto Kilometrico Final" required>
                <input type="submit" name="accion" value="Agregar">
            </form>
        </fieldset>
                     <c:if test="${!empty requestScope.mensaje}">
            <article>${requestScope.mensaje}</article>
        </c:if>
                    <div class="contenedor_tabla">
        <table class="tablas">
            <tr>
                <td>Progresiva Inicial</td>
                <td>Progresiva Final</td>
                
            </tr>
            
                <c:if test="${gl.buscarCircuitoVia(01)!='null'}">
                <c:forEach var="c" items="${gl.buscarCircuitoVia(01)}">
                    
            <tr>
                <td> <a id="editarCir" href="#" onclick="editarCircuitoVia('${c.circuitoViaPK.idLinea}','${c.circuitoViaPK.idPkInicialCircuito}')">${c.circuitoViaPK.idPkInicialCircuito}</a></td>
                 <td>${c.pkFinalCircuito}</td>
            <td> <a href="#" onclick="eliminarCircuitoVia('${c.circuitoViaPK.idLinea}','${c.circuitoViaPK.idPkInicialCircuito}')">X</a></td>
                 </tr>
        </c:forEach>
                 </c:if>
                
         </table>
                    </div>
        <footer><%@include file="footer.jsp" %></footer>
    </body>
    
</html>
