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
public class Venta extends Transaccion{

    public Venta(int precio, String descripcion, int codigo, ArrayList<Tupla> objetoCV) {
        super(precio, descripcion, codigo, objetoCV);
    }

    
}
