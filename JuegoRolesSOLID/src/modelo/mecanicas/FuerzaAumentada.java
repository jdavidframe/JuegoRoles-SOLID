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
public class FuerzaAumentada implements IEstadoAlterado {

    private int turnosRestantes;
    private int bonoAtaque;

    public FuerzaAumentada(int turnosRestantes, int bonoAtaque) {
        this.turnosRestantes = turnosRestantes;
        this.bonoAtaque = bonoAtaque;
    }

    @Override
    public String aplicarEfecto(Personaje p) {
        turnosRestantes--;
        return "[BUFF] " + p.getNombre()
                + " siente una tremenda oleada de fuerza (ATK +" + bonoAtaque + ").";
    }

    @Override
    public boolean haTerminado() {
        return turnosRestantes <= 0;
    }

    @Override
    public String getNombre() {
        return "Fuerza Aumentada";
    }

    @Override
    public boolean limitaAccion() {
        return false;
    }

    @Override
    public int modificarAtaque() {
        return bonoAtaque; // OCP: Modificador dinámico calculado por la base
    }
}
