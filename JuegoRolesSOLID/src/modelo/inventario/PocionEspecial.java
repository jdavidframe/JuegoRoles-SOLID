
package modelo.inventario;

import modelo.base.Objeto;
import modelo.base.Personaje;
import modelo.mecanicas.FuerzaAumentada;

public class PocionEspecial extends Objeto {
     private int curacion;
    private int bonoAtaqueTemporal;

    public PocionEspecial(String nombre, int curacion, int bonoAtaqueTemporal) {
        super(nombre);
        this.curacion = curacion;
        this.bonoAtaqueTemporal = bonoAtaqueTemporal;
    }

    public String consumir(Personaje p) {
        p.setVidaActual(p.getVidaActual() + curacion);
        p.agregarEstado(new FuerzaAumentada(2, bonoAtaqueTemporal));
        return p.getNombre() + " consumió " + nombre + ". Recuperó " + curacion + " HP y su ataque aumentó temporalmente.";
    }
}

