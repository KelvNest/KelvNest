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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_lb.css" type="text/css" rel="stylesheet">
        <script src="js/administrarLinea.js" type="text/javascript"></script>
        <title>S.I.P.T.R.A.F</title>
    </head>
    <body >
        <div id="bgVentanaModal">
            <div id="msjajax">                
            </div>
            <div id="datos">                
            </div>            
        </div>
        <header>
            <p class="titulo">Administrar Lineas</p>
        </header>


        <!--        <fieldset class="formulario">
                    <legend class="leg">DATOS DE LA LINEA</legend>
                    <p style="font-size: 20;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
                    <form method="get" action="AdministrarLinea" name="formulario">
                        <input type="text" name="nombre_linea" class="label_better"  placeholder="Nombre" required>
                        <input type="text" name="pk_inicial" class="label_better"  placeholder="Punto Kilométrico Inicial" required>
                        <input type="text" name="pk_final" class="label_better"  placeholder="Punto Kilométrico final" required>
                        <input type="text" name="trocha" class="label_better"  placeholder="Trocha" required>
                        <input type="submit" name="accion" value="Agregar"/>
                    </form>
                </fieldset>-->
        <div class="contenedorFormulario">
            <legend><h2>Datos de la linea</h2></legend>
            <p style="font-size: 16pt;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form>
                <label class="tituloFormulario">Nombre de la linea</label>
                <input type="text" id="nombre_linea" class="campoFormulario"  placeholder="Nombre" required>
                <label class="tituloFormulario">Progresiva Inicial</label>
                <input type="number" id="pk_inicial" class="campoFormulario"  placeholder="Punto Kilométrico Inicial" required>
                <label class="tituloFormulario">Progresiva Final</label>
                <input type="number" id="pk_final" class="campoFormulario"  placeholder="Punto Kilométrico final" required>
                <label class="tituloFormulario">Trocha</label>
                <input type="number" id="trocha" class="campoFormulario"  placeholder="Trocha" required>
                <!--<input type="submit" name="accion" value="Agregar"/>-->
                <div class="contenedorBoton">
                    <!--<input class="botonContinuar" type="submit" id="btnAgregar" name="accion" value="Agregar"/>-->
                    <input class="botonContinuar" type="button" id="btnAgregar" name="accion" value="Agregar"/>
                </div>
            </form>
        </div>

        <div class="contenedor_tabla" id="msj"></div>
        <div class="contenedor_tabla" id="data"></div>

        <div class="contenedorFormulario" id="tablaLineas">            
        </div>
        

      <!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>

</html>
