/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.CurvaEsfuerzoJpaController;
import controlador.EstacionJpaController;
import controlador.MaterialRodanteJpaController;
import controlador.RestriccionJpaController;
import controlador.SegmentoJpaController;
import entity.CurvaEsfuerzo;
import entity.Estacion;
import entity.Restriccion;
import entity.Segmento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CalculoMarchaTipo;
import modelo.Conex;

/**
 *
 * @author usuario
 */
@WebServlet(name = "MarchaTipo", urlPatterns = {"/MarchaTipo"})
public class MarchaTipo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MarchaTipo</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet MarchaTipo at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        simularMarchaTipo(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void simularMarchaTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         PrintWriter salida= response.getWriter();
         salida.print("intentando hacer algo");
        MaterialRodanteJpaController mrjc = new MaterialRodanteJpaController(Conex.getEmf());
    CurvaEsfuerzoJpaController cejc = new CurvaEsfuerzoJpaController(Conex.getEmf());
    SegmentoJpaController sjc = new SegmentoJpaController(Conex.getEmf());
    RestriccionJpaController rjc = new RestriccionJpaController(Conex.getEmf());
    EstacionJpaController ejc = new EstacionJpaController(Conex.getEmf());
    int idMaterialRodante=Integer.parseInt(request.getParameter("materialRodante"));
    double velocidadMarcha=Double.parseDouble(request.getParameter("velocidad"));
    int idLinea=Integer.parseInt(request.getParameter("idLinea"));
    boolean sentido= Boolean.parseBoolean(request.getParameter("sentido"));
    double progEstInicio=Double.parseDouble(request.getParameter("progInicial"));
    double progEstFinal=Double.parseDouble(request.getParameter("progFinal"));
        System.out.println(sentido);
    List<CurvaEsfuerzo> ce=cejc.curvaDelMaterialRodante(idMaterialRodante);
    List<Segmento> segmento;
    List<Estacion> estaciones;
    List<Restriccion> restricciones=null;
//    String restriccion=request.getParameter("restricciones");
    
    
    if(sentido==true){
       segmento=sjc.buscarIdLineaAscendente(idLinea);
       estaciones=ejc.buscarEstacionesMTAsc(progEstInicio, progEstFinal);
    }else{
        segmento=sjc.buscarIdLineaDescendente(idLinea);
        estaciones=ejc.buscarEstacionesMTDesc(progEstInicio, progEstFinal);
    }
    
    
    
  
        CalculoMarchaTipo cmt=new CalculoMarchaTipo(segmento, sentido, estaciones, idLinea, idMaterialRodante, velocidadMarcha, restricciones);
        double SGL = cmt.getTiempo();

        double H;
        double M;
        double S;

        H = SGL / 3600;
        
        M = SGL % 3600 /60;
        
        S = SGL % 3600 %60;
        
//                request.setAttribute("mensaje","Tiempo total: "+ M+ " minutos \n"+ S+" segundos\n Velocidad: "+velocidadMarcha+" Simulacion Finalizada");
//            RequestDispatcher rd= request.getRequestDispatcher("marchaTipo.jsp");
//            rd.forward(request, response);
       
        salida.print("Tiempo total: "+ M+ " minutos \n"+ S+" segundos\n Velocidad: "+velocidadMarcha+" Simulacion Finalizada");
    }

}