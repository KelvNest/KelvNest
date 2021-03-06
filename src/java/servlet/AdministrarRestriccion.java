/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

//import controlador.LineaJpaController;
//import controlador.RestriccionJpaController;
import modelo.entity.Linea;
import modelo.entity.Restriccion;
import modelo.entity.RestriccionPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Conex;
import modelo.controlBD.LineaJpaController;
import modelo.controlBD.RestriccionJpaController;

/**
 *
 * @author Kelvins Insua
 */
@WebServlet(name = "AdministrarRestriccion", urlPatterns = {"/AdministrarRestriccion"})
public class AdministrarRestriccion extends HttpServlet {

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
      RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());
        Restriccion r=new Restriccion();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        PrintWriter salida =response.getWriter();
        try{
            String usuario=request.getParameter("usuario");
            int idLinea=Integer.parseInt(request.getParameter("cmb_lineas"));
            int idRestriccion = 0;
            List<Restriccion> res = rjc.findRestriccionEntities(); 
            int contadorRestriccions = 0;
            for (int i = 0; i < res.size(); i++) {
                Restriccion re = res.get(i);
                if (re.getRestriccionPK().getIdRestriccion() != contadorRestriccions) {
                    idRestriccion = contadorRestriccions;
                    break;
                } else {
                    contadorRestriccions++;
                }
                if(i == res.size()-1){
                idRestriccion = contadorRestriccions;
                    break;
                }
            }
            double progInicio=Double.parseDouble(request.getParameter("prog_inicio"));
            double progFinal=Double.parseDouble(request.getParameter("prog_final"));
            double velMaxAscendente=Double.parseDouble(request.getParameter("vel_max_ascendente"));
            double velMaxDescendente=Double.parseDouble(request.getParameter("vel_max_descendente"));
            RestriccionPK rpk=new RestriccionPK(idLinea, idRestriccion);
            Linea linea=ljc.findLinea(idLinea);
            
            
            r.setRestriccionPK(rpk);
            r.setLinea(linea);
            r.setProgFinal(progFinal);
            r.setProgInicio(progInicio);
            r.setVelocidadMaxAscendente(velMaxAscendente);
            r.setVelocidadMaxDescendente(velMaxDescendente);
            r.setUsuario(usuario);
            
            rjc.create(r);
            salida.print("La Restriccion con progresiva de inicio "+r.getProgInicio()+
                    " ha sido creada satisfactoriamente");            
        
        }catch(Exception ex){
            salida.print("Uno de los Valores Ingresados No es Correcto");
            ex.printStackTrace();
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());
        Restriccion r=new Restriccion();
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        PrintWriter salida =response.getWriter();
        try{
            String usuario=request.getParameter("usuario");
            int idLinea=Integer.parseInt(request.getParameter("id_linea"));
            int idRestriccion=Integer.parseInt(request.getParameter("id_restriccion"));
            double progInicio=Double.parseDouble(request.getParameter("prog_inicio"));
            double progFinal=Double.parseDouble(request.getParameter("prog_final"));
            double velMaxAscendente=Double.parseDouble(request.getParameter("vel_max_ascendente"));
            double velMaxDescendente=Double.parseDouble(request.getParameter("vel_max_descendente"));
            RestriccionPK rpk=new RestriccionPK(idLinea, idRestriccion);
            Linea linea=ljc.findLinea(idLinea);
            
            
            r.setRestriccionPK(rpk);
            r.setLinea(linea);
            r.setProgFinal(progFinal);
            r.setProgInicio(progInicio);
            r.setVelocidadMaxAscendente(velMaxAscendente);
            r.setVelocidadMaxDescendente(velMaxDescendente);
            r.setUsuario(usuario);
            
            rjc.edit(r);
            salida.print("La Restriccion con progresiva de inicio "+r.getProgInicio()+
                    " ha sido editada satisfactoriamente");
            
        
        }catch(Exception ex){
            salida.print("Uno de los Valores Ingresados No es Correcto");
        
        }
        
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());      
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        PrintWriter salida =response.getWriter();
        try{
            int idLinea=Integer.parseInt(request.getParameter("id_linea"));
            int idRestriccion=Integer.parseInt(request.getParameter("id_restriccion"));
            Restriccion r= rjc.buscarRestriccionPK(idLinea, idRestriccion);
            RestriccionPK rpk=r.getRestriccionPK();
            
            r.setRestriccionPK(rpk);
                        
            rjc.destroy(rpk);
            salida.print("La Restriccion "+
                    " ha sido eliminada satisfactoriamente");
            
        
        }catch(Exception ex){
            salida.print("Uno de los Valores Ingresados No es Correcto");
        
        }
        
    }

}
