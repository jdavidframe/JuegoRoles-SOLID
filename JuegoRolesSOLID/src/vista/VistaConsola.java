/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import modelo.base.Personaje;
/**
 *
 * @author jesudavi
 */

public class VistaConsola {

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarEstadoPersonaje(Personaje p) {
        System.out.println(" > " + p.getNombre() 
            + " -> HP: " + p.getVidaActual() 
            + " | Energía: " + p.getEnergiaActual() 
            + " | CD Habilidad: " + p.getCooldownHabilidad());
    }

    public void mostrarInicioCombate(Personaje p1, Personaje p2) {
        System.out.println("\n=================================================");
        System.out.println("       INICIA EL COMBATE EN LA ARENA SOLID       ");
        System.out.println("=================================================");
        System.out.println(" Retador 1: " + p1.getNombre() + " listo.");
        System.out.println(" Retador 2: " + p2.getNombre() + " listo.");
        System.out.println("=================================================\n");
    }

    public void mostrarFinCombate(Personaje ganador) {
        System.out.println("\n=================================================");
        if (ganador != null) {
            System.out.println(" ¡COMBATE FINALIZADO! VICTORIA DE: " + ganador.getNombre().toUpperCase());
        } else {
            System.out.println(" ¡EMPATE EN LA ARENA! AMBOS COMBATIENTES HAN CAÍDO");
        }
        System.out.println("=================================================");
    }
}
