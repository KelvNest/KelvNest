/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
//import controlador.CurvaEsfuerzoJpaController;
//import controlador.EstacionJpaController;
//import controlador.MaterialRodanteJpaController;
//import controlador.RestriccionJpaController;
//import controlador.SegmentoJpaController;
import modelo.entity.CurvaEsfuerzo;
import modelo.entity.Estacion;
import modelo.entity.MaterialRodante;
import modelo.entity.Restriccion;
import modelo.entity.Segmento;
import java.util.ArrayList;
import modelo.controlBD.CurvaEsfuerzoJpaController;
import modelo.controlBD.EstacionJpaController;
import modelo.controlBD.MaterialRodanteJpaController;
import modelo.controlBD.RestriccionJpaController;
import modelo.controlBD.SegmentoJpaController;

/**
 *
 * @author usuario
 */
public class CalculoMarchaTipo {

    MaterialRodanteJpaController mrjc = new MaterialRodanteJpaController(Conex.getEmf());
    CurvaEsfuerzoJpaController cejc = new CurvaEsfuerzoJpaController(Conex.getEmf());
    SegmentoJpaController sjc = new SegmentoJpaController(Conex.getEmf());
    RestriccionJpaController rjc = new RestriccionJpaController(Conex.getEmf());
    MaterialRodante materialRodante;
    public double velocidad = 0;
    double traccion;
    List<CurvaEsfuerzo> ce;
    List<Segmento> segmento;
    double resistenciaEnCurva = 0;
    double resistenciaEnRecta = 0;
    double fuerzaResultante = 0;
    double resistenciaRampa = 0;
    double aceleracion = 0;
    double velocidadFinal = 0;
    double tiempoFinal = 0;
    double tiempo = 0;
    double progresivaActual;
    int r = 0;
    double progresivaFinal;
    EstacionJpaController ejc = new EstacionJpaController(Conex.getEmf());
    int e = 1;
    List<Double> cambiosVelocidad = new ArrayList<>();
    List<Double> cambiosProgresiva = new ArrayList<>();
    List<Double> tiempoEstaciones = new ArrayList<>();
    boolean acelerar = true;
    boolean agregado = false;

    public CalculoMarchaTipo(List<Segmento> segmentos, boolean sentido, List<Estacion> estaciones, int idLinea, int idMaterialRodante, double velocidadMarcha, List<Restriccion> restriccion) {

        simular(segmentos, sentido, estaciones, idLinea, idMaterialRodante, velocidadMarcha, restriccion);
    }

