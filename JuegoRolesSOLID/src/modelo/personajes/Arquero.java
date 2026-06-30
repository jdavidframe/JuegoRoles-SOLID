package modelo.personajes;

import modelo.base.Personaje;
import modelo.interfaces.IHabilidad;

public class Arquero extends Personaje {
    private int precision;
    private int flechas;

    public Arquero(String nombre, int vidaMax, int ataqueBase, int defensaBase, int precision, int flechas, IHabilidad habilidad) {
        super(nombre, vidaMax, ataqueBase, defensaBase, 60, habilidad);
        this.precision = precision;
        this.flechas = flechas;
    }

    @Override
    public int calcularAtaque() {
        return super.calcularAtaque() + this.precision;
    }

    public int getFlechas() { 
        return flechas; 
    }

    public void reducirFlechas(int cantidad) { 
        this.flechas = Math.max(0, this.flechas - cantidad); 
    }
}