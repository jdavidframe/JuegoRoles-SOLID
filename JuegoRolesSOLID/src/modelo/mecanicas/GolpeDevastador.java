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
public class GolpeDevastador implements IHabilidad {

    @Override
    public String usar(Personaje usuario, Personaje objetivo) {
        int dañoEspecial = usuario.calcularAtaque() * 2;
        objetivo.recibirDaño(dañoEspecial);
        return usuario.getNombre() + " ejecuta un Golpe Devastador e inflige " 
                + dañoEspecial + " de daño masivo a " + objetivo.getNombre() + ".";
    }

    @Override
    public String getNombre() {
        return "Golpe Devastador";
    }

    @Override
    public int getCostoEnergia() {
        return 30;
    }

    @Override
    public int getCooldownMax() {
        return 3;
    }
}
