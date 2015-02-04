<%-- 
    Document   : index
    Created on : 03/01/2015, 07:39:14 PM
    Author     : seront
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilo.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery-1.9.1.js"></script>
        <script src="js/marchaTipo.js"></script>
        <script type="text/javascript" src="js/highcharts.js"></script>
        <script type="text/javascript" src="js/exporting.js"></script>
        <script type="text/javascript" src="js/graficos.js"></script>
        <%@include file="jslt.jsp" %>
        <title>SIPTRAF</title>
    </head>

    <header>
        <%--<%@include file="encabezado.jsp" %>--%>
        <p class="titulo">Realizar Marcha tipo</p>
    </header>
<!--    <input type="button" class="btnIrAlMenu" value="Ir al Menú" onclick="location.href = 'index.jsp'">-->
    <main> 
        <div style="height: 50px">
             <input type="button" id="btnMenu" class="btnIrAlMenu" value="Ir al Menú" onclick="location.href = 'index.jsp'">
        </div>
       
        <div class="contDMT">
        <!--<div class="contenedorFormulario" id="DMT_peque">-->
        <div class="contenedorFormulario columna1" id="contMarTip">
            <legend> <h2>Datos para la marcha tipo</h2></legend>
            <form >
                <label class="tituloFormulario">Velocidad de la marcha tipo</label> 
                <input class="campoFormulario" type="number" id="velocidad" min="0">
                <label class="tituloFormulario">Linea </label> 
                <select id="cmb_lineas" class="campoFormulario" >
                    <option value=""> Seleccione...</option>
                    <jsp:useBean class="modelo.GestorLista" id="gl"/>
                    <c:set var="lineas" value="${gl.listaLinea()}"/>
                    <c:forEach var="linea" items="${lineas}">                        
                        <option value="${linea.idLinea}"> ${linea.idLinea}-${linea.nombreLinea}</option>                        
                    </c:forEach>
                </select>
                <label class="tituloFormulario">Material rodante </label> 
                <select id="cmb_materiales" class="campoFormulario">
                    <option value=""> Seleccione...</option>

                    <c:set var="materiales" value="${gl.listaMaterialRodante()}"/>
                    <c:forEach var="material" items="${materiales}">                       
                        <option value="${material.idMaterialRodante}"> ${material.idMaterialRodante}-${material.nombreMaterialRodante}</option>                        
                    </c:forEach>
                </select> 
                <!--<p  class="cargando"><img id="car1" src="img/ajax-loader.gif"/></p>-->
                <p><img class="cargando" id="car1" src="img/ajax-loader.gif"/></p>
                <div  id="estaciones">
                    <label class="tituloFormulario">Estacion inicial </label> 
                    <select id="cmb_est_inicio" class="campoFormulario">
                        <option value=""> Seleccione...</option>
                    </select>
                    <label class="tituloFormulario">Estacion final </label> 
                    <select id="cmb_est_final" class="campoFormulario">
                        <option value=""> Seleccione...</option>   
                    </select>
                </div>
                <div class="contenedorBoton">
                    <input type="button" id="continuar" class="botonContinuar" value="Continuar"/> 
                </div>
            </form>

        </div>
        <!--<div id="marcoRestricciones">--> 
        <div class="contenedorFormulario marcoRestricciones" id="rmt">
            <p  class="cargando"><img id="car2" src="img/ajax-loader.gif"/></p>
            <!--<div class="columna2" id="marcoRestricciones">-->            
            <!--<div class="columna2" id="marcoRestricciones">-->            
            <div class="columna2"  id="contRestricciones">            

            </div>
        </div>

    </div>

    <div id="resultadoMarchaTipo" class="contenedorFormulario">
        <p  class="cargando"><img id="car3" src="img/ajax-loader.gif"/></p>
        <div id="msj">                           
            <!--<p><image class="cargando" src="img/ajax-loader.gif"/></p>-->

        </div>
    </div>
    <div id="graficoMarchaTipo">

        <p><img class="cargando" src="img/ajax-loader.gif"></p>
    </div>
    </main>
    
<!--    <footer>
        <%--<%@include file="footer.jsp"%>--%>
    </footer>-->

</html>
