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
public interface IEstadoAlterado {

    String aplicarEfecto(Personaje p);

    boolean haTerminado();

    String getNombre();

    boolean limitaAccion();

    default int modificarAtaque() {
        return 0;
    }

    default int modificarDefensa() {
        return 0;
    }
}
