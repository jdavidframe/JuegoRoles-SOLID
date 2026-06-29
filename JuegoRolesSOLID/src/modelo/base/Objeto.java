/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.base;

/**
 *
 * @author jesudavi
 */
public abstract class Objeto {

    protected String nombre;

    public Objeto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBonificadorAtaque() {
        return 0;
    }

    public int getBonificadorDefensa() {
        return 0;
    }
}
