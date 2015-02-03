/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import modelo.entity.CurvaEsfuerzo;
import modelo.entity.Estacion;
import modelo.entity.Restriccion;
import modelo.entity.RestriccionPK;
import modelo.entity.Segmento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CalculoMarchaTipo;
import modelo.Conex;
import modelo.controlBD.CurvaEsfuerzoJpaController;
import modelo.controlBD.EstacionJpaController;
import modelo.controlBD.MaterialRodanteJpaController;
import modelo.controlBD.RestriccionJpaController;
import modelo.controlBD.SegmentoJpaController;

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
        System.out.println("Entrando en el metodo de simular la marcha");
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
        System.out.println("do get Entrando en el metodo de simular la marcha");
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
        System.out.println("doPost Entrando en el metodo de simular la marcha");
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
         
        MaterialRodanteJpaController mrjc = new MaterialRodanteJpaController(Conex.getEmf());
    CurvaEsfuerzoJpaController cejc = new CurvaEsfuerzoJpaController(Conex.getEmf());
    SegmentoJpaController sjc = new SegmentoJpaController(Conex.getEmf());
    RestriccionJpaController rjc = new RestriccionJpaController(Conex.getEmf());
    EstacionJpaController ejc = new EstacionJpaController(Conex.getEmf());
    int idMaterialRodante=Integer.parseInt(request.getParameter("materialRodante"));
    double velocidadMarcha=Double.parseDouble(request.getParameter("vel"));
    int idLinea=Integer.parseInt(request.getParameter("idLinea"));
//    boolean sentido= Boolean.parseBoolean(request.getParameter("sentido"));
    //List<Restriccion> restricciones=null;
    boolean sentido;
    double progEstInicio=Double.parseDouble(request.getParameter("progInicial"));
    double progEstFinal=Double.parseDouble(request.getParameter("progFinal"));
    if(progEstInicio>progEstFinal){
        sentido=false;
        //restricciones=rjc.restriccionEntreEstacionesDescendente(idLinea, progEstInicio, progEstFinal, velocidadMarcha);
    }else{
        //restricciones=rjc.restriccionEntreEstacionesAscendente(idLinea, progEstInicio, progEstFinal, velocidadMarcha);
    sentido=true;
    }
        System.out.println(sentido);
    List<CurvaEsfuerzo> ce=cejc.curvaDelMaterialRodante(idMaterialRodante);
    List<Segmento> segmento;
    List<Estacion> estaciones;
    List<Restriccion> restriccionesMarchaTipo=new ArrayList<>();
    String restriccion=request.getParameter("restricciones");
     String[] idRestricciones;
    
        
        //System.out.println(Arrays.toString(idRestricciones));
    if(!restriccion.equals("")){
        
    idRestricciones=restriccion.split(" ");
        for (int i = 0; i < idRestricciones.length; i++) {
           RestriccionPK rpk=new RestriccionPK(idLinea, Integer.parseInt(idRestricciones[i]));
           restriccionesMarchaTipo.add( rjc.findRestriccion(rpk));
            
            
        }
    }else{
        restriccionesMarchaTipo=null;
    }
//    List<Restriccion> restriccion=
//        System.out.println(restriccionesMarchaTipo.size());
  //      System.out.println(restriccionesMarchaTipo.get(0).getProgInicio());
//        System.out.println(restriccionesMarchaTipo.get(1).getProgInicio());
        //System.out.println(restriccionesMarchaTipo.get(2).getProgInicio());
       
        
    
    if(sentido==true){
       segmento=sjc.buscarIdLineaAscendente(idLinea);
       estaciones=ejc.buscarEstacionesMTAsc(progEstInicio, progEstFinal);
    }else{
        segmento=sjc.buscarIdLineaDescendente(idLinea);
        estaciones=ejc.buscarEstacionesMTDesc(progEstInicio, progEstFinal);
    }
    
    
    
  
        CalculoMarchaTipo cmt=new CalculoMarchaTipo(segmento, sentido, estaciones, idLinea, idMaterialRodante, velocidadMarcha, restriccionesMarchaTipo);
        double totalEnSegundos = cmt.getTiempo();

        int horas;
        int minutos;
        int segundos;
        horas= (int)(totalEnSegundos/ 3600);
  
        minutos =(int) (totalEnSegundos % 3600 /60);
       
        segundos =(int)(totalEnSegundos % 3600 %60);
        
//                request.setAttribute("mensaje","Tiempo total: "+ M+ " minutos \n"+ S+" segundos\n Velocidad: "+velocidadMarcha+" Simulacion Finalizada");
//            RequestDispatcher rd= request.getRequestDispatcher("marchaTipo.jsp");
//            rd.forward(request, response);
        try (PrintWriter out = response.getWriter()) {
     out.println("Tiempo total: "+horas+" horas "+ minutos+ " minutos \n"+ segundos+" segundos\n Velocidad: "+velocidadMarcha+" Simulacion Finalizada");
            for (int i = 0; i <cmt.getTiempoEstaciones().size(); i++) {
                out.println("<div>");
                out.println("Estacion "+estaciones.get(i).getEstacionPK().getIdNombreEstacion()+" Tiempo: "+(Math.rint(cmt.getTiempoEstaciones().get(i)*100)/100)+" segundos");   
                out.println("</div>");
            }
        out.println("<script>");
        out.println("var arr1="+cmt.getCambiosProgresiva());
        out.println("var arr2="+cmt.getCambiosVelocidad());
        //out.println("var arr3="+cmt.getTiempoEstaciones());
        out.println("</script>");
        
        //out.print(vel+":"+esf);
        }
        
        }

}
