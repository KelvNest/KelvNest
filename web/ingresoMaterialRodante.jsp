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
        <script type="text/javascript" src="js/administrarMaterialRodante.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link href="css/estilo_lb.css" type="text/css" rel="stylesheet">-->
        <title>S.I.P.T.R.A.F</title>
        <jsp:useBean class="modelo.GestorLista" id="gl"/>


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

        <!--        <fieldset class="formulario">
                    <legend class="leg">DATOS DEL MATERIAL RODANTE</legend>
                    <form method="get" action="AdministrarMaterialRodante" name="formulario">
                         <input type="text" name="nombre" class="label_better"  placeholder="Nombre" required>
                         <input type="text" name="tipo" class="label_better"  placeholder="Tipo" required >
                         <select name="sub_tipo" required>
                             <option value="Tren de Viajeros">Tren de Viajeros</option>
                             <option value="Tren de Mercarcias">Tren de Mercancias</option>
                             <option value="Locomotora">Locomotora</option>
                         </select>
                         <input type="text" name="numero_vagones" class="label_better" data-new-placeholder="Numero de Vagones"  placeholder="Numero de Vagones" required>
                         <input type="text" name="capacidad_pasajeros" class="label_better" data-new-placeholder="Capacidad de Pasajeros"  placeholder="Capacidad de Pasajeros" required>
                         <input type="text" name="kilometraje" class="label_better" data-new-placeholder="Kilometraje (Kilometros)"  placeholder="Kilometraje (Kilometros)" required>
                         <input type="text" name="largo" class="label_better" data-new-placeholder="Largo (metros)"  placeholder="Largo (metros)" required>
                         <input type="text" name="alto" class="label_better" data-new-placeholder="Alto (Kilometros)"  placeholder="Alto (metros)" required>
                         <input type="text" name="ancho" class="label_better" data-new-placeholder="Ancho (metros)"  placeholder="Ancho (metros)" required>
                         <input type="text" name="velocidad_diseño" class="label_better" data-new-placeholder="Velocidad de Diseño (m/s)"  placeholder="Velocidad de Diseño (m/s)" step="0.1" required>
                         <input type="text" name="velocidad_operacion" class="label_better" data-new-placeholder="Velocidad de Operacion (m/s)"  placeholder="Velocidad de Operacion (m/s)" required>
                         <input type="text" name="masa" class="label_better" data-new-placeholder="Peso (toneladas)"  placeholder="Peso (toneladas)" required>                 
                         <input type="text" name="aceleracion_maxima" class="label_better" data-new-placeholder="Aceleracion Maxima(m/s^2)"  placeholder="Aceleracion Maxima (m/s^2)" required>
                         <input type="text" name="desaceleracion_maxima" class="label_better" data-new-placeholder="Desaceleracion Maxima(m/s^2)"  placeholder="Desaceleracion Maxima(m/s^2)" required>
                         <input type="submit" name="accion" value="Agregar" >
                    </form>
                </fieldset>-->
        <div class="contenedorFormulario" id="formSeg">
            <legend><h2>Datos material rodante</h2></legend>
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
