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
import modelo.entity.MaterialRodante;
import modelo.Conex;
import modelo.controlBD.MaterialRodanteJpaController;
import modelo.controlBD.exceptions.IllegalOrphanException;
import modelo.controlBD.exceptions.NonexistentEntityException;
import modelo.controlBD.exceptions.PreexistingEntityException;

/**
 *
 * @author Kelvins Insua
 */
@WebServlet(name = "AdministrarMaterialRodante", urlPatterns = {"/AdministrarMaterialRodante"})
public class AdministrarMaterialRodante extends HttpServlet {

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
            Logger.getLogger(AdministrarMaterialRodante.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdministrarMaterialRodante.class.getName()).log(Level.SEVERE, null, ex);
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
        PrintWriter salida=response.getWriter();
        try{
        MaterialRodanteJpaController mrjc=new MaterialRodanteJpaController(Conex.getEmf());
        String nombre=request.getParameter("nombre");
        String tipo=request.getParameter("tipo");
        String subTipo=request.getParameter("sub_tipo");
        int numeroVagones=Integer.parseInt(request.getParameter("numero_vagones"));
        int capacidadPasajeros=Integer.parseInt(request.getParameter("capacidad_pasajeros"));
        double kilometraje=Double.parseDouble(request.getParameter("kilometraje"));
        double largo=Double.parseDouble(request.getParameter("largo"));
        double ancho=Double.parseDouble(request.getParameter("ancho"));
        double alto=Double.parseDouble(request.getParameter("alto"));
        double velocidadDiseño=Double.parseDouble(request.getParameter("velocidad_diseño"));
        double velocidadOperacion=Double.parseDouble(request.getParameter("velocidad_operacion"));
        double masa=Double.parseDouble(request.getParameter("masa"));
        double aceleracionMaxima=Double.parseDouble(request.getParameter("aceleracion_maxima"));
        double desaceleracionMaxima=Double.parseDouble(request.getParameter("desaceleracion_maxima"));
        
        int idMaterialRodante = 0;
            List<MaterialRodante> res = mrjc.findMaterialRodanteEntities(); 
            int contadorMaterialRodantes = 0;
            for (int i = 0; i < res.size(); i++) {
                MaterialRodante re = res.get(i);
                if (re.getIdMaterialRodante() != contadorMaterialRodantes) {
                    idMaterialRodante = contadorMaterialRodantes;
                    break;
                } else {
                    contadorMaterialRodantes++;
                }
                if(i == res.size()-1){
                idMaterialRodante = contadorMaterialRodantes;
                    break;
                }
            }
        MaterialRodante mr=new MaterialRodante();
        mr.setIdMaterialRodante(idMaterialRodante);
        mr.setNombreMaterialRodante(nombre);
        mr.setTipo(tipo);
        mr.setSubTipo(subTipo);
        mr.setNumeroVagones(numeroVagones);
        mr.setCapacidadPasajeros(capacidadPasajeros);
        mr.setKilometraje(kilometraje);
        mr.setLargo(largo);
        mr.setAnchoCara(ancho);
        mr.setAltoCara(alto);
        mr.setVelocidadDiseño(velocidadDiseño);
        mr.setVelocidadOperativa(velocidadOperacion);
        mr.setMasa(masa);
        mr.setAceleracionMax(aceleracionMaxima);
        mr.setDesaceleracionMax(desaceleracionMaxima);

        mrjc.create(mr);
        salida.print("Material Rodante "+mr.getNombreMaterialRodante()+
                    " ha sido creado satisfactoriamente");
         }catch(PreexistingEntityException e){
             salida.print("El Material Rodante "+
                    " ya existe");
        }catch (Exception e) {
            salida.print("No se pudo agregar el material rodante");
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, Exception {
        PrintWriter salida=response.getWriter();
        try{
        MaterialRodanteJpaController mrjc=new MaterialRodanteJpaController(Conex.getEmf());
        String nombre=request.getParameter("nombre");
        String tipo=request.getParameter("tipo");
        String subTipo=request.getParameter("sub_tipo");
        int numeroVagones=Integer.parseInt(request.getParameter("numero_vagones"));
        int capacidadPasajeros=Integer.parseInt(request.getParameter("capacidad_pasajeros"));
        double kilometraje=Double.parseDouble(request.getParameter("kilometraje"));
        double largo=Double.parseDouble(request.getParameter("largo"));
        double ancho=Double.parseDouble(request.getParameter("ancho"));
        double alto=Double.parseDouble(request.getParameter("alto"));
        double velocidadDiseño=Double.parseDouble(request.getParameter("velocidad_diseño"));
        double velocidadOperacion=Double.parseDouble(request.getParameter("velocidad_operacion"));
        double masa=Double.parseDouble(request.getParameter("masa"));
        double aceleracionMaxima=Double.parseDouble(request.getParameter("aceleracion_maxima"));
        double desaceleracionMaxima=Double.parseDouble(request.getParameter("desaceleracion_maxima"));        
        int idMaterialRodante=Integer.parseInt(request.getParameter("id_material_rodante"));
        
        MaterialRodante mr=new MaterialRodante();
        mr.setIdMaterialRodante(idMaterialRodante);
        mr.setNombreMaterialRodante(nombre);
        mr.setTipo(tipo);
        mr.setSubTipo(subTipo);
        mr.setNumeroVagones(numeroVagones);
        mr.setCapacidadPasajeros(capacidadPasajeros);
        mr.setKilometraje(kilometraje);
        mr.setLargo(largo);
        mr.setAnchoCara(ancho);
        mr.setAltoCara(alto);
        mr.setVelocidadDiseño(velocidadDiseño);
        mr.setVelocidadOperativa(velocidadOperacion);
        mr.setMasa(masa);
        mr.setAceleracionMax(aceleracionMaxima);
        mr.setDesaceleracionMax(desaceleracionMaxima);
        
        
        
        mrjc.edit(mr);
        salida.print("Material Rodante "+mr.getNombreMaterialRodante()+
                    " ha sido editado satisfactoriamente");
        
         }catch(PreexistingEntityException e){
             salida.print("El Material Rodante "+
                    " ya existe");             
        }catch (Exception e) {
            salida.print("No se pudo editar el material rodante");
                    }
    }

    

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IllegalOrphanException, NonexistentEntityException, ServletException, IOException {
       PrintWriter salida=response.getWriter();
        int idMaterialRodante=Integer.parseInt(request.getParameter("id_material_rodante"));
      
        MaterialRodanteJpaController mrjc=new MaterialRodanteJpaController(Conex.getEmf());
        
        mrjc.destroy(idMaterialRodante);
        salida.print("Material Rodante  ha sido eliminado satisfactoriamente");
              
    }

}
