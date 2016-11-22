/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;
import java.util.ArrayList;
/**
 *
 * @author ReyesBD
 */
public class Transaccion {
    protected int precio;
    protected String descripcion;
    protected int codigo;
    protected ArrayList<Tupla> objetoCV;
    
    public Transaccion() {
        precio = 0;
        descripcion = "";
        codigo = 0;
        objetoCV = new ArrayList<>();
    }

    public Transaccion(int precio, String descripcion, int codigo, ArrayList<Tupla> objetoCV) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.objetoCV = new ArrayList<Tupla>();
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    
}
