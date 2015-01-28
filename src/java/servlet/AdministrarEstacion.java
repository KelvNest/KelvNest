/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

//import controlador.EstacionJpaController;
//import controlador.LineaJpaController;
import modelo.entity.Estacion;
import modelo.entity.EstacionPK;
import modelo.entity.Linea;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Conex;
import modelo.controlBD.EstacionJpaController;
import modelo.controlBD.LineaJpaController;

/**
 *
 * @author Kelvins Insua
 */
@WebServlet(name = "AdministrarEstacion", urlPatterns = {"/AdministrarEstacion"})
public class AdministrarEstacion extends HttpServlet {

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

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstacionJpaController ejc=new EstacionJpaController(Conex.getEmf());
        Estacion e=new Estacion();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        try{
            int idLinea=Integer.parseInt(request.getParameter("select_linea"));
            String nombre=request.getParameter("id_nombre_estacion");
            double pkEstacion=Double.parseDouble(request.getParameter("pk_estacion"));
            EstacionPK epk=new EstacionPK(idLinea, nombre);
            Linea linea=ljc.findLinea(idLinea);
            e.setEstacionPK(epk);
            e.setLinea(linea);
            e.setPkEstacion(pkEstacion);
            
            ejc.create(e);
            
            request.setAttribute("mensaje","La Estacion "+e.getEstacionPK().getIdNombreEstacion()+
                    " ha sido creada satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoEstacion.jsp");
            rd.forward(request, response);
        
        }catch(Exception ex){
        request.setAttribute("mensaje","Uno de los Valores Ingresados No es Correcto");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoEstacion.jsp");
            rd.forward(request, response);
        }
        
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstacionJpaController ejc=new EstacionJpaController(Conex.getEmf());
        Estacion e=new Estacion();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        try{
            int idLinea=Integer.parseInt(request.getParameter("id_linea"));
            String nombre=request.getParameter("id_nombre_estacion");
            double pkEstacion=Double.parseDouble(request.getParameter("pk_estacion"));
            EstacionPK epk=new EstacionPK(idLinea, nombre);
            Linea linea=ljc.findLinea(idLinea);
            e.setEstacionPK(epk);
            e.setLinea(linea);
            e.setPkEstacion(pkEstacion);
            
            ejc.edit(e);
            
            
        
        }catch(Exception ex){
        request.setAttribute("mensaje","Uno de los Valores Ingresados No es Correcto");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoEstacion.jsp");
            rd.forward(request, response);
        }
        request.setAttribute("mensaje","La Estacion "+e.getEstacionPK().getIdNombreEstacion()+
                    " ha sido editada satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoEstacion.jsp");
            rd.forward(request, response);
    
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstacionJpaController ejc=new EstacionJpaController(Conex.getEmf());
        Estacion e=new Estacion();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        try{
            int idLinea=Integer.parseInt(request.getParameter("id_linea"));
            String nombre=request.getParameter("id_nombre_estacion");
         
            EstacionPK epk=new EstacionPK(idLinea, nombre);
            
            e.setEstacionPK(epk);
            
            
            ejc.destroy(epk);
            
            
        
        }catch(Exception ex){
        request.setAttribute("mensaje","Uno de los Valores Ingresados No es Correcto");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoEstacion.jsp");
            rd.forward(request, response);
        }
        request.setAttribute("mensaje","La Estacion "+
                    " ha sido eliminada satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoEstacion.jsp");
            rd.forward(request, response);
     
    }

}
