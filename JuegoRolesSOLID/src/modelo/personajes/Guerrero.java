package modelo.personajes;

import modelo.base.Personaje;
import modelo.interfaces.IHabilidad;
import modelo.excepciones.*;

public class Guerrero extends Personaje {

    private int fuerza;

    public Guerrero(String nombre, int vidaMax, int ataqueBase, int defensaBase, int fuerza, IHabilidad habilidad) {
        super(nombre, vidaMax, ataqueBase, defensaBase, 50, habilidad);
        this.fuerza = fuerza;
    }

    @Override
    public String usarHabilidadEspecial(Personaje objetivo) {
        try {
            this.usarEnergia(20);
            this.cooldownHabilidad = 3;
            return this.habilidadEspecial.usar(this, objetivo);
        } catch (EnergiaInsuficienteException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public int calcularAtaque() {
        return super.calcularAtaque() + this.fuerza;
    }
}
