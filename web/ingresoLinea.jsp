<%-- 
    Document   : DatosSegmento
    Created on : 05/11/2014, 01:00:12 PM
    Author     : Kelvins Insua
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
            <p class="titulo">Administrar Lineas</p>
        </header>
        <div id="bgVentanaModal">
            <div id="msjajax"></div>
            <div id="datos">
                
            </div>
            
        </div>
           
        <fieldset class="formulario">
            <legend class="leg">DATOS DE LA LINEA</legend>
            <p style="font-size: 20;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form method="get" action="AdministrarLinea" name="formulario">
                 <input type="text" name="nombre_linea" class="label_better"  placeholder="Nombre" required>
                 <input type="text" name="pk_inicial" class="label_better"  placeholder="Punto Kilométrico Inicial" required>
                 <input type="text" name="pk_final" class="label_better"  placeholder="Punto Kilométrico final" required>
                 <input type="text" name="trocha" class="label_better"  placeholder="Trocha" required>
                 <input type="submit" name="accion" value="Agregar"/>
            </form>
        </fieldset>
         <c:if test="${!empty requestScope.mensaje}">
            <article>${requestScope.mensaje}</article>
        </c:if>
            <div class="contenedor_tabla">
            <table class="tablas">
            <tr>
            <td>Nombre</td>
            <td>Punto K. Inicial</td>
            <td>Punto K. Final</td>
            <td>Trocha</td>
            </tr>
            <c:if test="${gl.listaLinea()!='null'}">
            <c:forEach var="l" items="${gl.listaLinea()}">
                <tr>
               
                    <td> <a href="#" onclick="editarLinea('${l.idLinea}')">${l.nombreLinea}</a></td>
                    <td>${l.pkInicial}</td>
                    <td>${l.pkFinal}</td>
                    <td>${l.trocha}</td>
                    <td><a href="#" onclick="eliminarLinea('${l.idLinea}')">X</a></td>
                </tr>
            </c:forEach>
            </c:if>
        </table>
            </div>
  
        <!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>
    
</html>
