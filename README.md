# Defensa Técnica de Aplicación de Principios SOLID  
### Proyecto: JuegoRoles-SOLID

## Introducción

El proyecto consiste en un sistema RPG de combate por turnos desarrollado en Java, donde existen personajes con atributos propios, habilidades especiales, objetos equipables y estados alterados durante el combate.

Se desarrollaron dos versiones del mismo sistema:

- **RPG BASE** → Implementación inicial con enfoque orientado a objetos tradicional.
- **RPG SOLID** → Versión refactorizada aplicando principios SOLID.

El objetivo de la refactorización fue mejorar la estructura del sistema aplicando los principios SOLID definidos por :contentReference[oaicite:0]{index=0}, permitiendo que el proyecto sea más escalable, mantenible y flexible ante futuras modificaciones.

---

# Arquitectura General del Proyecto

## RPG BASE

Toda la aplicación se encuentra en un único paquete:

```text
pkg1_juegorolesejecutor/

Personaje.java
SistemaCombate.java
Guerrero.java
Magos.java
Arqueros.java
Arma.java
Armadura.java
PocionEspecial.java
Congelado.java
Envenenado.java
FuerzaAumentada.java
```

Características:

- Código centralizado.
- Clases altamente dependientes unas de otras.
- Lógica de negocio mezclada con salida por consola.
- Difícil extensión futura.

---

## RPG SOLID

La aplicación se reorganiza por responsabilidades:

```text
modelo/
controlador/
vista/

modelo/base/
modelo/interfaces/
modelo/personajes/
modelo/mecanicas/
modelo/inventario/
```

Características:

- Código modular.
- Separación por responsabilidades.
- Uso de interfaces.
- Menor acoplamiento entre componentes.

---

# 1. Single Responsibility Principle (SRP)

## Principio Teórico

**Una clase debe tener una sola responsabilidad o una única razón para cambiar.**

Esto significa que una clase no debería encargarse de múltiples tareas distintas.

---

## Implementación en RPG BASE

En el archivo:

```java
SistemaCombate.java
```

Encontramos el siguiente código:

```java
public void iniciarCombate(Personaje jugador1, Personaje jugador2) {
    System.out.println("--- INICIO DEL COMBATE ---");
    System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());

    while (jugador1.getVidaActual() > 0 && jugador2.getVidaActual() > 0) {
        ejecutarTurnoPersonaje(jugador1, jugador2);
        ejecutarTurnoPersonaje(jugador2, jugador1);
    }

    mostrarGanador(jugador1, jugador2);
}
```

Aquí una sola clase realiza múltiples tareas:

- Controla la lógica del combate.
- Maneja el flujo de turnos.
- Imprime información en consola.
- Decide cuándo termina el combate.

Problema:

La clase tiene demasiadas responsabilidades.

---

## Implementación en RPG SOLID

Se separa la lógica de presentación creando:

```java
VistaConsola.java
```

Dentro de `SistemaCombate`:

```java
private final VistaConsola vista;

public SistemaCombate(VistaConsola vista){
    this.vista = vista;
}
```

Ahora:

```java
vista.mostrarInicioCombate(jugador1, jugador2);
```

La impresión ya no pertenece al sistema de combate.

---

## Aplicación práctica

Antes:

```java
System.out.println("Turno de " + jugador.getNombre());
```

Después:

```java
vista.mostrarMensaje(reporte);
```

---

## Defensa

Se aplica SRP porque ahora:

- `SistemaCombate` controla combate.
- `VistaConsola` controla salida visual.

Cada clase tiene una única función.

---

# 2. Open Closed Principle (OCP)

## Principio Teórico

**Una clase debe estar abierta para extender funcionalidades, pero cerrada a modificaciones internas.**

Se debe poder agregar comportamiento nuevo sin alterar código existente.

---

## Implementación en RPG BASE

Dentro de `SistemaCombate` encontramos:

```java
if (activo instanceof Guerrero && activo.getEnergiaActual() >= 30) {
    activo.usarHabilidadEspecial(objetivo);

} else if (activo instanceof Arqueros && activo.getEnergiaActual() >= 25) {
    activo.usarHabilidadEspecial(objetivo);

} else if (activo instanceof Magos && activo.getEnergiaActual() >= 40) {
    activo.usarHabilidadEspecial(objetivo);

} else {
    activo.atacar(objetivo);
}
```

Problema:

Si se crea un nuevo personaje:

```text
Asesino.java
```

Se debe modificar este bloque:

```java
else if (activo instanceof Asesino)
```

El código existente necesita cambiar constantemente.

---

## Implementación en RPG SOLID

Se crea la interfaz:

```java
public interface IHabilidad {

    String usar(Personaje usuario, Personaje objetivo);

    int getCostoEnergia();

}
```

Cada habilidad implementa esta interfaz.

Ejemplo:

```java
public class GolpeDevastador implements IHabilidad {

    @Override
    public String usar(Personaje usuario, Personaje objetivo) {
        int daño = usuario.calcularAtaque() * 2;
        objetivo.recibirDaño(daño);
        return "Ataque ejecutado";
    }
}
```

---

## Aplicación práctica

Antes:

```java
if(activo instanceof Guerrero)
```

Después:

```java
activo.usarHabilidadEspecial(objetivo);
```

---

## Defensa

Ahora se pueden agregar nuevas habilidades sin modificar código anterior.

