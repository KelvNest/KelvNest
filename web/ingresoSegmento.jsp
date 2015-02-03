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
        <!--<script type="text/javascript" src="js/funciones.js"></script>-->
        <script type="text/javascript" src="js/administrarSegmento.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link href="css/estilo_lb.css" type="text/css" rel="stylesheet">-->
        <!--<link href="css/estilo.css" type="text/css" rel="stylesheet">-->
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
            <p class="titulo">Administrar Segmentos</p>
        </header>
<input type="button" class="btnIrAlMenu" value="Ir al Menú" onclick="location.href='index.jsp'">
<main>
    <div class="contenedorFormulario" id="formSeg">
            <legend><h2>Datos del segmento</h2></legend>
            <p style="font-size: 16pt;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form>
                <div class="columna1">
                    <label class="tituloFormulario">Linea:</label>
                    <select class="campoFormulario" id="cmb_lineas">
                        <option value="">Seleccione la Linea</option>
                        <c:forEach var="b" items="${gl.listaLinea()}">
                            <option value="${b.idLinea}" required>${b.nombreLinea}</option>
                        </c:forEach>
                    </select>
                    <label class="tituloFormulario" >Tipo: </label>
                    <div class="grp_rbt campoFormulario">
                        <label class="tituloFormulario">Recta </label>
                        <input class="campoFormulario" type="radio" id="tipo_segmento" name="tipo_segmento" value="true"  required>               
                        <label class="tituloFormulario">Curva </label>
                        <input class="campoFormulario" type="radio" id="tipo_segmento" name="tipo_segmento" value="false" required>
                    </div>
                    <label class="tituloFormulario" for="radio">Radio de Curvatura </label>
                    <input class="campoFormulario" type="text" id="radio" name="radio" placeholder="Radio" >
                    <label class="tituloFormulario" for="pk_inicio">Progresiva Inicial </label>
                    <input class="campoFormulario" type="text" id="pk_inicio" name="pk_inicio" placeholder="Progresiva Inicial" required>
                    <label class="tituloFormulario" for="pk_final">Progresiva Final </label>
                    <input class="campoFormulario" type="text" id="pk_final" name="pk_final" placeholder="Progresiva Final" required>
                </div>
                <div class="columna2">
                    <label class="tituloFormulario" for="gradiente">Gradiente </label>
                    <input class="campoFormulario" type="text" id="gradiente" name="gradiente" placeholder="Gradiente" required >
                    <label class="tituloFormulario" for="velocidad_max_ascendente">Velocidad máxima ascendente </label>
                    <input class="campoFormulario" type="text" id="velocidad_max_ascendente" name="velocidad_max_ascendente" placeholder="Velocidad Maxima Ascendente" required>
                    <label class="tituloFormulario">Velocidad máxima descendente</label>
                    <input class="campoFormulario" type="text" id="velocidad_max_descendente" placeholder="Velocidad Maxima Descendente" required>
                    <label class="tituloFormulario">Tunel</label>
                    <div class="grp_rbt">
                        <label for="si">Si</label>
                        <input class="campoFormulario" type="radio" id="tunel" name="tunel" value="true" id="si">
                        <label for="no">No</label> 
                        <input class="campoFormulario" type="radio" id="tunel" name="tunel" value="false" id="no">
                    </div>
                </div>
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" onclick="agregar()" value="Agregar" >
                </div>               
            </form>
        </div>

        <div class="contenedorFormulario" id="msj"></div>
        <div class="contenedorFormulario" id="data"></div>

        <!--<div class="contenedorFormulario" id="tablaSegmentos">-->          
        <div id="tablaSegmentos">          
        </div>
</main>
        

<!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>

</html>
