/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import controlador.CurvaEsfuerzoJpaController;
import controlador.EstacionJpaController;
import controlador.MaterialRodanteJpaController;
import controlador.RestriccionJpaController;
import controlador.SegmentoJpaController;
import entity.CurvaEsfuerzo;
import entity.Estacion;
import entity.MaterialRodante;
import entity.Restriccion;
import entity.Segmento;

/**
 *
 * @author usuario
 */
public class CalculoMarchaTipo {
//public EntityManagerFactory emf=Persistence.createEntityManagerFactory("siptrafPU");
    MaterialRodanteJpaController mrjc = new MaterialRodanteJpaController(Conex.getEmf());
    CurvaEsfuerzoJpaController cejc = new CurvaEsfuerzoJpaController(Conex.getEmf());
    SegmentoJpaController sjc = new SegmentoJpaController(Conex.getEmf());
    RestriccionJpaController rjc = new RestriccionJpaController(Conex.getEmf());
    //Cinematica cinematica = new Cinematica();
    MaterialRodante materialRodante = mrjc.findMaterialRodante(01);
    public double velocidad = 0;
    double traccion;
    List<CurvaEsfuerzo> ce = cejc.curvaDelMaterialRodante(01);
    List<Segmento> segmento;
    double resistenciaEnCurva = 0;
    double resistenciaEnRecta = 0;
    double fuerzaResultante = 0;
    double resistenciaRampa = 0;
    //boolean sentido = true;
    double aceleracion = 0;
    double velocidadFinal = 0;
    double tiempoFinal = 0;
    double tiempo = 0;
    double progresivaActual = 0;
    List<Restriccion> restriccion = rjc.findRestriccionEntities();
    int r = 0;
    double progresivaFinal = 0;
    EstacionJpaController ejc = new EstacionJpaController(Conex.getEmf());
    List<Estacion> estaciones = ejc.ordenarDescendente(01);
    int e = 1;
    double velocidadMarcha=100;

    public CalculoMarchaTipo() {

        simular(segmento,false, estaciones);
    }

