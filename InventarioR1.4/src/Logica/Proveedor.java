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
public class Proveedor extends  Persona{
    
    private String empresa;

   
    public Proveedor(String empresa, String nombre, String id, String tipoId, String direccion, String telefono) {
        super(nombre, id, tipoId, direccion, telefono);
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    
    
    
    
}
