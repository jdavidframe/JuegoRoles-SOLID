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
public class Envenenado implements IEstadoAlterado {

    private int turnosRestantes;
    private int danoPorTurno;

    public Envenenado(int turnosRestantes, int danoPorTurno) {
        this.turnosRestantes = turnosRestantes;
        this.danoPorTurno = danoPorTurno;
    }

    @Override
    public String aplicarEfecto(Personaje p) {
        p.recibirDaño(danoPorTurno);
        turnosRestantes--;
        return "  [ESTADO] " + p.getNombre() + " sufre " + danoPorTurno
                + " de daño por Veneno.";
    }

    @Override
    public boolean haTerminado() {
        return turnosRestantes <= 0;
    }

    @Override
    public String getNombre() {
        return "Envenenado";
    }

    @Override
    public boolean limitaAccion() {
        return false;
    }
}
