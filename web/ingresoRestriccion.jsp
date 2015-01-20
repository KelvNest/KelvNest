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
        <!--<script type="text/javascript" src="js/funciones.js"></script>-->
        <script type="text/javascript" src="js/administrarRestriccion.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_lb.css" type="text/css" rel="stylesheet">
        <title>S.I.P.T.R.A.F</title>
      <jsp:useBean class="modelo.GestorLista" id="gl"/>
    </head>
    <body >
        <div id="bgVentanaModal" class="dialogoModal">
            <div id="msjajax"><p class="cargando"><image class="cargando" src="img/ajax-loader.gif"/></p></div>
            <div id="datos">                
            </div>
             </div>
        <header>
            <p class="titulo">Administrar Restricciones</p>
        </header>       
                   
<!--        <fieldset class="formulario">
            <legend class="leg">DATOS DE LA RESTRICCION</legend>
            <form method="get" action="AdministrarRestriccion" name="formulario">
                <select  name="select_linea" value="${lin.idLinea}">
                     <option name="select_linea" value="">Seleccione La Linea</option>
                     <%--<c:forEach var="lin" items="${gl.listaLinea()}">--%>
                    <option name="select_linea" value="${lin.idLinea}">${lin.nombreLinea}</option>
                    <%--</c:forEach>--%>
                </select>
                <input type="text" name="usuario" class="label_better" data-new-placeholder="Usuario" placeholder="Usuario" required>
                <input type="text" name="prog_inicio" class="label_better" data-new-placeholder="Progresiva Inicial" placeholder="Progresiva Inicial" required>
                <input type="text" name="prog_final" class="label_better" data-new-placeholder="Progresiva Final" placeholder="Progresiva Final" required>
                <input type="text" name="vel_max_ascendente" class="label_better" data-new-placeholder="Velocidad Max Ascendente" placeholder="Velocidad Max Ascendente" required>
                <input type="text" name="vel_max_descendente" class="label_better" data-new-placeholder="Velocidad Max Descendente" placeholder="Velocidad Max Descendente" required>
                <input type="submit" name="accion" value="Agregar">
            </form>
        </fieldset>-->
                    <div class="contenedorFormulario">
                        <legend><h2>Datos de la restricción</h2></legend>
            <form>
                <label class="tituloFormulario">Linea</label>
                <select  id="cmb_lineas" class="campoFormulario">
                     <option value="">Seleccione La Linea</option>
                     <c:forEach var="lin" items="${gl.listaLinea()}">
                    <option value="${lin.idLinea}">${lin.nombreLinea}</option>
                    </c:forEach>
                </select>
                <label class="tituloFormulario">Usuario</label>
                <input type="text" id="usuario" name="usuario" class="campoFormulario"  placeholder="Usuario" required>
                <label class="tituloFormulario">Progresiva inicial</label>
                <input type="text" id="prog_inicio" name="prog_inicio" class="campoFormulario" placeholder="Progresiva Inicial" required>
                <label class="tituloFormulario">Progresiva final</label>
                <input type="text" id="prog_final" name="prog_final" class="campoFormulario" placeholder="Progresiva Final" required>
                <label class="tituloFormulario">Velocidad maxima ascendente</label>
                <input type="text" id="vel_max_ascendente" name="vel_max_ascendente" class="campoFormulario" placeholder="Velocidad Max Ascendente" required>
                <label class="tituloFormulario">Velocidad maxima descendente</label>
                <input type="text" id="vel_max_descendente" name="vel_max_descendente" class="campoFormulario" placeholder="Velocidad Max Descendente" required>
                <div class="contenedorBoton">
                    <input type="button" id="agregar" value="Agregar" class="botonContinuar" >
                </div>                
            </form>
                    </div>
            
        
        <div class="contenedorFormulario" id="msj"></div>
        <div class="contenedorFormulario" id="data"></div>


            <div id="marcoRestricciones">
       
            </div>
        <footer><%@include file="footer.jsp" %></footer>
    </body>
    
</html>
