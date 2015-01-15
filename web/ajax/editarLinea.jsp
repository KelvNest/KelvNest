<%@page import="modelo.GestorLista"%>
<%--<%@include file="../css/estilo.css" %>--%>
<%@include file="../jslt.jsp" %>
<c:set var="id" value="${param.id}"/>
<c:choose>
    <c:when test="${!empty id}">
        <jsp:useBean class="modelo.GestorLista" id="gl"/>
        <c:set var="lin" value="${gl.buscarLinea(id)}"/>
        <!--  <fieldset class="dialogoModal">
                     <legend class="leg_edit">Editar Linea ${lin.nombreLinea}</legend>
                    <form method="get" action="AdministrarLinea" name="formulario">
                        <input type="hidden" name="id_linea" value="${lin.idLinea}" >
                        <input type="text" name="nombre_linea" value="${lin.nombreLinea}" >
                        Punto Kilométrico Inicial: <input type="text" name="pk_inicial" value="${lin.pkInicial}" >
                        Punto Kilométrico Final: <input type="text" name="pk_final" value="${lin.pkFinal}" >
                        <br/>
                        <br/>
                        Trocha: <input type="text" name="trocha" value="${lin.trocha}" ><br/>
                        <br/>
                        <input type="submit" name="accion" value="Editar" >
                        <input type="button" value="Cancelar" onclick="cancelarLinea()">
                    </form>
                </fieldset>-->
        <div class="contenedorFormulario">
            <legend><h2>Editar Linea ${lin.nombreLinea}</h2></legend>
            <form>
                <input type="hidden" name="id_linea" value="${lin.idLinea}" >
                <label class="tituloFormulario">Nombre de la linea</label>
                <input class="campoFormulario" type="text" name="nombre_linea" value="${lin.nombreLinea}" >
                <label class="tituloFormulario">Punto Kilométrico Inicial</label>
                <input class="campoFormulario" type="number" name="pk_inicial" value="${lin.pkInicial}" >
                <label class="tituloFormulario"> Punto Kilométrico Final</label>
                <input class="campoFormulario" type="number" name="pk_final" value="${lin.pkFinal}" >
                <label class="tituloFormulario">Trocha</label>
                <input class="campoFormulario" type="number" name="trocha" value="${lin.trocha}">
                <div class="contenedorBoton"><input class="botonContinuar" type="button" value="Editar" onclick="editarL('${lin.idLinea}','${lin.nombreLinea}','${lin.pkInicial}','${lin.pkFinal}','${lin.trocha}')"></div>                
                <div class="contenedorBoton"><input class="botonContinuar" type="button" value="Cancelar" onclick="cancelarLinea()"></div>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        Linea no Encontrada
    </c:otherwise>

</c:choose>