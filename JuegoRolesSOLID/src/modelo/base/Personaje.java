/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.base;

import java.util.ArrayList;
import java.util.List;
import modelo.interfaces.IEstadoAlterado;
import modelo.interfaces.IHabilidad;

/**
 *
 * @author jesudavi
 */
public abstract class Personaje {

    protected String nombre;
    protected int vidaMax;
    protected int vidaActual;
    protected int ataqueBase;
    protected int defensaBase;
    protected int energiaMax;
    protected int energiaActual;
    protected int cooldownHabilidad;
    protected List<IEstadoAlterado> estadosActivos;
    protected Objeto objetoEquipado;
    protected IHabilidad habilidadEspecial;

    public Personaje(String nombre, int vidaMax, int ataqueBase, int defensaBase, int energiaMax, IHabilidad habilidad) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.energiaMax = energiaMax;
        this.energiaActual = energiaMax;
        this.cooldownHabilidad = 0;
        this.estadosActivos = new ArrayList<>();
        this.objetoEquipado = null;
        this.habilidadEspecial = habilidad;
    }

    public int calcularAtaque() {
        int bonoObjeto = (objetoEquipado != null) ? objetoEquipado.getBonificadorAtaque() : 0;
        int bonoEstados = estadosActivos.stream().mapToInt(IEstadoAlterado::modificarAtaque).sum();
        return this.ataqueBase + bonoObjeto + bonoEstados;
    }

    public int calcularDefensa() {
        int bonoObjeto = (objetoEquipado != null) ? objetoEquipado.getBonificadorDefensa() : 0;
        int bonoEstados = estadosActivos.stream().mapToInt(IEstadoAlterado::modificarDefensa).sum();
        return this.defensaBase + bonoObjeto + bonoEstados;
    }

    public boolean puedeActuar() {
        return estadosActivos.stream().noneMatch(IEstadoAlterado::limitaAccion);
    }

    public String atacar(Personaje objetivo) {
        if (!puedeActuar()) {
            return nombre + " tiene sus acciones restringidas por un efecto de estado y pierde su turno.";
        }

        int daño = this.calcularAtaque() - objetivo.calcularDefensa();
        if (daño < 0) {
            daño = 0;
        }

        objetivo.recibirDaño(daño);
        return nombre + " inflige " + daño + " puntos de daño físico a " + objetivo.getNombre() + ".";
    }

    public void recibirDaño(int cantidad) {
        this.vidaActual = Math.max(0, this.vidaActual - cantidad);
    }

    public List<String> procesarTurno() {
        List<String> reportes = new ArrayList<>();

        if (cooldownHabilidad > 0) {
            cooldownHabilidad--;
        }

        for (int i = estadosActivos.size() - 1; i >= 0; i--) {
            IEstadoAlterado estado = estadosActivos.get(i);
            String reporteEstado = estado.aplicarEfecto(this);

            if (reporteEstado != null && !reporteEstado.isEmpty()) {
                reportes.add(reporteEstado);
            }

            if (estado.haTerminado()) {
                reportes.add("El estado [" + estado.getNombre() + "] ha expirado en " + nombre + ".");
                estadosActivos.remove(i);
            }
        }
        return reportes;
    }

    public void usarEnergia(int cantidad) throws Exception {
        if (this.energiaActual < cantidad) {
            throw new Exception("¡Energía insuficiente!");
        }
        this.energiaActual -= cantidad;
    }

    public String usarHabilidadEspecial(Personaje objetivo) {
        if (!puedeActuar()) {
            return nombre + " intenta usar su habilidad, pero sus estados actuales se lo impiden.";
        }
        if (cooldownHabilidad > 0) {
            return "La habilidad de " + nombre + " está en enfriamiento (" + cooldownHabilidad + " turnos).";
        }
        if (energiaActual < habilidadEspecial.getCostoEnergia()) {
            return nombre + " intenta usar " + habilidadEspecial.getNombre() + " pero le falta energía.";
        }

        try {
            usarEnergia(habilidadEspecial.getCostoEnergia());
            String resultado = habilidadEspecial.usar(this, objetivo);
            this.cooldownHabilidad = habilidadEspecial.getCooldownMax();
            return resultado;
        } catch (Exception e) {
            return "Error al ejecutar habilidad: " + e.getMessage();
        }
    }

    public void equiparObjeto(Objeto objeto) {
        this.objetoEquipado = objeto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vida) {
        this.vidaActual = Math.min(vida, vidaMax);
    }

    public int getEnergiaActual() {
        return energiaActual;
    }

    public void setEnergiaActual(int energia) {
        this.energiaActual = Math.min(energia, energiaMax);
    }

    public int getCooldownHabilidad() {
        return cooldownHabilidad;
    }

    public void setCooldownHabilidad(int cd) {
        this.cooldownHabilidad = cd;
    }

    public void agregarEstado(IEstadoAlterado estado) {
        this.estadosActivos.add(estado);
    }

    public boolean tieneEstado(String nombreEstado) {
        return estadosActivos.stream().anyMatch(e -> e.getNombre().equalsIgnoreCase(nombreEstado));
    }
}
