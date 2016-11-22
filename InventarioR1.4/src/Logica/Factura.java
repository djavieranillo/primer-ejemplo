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
public class Factura {
    private String fechadeFacturacion;//Fecha en la que compra el producto el cliente
    private String horadeFacturacion;//hora de la compra
    private Persona persona; //La persona a la cual se le generara l factura
    private int codigoTrans;//Define el tipo de pago  la relacion de los tipos de transaciones
    private int descuento;
    private int iva;
    private String descripcion;//Sera diferente para cada tipo de transaccion
    
    public Factura() {
        fechadeFacturacion = "00-00-0000";
        horadeFacturacion = "00-00-0000";
        persona = new Persona();
        codigoTrans = 0;
        descuento = 0;
        iva = 0;
        descripcion = "";
    }
    
    public Factura(String descripcion, String fechadeFacturacion, String horadeFacturacion, Persona persona, int tipoFacura, int descuento, int iva) {
        this.fechadeFacturacion = fechadeFacturacion;
        this.horadeFacturacion = horadeFacturacion;
        this.persona = persona;
        this.codigoTrans = tipoFacura;
        this.descuento = descuento;
        this.iva = iva;
        this.descripcion = descripcion;
    }

    public String getFechadeFacturacion() {
        return fechadeFacturacion;
    }

    public void setFechadeFacturacion(String fechadeFacturacion) {
        this.fechadeFacturacion = fechadeFacturacion;
    }

    public String getHoradeFacturacion() {
        return horadeFacturacion;
    }

    public void setHoradeFacturacion(String horadeFacturacion) {
        this.horadeFacturacion = horadeFacturacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getCodigoTrans() {
        return codigoTrans;
    }

    public void setCodigoTrans(int codigoTrans) {
        this.codigoTrans = codigoTrans;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }
    
    public void generarFactura(Transaccion transaccion){//Genera una factura dependiendo si es compra o  venta, ademas verifica para quien es la factura 
    }
    
    
}
