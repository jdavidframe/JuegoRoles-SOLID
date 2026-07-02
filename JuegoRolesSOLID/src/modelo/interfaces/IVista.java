/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.interfaces;
import modelo.base.Personaje;
/**
 *
 * @author jesudavi
 */
public interface IVista {
    void mostrarMensaje(String mensaje);
    void mostrarEstadoPersonaje(Personaje p);
    void mostrarInicioCombate(Personaje p1, Personaje p2);
    void mostrarFinCombate(Personaje ganador);
}
