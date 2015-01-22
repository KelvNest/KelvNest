/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import modelo.controlBD.EstacionJpaController;
import modelo.controlBD.LineaJpaController;
import modelo.entity.Estacion;
import modelo.entity.EstacionPK;
import modelo.entity.Linea;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter salida= response.getWriter();
        try{
            int idLinea=Integer.parseInt(request.getParameter("select_linea"));
            
            String nombre=request.getParameter("id_nombre_estacion");
            
            double pkEstacion=Double.parseDouble(request.getParameter("pk_estacion"));
            System.out.println(pkEstacion);
            EstacionPK epk=new EstacionPK(idLinea, nombre);
            Linea linea=ljc.findLinea(idLinea);
            e.setEstacionPK(epk);
            e.setLinea(linea);
            e.setPkEstacion(pkEstacion);
            
            ejc.create(e);
            

            salida.print("La Estacion "+e.getEstacionPK().getIdNombreEstacion()+" ha sido creada satisfactoriamente");
        }catch(Exception ex){
            salida.print("Uno de los Valores Ingresados No es Correcto");
            salida.print(ex.getCause());
        }
        
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstacionJpaController ejc=new EstacionJpaController(Conex.getEmf());
        Estacion e=new Estacion();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        PrintWriter salida=response.getWriter();
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
            
            salida.print("La Estacion "+e.getEstacionPK().getIdNombreEstacion()+" ha sido editada satisfactoriamente");
        
        }catch(Exception ex){
            salida.print("Uno de los Valores Ingresados No es Correcto");
        }            
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstacionJpaController ejc=new EstacionJpaController(Conex.getEmf());
        
        PrintWriter salida=response.getWriter();
        try{
            int idLinea=Integer.parseInt(request.getParameter("id_linea"));
            String nombre=request.getParameter("id_nombre_estacion");         
            EstacionPK epk=new EstacionPK(idLinea, nombre);           
            ejc.destroy(epk);             
            salida.print("La Estacion ha sido eliminada satisfactoriamente");
        }catch(Exception ex){
            salida.print("Uno de los Valores Ingresados No es Correcto");
               }
    }
}
