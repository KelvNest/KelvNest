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
            <p class="titulo">Administrar Segmentos</p>
        </header>
        <div id="bgVentanaModal" class="dialogoModal">
            <div id="msjajax"></div>
            <div id="datos">
                
            </div>
            
        </div>
        <fieldset class="formulario">
            <legend class="leg">DATOS DEL SEGMENTO</legend>
            <p style="font-size: 20;">Importante: para ingresar decimales utilice puntos. Ejemplo: 10.5</p>
            <form method="get" action="AdministrarSegmento" name="formulario">
                Linea:<select name="select_linea" value="${b.idLinea}">
                    <option value="">Seleccione la Linea</option>
                    <c:forEach var="b" items="${gl.listaLinea()}">
                        <option name="select_linea" value="${b.idLinea}" required>${b.nombreLinea}</option>
                    </c:forEach>
                </select>
                Tipo: Recta<input type="radio" name="tipo_segmento" value="true"  required>               
                Curva<input type="radio" name="tipo_segmento" value="false" required>               
                <input type="text" name="pk_inicio" class="label_better"  placeholder="Progresiva Inicio" required>
                <input type="text" name="pk_final" class="label_better"  placeholder="Progresiva Final" required>
                <input type="text" name="gradiente" class="label_better" data-new-placeholder="Gradiente" placeholder="Gradiente" required >
                <input type="text" name="radio" class="label_better" data-new-placeholder="Radio" placeholder="Radio" >
                <input type="text" name="velocidad_max_ascendente" class="label_better" data-new-placeholder="Velocidad Maxima Ascendente" placeholder="Velocidad Maxima Ascendente" required>
                <input type="text" name="velocidad_max_descendente" class="label_better" data-new-placeholder="Velocidad Maxima Descendente" placeholder="Velocidad Maxima Descendente" required>
                <br/><label for="tunel">Tunel</label>
                <label for="si">Si</label>
                <input type="radio" name="tunel" value="true" id="si">
                <label for="no">No</label> 
                <input type="radio" name="tunel" value="false" id="no">
                               
                
                <input type="submit" name="accion" value="Agregar" >
                
            </form>
        </fieldset>
         <c:if test="${!empty requestScope.mensaje}">
            <article>${requestScope.mensaje}</article>
        </c:if>
            <div class="contenedor_tabla">
        <table class="tablas">
            <tr>
                <td>Progresiva Inicial</td>
                <td>Progresiva Final</td>
                <td>Curva/Recta</td>
                <td>Gradiente</td>
                <td>Radio</td>
                <td>Velocidad Max. Ascendente</td>
                <td>Velocidad Max. Descendente</td>
                <td>Tunel</td>
            </tr>
            <c:choose>
                <c:when test="${gl.listaSegmento(01)!='null'}">
            <c:forEach var="a" items="${gl.listaSegmento(01)}">
            <tr>
                <td ><a href="#" onclick="editarSegmento('${a.segmentoPK.idLinea}','${a.segmentoPK.idPkInicial}')">${a.segmentoPK.idPkInicial}</a></td>
                    <td>${a.pkFinal}</td>
                    
                    <c:choose>
                          <c:when  test="${a.recta=='true'}">
                               <td>Recta</td>
                          </c:when>
                          <c:otherwise>
                              <td>Curva</td>
                          </c:otherwise>
                      </c:choose>
                    <td>${a.gradiente}</td>
                
                    <td>${a.radioCurvatura}</td>
                    
                    <td>${a.velocidadMaxAscendente}</td>
                    
                    <td>${a.velocidadMaxDescendente}</td>
               
                
                      <c:choose>
                          <c:when  test="${a.tunel=='true'}">
                               <td>Si</td>
                          </c:when>
                          <c:otherwise>
                              <td>No</td>
                          </c:otherwise>
                      </c:choose>
                   <td><a href="#" onclick="eliminarSegmento('${a.segmentoPK.idLinea}','${a.segmentoPK.idPkInicial}')">X</a></td>
                 </tr>
        </c:forEach>
        </c:when>
            </c:choose>
           
         </table>
            </div>
  
        <!--<footer><%@include file="footer.jsp" %></footer>-->
    </body>
    
</html>
