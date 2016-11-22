package Logica;

import java.util.ArrayList;

/**
 *
 * @author Estudiante
 */
public class Alquiler {

    private String id;
    private Persona persona;
    private boolean alquilado;
    private String fechaEntrega;//Fecha en la que se le entrega al cliente
    private String horaEntrega;//hora de la entrega
    private String fechaRecibido;//Fecha en la que devuelve el cliente
    private String horaRecibido;//hora de la devolucion
    private String tiempoPrestamo;
    private ArrayList<Tupla> herramientas;// guarda una lista de herramientas

    
    public Alquiler() {
        id = "";
        persona = new Persona();
        alquilado = false;
        fechaEntrega = "";
        horaEntrega = "";
        fechaRecibido = "";
        horaRecibido = "";
        tiempoPrestamo = "";
        herramientas = new ArrayList<>();
    }

    public Alquiler(Persona persona,String id, boolean alquilado, String fechaEntrega, String horaEntrega,
            String fechaRecibido, String horaRecibido, String tiempoPrestramo,ArrayList herramienta) {
        this.id = id;
        this.persona = persona;
        this.alquilado = alquilado;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.fechaRecibido = fechaRecibido;
        this.horaRecibido = horaRecibido;
        this.tiempoPrestamo = tiempoPrestramo;
        this.herramientas = herramienta;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getFechaDevolucion() {
        if(fechaRecibido.equals("")||fechaRecibido.equals(null)){
            return "0000-00-00";
        }
        return fechaRecibido;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaRecibido = fechaDevolucion;
    }

    public String getHoraDevolucion() {
        return horaRecibido;
    }

    public void setHoraDevolucion(String horaDevolucion) {
        this.horaRecibido = horaDevolucion;
    }

    public String getTiempoPrestamo() {
        return tiempoPrestamo;
    }

    public void setTiempoPrestamo(String tiempoPrestamo) {
        this.tiempoPrestamo = tiempoPrestamo;
    }

    public ArrayList<Tupla> getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(ArrayList<Tupla> herramientas) {
        this.herramientas = herramientas;
    }

    @Override
    public String toString() {
        return "Alquiler{" + "persona=" + persona + ", alquilado=" + alquilado + ", fechaEntrega=" + fechaEntrega + ", horaEntrega=" + horaEntrega + ", fechaRecibido=" + fechaRecibido + ", horaRecibido=" + horaRecibido + ", tiempoPrestramo=" + tiempoPrestamo + '}';
    }

}