    public void simular(List<Segmento> segmentos, boolean sentido, List<Estacion> estaciones) {

        if (sentido == true) {
            restriccion = rjc.buscarIdLineaAscendente(01);
            estaciones = ejc.ordenarAscendente(01);
            e = 1;
            segmentos = sjc.buscarIdLineaAscendente(01);
            progresivaActual = 0;
            progresivaFinal = 41377;
        } else {
            restriccion = rjc.buscarIdLineaDescendente(01);
            estaciones = ejc.ordenarDescendente(01);
             e = 1;
            segmentos = sjc.buscarIdLineaDescendente(01);
            progresivaActual = segmentos.get(0).getPkFinal();
            System.out.println("progresiva actual: " + progresivaActual);
        }

        for (Segmento segmentoActual : segmentos) {
            if (sentido == true) {
                if (progresivaActual < estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                    while (progresivaActual < segmentoActual.getPkFinal()) {
                        System.out.println("--------------------------Posicion del segmento: " + segmentos.indexOf(segmentoActual) + " ---------------------------------");
                        System.out.println("progresiva actual " + progresivaActual);
                        if (restriccion.size() > 0) {
                            if (progresivaActual > restriccion.get(r).getProgFinal()) {
                                if (r < (restriccion.size() - 1)) {
                                    r++;
                                }
                            }
                            if (progresivaActual > estaciones.get(e).getPkEstacion()) {
                                if (e < (estaciones.size() - 1)) {
                                    e++;
                                }
                            }

                            if (velocidad < velocidadMarcha / 3.6) {
                                velocidad = acelerar(segmentoActual, restriccion.get(r), sentido, estaciones.get(e));

                                if (progresivaActual >= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                    break;
                                }
                            }
                            if (velocidad == velocidadMarcha / 3.6||velocidad == segmentoActual.getVelocidadMaxAscendente() / 3.6 || velocidad == restriccion.get(r).getVelocidadMaxAscendente() / 3.6) {
                                velocidad = velocidadCrucero(segmentoActual, restriccion.get(r), sentido, estaciones.get(e));
                                if (progresivaActual >= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                    break;
                                }
                            }
                            if (velocidad > velocidadMarcha / 3.6||velocidad > segmentoActual.getVelocidadMaxAscendente() / 3.6 || (velocidad > restriccion.get(r).getVelocidadMaxAscendente() / 3.6 && progresivaActual >= restriccion.get(r).getProgInicio() && progresivaActual < restriccion.get(r).getProgFinal())) {
                                System.out.println("FRENOOOOOOOOOOOOOOOO");
                                System.out.println("velocidad " + velocidad);
                                velocidad = frenado(sentido, segmentoActual, restriccion.get(r), materialRodante, estaciones.get(e));

                                if (progresivaActual >= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                    break;
                                }
                            }
                        } else {
                            if (progresivaActual < estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                             if (progresivaActual > estaciones.get(e).getPkEstacion()) {
                                if (e < (estaciones.size() - 1)) {
                                    e++;
                                }
                            }

                            if (velocidad < velocidadMarcha / 3.6) {
                                velocidad = acelerarSinRestriccion(segmentoActual, sentido, estaciones.get(e));

                            }
                            if (velocidad == velocidadMarcha / 3.6||velocidad == segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                                velocidad = velocidadCruceroSinRestriccion(segmentoActual, sentido, estaciones.get(e));

                            }
                            if (velocidad > velocidadMarcha / 3.6||velocidad > segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                                System.out.println("FRENOOOOOOOOOOOOOOOO");
                                System.out.println("velocidad " + velocidad);
                                velocidad = frenadoSinRestriccion(sentido, segmentoActual, materialRodante, tiempo, estaciones.get(e));

                            }}else{
                                break;
                            }

                        }
                    }
                } else {
                    break;
                }
            } else {
                System.out.println("--------------------------Posicion del segmento: " + segmentos.indexOf(segmentoActual) + " ---------------------------------");
                while (progresivaActual > segmentoActual.getSegmentoPK().getIdPkInicial()) {
                    System.out.println("progresiva actual " + progresivaActual);
                    if (restriccion.size() > 0) {
                        if (progresivaActual < restriccion.get(r).getProgInicio()) {
                            if (r < (restriccion.size() - 1)) {
                                r++;
                            }
                        }
                        System.out.println("ESTACIOOOOOOOOOOOON: " + estaciones.get(e).getPkEstacion());
                        if (progresivaActual < estaciones.get(e).getPkEstacion()) {
                            if (e < (estaciones.size() - 1)) {
                                e++;
                            }
                        }

                        if (velocidad < velocidadMarcha / 3.6) {
                            velocidad = acelerar(segmentoActual, restriccion.get(r), sentido, estaciones.get(e));

                        }
                        if (velocidad == velocidadMarcha / 3.6||velocidad == segmentoActual.getVelocidadMaxAscendente() / 3.6 || velocidad == restriccion.get(r).getVelocidadMaxAscendente() / 3.6) {
                            velocidad = velocidadCrucero(segmentoActual, restriccion.get(r), sentido, estaciones.get(e));

                        }
                        if (velocidad > velocidadMarcha / 3.6||velocidad > segmentoActual.getVelocidadMaxAscendente() / 3.6 || (velocidad > restriccion.get(r).getVelocidadMaxAscendente() / 3.6 && progresivaActual >= restriccion.get(r).getProgInicio() && progresivaActual < restriccion.get(r).getProgFinal())) {
                            System.out.println("FRENOOOOOOOOOOOOOOOO");
                            System.out.println("velocidad " + velocidad);
                            velocidad = frenado(sentido, segmentoActual, restriccion.get(r), materialRodante, estaciones.get(e));
                        }
                    } else {
                        if (progresivaActual < estaciones.get(e).getPkEstacion()) {
                            if (e < (estaciones.size() - 1)) {
                                e++;
                            }
                        }

                        if (velocidad < velocidadMarcha / 3.6) {
                            velocidad = acelerarSinRestriccion(segmentoActual, sentido, estaciones.get(e));

                        }
                        if (velocidad == velocidadMarcha / 3.6||velocidad == segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                            velocidad = velocidadCruceroSinRestriccion(segmentoActual, sentido, estaciones.get(e));

                        }
                        if (velocidad > velocidadMarcha / 3.6||velocidad > segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                            System.out.println("FRENOOOOOOOOOOOOOOOO");
                            System.out.println("velocidad " + velocidad);
                            velocidad = frenadoSinRestriccion(sentido, segmentoActual, materialRodante, tiempo, estaciones.get(e));

                        }

                    }
                }
            }
        }
    }

