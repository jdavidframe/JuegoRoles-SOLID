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
public class LluviaFlechas implements IHabilidad {

    @Override
    public String usar(Personaje usuario, Personaje objetivo) {
        int dañoImpacto = (int) (usuario.calcularAtaque() * 0.8);
        objetivo.recibirDaño(dañoImpacto);
        String resultado = usuario.getNombre() + " desata una Lluvia de Flechas. "
                + "¡Primer impacto de " + dañoImpacto + " de daño!";
        if (objetivo.getVidaActual() > 0) {
            objetivo.recibirDaño(dañoImpacto);
            resultado += " ¡Segundo impacto consecutivo por " + dañoImpacto + " de daño!";
        }
        return resultado;
    }

    @Override
    public String getNombre() {
        return "Lluvia de Flechas";
    }

    @Override
    public int getCostoEnergia() {
        return 25;
    }

    @Override
    public int getCooldownMax() {
        return 2;
    }
}
