package Logica;

/**
 *
 * @author Estudiante
 */
public class Tupla implements Cloneable {

    private Herramienta herramienta;
    private int cantidadActual;

    public Tupla() {
        herramienta = new Herramienta();
        cantidadActual = 0;
    }

    public Tupla(Herramienta herramientas, int cantidadActual) {
        this.herramienta = herramientas;
        this.cantidadActual = cantidadActual;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramientas(Herramienta Herramientas) {
        this.herramienta = Herramientas;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    @Override
    public String toString() {
        return "Tupla{" + "Herramientas=" + herramienta + ", cantidadActual=" + cantidadActual + '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
