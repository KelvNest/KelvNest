<%-- 
Document   : index
Created on : 11/11/2014, 09:30:34 AM
Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jslt.jsp" %>
        <title>S.I.P.T.R.A.F</title>
        <%@include file="estilo.jsp" %>
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/highcharts.js"></script>
        <script type="text/javascript" src="js/exporting.js"></script>
    </head>
    <body >
        <div id="bgVentanaModal">
            <div id="msjajax"></div>
            <div id="datos">                
            </div>
        </div>
        <header>
            <p class="titulo">Curvas de Traccion y Frenado</p>
        </header>
        <div class="contenedorFormulario">            
            <legend><h2>Traccion y frenado del Material Rodante</h2></legend>
            <p style="font-size: 20px;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form>
                <label class="tituloFormulario">Material Rodante</label>
                <select id="cmb_materiales" class="campoFormulario">
                    <option value=""> Seleccione...</option>
                    <c:set var="materiales" value="${gl.listaMaterialRodante()}"/>
                    <c:forEach var="material" items="${materiales}">                       
                        <option value="${material.idMaterialRodante}"> ${material.idMaterialRodante}-${material.nombreMaterialRodante}</option>                        
                    </c:forEach>
                </select> 
                <label class="tituloFormulario">Velocidad(km/h)</label>
                <input type="text" id="txt_vel_mat_rod" class="campoFormulario" placeholder="Velocidad(km/h)" required>
                <label class="tituloFormulario">Esfuerzo Tractivo(kgf)</label>
                <input type="text" id="txt_esf_tra" class="campoFormulario" placeholder="Esfuerzo Tractivo(kgf)" required>
                <label class="tituloFormulario">Esfuerzo de Frenado(kgf)</label>
                <input type="text" id="txt_esf_fre" class="campoFormulario" placeholder="Esfuerzo de Frenado(kgf)" required>
                <div class="contenedorBoton">
                    <input type="button" class="botonContinuar" id="btn_agr_curv" value="Agregar">
                </div>
            </form>
        </div>
                    <div class="contenedorFormulario" id="msj">
                        
                    </div>
        <div class="contenedorFormulario" id="tablaCurvas">
        </div>

        <div id="graficaLineal" style="width: 65%; height: 500px; ">
        </div>
    </body>
</html>