    public double acelerar(Segmento segmento, Restriccion restriccion, boolean sentido, Estacion estacion) {
        while (true) {
            System.out.println("ACELERAR RESTRICCION");

            traccion = Calculos.seleccionarTraccion(traccion, velocidad, ce);
            if (segmento.getRecta() == false) {
                resistenciaEnCurva = Calculos.resistenciaAvanceCurva(segmento, materialRodante);
            } else {
                resistenciaEnCurva = 0;
            }
            resistenciaEnRecta = Calculos.resistenciaAlAvanceRecta(materialRodante, velocidad);
            resistenciaRampa = Calculos.fuerzaDebidoAGravedad(materialRodante, segmento, sentido);
            fuerzaResultante = ((traccion * 9.8) - resistenciaEnCurva - resistenciaEnRecta - resistenciaRampa);
            System.out.println("fuerzaResultante: " + fuerzaResultante);
            aceleracion = fuerzaResultante / (materialRodante.getMasa() * 1000);
            velocidadFinal = Calculos.velocidadFinal(velocidad, aceleracion, 1);
            tiempoFinal = Calculos.tiempoFinal(tiempo, velocidadFinal, velocidad, aceleracion);
            velocidad = velocidadFinal;
            tiempo = tiempoFinal;
            System.out.println("tempo acumulado: " + tiempo);
            System.out.println("velocidad: " + velocidad);
            System.out.println("progresiva actual: " + progresivaActual);
            if (sentido == true) {
                progresivaActual++;
                if (progresivaActual >= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                if (progresivaActual > segmento.getPkFinal()) {
                    return velocidad;
                }
                if(velocidad > velocidadMarcha / 3.6){
                    velocidad=velocidadMarcha/3.6;
                    return velocidad;
                }
                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxAscendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                    return velocidad;
                }
                if (progresivaActual >= restriccion.getProgInicio() && progresivaActual < restriccion.getProgFinal()) {
                    if (velocidad > (restriccion.getVelocidadMaxAscendente() / 3.6)) {
                        velocidad = restriccion.getVelocidadMaxAscendente() / 3.6;
                        return velocidad;
                    }

                } else {
                }

            } else {
                progresivaActual--;
                if (progresivaActual <= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                if (progresivaActual < segmento.getSegmentoPK().getIdPkInicial()) {
                    return velocidad;
                }
                if(velocidad > velocidadMarcha / 3.6){
                    velocidad=velocidadMarcha/3.6;
                    return velocidad;
                }
                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxAscendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                    return velocidad;
                }
                if (progresivaActual < restriccion.getProgFinal() && progresivaActual > restriccion.getProgInicio()) {
                    if (velocidad > (restriccion.getVelocidadMaxAscendente() / 3.6)) {
                        velocidad = restriccion.getVelocidadMaxAscendente() / 3.6;
                        return velocidad;
                    }
                }

            }
            System.out.println("velocidad: " + velocidad);
            System.out.println("progresiva actual: " + progresivaActual);
        }

    }

    public double acelerarSinRestriccion(Segmento segmento, boolean sentido, Estacion estacion) {
        while (true) {
            System.out.println("ACELERAR");

            traccion = Calculos.seleccionarTraccion(traccion, velocidad, ce);
            if (segmento.getRecta() == false) {
                resistenciaEnCurva = Calculos.resistenciaAvanceCurva(segmento, materialRodante);
            } else {
                resistenciaEnCurva = 0;
            }
            resistenciaEnRecta = Calculos.resistenciaAlAvanceRecta(materialRodante, velocidad);
            resistenciaRampa = Calculos.fuerzaDebidoAGravedad(materialRodante, segmento, sentido);
            fuerzaResultante = ((traccion * 9.8) - resistenciaEnCurva - resistenciaEnRecta - resistenciaRampa);
            System.out.println("fuerzaResultante: " + fuerzaResultante);
            aceleracion = fuerzaResultante / (materialRodante.getMasa() * 1000);
            velocidadFinal = Calculos.velocidadFinal(velocidad, aceleracion, 1);
            tiempoFinal = Calculos.tiempoFinal(tiempo, velocidadFinal, velocidad, aceleracion);
            velocidad = velocidadFinal;
            tiempo = tiempoFinal;
            System.out.println("tempo acumulado: " + tiempo);

            if (sentido == true) {
                progresivaActual++;
                if (progresivaActual >= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                if (progresivaActual > segmento.getPkFinal()) {
                    return velocidad;
                }
                if(velocidad > velocidadMarcha / 3.6){
                    velocidad=velocidadMarcha/3.6;
                    return velocidad;
                }
                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxAscendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                    return velocidad;
                }

            } else {
                progresivaActual -= 1;
                if (progresivaActual <= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                if (progresivaActual < segmento.getSegmentoPK().getIdPkInicial()) {
                    return velocidad;
                }
                if(velocidad > velocidadMarcha / 3.6){
                    velocidad=velocidadMarcha/3.6;
                    return velocidad;
                }
                
                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxAscendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                    return velocidad;
                }

            }
            System.out.println("velocidad: " + velocidad);
            System.out.println("progresiva actual: " + progresivaActual);
        }

    }

