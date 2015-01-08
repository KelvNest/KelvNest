<%-- 
    Document   : ingresoRestriccion
    Created on : 05/01/2015, 09:57:19 AM
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
            <p class="titulo">Administrar Restricciones</p>
        </header>
        <div id="bgVentanaModal" class="dialogoModal">
            <div id="msjajax"></div>
            <div id="datos">
                
            </div>
            
        </div>
        <fieldset class="formulario">
            <legend class="leg">DATOS DE LA RESTRICCION</legend>
            <form method="get" action="AdministrarRestriccion" name="formulario">
                <select  name="select_linea" value="${lin.idLinea}">
                     <option name="select_linea" value="">Seleccione La Linea</option>
                     <c:forEach var="lin" items="${gl.listaLinea()}">
                    <option name="select_linea" value="${lin.idLinea}">${lin.nombreLinea}</option>
                    </c:forEach>
                </select>
                <input type="text" name="usuario" class="label_better" data-new-placeholder="Usuario" placeholder="Usuario" required>
                <input type="text" name="prog_inicio" class="label_better" data-new-placeholder="Progresiva Inicial" placeholder="Progresiva Inicial" required>
                <input type="text" name="prog_final" class="label_better" data-new-placeholder="Progresiva Final" placeholder="Progresiva Final" required>
                <input type="text" name="vel_max_ascendente" class="label_better" data-new-placeholder="Velocidad Max Ascendente" placeholder="Velocidad Max Ascendente" required>
                <input type="text" name="vel_max_descendente" class="label_better" data-new-placeholder="Velocidad Max Descendente" placeholder="Velocidad Max Descendente" required>
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
                <td>Usuario</td>
                <td>Vel. Max. Ascendente</td>
                <td>Vel. Max. Descendente</td>
            </tr>
            
                <c:if test="${gl.buscarRestriccion(01)!='null'}">
                <c:forEach var="r" items="${gl.buscarRestriccion(01)}">
            <tr>
                <td><a href="#" onclick="editarRestriccion('${r.restriccionPK.idLinea}','${r.restriccionPK.idRestriccion}')">${r.progInicio}</a></td>
                 <td>${r.progFinal}</td>
                 <td>${r.usuario}</td>
                 <td>${r.velocidadMaxAscendente}</td>
                 <td>${r.velocidadMaxDescendente}</td>
                 <td><a href="#" onclick="eliminarRestriccion('${r.restriccionPK.idLinea}','${r.restriccionPK.idRestriccion}')">X</a></td>
                 </tr>
        </c:forEach>
        </c:if>        
         </table>
            </div>
        <footer><%@include file="footer.jsp" %></footer>
    </body>
    
</html>
