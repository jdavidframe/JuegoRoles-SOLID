package modelo.personajes;

import modelo.base.Personaje;
import modelo.interfaces.IHabilidad;

public class Mago extends Personaje {
    private int intelecto;

    public Mago(String nombre, int vidaMax, int ataqueBase, int defensaBase, int intelecto, IHabilidad habilidad) {
        super(nombre, vidaMax, ataqueBase, defensaBase, 100, habilidad);
        this.intelecto = intelecto;
    }

    @Override
    public int calcularAtaque() {
        return super.calcularAtaque() + this.intelecto;
    }
}