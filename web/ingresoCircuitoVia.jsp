<%-- 
    Document   : ingresoCircuitoVia
    Created on : 05/01/2015, 10:12:11 AM
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilo.jsp" %>
<%@include file="jslt.jsp" %>
<%@include file="labelbetter.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/administrarCircuitoVia.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_lb.css" type="text/css" rel="stylesheet">
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
        <div class="contenedorFormulario">
            
            <legend><h2>Datos del Circuito de Via</h2></legend>
            <p style="font-size: 20;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form>
                <label class="tituloFormulario">Linea</label>
                <select class="campoFormulario" id="cmb_lineas">
                    <option value="">Seleccione la Linea</option>
                    <c:forEach var="b" items="${gl.listaLinea()}">
                        <option  name="select_linea" value="${b.idLinea}">${b.nombreLinea}</option>
                    </c:forEach>
                </select>
                <label class="tituloFormulario">Progresiva inicial</label>
                <input type="text" name="id_pk_inicial_circuito" class="campoFormulario" placeholder="Progresiva Inicial(m)" required>
                <label class="tituloFormulario">Progresiva final</label>
                <input type="text" name="pk_final_circuito" class="campoFormulario" placeholder="Progresiva Final(m)" required>
                <div class="contenedorBoton">
                    <input type="button" id="accion" value="Agregar">
                </div>
            </form>
       
        </div>
         <div class="contenedorFormulario" id="msj">
            <p><image class="cargando" src="img/ajax-loader.gif"/></p>
        </div>
        <div class="contenedorFormulario" id="data"></div>
        
        <div class="contenedorFormulario" id="marcoCV">
            
        </div>
        
        <!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>

</html>
