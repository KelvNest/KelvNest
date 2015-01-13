/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.SegmentoJpaController;
import entity.Restriccion;
import entity.Segmento;
import entity.SegmentoPK;
import java.util.List;

/**
 *
 * @author Kelvins Insua
 */
public class prueba {
    static GestorLista gl=new GestorLista();
    static int idLinea=01;
    static double inicio=0.0;
    static double fin=41000.0;
    static double vel=100;
    
//    static List<Restriccion> s= gl.buscarRestriccionEntreEstacionesAscendente(idLinea, inicio, fin, vel);
    static List<Restriccion> s= gl.buscarRestriccionEntreEstacionesDescendente(idLinea, fin, inicio, vel);
    public static void main(String[] args) {
        System.out.println(s.size());
        for (Restriccion res : s) {
            System.out.println("usuario"+res.getUsuario());
            System.out.println("inicio "+res.getProgInicio());
        }
        
    }
    
}
