/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import controlador.CircuitoViaJpaController;
import controlador.LineaJpaController;
import controlador.exceptions.NonexistentEntityException;
import entity.CircuitoVia;
import entity.CircuitoViaPK;
import entity.Linea;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Conex;

/**
 *
 * @author Kelvins Insua
 */
@WebServlet(name = "AdministrarCircuitoVia", urlPatterns = {"/AdministrarCircuitoVia"})
public class AdministrarCircuitoVia extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
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
       CircuitoViaJpaController cvjc=new CircuitoViaJpaController(Conex.getEmf());
        CircuitoVia cv=new CircuitoVia();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        try{
            int idLinea=Integer.parseInt(request.getParameter("select_linea"));
            double pkInicialCircuito=Double.parseDouble(request.getParameter("id_pk_inicial_circuito"));
            double pkFinalCircuito=Double.parseDouble(request.getParameter("pk_final_circuito"));
            CircuitoViaPK cvpk=new CircuitoViaPK( pkInicialCircuito,idLinea);
            Linea linea=ljc.findLinea(idLinea);
            cv.setCircuitoViaPK(cvpk);
            cv.setLinea(linea);
            cv.setPkFinalCircuito(pkFinalCircuito);
            
            cvjc.create(cv);
            
            request.setAttribute("mensaje","El Circuito de Via de progresiva "+cv.getCircuitoViaPK().getIdPkInicialCircuito()+
                    " ha sido creado satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoCircuitoVia.jsp");
            rd.forward(request, response);
        
        }catch(Exception ex){
        request.setAttribute("mensaje","Uno de los Valores Ingresados No es Correcto");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoCircuitoVia.jsp");
            rd.forward(request, response);
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CircuitoViaJpaController cvjc=new CircuitoViaJpaController(Conex.getEmf());
        CircuitoVia cv=new CircuitoVia();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        try{
            int idLinea=Integer.parseInt(request.getParameter("id_linea"));
            double pkInicialCircuito=Double.parseDouble(request.getParameter("id_pk_inicial_circuito"));
            double pkFinalCircuito=Double.parseDouble(request.getParameter("pk_final_circuito"));
            CircuitoViaPK cvpk=new CircuitoViaPK( pkInicialCircuito,idLinea);
            Linea linea=ljc.findLinea(idLinea);
            cv.setCircuitoViaPK(cvpk);
            cv.setLinea(linea);
            cv.setPkFinalCircuito(pkFinalCircuito);
            
            cvjc.edit(cv);
            
            request.setAttribute("mensaje","El Circuito de Via de progresiva "+cv.getCircuitoViaPK().getIdPkInicialCircuito()+
                    " ha sido editado satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoCircuitoVia.jsp");
            rd.forward(request, response);
        
        }catch(Exception ex){
        request.setAttribute("mensaje","Uno de los Valores Ingresados No es Correcto");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoCircuitoVia.jsp");
            rd.forward(request, response);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       CircuitoViaJpaController cvjc=new CircuitoViaJpaController(Conex.getEmf());
       
        
        try{
            int idLinea=Integer.parseInt(request.getParameter("id_linea"));
            double pkInicialCircuito=Double.parseDouble(request.getParameter("id_pk_inicial_circuito"));
             CircuitoVia cv=cvjc.buscarCircuitoViaPK(idLinea,pkInicialCircuito);
            CircuitoViaPK cvpk=cv.getCircuitoViaPK();
            
            
           
            
            cvjc.destroy(cvpk);
            printStackTrace();
            request.setAttribute("mensaje","El Circuito de Via "+
                    " ha sido eliminado satisfactoriamente");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoCircuitoVia.jsp");
            rd.forward(request, response);
        
        }catch(NumberFormatException | NonexistentEntityException | ServletException | IOException ex){
        request.setAttribute("mensaje","Ha ocurrido un Error");
            RequestDispatcher rd= request.getRequestDispatcher("ingresoCircuitoVia.jsp");
            rd.forward(request, response);
        }
    }

}
