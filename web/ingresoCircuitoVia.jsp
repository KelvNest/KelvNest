<%-- 
    Document   : ingresoCircuitoVia
    Created on : 05/01/2015, 10:12:11 AM
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilo.jsp" %>
<%@include file="jslt.jsp" %>
<%--<%@include file="labelbetter.jsp" %>--%>


<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/administrarCircuitoVia.js"></script>
        <title>S.I.P.T.R.A.F</title>
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
    </head>
    <body>
        <div id="bgVentanaModal">
            <div id="msjajax"></div>
            <div id="datos">                
            </div>
        </div>
        <header>
            <p class="titulo">Administrar Circuitos de Via</p>
        </header>
        <input type="button" class="btnIrAlMenu" value="Ir al Menú" onclick="location.href='index.jsp'">
        <div class="contenedorFormulario">
            
            <legend><h2>Datos del Circuito de Via</h2></legend>
            <p style="font-size: 20px;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form>
                <label class="tituloFormulario">Linea</label>
                <select class="campoFormulario" id="cmb_lineas">
                    <option value="">Seleccione la Linea</option>
                    <c:forEach var="b" items="${gl.listaLinea()}">
                        <option value="${b.idLinea}">${b.nombreLinea}</option>
                    </c:forEach>
                </select>
                <label class="tituloFormulario">Progresiva inicial</label>
                <input type="text" id="prog_ini_cir" class="campoFormulario" placeholder="Progresiva Inicial(m)" required>
                <label class="tituloFormulario">Progresiva final</label>
                <input type="text" id="prog_fin_cir" class="campoFormulario" placeholder="Progresiva Final(m)" required>
                <div class="contenedorBoton">
                    <input type="button" class="botonContinuar" id="btn_agr_cir_via" value="Agregar">
                </div>
            </form>
       
        </div>
         <div class="contenedorFormulario" id="msj">
            <p><image class="cargando" src="img/ajax-loader.gif"/></p>
        </div>
               
        <div class="contenedorFormulario" id="marcoCV">
            
        </div>
        
        <!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>

</html>
