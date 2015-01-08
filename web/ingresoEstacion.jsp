<%-- 
    Document   : ingresoEstacion
    Created on : 05/01/2015, 09:53:23 AM
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
        <div id="bgVentanaModal" class="dialogoModal">
            <div id="msjajax"></div>
            <div id="datos">
                
            </div>
            
        </div>
        <header>
            <p class="titulo">Administrar Estaciones</p>
        </header>
        
        <fieldset class="formulario">
            <legend class="leg">DATOS DE LA ESTACION</legend>
            <p style="font-size: 20px;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form method="get" action="AdministrarEstacion" name="formulario">
                <select name="select_linea" value="${lin.idLinea}">
                     <option name="select_linea" value="">Seleccione La Linea</option>
                     <c:forEach var="lin" items="${gl.listaLinea()}">
                    <option  name="select_linea" value="${lin.idLinea}">${lin.nombreLinea}</option>
                    </c:forEach>
                </select>
                <input type="text" name="id_nombre_estacion" class="label_better" data-new-placeholder="Nombre de la Estacion" placeholder="Nombre de la Estacion" required>
                <input type="text" name="pk_estacion" class="label_better" data-new-placeholder="Punto Kilometrico de la Estacion" placeholder="Punto Kilometrico de la Estacion" required>
                <input type="submit" name="accion" value="Agregar">
            </form>
        </fieldset>
                     <c:if test="${!empty requestScope.mensaje}">
            <article>${requestScope.mensaje}</article>
        </c:if>
            <div class="contenedor_tabla">
        <table class="tablas">
            <tr>
                <td>Nombre de la Estacion</td>
                <td>Progresiva</td>                              
            </tr>
          
                <c:if test="${gl.buscarEstacion(01)!='null'}">
                <c:forEach var="e" items="${gl.buscarEstacion(01)}">
            <tr>
                <td>  <a href="#"  onclick="editarEstacion('${e.estacionPK.idLinea}','${e.estacionPK.idNombreEstacion}')" >${e.estacionPK.idNombreEstacion}</a></td>
                 <td>${e.pkEstacion}</td>
                 <td> <a href="#" onclick="eliminarEstacion('${e.estacionPK.idLinea}','${e.estacionPK.idNombreEstacion}')">X</a></td>
                 </tr>
        </c:forEach>
                  </c:if>
                
         </table>
            </div>
        <footer><%@include file="footer.jsp" %></footer>
    </body>
    
</html>
