/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mecanicas;

import modelo.base.Personaje;
import modelo.interfaces.IEstadoAlterado;

/**
 *
 * @author jesudavi
 */
public class Congelado implements IEstadoAlterado {

    private int turnosRestantes;

    public Congelado(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    @Override
    public String aplicarEfecto(Personaje p) {
        turnosRestantes--;
        return "[ESTADO] " + p.getNombre() + " está congelado y no puede actuar.";
    }

    @Override
    public boolean haTerminado() {
        return turnosRestantes <= 0;
    }

    @Override
    public String getNombre() {
        return "Congelado";
    }

    @Override
    public boolean limitaAccion() {
        return true;
    } 
}
