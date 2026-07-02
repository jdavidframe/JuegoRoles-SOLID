/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.base.Personaje;
import modelo.interfaces.IVista;

/**
 *
 * @author jesudavi
 */
public class SistemaCombate {

    private final IVista vista; // DIP aplicado: depende de la interfaz

    public SistemaCombate(IVista vista) { // Inyección de dependencia abstracta
        this.vista = vista;
    }

    public void iniciarCombate(Personaje jugador1, Personaje jugador2) {
        vista.mostrarInicioCombate(jugador1, jugador2);
        int ronda = 1;

        while (jugador1.getVidaActual() > 0 && jugador2.getVidaActual() > 0) {
            vista.mostrarMensaje("--- RONDA " + ronda + " ---");
            vista.mostrarEstadoPersonaje(jugador1);
            vista.mostrarEstadoPersonaje(jugador2);
            vista.mostrarMensaje("");

            if (jugador1.getVidaActual() > 0) {
                ejecutarTurnoPersonaje(jugador1, jugador2);
            }

            if (jugador2.getVidaActual() > 0 && jugador1.getVidaActual() > 0) {
                ejecutarTurnoPersonaje(jugador2, jugador1);
            }

            ronda++;
            vista.mostrarMensaje("\n-------------------------------------------------\n");

            if (ronda > 6) {
                vista.mostrarMensaje("[ALERTA] Se ha alcanzado el límite de 6 rondas. Combate detenido.");
                break;
            }
        }

        evaluarGanador(jugador1, jugador2);
    }

    private void ejecutarTurnoPersonaje(Personaje activo, Personaje objetivo) {
        List<String> reportesEstados = activo.procesarTurno();
        for (String reporte : reportesEstados) {
            if (reporte != null && !reporte.isEmpty()) {
                vista.mostrarMensaje(reporte);
            }
        }

        if (activo.getVidaActual() <= 0) {
            vista.mostrarMensaje("☠️ " + activo.getNombre() + " ha caído al inicio de su turno.");
            return;
        }

        if (activo.puedeActuar()) {
            if (activo.getCooldownHabilidad() == 0) {
                vista.mostrarMensaje(activo.usarHabilidadEspecial(objetivo));
            } else {
                vista.mostrarMensaje(activo.atacar(objetivo));
            }
        } else {
            vista.mostrarMensaje(activo.getNombre() + " pierde su oportunidad de actuar en esta ronda.");
        }
    }

    private void evaluarGanador(Personaje j1, Personaje j2) {
        if (j1.getVidaActual() > 0 && j2.getVidaActual() <= 0) {
            vista.mostrarFinCombate(j1);
        } else if (j2.getVidaActual() > 0 && j1.getVidaActual() <= 0) {
            vista.mostrarFinCombate(j2);
        } else {
            vista.mostrarFinCombate(null);
        }
    }
}