    public double velocidadCrucero(Segmento segmento, Restriccion restriccion, boolean sentido, Estacion estacion) {
        System.out.println("VELOCIDAD CRUCERO");
        System.out.println("velocidad: " + velocidad);
        System.out.println("progresiva actual: " + progresivaActual);
        System.out.println("tempo acumulado: " + tiempo);
        if (sentido == true) {
            if (progresivaActual > restriccion.getProgInicio() && progresivaActual < restriccion.getProgFinal()) {
                tiempoFinal = tiempo + ((restriccion.getProgFinal() - progresivaActual) / velocidad);
                progresivaActual = restriccion.getProgFinal() + 1;
                tiempo = tiempoFinal;
                System.out.println("aaaaaaa");
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tempo acumulado: " + tiempo);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }
            if (segmento.getPkFinal() > restriccion.getProgInicio() && progresivaActual < restriccion.getProgInicio()) {
                tiempoFinal = tiempo + ((restriccion.getProgInicio() - progresivaActual) / velocidad);
                progresivaActual = restriccion.getProgInicio() + 1;
                tiempo = tiempoFinal;
                System.out.println("eeeee");
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tempo acumulado: " + tiempo);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;

            } else {
                tiempoFinal = tiempo + ((segmento.getPkFinal() - progresivaActual) / velocidad);
                progresivaActual = segmento.getPkFinal();
                tiempo = tiempoFinal;
                System.out.println("iiiiiii");
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tempo acumulado: " + tiempo);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }

        } else {
            if (restriccion.getProgFinal() > progresivaActual && progresivaActual > restriccion.getProgInicio()) {
                tiempoFinal = tiempo + ((progresivaActual - restriccion.getProgInicio()) / velocidad);
                progresivaActual = restriccion.getProgInicio();
                tiempo = tiempoFinal;
                System.out.println("velocidad: " + velocidad);
                System.out.println("aaaaaaaaaaa");
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tempo acumulado: " + tiempo);
                if (progresivaActual <= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }
            if (segmento.getSegmentoPK().getIdPkInicial() <= restriccion.getProgFinal() && progresivaActual > restriccion.getProgFinal()) {
                tiempoFinal = tiempo + ((progresivaActual - restriccion.getProgFinal()) / velocidad);
                progresivaActual = restriccion.getProgFinal() - 1;
                tiempo = tiempoFinal;
                System.out.println("eeeeeeeeee");
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tempo acumulado: " + tiempo);
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;

            } else {
                tiempoFinal = tiempo + ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
                progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
                tiempo = tiempoFinal;
                System.out.println("iiiiiiiiiiiii");
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tempo acumulado: " + tiempo);
                if (progresivaActual < estacion.getPkEstacion()) {
                    System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }

        }

    }

    public double velocidadCruceroSinRestriccion(Segmento segmento, boolean sentido, Estacion estacion) {
        System.out.println("VELOCIDAD CRUCERO");
        System.out.println("velocidad: " + velocidad);
        System.out.println("progresiva actual: " + progresivaActual);
        System.out.println("tempo acumulado: " + tiempo);
        if (sentido == true) {

            tiempoFinal = tiempo + ((segmento.getPkFinal() - progresivaActual) / velocidad);
            progresivaActual = segmento.getPkFinal();
            tiempo = tiempoFinal;
            System.out.println("iiiiiii");
            System.out.println("velocidad: " + velocidad);
            System.out.println("progresiva actual: " + progresivaActual);
            System.out.println("tempo acumulado: " + tiempo);
            if (progresivaActual > estacion.getPkEstacion()) {
                frenoEnParada(estacion, sentido);
            }
            return velocidad;

        } else {

            tiempoFinal = tiempo + ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
            progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
            tiempo = tiempoFinal;
            System.out.println("descendente");
            System.out.println("velocidad: " + velocidad);
            System.out.println("progresiva actual: " + progresivaActual);
            System.out.println("tempo acumulado: " + tiempo);
            if (progresivaActual < estacion.getPkEstacion()) {
                frenoEnParada(estacion, sentido);
            }
            return velocidad;

        }

    }

    public double frenado(boolean sentido, Segmento segmento, Restriccion restriccion,
            MaterialRodante materialRodante, Estacion estacion) {

        if (sentido == true) {
            System.out.println(" entre a frenar");
            if (velocidad > segmento.getVelocidadMaxAscendente() / 3.6) {
                System.out.println("***** Frenando *****");

                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxAscendente() / 3.6, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1] - ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
                progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
                progresivaActual += distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }
            if (velocidad > restriccion.getVelocidadMaxAscendente() / 3.6) {
                System.out.println("***** Frenando *****");
                System.out.println("por resticcion-----------");
                double[] distanciaTiempo = freno(velocidad, restriccion.getVelocidadMaxAscendente() / 3.6, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1];
                progresivaActual = restriccion.getProgInicio();
                progresivaActual += distanciaTiempo[0];
                velocidad = restriccion.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;

            }
        } else {
            if (velocidad > restriccion.getVelocidadMaxDescendente() / 3.6) {

                double[] distanciaTiempo = freno(velocidad, restriccion.getVelocidadMaxDescendente() / 3.6, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1];
                progresivaActual = progresivaActual - distanciaTiempo[0];
                velocidad = restriccion.getVelocidadMaxDescendente() / 3.6;
                System.out.println("***** Frenando *****");
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tiempo " + tiempo);
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }
            if (velocidad > segmento.getVelocidadMaxDescendente() / 3.6) {
                System.out.println("***** Frenando *****");
                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxDescendente() / 3.6, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1];
                progresivaActual = progresivaActual - distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("tiempo " + tiempo);
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }

        }

        return velocidad;

    }

    public double frenadoSinRestriccion(boolean sentido, Segmento segmento,
            MaterialRodante materialRodante, double tiempo, Estacion estacion) {

        if (sentido == true) {
            if (velocidad > segmento.getVelocidadMaxAscendente() / 3.6) {
                System.out.println("***** Frenando *****");

                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxAscendente() / 3.6, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1] - ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
                progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
                progresivaActual += distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }

        } else {
            if (velocidad > segmento.getVelocidadMaxDescendente() / 3.6) {
                System.out.println("***** Frenando *****");
                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxDescendente() / 3.6, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1];
                progresivaActual = segmento.getPkFinal();
                progresivaActual -= distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }
        }

        return velocidad;

    }

