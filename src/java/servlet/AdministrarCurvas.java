package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Conex;
import modelo.controlBD.CurvaEsfuerzoJpaController;
import modelo.controlBD.MaterialRodanteJpaController;
import modelo.controlBD.exceptions.NonexistentEntityException;
import modelo.entity.CurvaEsfuerzo;
import modelo.entity.CurvaEsfuerzoPK;
import modelo.entity.MaterialRodante;

/**
 *
 * @author seront
 */
public class AdministrarCurvas extends HttpServlet {

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
        
        switch (request.getParameter("accion")) {
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

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter salida=response.getWriter();   
        try {                     
            int idMatRod= Integer.parseInt(request.getParameter("idMatRod"));
            double idVel= Integer.parseInt(request.getParameter("vel"));
//            double esfuerzoTraccion=Double.parseDouble(request.getParameter("esfTrac"));
            double esfuerzoTraccion;
//            double esfuerzoFrenado=Double.parseDouble(request.getParameter("esfFre"));
            double esfuerzoFrenado;
            CurvaEsfuerzoPK curPK= new CurvaEsfuerzoPK(idMatRod, idVel);
//            CurvaEsfuerzo cur=new CurvaEsfuerzo(curPK);
//            CurvaEsfuerzo cur=new CurvaEsfuerzo(idMatRod, idVel);
            CurvaEsfuerzo cur=new CurvaEsfuerzo();
            cur.setCurvaEsfuerzoPK(curPK);
            MaterialRodanteJpaController mjc=new MaterialRodanteJpaController(Conex.getEmf());
            MaterialRodante mr= mjc.findMaterialRodante(idMatRod);
            cur.setMaterialRodante(mr);
            if(!request.getParameter("esfTrac").equals("")){
            esfuerzoTraccion=Double.parseDouble(request.getParameter("esfTrac"));
            cur.setEsfuerzoTraccion(esfuerzoTraccion);
            }
            if(!request.getParameter("esfFre").equals("")){
                esfuerzoFrenado=Double.parseDouble(request.getParameter("esfFre"));
                cur.setEsfuerzoFrenado(esfuerzoFrenado);
            }
            CurvaEsfuerzoJpaController cjc= new CurvaEsfuerzoJpaController(Conex.getEmf());
            cjc.create(cur);
            salida.print("Los parametros han sido registrados satisfactoriamente"+idVel);
            
        } catch (IOException ex) {
            salida.print("No se pudo agregar");
            ex.printStackTrace();
        } catch (Exception ex) {
            salida.print("No se pudo agregar");
            ex.printStackTrace();
        }
        
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter salida=response.getWriter();
        System.out.println("Editando");
        try {
            int idMatRod= Integer.parseInt(request.getParameter("idMatRod"));
            double idVel= Double.parseDouble(request.getParameter("idVel"));
            CurvaEsfuerzoPK curPK= new CurvaEsfuerzoPK(idMatRod, idVel);
            CurvaEsfuerzoJpaController cjc= new CurvaEsfuerzoJpaController(Conex.getEmf());
            CurvaEsfuerzo cur=new CurvaEsfuerzo();
            cur.setCurvaEsfuerzoPK(curPK);
            MaterialRodanteJpaController mjc=new MaterialRodanteJpaController(Conex.getEmf());
            MaterialRodante mr= mjc.findMaterialRodante(idMatRod);
            cur.setMaterialRodante(mr);
            double esfuerzoTraccion;
            double esfuerzoFrenado;
            if(!request.getParameter("esfTrac").equals("")){
                esfuerzoTraccion=Double.parseDouble(request.getParameter("esfTrac"));
                cur.setEsfuerzoTraccion(esfuerzoTraccion);
            }
            if((request.getParameter("esfFre")!=null)&&(!request.getParameter("esfFre").equals(""))){
                esfuerzoFrenado=Double.parseDouble(request.getParameter("esfFre"));
                cur.setEsfuerzoFrenado(esfuerzoFrenado);
            }
            System.out.println("Llego hasta");
            cjc.edit(cur);
            salida.print("Editado satisfactoriamente...");
        } catch (Exception ex) {
            salida.print("Ha ocurrido un error...");
            ex.printStackTrace();
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter salida=response.getWriter();
        try {
            int idMatRod= Integer.parseInt(request.getParameter("idMatRod"));
            double idVel= Double.parseDouble(request.getParameter("vel"));
            CurvaEsfuerzoPK curPK= new CurvaEsfuerzoPK(idMatRod, idVel);
            
            CurvaEsfuerzoJpaController cjc= new CurvaEsfuerzoJpaController(Conex.getEmf());
            cjc.destroy(curPK);
            salida.print("Eliminado satisfactoriemente");
        } catch (NonexistentEntityException ex) {
            salida.print("No se pudo eliminar");
            ex.printStackTrace();
        }
    }

}
