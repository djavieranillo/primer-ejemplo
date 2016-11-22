/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author DDV
 */

// agregar atributo boolean insumo
public class Herramienta {

    private String nombre;
    private String detalles_herramienta;
    private double precio_base;
    private String id;
    

    public Herramienta() {
        nombre = "";
        detalles_herramienta = "";
        precio_base = 0;
        id = "";
        
    }

    public Herramienta(String nombre, String detalles_herramienta, double precio_base, String id) {
        this.nombre = nombre;
        this.detalles_herramienta = detalles_herramienta;
        this.precio_base = precio_base;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles_herramienta() {
        return detalles_herramienta;
    }

    public void setDetalles_herramienta(String detalles_herramienta) {
        this.detalles_herramienta = detalles_herramienta;
    }

    public double getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(double precio_base) {
        this.precio_base = precio_base;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Herramienta{" + "nombre=" + nombre + ", detalles_herramienta=" + detalles_herramienta + ", ID=" + id + '}';
    }
}