    static double[] freno(double velInicio, double velFinal, double desaceleracion) {
        double[] distanciaTiempo = new double[2];
        distanciaTiempo[0] = Math.abs((((velFinal * velFinal) / 3.6) - (velInicio * velInicio) / 3.6) / 2 * Math.abs(desaceleracion));
        distanciaTiempo[1] = Math.sqrt(distanciaTiempo[0] / Math.abs(desaceleracion));
        return distanciaTiempo;
    }

    public double frenoEnParada(Estacion estacion, boolean sentido) {
        if (sentido == true) {
            if (progresivaActual >= estacion.getPkEstacion()) {
                System.out.println("***** Frenando En Estacion *****" + estacion.getEstacionPK().getIdNombreEstacion());

                double[] distanciaTiempo = freno(velocidad, 0, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1] - ((estacion.getPkEstacion() - progresivaActual) / velocidad);
                progresivaActual = estacion.getPkEstacion() + 1;
                velocidad = 0;
                System.out.println("velocidad: " + velocidad);
                System.out.println("progresiva actual: " + progresivaActual);
                System.out.println("Tiempo: " + tiempo);
                return velocidad;
            }
        } else {

            System.out.println("***** Frenando En Estacion *****" + estacion.getEstacionPK().getIdNombreEstacion());

            double[] distanciaTiempo = freno(velocidad, 0, materialRodante.getDesaceleracionMax());
            tiempo += distanciaTiempo[1] - ((estacion.getPkEstacion() - progresivaActual) / velocidad);
           
            progresivaActual = estacion.getPkEstacion() - 1;
            if(progresivaActual==-1){
                progresivaActual=0;
            }
            velocidad = 0;
            System.out.println("velocidad: " + velocidad);
            System.out.println("progresiva actual: " + progresivaActual);
            System.out.println("Tiempo: " + tiempo);
            return velocidad;

        }

        return velocidad;

    }

}
