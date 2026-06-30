
package modelo.inventario;

import modelo.base.Objeto;
import modelo.base.Personaje;

public class Armadura extends Objeto {
    private int bonificadorDefensa;

    public Armadura(String nombre, int bonificadorDefensa) {
        super(nombre);
        this.bonificadorDefensa = bonificadorDefensa;
    }

    @Override
    public int getBonificadorDefensa() { 
        return bonificadorDefensa; 
    }
}

