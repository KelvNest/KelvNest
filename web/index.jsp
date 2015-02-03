

<!-- Document   : index
    Created on : 01/11/2014, 10:03:32 AM
    Author     : Kelvins Insua-->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="jslt.jsp" %>
<%@include file="estilo.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="SquareMenu.jsp" %>
        <title>S.I.P.T.R.A.F</title>
    </head>
    <body>
        <header>
            <%@include file="encabezado.jsp" %>
           </header>
         <h2>sistema inteligente para la planificacion del trafico ferroviario</h2>
        <p class="credit">Creado Por: Kelvins Insua y Néstor Mendoza</p>
        <div class="wrapper">
	  <div class="main">

  	  
      <div class="page-container">
          <div class="sidemenu">
          <nav class="left">
              <a href="ingresoLinea.jsp">Administrar Línea</a>
            <a href="ingresoMaterialRodante.jsp">Administrar Material Rodante</a>
            <a href="ingresoSegmento.jsp">Administrar Segmentos</a>
          </nav>
          <nav class="right">
              <a href="Concepto.jsp" target="_blank">¿Qué es S.I.P.T.R.A.F?</a>
            <a href="ingresoRestriccion.jsp">Administrar Restricciones</a>
            <a href="ingresoCircuitoVia.jsp">Administrar Circuitos de Via</a>
            <a href="ingresoEstacion.jsp">Administrar Estaciones</a>
            <a href="ingresoMarchaTipo.jsp">Nueva Marcha Tipo</a>
            <a href="ingresoCurvasMaterialRodante.jsp">Caracteristicas del material rodante</a>
          </nav>
        </div>
      </div>
    </div>
      
  </div>
        
<!--        <footer><%@include file="footer.jsp" %></footer>-->
    </body>
</html>
