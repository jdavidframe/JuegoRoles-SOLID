package modelo.personajes;

import modelo.base.Personaje;
import modelo.interfaces.IHabilidad;
import modelo.excepciones.*;

public class Mago extends Personaje {

    private int intelecto;

    public Mago(String nombre, int vidaMax, int ataqueBase, int defensaBase, int intelecto, IHabilidad habilidad) {
        super(nombre, vidaMax, ataqueBase, defensaBase, 100, habilidad);
        this.intelecto = intelecto;
    }

    @Override
    public String usarHabilidadEspecial(Personaje objetivo) {
        try {
            this.usarEnergia(30); 
            this.cooldownHabilidad = 4;
            return this.habilidadEspecial.usar(this, objetivo);
        } catch (EnergiaInsuficienteException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public int calcularAtaque() {
        return super.calcularAtaque() + this.intelecto;
    }
}
