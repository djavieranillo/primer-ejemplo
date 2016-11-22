package Logica;
// persona se trata como el cliente
public class Persona {

    protected String nombre;
    protected String id;
    protected String tipoId;
    protected String direccion;
    protected String telefono;
    

    public Persona() {
        nombre = "No Definido";
        id = null;
        tipoId = "No Definido";
        direccion = "No Definido";
        telefono = "No Definido";
    }

    public Persona(String nombre, String id, String tipoId, String direccion, String telefono) {
        this.nombre = nombre;
        this.id = id;
        this.tipoId = tipoId;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", id=" + id + ", tipoId=" + tipoId + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }
}