    public void simular(List<Segmento> segmentos, boolean sentido, List<Estacion> estaciones,
            int idLinea, int idMaterialRodante, double velocidadMarcha, List<Restriccion> restriccion) {
        materialRodante = mrjc.findMaterialRodante(idMaterialRodante);
        ce = cejc.curvaDelMaterialRodante(idMaterialRodante);

        e = 1;
        progresivaActual = estaciones.get(0).getPkEstacion();
        progresivaFinal = estaciones.get(estaciones.size() - 1).getPkEstacion();
        cambiosVelocidad.add(0.0);
        tiempoEstaciones.add(0.0);
        cambiosProgresiva.add(progresivaActual);
        System.out.println("progresiva actual: " + progresivaActual);

        for (Segmento segmentoActual : segmentos) {
            if (sentido == true) {
                if (progresivaActual < progresivaFinal) {
                    while (progresivaActual < segmentoActual.getPkFinal()) {
                        System.out.println("--------------------------Posicion del segmento: " + segmentos.indexOf(segmentoActual) + " ---------------------------------");
                        System.out.println("progresiva actual " + progresivaActual);

                        if (restriccion != null) {
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
                            if (progresivaActual >= restriccion.get(r).getProgInicio()
                                    && progresivaActual < restriccion.get(r).getProgFinal()) {
                                if (velocidad < restriccion.get(r).getVelocidadMaxAscendente() / 3.6) {
                                    velocidad = acelerar(segmentoActual, restriccion.get(r), sentido, estaciones.get(e), velocidadMarcha);

                                    if (progresivaActual >= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                        break;
                                    }
                                }
                            } else {
                                if ((velocidad < velocidadMarcha / 3.6 && segmentoActual.getVelocidadMaxAscendente() >= velocidadMarcha) || velocidad < segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                                    velocidad = acelerar(segmentoActual, restriccion.get(r), sentido, estaciones.get(e), velocidadMarcha);

                                    if (progresivaActual >= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                        break;
                                    }
                                }
                            }

                            if (velocidad == velocidadMarcha / 3.6 || velocidad == segmentoActual.getVelocidadMaxAscendente() / 3.6 || velocidad == restriccion.get(r).getVelocidadMaxAscendente() / 3.6) {
                                velocidad = velocidadCrucero(segmentoActual, restriccion.get(r), sentido, estaciones.get(e));
                                if (progresivaActual >= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                    break;
                                }
                            }
                            if (velocidad > velocidadMarcha / 3.6
                                    || velocidad > segmentoActual.getVelocidadMaxAscendente() / 3.6
                                    || (velocidad > restriccion.get(r).getVelocidadMaxAscendente() / 3.6
                                    && progresivaActual >= restriccion.get(r).getProgInicio() && progresivaActual < restriccion.get(r).getProgFinal())) {
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

                                if ((velocidad < velocidadMarcha / 3.6 && segmentoActual.getVelocidadMaxAscendente() >= velocidadMarcha) || velocidad < segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                                    velocidad = acelerarSinRestriccion(segmentoActual, sentido, estaciones.get(e), velocidadMarcha);

                                }
                                if (velocidad == velocidadMarcha / 3.6 || velocidad == segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                                    velocidad = velocidadCruceroSinRestriccion(segmentoActual, sentido, estaciones.get(e));

                                }
                                if (velocidad > velocidadMarcha / 3.6 || velocidad > segmentoActual.getVelocidadMaxAscendente() / 3.6) {
                                    System.out.println("FRENOOOOOOOOOOOOOOOO");
                                    System.out.println("velocidad " + velocidad);
                                    velocidad = frenadoSinRestriccion(sentido, segmentoActual, materialRodante, tiempo, estaciones.get(e));

                                }
                            } else {
                                break;
                            }

                        }
                    }
                } else {
                    break;
                }
            } else {
                if (progresivaActual > progresivaFinal) {
                    System.out.println("--------------------------Posicion del segmento: " + segmentos.indexOf(segmentoActual) + " ---------------------------------");
                    while (progresivaActual > segmentoActual.getSegmentoPK().getIdPkInicial()) {
                        System.out.println("progresiva actual " + progresivaActual);
                        if (restriccion != null) {
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
                            if (progresivaActual > restriccion.get(r).getProgInicio() && progresivaActual <= restriccion.get(r).getProgFinal()) {
//                                if ((velocidad < velocidadMarcha / 3.6 && segmentoActual.getVelocidadMaxDescendente() >= velocidadMarcha) || (velocidad < segmentoActual.getVelocidadMaxDescendente() / 3.6 && velocidadMarcha > segmentoActual.getVelocidadMaxDescendente()) || velocidad < restriccion.get(r).getVelocidadMaxDescendente() / 3.6) {
//                                if ((velocidad < velocidadMarcha / 3.6 && segmentoActual.getVelocidadMaxDescendente() >= velocidadMarcha ) || (velocidad < segmentoActual.getVelocidadMaxDescendente() / 3.6 && velocidadMarcha > segmentoActual.getVelocidadMaxDescendente()) || velocidad < restriccion.get(r).getVelocidadMaxDescendente() / 3.6) {
                                if (velocidad < restriccion.get(r).getVelocidadMaxDescendente() / 3.6) {
//                                   
//                                    System.out.println("Condicion 1 " + (velocidad < velocidadMarcha / 3.6));
//                                    System.out.println("Condicion 2 " + (segmentoActual.getVelocidadMaxDescendente() >= velocidadMarcha));
//                                    System.out.println("Condicion 3 " + (velocidad < segmentoActual.getVelocidadMaxDescendente() / 3.6));
//                                    System.out.println("Condicion 4 " + (velocidadMarcha > segmentoActual.getVelocidadMaxDescendente()));
                                    velocidad = acelerar(segmentoActual, restriccion.get(r), sentido, estaciones.get(e), velocidadMarcha);

                                    if (progresivaActual <= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                        break;
                                    }
                                }
                            } else {
                                if ((velocidad < velocidadMarcha / 3.6 && segmentoActual.getVelocidadMaxDescendente() >= velocidadMarcha) || (velocidad < segmentoActual.getVelocidadMaxDescendente() / 3.6 && velocidadMarcha > segmentoActual.getVelocidadMaxDescendente())) {
//                                    System.out.println("Condicion 1 " + (velocidad < velocidadMarcha / 3.6));
//                                    System.out.println("Condicion 2 " + (segmentoActual.getVelocidadMaxDescendente() >= velocidadMarcha));
//                                    System.out.println("Condicion 3 " + (velocidad < segmentoActual.getVelocidadMaxDescendente() / 3.6));
//                                    System.out.println("Condicion 4 " + (velocidadMarcha > segmentoActual.getVelocidadMaxDescendente()));
                                    velocidad = acelerar(segmentoActual, restriccion.get(r), sentido, estaciones.get(e), velocidadMarcha);

                                    if (progresivaActual <= estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                        break;
                                    }
                                }
                            }
                            if (velocidad == velocidadMarcha / 3.6 || velocidad == segmentoActual.getVelocidadMaxDescendente() / 3.6 || velocidad == restriccion.get(r).getVelocidadMaxDescendente() / 3.6) {
                                velocidad = velocidadCrucero(segmentoActual, restriccion.get(r), sentido, estaciones.get(e));

                            }
                            if (velocidad > velocidadMarcha / 3.6 || (velocidad > segmentoActual.getVelocidadMaxDescendente() / 3.6 && progresivaActual <= segmentoActual.getPkFinal() && velocidadMarcha > segmentoActual.getVelocidadMaxDescendente()) || (velocidad > restriccion.get(r).getVelocidadMaxDescendente() / 3.6 && progresivaActual <= restriccion.get(r).getProgFinal() && progresivaActual > restriccion.get(r).getProgInicio())) {
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

                            if ((velocidad < velocidadMarcha / 3.6 && segmentoActual.getVelocidadMaxDescendente() >= velocidadMarcha) || (velocidad < segmentoActual.getVelocidadMaxDescendente() / 3.6 && velocidadMarcha > segmentoActual.getVelocidadMaxDescendente())) {
                                velocidad = acelerarSinRestriccion(segmentoActual, sentido, estaciones.get(e), velocidadMarcha);
                                if (progresivaActual < estaciones.get(estaciones.size() - 1).getPkEstacion()) {
                                    break;
                                }

                            }
                            if (velocidad == velocidadMarcha / 3.6 || velocidad == segmentoActual.getVelocidadMaxDescendente() / 3.6) {
                                velocidad = velocidadCruceroSinRestriccion(segmentoActual, sentido, estaciones.get(e));

                            }
                            if (velocidad > velocidadMarcha / 3.6 || velocidad > segmentoActual.getVelocidadMaxDescendente() / 3.6) {
                                System.out.println("FRENOOOOOOOOOOOOOOOO");
                                System.out.println("velocidad " + velocidad);
                                velocidad = frenadoSinRestriccion(sentido, segmentoActual, materialRodante, tiempo, estaciones.get(e));

                            }

                        }
                    }
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < cambiosVelocidad.size(); i++) {
            System.out.println("velocidad: " + cambiosVelocidad.get(i));
            System.out.println("progresiva: " + cambiosProgresiva.get(i));

        }
        for (Double tiempoEstacione : tiempoEstaciones) {
            System.out.println("TIEMPO: " + tiempoEstacione);
        }
    }

    public double acelerar(Segmento segmento, Restriccion restriccion, boolean sentido, Estacion estacion, double velocidadMarcha) {
        if (acelerar == false && agregado == false && !cambiosProgresiva.get(cambiosProgresiva.size() - 1).equals(Math.rint((progresivaActual * 10) / 10))) {
//            System.out.println("AGREGO AL ENTRAR A Acelerar");
            cambiosVelocidad.add(Math.rint(((velocidad * 3.6) * 10) / 10));
//            System.out.println(velocidad * 3.6);
            cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
//            System.out.println(progresivaActual);
            agregado = true;
        }
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
            System.out.println(" velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
            if (sentido == true) {
                progresivaActual++;
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                if (progresivaActual > segmento.getPkFinal()) {
                    agregado = false;
                    acelerar = true;
                    return velocidad;
                }
                if (velocidad > velocidadMarcha / 3.6) {
                    velocidad = velocidadMarcha / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;
                }
                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxAscendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;
                }
                if (progresivaActual >= restriccion.getProgInicio() && progresivaActual < restriccion.getProgFinal()) {
                    if (velocidad > (restriccion.getVelocidadMaxAscendente() / 3.6)) {
                        velocidad = restriccion.getVelocidadMaxAscendente() / 3.6;
                        if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                            cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                            cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                            agregado = false;
                            acelerar = true;
                        }
                        return velocidad;
                    }

                } else {
                }

            } else {
                progresivaActual--;
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                if (progresivaActual < segmento.getSegmentoPK().getIdPkInicial()) {
                    agregado = false;
                    acelerar = true;
                    return velocidad;
                }
                if (velocidad > velocidadMarcha / 3.6) {
                    velocidad = velocidadMarcha / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;
                }
                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxDescendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxDescendente() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;
                }
                if (progresivaActual < restriccion.getProgFinal() && progresivaActual > restriccion.getProgInicio()) {
                    if (velocidad > (restriccion.getVelocidadMaxDescendente() / 3.6)) {
                        velocidad = restriccion.getVelocidadMaxDescendente() / 3.6;
                        if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                            cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                            cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                            agregado = false;
                            acelerar = true;
                        }
                        return velocidad;
                    }
                }

            }
            System.out.println(" velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
        }

    }

