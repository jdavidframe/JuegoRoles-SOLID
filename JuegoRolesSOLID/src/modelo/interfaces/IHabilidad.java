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
public interface IHabilidad {

    String usar(Personaje usuario, Personaje objetivo);

    String getNombre();

    int getCostoEnergia();

    int getCooldownMax();
}
