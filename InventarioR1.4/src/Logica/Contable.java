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
public class Contable {
    
  private ArrayList<Alquiler> alquileres;
  private ArrayList<Factura> facturas;
  private ArrayList<Compra> compras;
  private ArrayList<Venta> Ventas; 
  private ArrayList<Pago> pagos;
  private float capital;
  
      public Contable() {
        alquileres = new ArrayList<Alquiler>();
        facturas = new ArrayList<Factura>();
        compras = new ArrayList<Compra>();
        Ventas = new ArrayList<Venta>();
        capital = 0;
    }

    public Contable(ArrayList<Alquiler> alquileres, ArrayList<Factura> facturas, ArrayList<Compra> compras, ArrayList<Venta> Ventas, float capital) {
        this.alquileres = alquileres;
        this.facturas = facturas;
        this.compras = compras;
        this.Ventas = Ventas;
        this.capital = capital;
    }

    public ArrayList<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(ArrayList<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    public ArrayList<Venta> getVentas() {
        return Ventas;
    }

    public void setVentas(ArrayList<Venta> Ventas) {
        this.Ventas = Ventas;
    }

    public float getCapital() {
        return capital;
    }

    public void setCapital(float capital) {
        this.capital = capital;
    }
    
    public void graficaVentas(ArrayList<Venta> Ventas){//La busqueda de las ventas la realizara otro metodo
    
    }
    
    public void graficaGanancia(ArrayList<Venta> Ventas){//La busqueda de las ventas la realizara otro metodo
    
    }
  
  
}
