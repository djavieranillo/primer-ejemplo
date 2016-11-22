/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author ReyesBD
 */
public class Obrero extends Persona{
    
    private boolean devengado;

    public Obrero(boolean devengado, String nombre, String id, String tipoId, String direccion, String telefono) {
        super(nombre, id, tipoId, direccion, telefono);
        this.devengado = devengado;
    }

    public boolean isDevengado() {
        return devengado;
    }

    public void setDevengado(boolean devengado) {
        this.devengado = devengado;
    }
    
    
    
    
    
    
}
