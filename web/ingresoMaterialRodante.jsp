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
       
        <script type="text/javascript" src="js/administrarMaterialRodante.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>S.I.P.T.R.A.F</title>
    </head>
    <body >
        <div id="bgVentanaModal">
            <div id="msjajax"><p class="cargando"><image class="cargando" src="img/ajax-loader.gif"/></p></div>
            <div id="datos">                
            </div>            
        </div>
        <header>
            <p class="titulo">Administrar Material Rodante</p>
        </header>
<input type="button" class="btnIrAlMenu" value="Ir al Menú" onclick="location.href='index.jsp'">
        <div class="contenedorFormulario" id="formSeg">
            <legend><h2>Datos del material rodante</h2></legend>
            <form method="get" action="AdministrarMaterialRodante" name="formulario">
                <div class="columna1">
                    <label class="tituloFormulario">Nombre</label>
                    <input class="campoFormulario" type="text" id="nombre" placeholder="Nombre" required>
                    <label class="tituloFormulario">Tipo</label>
                    <input class="campoFormulario" type="text" id="tipo"  placeholder="Tipo" required >
                    <label class="tituloFormulario">Sub-Tipo</label>
                    <select class="campoFormulario" id="sub_tipo" required>
                        <option value="Tren de Viajeros">Tren de Viajeros</option>
                        <option value="Tren de Mercarcias">Tren de Mercancias</option>
                        <option value="Locomotora">Locomotora</option>
                    </select>
                    <label class="tituloFormulario">N° de vagones</label>
                    <input class="campoFormulario" type="text" id="numero_vagones"  placeholder="Numero de Vagones" required>
                    <label class="tituloFormulario">Capacidad de pasajeros</label>
                    <input class="campoFormulario" type="text" id="capacidad_pasajeros" placeholder="Capacidad de Pasajeros" required>
                    <label class="tituloFormulario">Kilometraje</label>
                    <input class="campoFormulario" type="text" id="kilometraje" required>
                    <label class="tituloFormulario">Largo</label>
                    <input class="campoFormulario" type="text" id="largo" placeholder="Largo (m)" required>
                </div>
                <div class="columna2">
                    <label class="tituloFormulario">Alto</label>
                    <input class="campoFormulario" type="text" id="alto"  placeholder="Alto (m)" required>
                    <label class="tituloFormulario">Ancho</label>
                    <input class="campoFormulario" type="text" id="ancho" placeholder="Ancho (m)" required>
                    <label class="tituloFormulario">Velocidad de diseño (m/s)</label>
                    <input class="campoFormulario" type="text" id="velocidad_diseño" placeholder="Velocidad de Diseño (m/s)" step="0.1" required>
                    <label class="tituloFormulario">Velocidad de operacion (m/s)</label>
                    <input class="campoFormulario" type="text" id="velocidad_operacion" placeholder="Velocidad de Operacion (m/s)" required>
                    <label class="tituloFormulario">Peso (tn)</label>
                    <input class="campoFormulario" type="text" id="masa" placeholder="Peso (toneladas)" required>                 
                    <label class="tituloFormulario">Aceleración máxima</label>
                    <input class="campoFormulario" type="text" id="aceleracion_maxima" placeholder="Aceleracion Maxima (m/s^2)" required>
                    <label class="tituloFormulario">Desaceleración máxima</label>
                    <input class="campoFormulario" type="text" id="desaceleracion_maxima"  placeholder="Desaceleracion Maxima(m/s^2)" required>
                </div>
                <div class="contenedorBoton">
                    <input class="botonContinuar" type="button" id="agregar" value="Agregar" >
                </div>
            </form>
        </div>
        <div class="contenedorFormulario" id="msj">
            <p class="cargando"><image class="cargando" src="img/ajax-loader.gif"/></p>
        </div>
        <div class="contenedorFormulario" id="data"></div>

        <div class="contenedorFormulario" id="marcoMR"></div>
    <!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>
</html>
