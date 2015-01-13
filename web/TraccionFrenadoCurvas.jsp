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
<%--<%@include file="lineaGrafico.jsp" %>--%>
 <script type="text/javascript">
     
	$(document).ready(function() {
                   
        
//        for(i=0; i<=10; i++){
//                x[i]=1+i;    
//                    
//                }
var arr=[103,474,402,536,1041,270,0,160,2462,3797,3527,4505,8090,7493,7048,11408,10886];
// arr[0]=103;
// arr[1]=474;
// arr[2]=402;
// arr[3]=536;
		var chart = new Highcharts.Chart({
			chart: {
				renderTo: 'graficaLineal', 	// Le doy el nombre a la gráfica
				defaultSeriesType: 'line'	// Pongo que tipo de gráfica es
			},
			title: {
				text: 'Grafica de Traccion y Frenado'	// Titulo (Opcional)
			},
			subtitle: {
				text: 'S.I.P.T.R.A.F'		// Subtitulo (Opcional)
			},
			// Pongo los datos en el eje de las 'X'
			xAxis: {
				categories: ['1']
                                
,
				// Pongo el título para el eje de las 'X'
				title: {
					text: 'Velocidad'
				}
			},
			yAxis: {
				// Pongo el título para el eje de las 'Y'
				title: {
					text: 'Esfuerzo'
				}
			},
			// Doy formato al la "cajita" que sale al pasar el ratón por encima de la gráfica
			tooltip: {
				enabled: true,
				formatter: function() {
					return '<b>'+ this.series.name +'</b><br/>'+
						this.x +': '+ this.y +' '+this.series.name;
				}
			},
			// Doy opciones a la gráfica
			plotOptions: {
				line: {
					dataLabels: {
						enabled: true
					},
					enableMouseTracking: true
				}
			},
			// Doy los datos de la gráfica para dibujarlas
			series: [{
		                name: 'Visitas',
//		                data: [103,474,402,536,1041,270,0,160,2462,3797,3527,4505,8090,7493,7048,11408,10886]
		                data: arr
		            },
		            {
		                name: 'Visitantes Únicos',
		                data: [21,278,203,370,810,213,0,134,1991,3122,2870,3655,6400,5818,5581,8529,8261]
		            },
		            {
		                name: 'Páginas Vistas',
		                data: [1064,1648,1040,1076,2012,397,0,325,3732,6067,5226,6482,11503,11937,9751,16061,15643]
		            }],
		});	
	});	
        </script>
    </head>
    <body >
        <header>
            <h1 class="titulo" >Curvas de Traccion y Frenado</h1>
        </header>
        <form action="AdministrarCurvas" method="get" style="margin-top: 90px; margin-left: 70px">
            Velocidad: <input type="text" name="velocidad_traccion"  />
            Esfuerzo Tractivo: <input type="text" name="esfuerzo_traccion"/>
            Esfuerzo Frenado: <input type="text" name="esfuerzo_frenado"/>
            Material Rodante: <select name="material_rodante">
                    <option value="">Seleccione el Material Rodante</option>
                    <c:forEach var="b" items="${gl.listaMaterialRodante()}">
                        <option name="select_mr" value="${b.idMaterialRodante}" onchange="">${b.nombreMaterialRodante}</option>
                    </c:forEach>
                </select>
            <input type="submit" name="accion" value="Agregar">
            <input type="submit" name="accion" value="Actualizar">
            
            
        </form>
       
        <table class="tabla_graficos_tyf">
            <tr>
                <th>Velocidad</th>
                <th>Esfuerzo Tractivo</th>
                <th>Esfuerzo Frenado</th>
                
            </tr>
           
            <c:forEach var="a" items="${gl.listaEsfuerzos(01)}">
            <tr>
                <td> ${a.curvaEsfuerzoPK.idVelocidadCurvaEsfuerzo}</td>
                <td>${a.esfuerzoTraccion}</td>
                <td>${a.esfuerzoFrenado}</td>
              
                   
                 </tr>
        </c:forEach>
         </table>
       
        <div id="graficaLineal" style="width: 65%; height: 500px; ">
</div>
    </body>
</html>
