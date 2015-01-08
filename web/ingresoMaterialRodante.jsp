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
        <script type="text/javascript" src="js/funciones.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_lb.css" type="text/css" rel="stylesheet">
        <title>S.I.P.T.R.A.F</title>
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
   
        
    </head>
    <body >
        
        <header>
            <p class="titulo">Administrar Material Rodante</p>
        </header>
        
        <div id="bgVentanaModal">
            <div id="msjajax"></div>
            <div id="datos">
                
            </div>
            
        </div>
           
        <fieldset class="formulario">
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
        </fieldset>
        <c:if test="${!empty requestScope.mensaje}">
            <article>${requestScope.mensaje}</article>
        </c:if>
            <div class="contenedor_tabla" id="ingMat">
        <table class="tablas">
            <tr>
            <td>Nombre</td>
            <td>Tipo</td>
            <td>Sub Tipo</td>
            <td>Vagones</td>
            <td>Pasajeros</td>
            <td>Kilometraje</td>
            <td>Peso</td>
            <td>Largo</td>
            <td>Alto</td>
            <td>Ancho</td>
            <td>Vel. de Diseño</td>
            <td>Vel. de Operacion</td>
            <td>Acel. Max</td>
            <td>Desacel. Max</td>
            
                
            </tr>
          
                <c:if test="${gl.listaMaterialRodante()!='null'}">
            <c:forEach var="mr" items="${gl.listaMaterialRodante()}">
                <tr>
               
                    <td> <a href="#" onclick="editarMaterialRodante('${mr.idMaterialRodante}')">${mr.nombreMaterialRodante}</a></td>
                    <td>${mr.tipo}</td>
                    <td>${mr.subTipo}</td>
                    <td>${mr.numeroVagones}</td>
                    <td>${mr.capacidadPasajeros}</td>
                    <td>${mr.kilometraje}</td>
                    <td>${mr.masa}</td>
                    <td>${mr.largo}</td>
                    <td>${mr.altoCara}</td>
                    <td>${mr.anchoCara}</td>
                    <td>${mr.velocidadDiseño}</td>
                    <td>${mr.velocidadOperativa}</td>
                    <td>${mr.aceleracionMax}</td>
                    <td>${mr.desaceleracionMax}</td>
                    <td><a href="#" onclick="eliminarMaterialRodante('${mr.idMaterialRodante}')">X</a></td>
                </tr>
            </c:forEach>
                </c:if>
            
        </table>
            </div>
        <!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>
    
</html>
