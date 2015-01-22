/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.List;
import modelo.entity.CurvaEsfuerzo;
import modelo.entity.MaterialRodante;
import modelo.entity.Segmento;

/**
 *
 * @author usuario
 */
public class Calculos {
    
    public static double resistenciaAlAvanceRecta(MaterialRodante materialRodante, double velocidad){
        double resistenciaAvanceRecta=0;
        int subTipo=0;
        double nEjes=0;
        if(materialRodante.getSubTipo().equals("Tren de Viajeros")){
            subTipo=0;
        }
        if(materialRodante.getSubTipo().equals("Tren de Mercancias")){
            subTipo=1;
        }
        if(materialRodante.getSubTipo().equals("Locomotora")){
            subTipo=2;
        }
        
        switch(subTipo){
            case 0:
                resistenciaAvanceRecta= materialRodante.getMasa()*(2+(velocidad*velocidad/4500));
                break;
            case 1:
                resistenciaAvanceRecta= materialRodante.getMasa()*(2+(velocidad*velocidad/1600));
                break;
            case 2:
                resistenciaAvanceRecta= ((0.65*materialRodante.getMasa())+(13*nEjes)+
                        (0.01*materialRodante.getMasa()*velocidad)+(0.03*velocidad*velocidad));
                break;
        
        }
        
        return resistenciaAvanceRecta*10;
    
    }
    
    public static double resistenciaAvanceCurva(Segmento segmento, MaterialRodante materialRodante){
                
        double resistenciaAvanceCurva=(materialRodante.getMasa()*600/segmento.getRadioCurvatura());
                
        return resistenciaAvanceCurva*10;
    }
    
    public static double fuerzaDebidoAGravedad(MaterialRodante materialRodante, Segmento segmento, boolean sentido){
        double resistenciaRampa=0;
        
        if(sentido==true){
            resistenciaRampa=(materialRodante.getMasa()*segmento.getRampaAscendente());
        }else{
            resistenciaRampa=(materialRodante.getMasa()*segmento.getRampaDescendente());
        }
               
        return resistenciaRampa*10;
        
    }
    
    public static double velocidadFinal(double vi, double a, double d) {
        double vf = Math.sqrt((vi * vi) + (2 * a * d));
        return vf;
    }
    
   public static double tiempoFinal(double to, double vf, double vi, double a) {
        double tf = to + ((vf - vi) / a);
        return tf;
    }
    
     public static double interpolacionLineal(double x, double x1, double y1, double x2, double y2) {
        double y = y1 + (((y2 - y1) / (x2 - x1)) * (x - x1));
        return y;
    }
     public static double seleccionarTraccion(double traccionActual, double velocidadActual, List<CurvaEsfuerzo> ce) {

        for (CurvaEsfuerzo ce1 : ce) {
            if (ce1.getCurvaEsfuerzoPK().getIdVelocidadCurvaEsfuerzo() > (velocidadActual * 3.6)) {
                traccionActual = interpolacionLineal(velocidadActual * 3.6,
                        ce.get(ce.indexOf(ce1) - 1).getCurvaEsfuerzoPK().getIdVelocidadCurvaEsfuerzo(),
                        ce.get(ce.indexOf(ce1) - 1).getEsfuerzoTraccion(),
                        ce1.getCurvaEsfuerzoPK().getIdVelocidadCurvaEsfuerzo(),
                        ce1.getEsfuerzoTraccion());
                break;
            }
        }
        return traccionActual;
    }
     public static double seleccionarFrenado(double esfuerzoFrenado, double velocidadActual, List<CurvaEsfuerzo> ce) {

        for (CurvaEsfuerzo ce1 : ce) {
            if (ce1.getCurvaEsfuerzoPK().getIdVelocidadCurvaEsfuerzo() > (velocidadActual * 3.6)) {
                esfuerzoFrenado = interpolacionLineal(velocidadActual * 3.6,
                        ce.get(ce.indexOf(ce1) - 1).getCurvaEsfuerzoPK().getIdVelocidadCurvaEsfuerzo(),
                        ce.get(ce.indexOf(ce1) - 1).getEsfuerzoFrenado(),
                        ce1.getCurvaEsfuerzoPK().getIdVelocidadCurvaEsfuerzo(),
                        ce1.getEsfuerzoFrenado());
            }
        }
        return esfuerzoFrenado;
    }
     
    
    
}
