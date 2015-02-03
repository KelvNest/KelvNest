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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_lb.css" type="text/css" rel="stylesheet">
        <script src="js/administrarEstacion.js" type="text/javascript" rel=""></script>
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
<input type="button" class="btnIrAlMenu" value="Ir al Menú" onclick="location.href='index.jsp'">
        <!--<fieldset class="formulario">-->
        <div class="contenedorFormulario">
            <!--<legend class="leg">DATOS DE LA ESTACION</legend>-->            
            <legend><h2>Datos de la estacion</h2></legend>
            <p style="font-size: 20px;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <!--<form method="get" action="AdministrarEstacion" name="formulario">-->
            <form>
                <!--<select name="select_linea" id="select_linea">-->
                <label class="tituloFormulario">Linea </label> 
                <select id="cmb_lineas" class="campoFormulario">
                    <option value="">Seleccione La Linea</option>
                    <c:forEach var="lin" items="${gl.listaLinea()}">
                        <option value="${lin.idLinea}">${lin.nombreLinea}</option>
                    </c:forEach>
                </select>
                <!--<input type="text" name="id_nombre_estacion" class="label_better" data-new-placeholder="Nombre de la Estacion" placeholder="Nombre de la Estacion" required>-->
                <!--<input type="text" id="id_nombre_estacion" class="label_better" data-new-placeholder="Nombre de la Estacion" placeholder="Nombre de la Estacion" required>-->
                <label class="tituloFormulario">Nombre de la estacion </label> 
                <input type="text" id="id_nombre_estacion" class="campoFormulario" placeholder="Nombre de la Estacion" required>
                <!--<input type="text" name="pk_estacion" class="label_better" data-new-placeholder="Punto Kilometrico de la Estacion" placeholder="Punto Kilometrico de la Estacion" required>-->
                <!--<input type="text" id="pk_estacion" class="label_better" data-new-placeholder="Punto Kilometrico de la Estacion" placeholder="Punto Kilometrico de la Estacion" required>-->
                <label class="tituloFormulario">Ubicacion de la estacion </label> 
                <!--<input type="text" id="pk_estacion" class="campoFormulario" data-new-placeholder="Punto Kilometrico de la Estacion" placeholder="Punto Kilometrico de la Estacion" required>-->
                <input type="number" id="pk_estacion" class="campoFormulario" placeholder="Punto Kilometrico de la Estacion" required min="0">
                <!--<input type="submit" name="accion" value="Agregar">-->
                <div class="contenedorBoton">
                <input type="button" id="agregarEstacion" value="Agregar" class="botonContinuar">
                </div>
            </form>
            </div>
        <!--</fieldset>-->
        <div class="contenedorFormulario" id="msj">
            <!--<p><image class="cargando" src="img/ajax-loader.gif"/></p>-->
        </div>
        <div class="contenedorFormulario" id="data"></div>
<!--        <div class="contenedorFormulario">-->
            
            <div id="tablaEstaciones" class="contenedorFormulario">
                <p><image class="cargando" src="img/ajax-loader.gif"/></p>
            </div> 
        <!--</div>-->


        <footer><%@include file="footer.jsp" %></footer>
    </body>

</html>