Se cumple OCP.

---

# 3. Liskov Substitution Principle (LSP)

## Principio Teórico

**Las clases hijas deben poder sustituir a su clase padre sin alterar el funcionamiento del sistema.**

Si varias clases heredan de una clase base, todas deben comportarse correctamente al ser utilizadas como el tipo padre.

---

## Implementación en RPG BASE

Jerarquía:

```text
Personaje
 ├ Guerrero
 ├ Magos
 └ Arqueros
```

Sin embargo el sistema necesita identificar tipos concretos.

Ejemplo:

```java
if(activo instanceof Guerrero)
```

Problema:

El sistema necesita saber exactamente qué objeto está usando.

No existe verdadero polimorfismo.

---

## Implementación en RPG SOLID

Método:

```java
public void iniciarCombate(Personaje jugador1, Personaje jugador2)
```

Uso:

```java
activo.usarHabilidadEspecial(objetivo);
```

No importa si el personaje es:

- Guerrero
- Arquero
- Mago

Todos pueden ser tratados como:

```java
Personaje
```

---

## Aplicación práctica

Antes:

```java
if (activo instanceof Magos)
```

Después:

```java
Personaje activo
```

---

## Defensa

Cualquier subclase puede reemplazar al padre sin cambiar el comportamiento del sistema.

Se cumple LSP.

---

# 4. Interface Segregation Principle (ISP)

## Principio Teórico

**Una clase no debe depender de métodos o interfaces que no necesita utilizar.**

Cada contrato debe ser específico.

---

## Implementación en RPG BASE

Dentro de `Personaje.java`

```java
public abstract void usarHabilidadEspecial(Personaje objetivo);
```

Todas las clases hijas quedan obligadas a implementar este método directamente.

La lógica de habilidades queda ligada al personaje.

Problema:

Existe dependencia innecesaria.

---

## Implementación en RPG SOLID

Se crean interfaces independientes.

```java
public interface IHabilidad
```

y

```java
public interface IEstadoAlterado
```

Dentro de `Personaje`

```java
protected IHabilidad habilidadEspecial;
```

Ahora el personaje no implementa directamente la habilidad.

Solo utiliza el contrato.

---

## Aplicación práctica

Antes:

```java
public abstract void usarHabilidadEspecial(...)
```

Después:

```java
protected IHabilidad habilidadEspecial;
```

---

## Defensa

Cada módulo depende solamente de lo que realmente necesita.

Se cumple ISP.

---

# 5. Dependency Inversion Principle (DIP)

## Principio Teórico

**Los módulos de alto nivel no deben depender directamente de implementaciones concretas, sino de abstracciones.**

El sistema debe depender de contratos, no de objetos específicos.

---

## Implementación en RPG BASE

En `Personaje.java`

```java
protected Object objetoEquipado;
```

Problema:

Se utiliza:

```java
Object
```

que es demasiado genérico.

Además encontramos dependencia directa:

```java
System.out.println(...)
```

El sistema depende directamente de consola.

---

## Implementación en RPG SOLID

Se reemplaza:

```java
protected Object objetoEquipado;
```

por:

```java
protected Objeto objetoEquipado;
```

Además:

```java
protected IHabilidad habilidadEspecial;
```

Constructor:

```java
public Personaje(
    String nombre,
    int vidaMax,
    int ataqueBase,
    int defensaBase,
    int energiaMax,
    IHabilidad habilidad
)
```

También:

```java
public SistemaCombate(VistaConsola vista)
```

---

## Aplicación práctica

Antes:

```java
protected Object objetoEquipado;
```

Después:

```java
protected Objeto objetoEquipado;
```

Antes:

```java
System.out.println(...)
```

Después:

```java
VistaConsola vista
```

---

## Defensa

Ahora las clases dependen de abstracciones:

- Objeto
- IHabilidad
- VistaConsola

Se reduce el acoplamiento entre módulos.

Se cumple DIP.

---

# Comparación Global

| Principio | RPG BASE | RPG SOLID |
|------------|----------|------------|
| SRP | Una clase hace múltiples tareas | Responsabilidades separadas |
| OCP | Requiere modificar código | Se extiende creando nuevas clases |
| LSP | Depende de instanceof | Uso correcto de polimorfismo |
| ISP | Métodos obligatorios en subclases | Interfaces separadas |
| DIP | Dependencia directa | Dependencia sobre abstracciones |

---

# Conclusión Final

El proyecto originalmente presentaba una implementación funcional, pero con una arquitectura fuertemente acoplada.

Los principales problemas detectados fueron:

- Clases con demasiadas responsabilidades.
- Dependencia directa entre módulos.
- Código rígido ante nuevas funcionalidades.
- Falta de separación entre lógica y presentación.

Durante la refactorización se aplicaron correctamente los cinco principios SOLID mediante:

- Separación de responsabilidades.
- Uso de interfaces.
- Delegación de comportamiento.
- Aplicación de polimorfismo.
- Dependencia sobre abstracciones.

Como resultado, el sistema RPG pasó de ser una aplicación funcional pero rígida, a una arquitectura modular, extensible y preparada para crecer sin afectar el código existente.

En términos prácticos, el proyecto evolucionó desde una estructura orientada únicamente a funcionar, hacia una estructura orientada a mantenerse, escalar y reutilizar componentes de forma correcta.
