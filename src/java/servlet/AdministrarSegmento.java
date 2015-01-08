/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.LineaJpaController;
import controlador.SegmentoJpaController;
import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import entity.Linea;
import entity.Segmento;
import entity.SegmentoPK;
import modelo.Conex;

/**
 *
 * @author Kelvins Insua
 */
@WebServlet(name = "AdministrarSegmento", urlPatterns = {"/AdministrarSegmento"})
public class AdministrarSegmento extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AgregarSegmento</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AgregarSegmento at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
         String accion= request.getParameter("accion");
        if(accion!=null){
            switch(accion){
                case "Agregar":
                    agregar(request,response);
                    break;
                case "Editar":
                    editar(request,response);
                    break;
                case "Eliminar":
                    eliminar(request,response);
                    break;
                
                
            }
        }
        
        
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdministrarSegmento.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdministrarSegmento.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
        int linea=Integer.parseInt(request.getParameter("select_linea"));
        boolean recta=Boolean.parseBoolean(request.getParameter("tipo_segmento"));
        double pkInicio=Double.parseDouble(request.getParameter("pk_inicio"));
        double pkFinal=Double.parseDouble(request.getParameter("pk_final"));
        double radioCurvatura=Double.parseDouble(request.getParameter("radio"));
        double gradiente=Double.parseDouble(request.getParameter("gradiente"));
         double rampaAscendente;
         double rampaDescendente;
        if(radioCurvatura>0){
            rampaAscendente=gradiente+(600/radioCurvatura);
            rampaDescendente=gradiente-(600/radioCurvatura);
        }else{
        rampaAscendente=gradiente;
        rampaDescendente=-gradiente;
        }
        
        boolean tunel=Boolean.parseBoolean(request.getParameter("tunel"));
        double velocidadMaxAscendente=Double.parseDouble(request.getParameter("velocidad_max_ascendente"));
        double velocidadMaxDescendente=Double.parseDouble(request.getParameter("velocidad_max_descendente"));
        
        
        System.out.println("aaaaaaaaa"+tunel);
        
        Segmento seg=new Segmento();
        seg.setVelocidadMaxAscendente(velocidadMaxAscendente);
        seg.setVelocidadMaxDescendente(velocidadMaxDescendente);
        seg.setRadioCurvatura(radioCurvatura);
        seg.setGradiente(gradiente);
        seg.setPkFinal(pkFinal);
        seg.setRecta(recta);
        seg.setTunel(tunel);
        seg.setRampaAscendente(rampaAscendente);
        seg.setRampaDescendente(rampaDescendente);
        SegmentoPK spk=new SegmentoPK();
        spk.setIdPkInicial(pkInicio);
        spk.setIdLinea(linea);
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        Linea li= ljc.findLinea(linea);
        seg.setSegmentoPK(spk);
        seg.setLinea(li);
        SegmentoJpaController sjc=new SegmentoJpaController(Conex.getEmf());
        
        sjc.create(seg);
         request.setAttribute("mensaje","Segmento con Progresiva Inicial "+seg.getSegmentoPK().getIdPkInicial()+
                    " ha sido creado satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
        
        }catch(PreexistingEntityException e) {
             request.setAttribute("mensaje","El Segmento "+
                    " ya existe");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
        }catch (Exception e) {
                        request.setAttribute("mensaje","ha ocurrido una excepcion: ");
            e.printStackTrace();
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
        int linea=Integer.parseInt(request.getParameter("id_linea"));
        boolean recta=Boolean.parseBoolean(request.getParameter("tipo_segmento"));
        double pkInicio=Double.parseDouble(request.getParameter("pk_inicio"));
        double pkFinal=Double.parseDouble(request.getParameter("pk_final"));
        double radioCurvatura=Double.parseDouble(request.getParameter("radio"));
        double gradiente=Double.parseDouble(request.getParameter("gradiente"));
         double rampaAscendente;
         double rampaDescendente;
        if(radioCurvatura>0){
            rampaAscendente=gradiente+(600/radioCurvatura);
            rampaDescendente=gradiente-(600/radioCurvatura);
        }else{
        rampaAscendente=gradiente;
        rampaDescendente=-gradiente;
        }
        boolean tunel=Boolean.parseBoolean(request.getParameter("tunel"));
        double velocidadMaxAscendente=Double.parseDouble(request.getParameter("velocidad_max_ascendente"));
        double velocidadMaxDescendente=Double.parseDouble(request.getParameter("velocidad_max_descendente"));
        
        
        Segmento seg=new Segmento();
        
        seg.setRadioCurvatura(radioCurvatura);
        seg.setGradiente(gradiente);
        seg.setPkFinal(pkFinal);
        seg.setRecta(recta);
        seg.setTunel(tunel);
        seg.setRampaAscendente(rampaAscendente);
        seg.setRampaDescendente(rampaDescendente);
        seg.setVelocidadMaxAscendente(velocidadMaxAscendente);
        seg.setVelocidadMaxDescendente(velocidadMaxDescendente);
        SegmentoPK spk=new SegmentoPK();
        spk.setIdPkInicial(pkInicio);
        spk.setIdLinea(linea);
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        Linea li= ljc.findLinea(linea);
        seg.setSegmentoPK(spk);
        seg.setLinea(li);
        SegmentoJpaController sjc=new SegmentoJpaController(Conex.getEmf());
        
        sjc.edit(seg);
         request.setAttribute("mensaje","Segmento con Progresiva Inicial "+seg.getSegmentoPK().getIdPkInicial()+
                    " ha sido editado satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
        
        }catch(PreexistingEntityException e) {
             request.setAttribute("mensaje","El Segmento "+
                    " ya existe");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
        }catch (Exception e) {
            request.setAttribute("mensaje","ha ocurrido una excepcion");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, ServletException, IOException {
       try{
        Double idPkInicial= Double.parseDouble(request.getParameter("progresiva"));
       int linea=Integer.parseInt(request.getParameter("linea"));
       
        SegmentoJpaController sjc=new SegmentoJpaController(Conex.getEmf());
        Segmento s= sjc.buscarSegmentoPK(linea, idPkInicial);
        SegmentoPK spk=s.getSegmentoPK();
        sjc.destroy(spk);
        request.setAttribute("mensaje","Segmento "+
                    " eliminado satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
       }catch(NumberFormatException | NonexistentEntityException | ServletException | IOException e){
           request.setAttribute("mensaje","Segmento "+
                    " eliminado satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoSegmento.jsp");
            rd.forward(request, response);
            e.printStackTrace();
       }
        System.out.println("Se elimino");
    }

    

    

}
