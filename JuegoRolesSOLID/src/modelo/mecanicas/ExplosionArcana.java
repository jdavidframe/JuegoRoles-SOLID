/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mecanicas;

import modelo.base.Personaje;
import modelo.interfaces.IHabilidad;

/**
 *
 * @author jesudavi
 */
public class ExplosionArcana implements IHabilidad {

    @Override
    public String usar(Personaje usuario, Personaje objetivo) {
        int defensaReducida = objetivo.calcularDefensa() / 2;
        int dañoEspecial = usuario.calcularAtaque() - defensaReducida;
        if (dañoEspecial < 0) {
            dañoEspecial = 0;
        }
        objetivo.recibirDaño(dañoEspecial);
        return usuario.getNombre() + " canaliza una Explosión Arcana perforando las defensas de " 
                + objetivo.getNombre() + " por " + dañoEspecial + " de daño.";
    }

    @Override
    public String getNombre() {
        return "Explosión Arcana";
    }

    @Override
    public int getCostoEnergia() {
        return 40;
    }

    @Override
    public int getCooldownMax() {
        return 4;
    }
}
