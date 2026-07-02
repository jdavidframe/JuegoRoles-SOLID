/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.SistemaCombate;
import modelo.base.Personaje;
import modelo.mecanicas.*;
import modelo.personajes.*;
import modelo.inventario.*;
import modelo.interfaces.IVista;

/**
 *
 * @author jesudavi
 */
public class Ejecutor {

    public static void main(String[] args) {

        IVista vista = new VistaConsola();
        SistemaCombate coreJuego = new SistemaCombate(vista);

        GolpeDevastador golpe = new GolpeDevastador();
        ExplosionArcana magia = new ExplosionArcana();

        Personaje arturo = new Guerrero("Ledesma El Guerrero", 130, 14, 10, 6, golpe);
        Personaje merlin = new Mago("Merlin El Mago", 95, 12, 4, 15, magia);

        Arma espadaSagrada = new Arma("Espada Sagrada", 6);
        Armadura tunicaMagica = new Armadura("Manto Sagrado", 4);

        arturo.equiparObjeto(espadaSagrada);
        merlin.equiparObjeto(tunicaMagica);

        merlin.agregarEstado(new Envenenado(3, 5));
        arturo.agregarEstado(new FuerzaAumentada(2, 5));

        coreJuego.iniciarCombate(arturo, merlin);
    }
}
