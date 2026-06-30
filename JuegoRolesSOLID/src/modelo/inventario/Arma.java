
package modelo.inventario;

import modelo.base.Objeto;
import modelo.base.Personaje;

public class Arma extends Objeto {
    private int bonificadorAtaque;

    public Arma(String nombre, int bonificadorAtaque) {
        super(nombre);
        this.bonificadorAtaque = bonificadorAtaque;
    }

    @Override
    public int getBonificadorAtaque() { 
        return bonificadorAtaque; 
    }
}

