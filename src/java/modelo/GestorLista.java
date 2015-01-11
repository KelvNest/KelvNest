package modelo;


import controlador.CircuitoViaJpaController;
import controlador.CurvaEsfuerzoJpaController;
import controlador.EstacionJpaController;
import controlador.LineaJpaController;
import controlador.MaterialRodanteJpaController;
import controlador.RestriccionJpaController;
import controlador.SegmentoJpaController;
import entity.CircuitoVia;
import entity.CurvaEsfuerzo;
import entity.Estacion;
import entity.Linea;
import entity.MaterialRodante;
import entity.Restriccion;
import entity.Segmento;
import entity.SegmentoPK;
import java.util.List;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kelvins Insua
 */
public class GestorLista {
    
    
public List<Segmento> listaSegmento(int id_linea){
        SegmentoJpaController sjc=new SegmentoJpaController(Conex.getEmf());
        return sjc.buscarIdLineaDescendente(id_linea);
    }
public List<CurvaEsfuerzo> listaEsfuerzos(int id_mr){
       CurvaEsfuerzoJpaController crjc =new CurvaEsfuerzoJpaController(Conex.getEmf());
        return crjc.curvaDelMaterialRodante(id_mr);
    }
public Segmento buscarSegmento(SegmentoPK spk){
        SegmentoJpaController sjc=new SegmentoJpaController(Conex.getEmf());
        return sjc.findSegmento(spk);
        
    }
public List<MaterialRodante> listaMaterialRodante(){
        MaterialRodanteJpaController mrjc=new MaterialRodanteJpaController(Conex.getEmf());
        return mrjc.findMaterialRodanteEntities();
        
    }
public MaterialRodante buscarMaterialRodante(int id_mr){
    MaterialRodanteJpaController mrjc=new MaterialRodanteJpaController(Conex.getEmf());
    return mrjc.findMaterialRodante(id_mr);
}
public Linea buscarLinea(int id_linea){
    LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
    return ljc.findLinea(id_linea);
}
public List<Linea> listaLinea(){
        LineaJpaController ljc=new LineaJpaController(Conex.getEmf());
        return ljc.findLineaEntities();
        
    }
public List<Estacion> buscarEstacion(int id_linea){
        EstacionJpaController ejc=new EstacionJpaController(Conex.getEmf());
        return ejc.buscarEstacion(id_linea);
    }
public List<Restriccion> buscarRestriccion(int id_linea){
        RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());
        return rjc.buscarRestriccion(id_linea);
    }
public List<Restriccion> buscarRestriccionVel(int id_linea, double vel){
        RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());
        return rjc.buscarIdLineaVelocidad(id_linea, vel);
    }

public List<Restriccion> buscarRestriccionEntreEstacionesAscendente(int id_linea,double progEstacionInicial,double progEstacionFinal, double vel){
        RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());
        System.out.println("id_linea: "+id_linea+" EstacionInicial "+progEstacionInicial+" EstacionFinal "+progEstacionFinal+" vel "+vel);
        return rjc.restriccionEntreEstacionesAscendente(id_linea, progEstacionInicial, progEstacionFinal, vel);
    }
public List<Restriccion> buscarRestriccionEntreEstacionesDescendente(int id_linea,double progEstacionInicial,double progEstacionFinal, double vel){
        RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());
        return rjc.restriccionEntreEstacionesDescendente(id_linea, progEstacionInicial, progEstacionFinal, vel);
    }
public List<CircuitoVia> buscarCircuitoVia(int id_linea){
        CircuitoViaJpaController cvjc=new CircuitoViaJpaController(Conex.getEmf());
        return cvjc.buscarCircuitoVia(id_linea);
    }
public Segmento buscarSegmentoPorPK(int idLinea, double idPkInicial){
SegmentoJpaController sjc=new SegmentoJpaController(Conex.getEmf());
return sjc.buscarSegmentoPK(idLinea, idPkInicial);
}
public Restriccion buscarRestriccionPorPK(int idLinea, int idRestriccion){
RestriccionJpaController rjc=new RestriccionJpaController(Conex.getEmf());
return rjc.buscarRestriccionPK(idLinea, idRestriccion);
}
public Estacion buscarEstacionPorPK(int idLinea, String idNombreEstacion){
EstacionJpaController ejc=new EstacionJpaController(Conex.getEmf());
return ejc.buscarEstacionPK(idLinea, idNombreEstacion);
}

public CircuitoVia buscarCircuitoViaPorPK(int idLinea, double idPkInicialCircuito){
CircuitoViaJpaController cvjc=new CircuitoViaJpaController(Conex.getEmf());
return cvjc.buscarCircuitoViaPK(idLinea, idPkInicialCircuito);
}
        
        

}
