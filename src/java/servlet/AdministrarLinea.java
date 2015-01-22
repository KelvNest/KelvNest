/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.controlBD.LineaJpaController;
import modelo.controlBD.exceptions.IllegalOrphanException;
import modelo.controlBD.exceptions.NonexistentEntityException;
import modelo.entity.Linea;
import modelo.Conex;

/**
 *
 * @author Kelvins Insua
 */
@WebServlet(name = "AdministrarLinea", urlPatterns = {"/AdministrarLinea"})
public class AdministrarLinea extends HttpServlet {

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
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "Agregar":
                    agregar(request, response);
                    break;
                case "Editar":
                    editar(request, response);
                    break;
                case "Eliminar":
                    eliminar(request, response);
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
            Logger.getLogger(AdministrarLinea.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdministrarLinea.class.getName()).log(Level.SEVERE, null, ex);
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
        LineaJpaController ljc = new LineaJpaController(Conex.getEmf());
        Linea linea = new Linea();
        PrintWriter salida = response.getWriter();
        try {
            String nombre = request.getParameter("nombre_linea");
            double pkInicial = Double.parseDouble(request.getParameter("pk_inicial"));
            double pkFinal = Double.parseDouble(request.getParameter("pk_final"));
            int idLinea=0;
            double trocha = Double.parseDouble(request.getParameter("trocha"));
//            if (ljc.getLineaCount() == 0) {
//                idLinea = 0;
//            } else {
//                System.out.println(ljc.getLineaCount());
//                idLinea = ljc.getLineaCount() + 1;
//            }
            List<Linea> lineas = ljc.findLineaEntities();
            int contadorLineas = 0;
            for (int i = 0; i < lineas.size(); i++) {
                Linea lin = lineas.get(i);
                if (lin.getIdLinea() != contadorLineas) {
                    idLinea = contadorLineas;
                    break;
                } else {
                    contadorLineas++;
                }
                if(i == lineas.size()-1){
                idLinea = contadorLineas;
                    break;
                }
            }
            System.out.println(idLinea);
            linea.setIdLinea(idLinea);
            linea.setNombreLinea(nombre);
            linea.setPkInicial(pkInicial);
            linea.setPkFinal(pkFinal);
            linea.setTrocha(trocha);

            ljc.create(linea);

            salida.print("La Linea " + linea.getNombreLinea() + " ha sido creado satisfactoriamente");

        } catch (Exception e) {
            salida.print("Uno de los Valores Ingresados No es Correcto");
            e.getLocalizedMessage();
            e.printStackTrace();
        }

    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, Exception {
        Linea linea = new Linea();
        LineaJpaController ljc = new LineaJpaController(Conex.getEmf());
        PrintWriter salida = response.getWriter();
        try {
            double pkInicial = Double.parseDouble(request.getParameter("pk_inicial"));
            double pkFinal = Double.parseDouble(request.getParameter("pk_final"));
            int idLinea = Integer.parseInt(request.getParameter("id_linea"));
            double trocha = Double.parseDouble(request.getParameter("trocha"));
            String nombre = request.getParameter("nombre_linea");

            linea.setIdLinea(idLinea);
            linea.setNombreLinea(nombre);
            linea.setPkInicial(pkInicial);
            linea.setPkFinal(pkFinal);
            linea.setTrocha(trocha);
            ljc.edit(linea);
            salida.print("La Linea " + linea.getNombreLinea() + " ha sido editada satisfactoriamente");
        } catch (Exception e) {
            salida.print("Uno de los Valores Ingresados No es Correcto" + e.getLocalizedMessage());
//            request.setAttribute("mensaje", "Uno de los Valores Ingresados No es Correcto");
//            RequestDispatcher rd = request.getRequestDispatcher("ingresoLinea.jsp");
//            rd.forward(request, response);

        }

//        request.setAttribute("mensaje", "La Linea " + linea.getNombreLinea()
//                + " ha sido editada satisfactoriamente");
//        RequestDispatcher rd = request.getRequestDispatcher("ingresoLinea.jsp");
//        rd.forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IllegalOrphanException, NonexistentEntityException, ServletException, IOException {
        String nombre = request.getParameter("nombre_linea");
        LineaJpaController ljc = new LineaJpaController(Conex.getEmf());
        int idLinea = ljc.bucarLinea(nombre).getIdLinea();
        Linea linea = new Linea();
        linea.setIdLinea(idLinea);
        PrintWriter salida = response.getWriter();
        try {
            ljc.destroy(linea.getIdLinea());
            salida.print("La Linea ha sido eliminada satisfactoriamente");
        } catch (Exception e) {
            salida.print("La Linea No Pudo Ser Eliminada");
//        request.setAttribute("mensaje","La Linea No Pudo Ser Eliminada");
//            RequestDispatcher rd= request.getRequestDispatcher("ingresoLinea.jsp");
//            rd.forward(request, response);
        }
//        request.setAttribute("mensaje","La Linea "+
//                    " ha sido eliminada satisfactoriamente");
//            RequestDispatcher rd= request.getRequestDispatcher("ingresoLinea.jsp");
//            rd.forward(request, response);        
    }

}