    public double acelerarSinRestriccion(Segmento segmento, boolean sentido, Estacion estacion, double velocidadMarcha) {
        if (acelerar == false && agregado == false && !cambiosProgresiva.get(cambiosProgresiva.size() - 1).equals(progresivaActual)) {
            cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
//            System.out.println("AGREGO AL ENTRAR a Acelerar Sin Restriccion");
            cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
            agregado = true;
        }

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
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                if (progresivaActual > segmento.getPkFinal()) {
                    agregado = false;
                    acelerar = true;
                    return velocidad;
                }
                if (velocidad > velocidadMarcha / 3.6) {
                    velocidad = velocidadMarcha / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;
                }
                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxAscendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }

                    return velocidad;
                }

            } else {
                progresivaActual -= 1;
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                    if (progresivaActual < estacion.getPkEstacion()) {
                        return velocidad;
                    }
                }
                if (progresivaActual < segmento.getSegmentoPK().getIdPkInicial()) {
                    agregado = false;
                    acelerar = true;
                    return velocidad;

                }
                if (velocidad > velocidadMarcha / 3.6) {
                    velocidad = velocidadMarcha / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;
                }

                if (velocidad > (materialRodante.getVelocidadOperativa() / 3.6)) {
                    velocidad = materialRodante.getVelocidadOperativa() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;

                }
                if (velocidad > (segmento.getVelocidadMaxDescendente() / 3.6)) {
                    velocidad = segmento.getVelocidadMaxDescendente() / 3.6;
                    if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                        cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                        agregado = false;
                        acelerar = true;
                    }
                    return velocidad;
                }

            }
            System.out.println(" velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
        }

    }

    public double velocidadCrucero(Segmento segmento, Restriccion restriccion, boolean sentido, Estacion estacion) {
        System.out.println("VELOCIDAD CRUCERO" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
        if (sentido == true) {
            System.out.println(restriccion.getProgInicio());
            if (progresivaActual > restriccion.getProgInicio() && progresivaActual < restriccion.getProgFinal()) {
                tiempoFinal = tiempo + ((restriccion.getProgFinal() - progresivaActual) / velocidad);
                acelerar = false;
                progresivaActual = restriccion.getProgFinal() + 1;
                tiempo = tiempoFinal;
                System.out.println("de inicio a fin de una restriccion" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
                if (progresivaActual >= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }
            if (segmento.getPkFinal() > restriccion.getProgInicio() && progresivaActual < restriccion.getProgInicio()) {
                tiempoFinal = tiempo + ((restriccion.getProgInicio() - progresivaActual) / velocidad);
                acelerar = false;
                progresivaActual = restriccion.getProgInicio() + 1;
                tiempo = tiempoFinal;
                System.out.println("Des inicio de un segmento a inicio de una restriccion" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
                if (progresivaActual >= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;

            } else {
                acelerar = false;
                tiempoFinal = tiempo + ((segmento.getPkFinal() - progresivaActual) / velocidad);
                progresivaActual = segmento.getPkFinal();
                tiempo = tiempoFinal;
                System.out.println("de inicio a fin de un segmento" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
                if (progresivaActual >= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }

        } else {
            if (restriccion.getProgFinal() > progresivaActual && progresivaActual > restriccion.getProgInicio()) {
                acelerar = false;
                tiempoFinal = tiempo + ((progresivaActual - restriccion.getProgInicio()) / velocidad);
                progresivaActual = restriccion.getProgInicio();
                tiempo = tiempoFinal;
                System.out.println("de inicio a fin de una restriccion" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
                if (progresivaActual <= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }
            if (segmento.getSegmentoPK().getIdPkInicial() <= restriccion.getProgFinal() && progresivaActual > restriccion.getProgFinal()) {
                acelerar = false;
                tiempoFinal = tiempo + ((progresivaActual - restriccion.getProgFinal()) / velocidad);
                progresivaActual = restriccion.getProgFinal() - 1;
                tiempo = tiempoFinal;
                System.out.println("De inicio de un segmento a inicio de una restriccion" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
                if (progresivaActual <= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;

            } else {
                acelerar = false;
                tiempoFinal = tiempo + ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
                progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
                tiempo = tiempoFinal;
                System.out.println("De inicio a Final de una Segmento" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
                if (progresivaActual <= estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                }
                return velocidad;
            }

        }

    }

    public double velocidadCruceroSinRestriccion(Segmento segmento, boolean sentido, Estacion estacion) {
        System.out.println("VELOCIDAD CRUCERO" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
        //acelerar=false;
        if (sentido == true) {
            acelerar = false;
            tiempoFinal = tiempo + ((segmento.getPkFinal() - progresivaActual) / velocidad);
            progresivaActual = segmento.getPkFinal();
            tiempo = tiempoFinal;
            System.out.println("Sentido Ascendente" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
            if (progresivaActual >= estacion.getPkEstacion()) {
                frenoEnParada(estacion, sentido);
            }
            return velocidad;

        } else {
            acelerar = false;
            tiempoFinal = tiempo + ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
            progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
            tiempo = tiempoFinal;
            System.out.println("Sentido descendente" + "\n velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo acumulado: " + tiempo);
            if (progresivaActual <= estacion.getPkEstacion()) {
                frenoEnParada(estacion, sentido);
            }
            return velocidad;

        }

    }

    public double frenado(boolean sentido, Segmento segmento, Restriccion restriccion,
            MaterialRodante materialRodante, Estacion estacion) {
        if (sentido == true) {
            if (velocidad > segmento.getVelocidadMaxAscendente() / 3.6) {
                if (estacion.getPkEstacion() <= segmento.getPkFinal()) {
                    double[] distanciaTiempo = freno(velocidad, 0, materialRodante.getDesaceleracionMax());
                    if (distanciaTiempo[0] > (estacion.getPkEstacion() - segmento.getSegmentoPK().getIdPkInicial())) {
                        frenoEnParada(estacion, sentido);
                        return velocidad;
                    }
                }
                System.out.println("***** Frenando *****");
                acelerar = false;
                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxAscendente() / 3.6, materialRodante.getDesaceleracionMax());
                if (!cambiosProgresiva.get(cambiosProgresiva.size() - 1).equals(Math.rint((progresivaActual * 10) / 10))) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                tiempo += distanciaTiempo[1] - ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
                progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
                progresivaActual += distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n Tiempo: " + tiempo);
                System.out.println();
                System.out.println();
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                    return velocidad;
                }
                if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                return velocidad;
            }
            if (velocidad > restriccion.getVelocidadMaxAscendente() / 3.6) {
                acelerar = false;
                if (estacion.getPkEstacion() <= restriccion.getProgFinal()) {
                    double[] distanciaTiempo = freno(velocidad, 0, materialRodante.getDesaceleracionMax());
                    if (distanciaTiempo[0] > (estacion.getPkEstacion() - restriccion.getProgInicio())) {
                        frenoEnParada(estacion, sentido);
                        return velocidad;
                    }
                }
                System.out.println("***** Frenando ***** por resticcion-----------");
                double[] distanciaTiempo = freno(velocidad, restriccion.getVelocidadMaxAscendente() / 3.6, materialRodante.getDesaceleracionMax());
                if (!cambiosProgresiva.get(cambiosProgresiva.size() - 1).equals(Math.rint((progresivaActual * 10) / 10))) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                tiempo += distanciaTiempo[1];
                progresivaActual = restriccion.getProgInicio();
                progresivaActual += distanciaTiempo[0];
                velocidad = restriccion.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                    return velocidad;
                }
                if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }

                return velocidad;

            }
        } else {
            if (velocidad > restriccion.getVelocidadMaxDescendente() / 3.6 && progresivaActual <= restriccion.getProgFinal() && progresivaActual > restriccion.getProgInicio()) {
                acelerar = false;
                if (estacion.getPkEstacion() >= restriccion.getProgInicio()) {
                    double[] distanciaTiempo = freno(velocidad, 0, materialRodante.getDesaceleracionMax());
                    if (distanciaTiempo[0] > (restriccion.getProgFinal() - estacion.getPkEstacion())) {
                        frenoEnParada(estacion, sentido);
                        return velocidad;
                    }
                }
                double[] distanciaTiempo = freno(velocidad, restriccion.getVelocidadMaxDescendente() / 3.6, materialRodante.getDesaceleracionMax());

                if (!cambiosProgresiva.get(cambiosProgresiva.size() - 1).equals(Math.rint((progresivaActual * 10) / 10))) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                tiempo += distanciaTiempo[1];
                progresivaActual = restriccion.getProgFinal();
                progresivaActual = progresivaActual - distanciaTiempo[0];
                velocidad = restriccion.getVelocidadMaxDescendente() / 3.6;
                System.out.println("***** Frenando Akiiii*****" + "\n progresiva actual: " + progresivaActual
                        + "\n velocidad: " + velocidad + "\n tiempo " + tiempo);
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                    return velocidad;
                }
                if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                return velocidad;
            }
            if (velocidad > segmento.getVelocidadMaxDescendente() / 3.6) {
                acelerar = false;
                if (estacion.getPkEstacion() >= segmento.getSegmentoPK().getIdPkInicial()) {
                    double[] distanciaTiempo = freno(velocidad, 0, materialRodante.getDesaceleracionMax());
                    if (distanciaTiempo[0] > (segmento.getPkFinal() - estacion.getPkEstacion())) {
                        frenoEnParada(estacion, sentido);
                        return velocidad;
                    }
                }
                System.out.println("***** Frenando *****////////");
                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxDescendente() / 3.6, materialRodante.getDesaceleracionMax());
                if (!cambiosProgresiva.get(cambiosProgresiva.size() - 1).equals(Math.rint((progresivaActual * 10) / 10))) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                tiempo += distanciaTiempo[1];
                progresivaActual = segmento.getPkFinal();
                progresivaActual = progresivaActual - distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxDescendente() / 3.6;
                System.out.println(segmento.getVelocidadMaxAscendente() / 3.6);
                System.out.println("velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n tiempo " + tiempo);
                System.out.println();
                System.out.println();
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                    return velocidad;
                }
                if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
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
                System.out.println("***** Frenando sin restriccion*****");
                acelerar = false;
                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxAscendente() / 3.6, materialRodante.getDesaceleracionMax());
                cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                cambiosProgresiva.add(Math.rint((segmento.getSegmentoPK().getIdPkInicial() * 10) / 10));
                tiempo += distanciaTiempo[1] - ((progresivaActual - segmento.getSegmentoPK().getIdPkInicial()) / velocidad);
                progresivaActual = segmento.getSegmentoPK().getIdPkInicial();
                progresivaActual += distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxAscendente() / 3.6;
                System.out.println("velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual);
                if (progresivaActual > estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                    return velocidad;
                }
                if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                return velocidad;
            }

        } else {
            if (velocidad > segmento.getVelocidadMaxDescendente() / 3.6) {
                acelerar = false;
                System.out.println("***** Frenando *****");
                double[] distanciaTiempo = freno(velocidad, segmento.getVelocidadMaxDescendente() / 3.6, materialRodante.getDesaceleracionMax());
                tiempo += distanciaTiempo[1];
                cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                cambiosProgresiva.add(Math.rint((segmento.getPkFinal() * 10) / 10));
                progresivaActual = segmento.getPkFinal();
                progresivaActual -= distanciaTiempo[0];
                velocidad = segmento.getVelocidadMaxDescendente() / 3.6;
                System.out.println("velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual);
                if (progresivaActual < estacion.getPkEstacion()) {
                    frenoEnParada(estacion, sentido);
                    return velocidad;
                }
                if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));

                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }
                return velocidad;
            }
        }

        return velocidad;

    }

    static double[] freno(double velInicio, double velFinal, double desaceleracion) {
        double[] distanciaTiempo = new double[2];
        distanciaTiempo[0] = Math.abs(((velFinal * velFinal) - (velInicio * velInicio)) / (2 * Math.abs(desaceleracion)));
//        System.out.println("velocidad al frenar: " + velInicio);
//        System.out.println("velocidad final: " + velFinal);
//        System.out.println("Desaceleracion: " + desaceleracion);
//        System.out.println("Distacia: " + distanciaTiempo[0]);
        distanciaTiempo[1] = Math.sqrt(distanciaTiempo[0] / Math.abs(desaceleracion));
        return distanciaTiempo;
    }

    public double frenoEnParada(Estacion estacion, boolean sentido) {
        if (sentido == true) {
            if (progresivaActual >= estacion.getPkEstacion()) {
                acelerar = false;
                System.out.println("***** Frenando En Estacion *****" + estacion.getEstacionPK().getIdNombreEstacion());

                double[] distanciaTiempo = freno(velocidad, 0.0, materialRodante.getDesaceleracionMax());

                if (cambiosProgresiva.get(cambiosProgresiva.size() - 1) > (estacion.getPkEstacion() - distanciaTiempo[0])) {
                    cambiosProgresiva.remove(cambiosProgresiva.size() - 1);
                    cambiosVelocidad.remove(cambiosVelocidad.size() - 1);

                }

                cambiosVelocidad.add(Math.rint(Math.rint((velocidad * 3.6 * 10) / 10)));
                cambiosProgresiva.add(Math.rint((estacion.getPkEstacion() - distanciaTiempo[0]) * 10) / 10);
                tiempo += distanciaTiempo[1] - ((progresivaActual - estacion.getPkEstacion()) / velocidad);

                progresivaActual = estacion.getPkEstacion() + 1;
                velocidad = 0;
                System.out.println("velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n Tiempo: " + tiempo);
                double tiempoEntreEstaciones = tiempo;
                for (int i = 0; i < tiempoEstaciones.size(); i++) {

                    tiempoEntreEstaciones -= tiempoEstaciones.get(i);

                }
                tiempoEstaciones.add(tiempoEntreEstaciones);

                if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                    cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                    if (progresivaActual > progresivaFinal) {
                        cambiosProgresiva.add(progresivaFinal);
                    } else {
                        cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                    }

                }
                return velocidad;
            }
        } else {
            acelerar = false;
            System.out.println("***** Frenando En Estacion *****" + estacion.getEstacionPK().getIdNombreEstacion());
            cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
            double[] distanciaTiempo = freno(velocidad, 0.0, materialRodante.getDesaceleracionMax());
            tiempo += distanciaTiempo[1] - ((estacion.getPkEstacion() - progresivaActual) / velocidad);
            if (cambiosProgresiva.get(cambiosProgresiva.size() - 1) < (estacion.getPkEstacion() + distanciaTiempo[0])) {
                cambiosProgresiva.remove(cambiosProgresiva.size() - 1);
                cambiosVelocidad.remove(cambiosVelocidad.size() - 1);
            }
            cambiosProgresiva.add(Math.rint((estacion.getPkEstacion() + distanciaTiempo[0]) * 10) / 10);
            progresivaActual = estacion.getPkEstacion() - 1;
            velocidad = 0;
            System.out.println("velocidad: " + velocidad + "\n progresiva actual: " + progresivaActual + "\n Tiempo: " + tiempo);
            double tiempoEntreEstaciones = tiempo;
            for (int i = 0; i < tiempoEstaciones.size(); i++) {

                tiempoEntreEstaciones -= tiempoEstaciones.get(i);

            }
            tiempoEstaciones.add(tiempoEntreEstaciones);

            if (!cambiosVelocidad.get(cambiosVelocidad.size() - 1).equals(velocidad * 3.6)) {
                cambiosVelocidad.add(Math.rint((velocidad * 3.6 * 10) / 10));
                if (progresivaActual < 0) {
                    cambiosProgresiva.add(0.0);
                } else {
                    cambiosProgresiva.add(Math.rint((progresivaActual * 10) / 10));
                }

            }
            return velocidad;

        }

        return velocidad;

    }

    public double getVelocidad() {
        return velocidad;
    }

    public double getTiempo() {
        return tiempo;
    }

    public double getProgresivaFinal() {
        return progresivaFinal;
    }

    public List<Double> getCambiosVelocidad() {
        return cambiosVelocidad;
    }

    public List<Double> getCambiosProgresiva() {
        return cambiosProgresiva;
    }

    public List<Double> getTiempoEstaciones() {
        return tiempoEstaciones;
    }

}
