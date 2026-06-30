package modelo.personajes;

import modelo.base.Personaje;
import modelo.interfaces.IHabilidad;

public class Guerrero extends Personaje {
    private int fuerza;

    public Guerrero(String nombre, int vidaMax, int ataqueBase, int defensaBase, int fuerza, IHabilidad habilidad) {
        super(nombre, vidaMax, ataqueBase, defensaBase, 50, habilidad);
        this.fuerza = fuerza;
    }

    @Override
    public int calcularAtaque() {
        return super.calcularAtaque() + this.fuerza;
    }
}