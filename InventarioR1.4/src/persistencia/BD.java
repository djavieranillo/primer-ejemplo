/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Logica.Alquiler;
import Logica.Herramienta;
import Logica.DateTime;
import Logica.Persona;
import Logica.Tupla;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ichigo-Sama
 */
//base de datos para probar
public class BD {

// atributos de la clase
    private static String driver, url, login, password;
    private static Connection conexion = null;
    private static BD instancia = null;
    private static String nombreBD;

    /**
     * Creates a new instance of BD
     */
    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        BD.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        BD.password = password;
    }

// <editor-fold defaultstate="collapsed" desc="Singleton">
    private BD() {
        nombreBD = "inventario";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/" + nombreBD;
        actualizar();
    }

    public static void actualizar() {
        CargaArchivo.cargarLogin("Login");
        if (conexion == null) {
            try {
                Class.forName(driver).newInstance();
                conexion = DriverManager.getConnection(url, login, password);
                System.out.println("Conexion con Base de datos Ok....");
            } catch (Exception exc) {
                System.err.println("error: " + exc);
                //JOptionPane.showMessageDialog(null, "Error al tratar de conectar con la base de datos, pruebe digitando el usuario y la contraseña");
            }
        } else {
            conexion = null;
            actualizar();
            System.out.println("ya se encuentra conectado a la base de datos...");
        }
    }

    private synchronized static void createInstance() {
        if (instancia == null) {
            instancia = new BD();
        }
    }

    public static BD getBD() {
        createInstance();
        actualizar();
        return instancia;
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="getConexion()">

    public Connection getConexion() {
        return conexion;
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="cargarPersonas()">

    public static ArrayList<Persona> cargarPersonas() {
        createInstance();
        try {
            ArrayList<Persona> personas = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from personas");
            while (rs.next()) {
                personas.add(new Persona(rs.getString("nombre"), rs.getString("id_persona"), rs.getString("tipoID"), rs.getString("direccion"), rs.getString("telefono")));
            }
            return personas;
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla personas");
            return new ArrayList<Persona>();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="coincidirPersonas(String, String)">

    public static ArrayList<Persona> coincidirPersonas(String coincidencia, String campo) {
        createInstance();
        try {
            ArrayList<Persona> personas = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM personas "
                    + "WHERE " + campo + " LIKE '%" + coincidencia + "%';");
            while (rs.next()) {
                personas.add(new Persona(rs.getString("nombre"), rs.getString("id_persona"), rs.getString("tipoID"), rs.getString("direccion"), rs.getString("telefono")));
            }
            return personas;
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla personas");
            return new ArrayList<Persona>();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="buscarPersonas(String, String)">

    public static Persona buscarPersona(String criterio) {
        createInstance();
        try {
            ArrayList<Persona> personas = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM personas  WHERE `id_persona` = '" + criterio + "' LIMIT 1");
            rs.next();
            return new Persona(rs.getString("nombre"), rs.getString("id_persona"), rs.getString("tipoID"), rs.getString("direccion"), rs.getString("telefono"));
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla personas");
            return null;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="insertPersonas(ArrayList<Persona>, boolean)">

    public static boolean insertPersonas(ArrayList<Persona> personas, boolean proveedor) {
        createInstance();
        try {
            for (int i = 0; i < personas.size(); i++) {
                Statement s = conexion.createStatement();
                s.execute("INSERT INTO  `personas`"
                        + "(`id_persona` ,`tipoID` ,`tipoPersona`,`nombre`,`direccion`,`telefono`)"
                        + "VALUES ('" + personas.get(i).getId() + "','" + personas.get(i).getTipoId() + "','" + proveedor + "',"
                        + "'" + personas.get(i).getNombre() + "','" + personas.get(i).getDireccion() + "','" + personas.get(i).getTelefono() + "');");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="insertPersonas(Persona, boolean)">

    public static boolean insertPersonas(Persona persona, boolean proveedor) {
        createInstance();
        try {
            Statement s = conexion.createStatement();
            s.execute("INSERT INTO  `personas`"
                    + "(`id_persona` ,`tipoID` ,`tipoPersona`,`nombre`,`direccion`,`telefono`)"
                    + "VALUES ('" + persona.getId() + "','" + persona.getTipoId() + "','" + proveedor + "',"
                    + "'" + persona.getNombre() + "','" + persona.getDireccion() + "','" + persona.getTelefono() + "');");

            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="cargarHerramientas()">

    public static ArrayList<Tupla> cargarHerramientas() {
        createInstance();
        try {
            ArrayList<Tupla> herramientas = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from herramientas");
            while (rs.next()) {
                herramientas.add(new Tupla(new Herramienta(rs.getString("nombre"), rs.getString("detalle"),
                        rs.getDouble("precio_base"), rs.getString("id_herramienta")), rs.getInt("cantidad")));
            }
            return herramientas;
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla tuplas");
            return new ArrayList<Tupla>();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="buscarHerramienta(String)">

    public static Tupla buscarHerramienta(String criterio) {
        createInstance();
        try {
            Tupla tupla = new Tupla();
            ArrayList<Tupla> herramientas = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM herramientas  WHERE `id_herramienta` = '" + criterio + "'");
            while (rs.next()) {
                herramientas.add(new Tupla(new Herramienta(rs.getString("nombre"), rs.getString("detalle"),
                        rs.getDouble("precio_base"), rs.getString("id_herramienta")), rs.getInt("cantidad")));
            }
            if (herramientas.size() == 1) {
                tupla = herramientas.get(0);
                return tupla;
            }
            System.out.println("Error buscarHerramienta(String criterio): cantidad de elementos encontrados " + herramientas.size());
            return null;
        } catch (Exception exception) {
            System.out.println("Error buscarHerramienta(String criterio): no se pudo cargar la tabla tuplas");
            return null;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="coincidirHerramientas(String, String)">

    public static ArrayList<Tupla> coincidirHerramientas(String coincidencia, String campo) {
        createInstance();
        try {
            ArrayList<Tupla> herramientas = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM herramientas "
                    + "WHERE " + campo + " LIKE '%" + coincidencia + "%';");
            while (rs.next()) {
                herramientas.add(new Tupla(new Herramienta(rs.getString("nombre"), rs.getString("detalle"),
                        rs.getDouble("precio_base"), rs.getString("id_herramienta")), rs.getInt("cantidad")));
            }
            return herramientas;
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla tuplas");
            return new ArrayList<Tupla>();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="insertarHerramientas(ArrayList<Tupla>)">

    public static boolean insertarHerramientas(ArrayList<Tupla> herramientas) {
        createInstance();
        try {
            Statement s = conexion.createStatement();
            for (int i = 0; i < herramientas.size(); i++) {
                s.execute("INSERT INTO  `inventario`.`herramientas` (`id_herramienta` ,`nombre` ,`detalle` ,`precio_base` ,`cantidad`)"
                        + "VALUES ('" + herramientas.get(i).getHerramienta().getId() + "','" + herramientas.get(i).getHerramienta().getNombre()
                        + "','" + herramientas.get(i).getHerramienta().getDetalles_herramienta() + "','" + herramientas.get(i).getHerramienta().getPrecio_base()
                        + "','" + herramientas.get(i).getCantidadActual() + "');");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="insertarHerramientas(Tupla)">

    public static boolean insertarHerramientas(Tupla herramienta) {
        createInstance();
        try {
            Statement s = conexion.createStatement();
            s.execute("INSERT INTO  `inventario`.`herramientas` (`id_herramienta`,`nombre` ,`detalle` ,`precio_base` ,`cantidad`)"
                    + "VALUES ('" + herramienta.getHerramienta().getId()
                    + "','" + herramienta.getHerramienta().getNombre()
                    + "','" + herramienta.getHerramienta().getDetalles_herramienta() + "','" + herramienta.getHerramienta().getPrecio_base()
                    + "','" + herramienta.getCantidadActual() + "');");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="devolverAlquiler(Alquiler alquiler)">

    public static boolean devolverAlquiler(Alquiler alquiler) {
        createInstance();
        try {
            Statement s = conexion.createStatement();
            int isAlquilado = 0;
            if (alquiler.isAlquilado()) {
                isAlquilado = 1;
            }
            s.execute("UPDATE  `inventario`.`alquiler` SET  `estado` =  " + isAlquilado + ", "
                    + "`fecha_devolucion` = '" + alquiler.getFechaDevolucion() + "', "
                    + "`hora_devolucion` = '" + alquiler.getHoraDevolucion() + "', "
                    + "`tiempo_Prestamo` = '" + alquiler.getTiempoPrestamo() + "'"
                    + "WHERE  `alquiler`.`id_alquiler` =" + alquiler.getId() + ";");
            ArrayList<Tupla> herramientas = alquiler.getHerramientas();

            System.out.println("Size: " + herramientas.size());
            for (int i = 0; i < herramientas.size(); i++) {
                Tupla tupla = BD.buscarHerramienta(herramientas.get(i).getHerramienta().getId());
                System.out.println("cantidad nueva: " + tupla.getCantidadActual());
                tupla.setCantidadActual(tupla.getCantidadActual() + herramientas.get(i).getCantidadActual());
                BD.modificarHerramientas(tupla);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error devolverAlquiler(Alquiler): " + e);
            return false;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="cargarAlquilados()">

    public static ArrayList<Alquiler> cargarAlquilados() {
        createInstance();
        try {
            ArrayList<Alquiler> Alquilados = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM  `alquiler` AS a,  `personas` AS p WHERE p.id_persona = a.id_persona");

            while (rs.next()) {
                Persona persona = new Persona(rs.getString("nombre"), rs.getString("id_persona"),
                        rs.getString("tipoID"), rs.getString("direccion"), rs.getString("telefono"));
                Statement s2 = conexion.createStatement();
                ResultSet rs2 = s2.executeQuery("SELECT h.id_herramienta, h.nombre, h.detalle, h.precio_base, h.cantidad "
                        + "FROM  `alquiler` AS a,  `alquilados` AS al, `herramientas` AS h "
                        + "where " + rs.getString("id_alquiler") + " = al.id_alquiler and h.id_herramienta = al.id_herramienta");
                ArrayList<Tupla> herramientas = new ArrayList<>();
                while (rs2.next()) {
                    herramientas.add(new Tupla(new Herramienta(rs2.getString("nombre"), rs2.getString("detalle"),
                            rs2.getDouble("precio_base"), rs2.getString("id_herramienta")), rs2.getInt("cantidad")));
                }
                Alquilados.add(new Alquiler(persona, rs.getString("id_alquiler"), rs.getBoolean("estado"), rs.getString("fecha_entrega"),
                        rs.getString("hora_entrega"), rs.getString("fecha_devolucion"), rs.getString("hora_devolucion"),
                        rs.getString("tiempo_Prestamo"), herramientas));
            }
            return Alquilados;
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla Alquilados");
            System.out.println("Exeption: " + exception);
            return new ArrayList<Alquiler>();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="coincidirAlquilados(String, String)">

    public static ArrayList<Alquiler> coincidirAlquilados(String coincidencia, String campo) {
        createInstance();
        try {
            ArrayList<Alquiler> Alquilados = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select  a.id_alquiler, a.estado, a.fecha_entrega, a.fecha_devolucion,"
                    + " a.hora_entrega, a.hora_devolucion, a.tiempo_Prestamo, p.id_persona, p.tipoID, "
                    + "p.tipoPersona, p.nombre, p.direccion, p.telefono from alquiler as a, personas as p "
                    + "WHERE p.id_persona = a.id_persona and "
                    + campo + " LIKE '%" + coincidencia + "%';");

            while (rs.next()) {
                Persona persona = new Persona(rs.getString("nombre"), rs.getString("id_persona"),
                        rs.getString("tipoID"), rs.getString("direccion"), rs.getString("telefono"));
                Statement s2 = conexion.createStatement();
                ResultSet rs2 = s2.executeQuery("SELECT h.id_herramienta, h.nombre, h.detalle, h.precio_base, h.cantidad "
                        + "FROM  `alquiler` AS a,  `alquilados` AS al, `herramientas` AS h "
                        + "where " + rs.getString("id_alquiler") + " = al.id_alquiler and h.id_herramienta = al.id_herramienta");
                ArrayList<Tupla> herramientas = new ArrayList<>();
                while (rs2.next()) {
                    herramientas.add(new Tupla(new Herramienta(rs2.getString("nombre"), rs2.getString("detalle"),
                            rs2.getDouble("precio_base"), rs2.getString("id_herramienta")), rs2.getInt("cantidad")));
                }
                Alquilados.add(new Alquiler(persona, rs.getString("id_alquiler"), rs.getBoolean("estado"), rs.getString("fecha_entrega"),
                        rs.getString("hora_entrega"), rs.getString("fecha_devolucion"), rs.getString("hora_devolucion"),
                        rs.getString("tiempo_Prestamo"), herramientas));
            }
            return Alquilados;
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla Alquilados");
            System.out.println("Exeption: " + exception);
            return new ArrayList<Alquiler>();
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="buscarAlquilado(String id)">

    public static Alquiler buscarAlquilado(String id) {
        createInstance();
        try {
            ArrayList<Alquiler> Alquilados = new ArrayList<>();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select  a.id_alquiler, a.estado, a.fecha_entrega, a.fecha_devolucion,"
                    + " a.hora_entrega, a.hora_devolucion, a.tiempo_Prestamo, p.id_persona, p.tipoID, "
                    + "p.tipoPersona, p.nombre, p.direccion, p.telefono FROM alquiler as a, personas as p "
                    + "WHERE p.id_persona = a.id_persona and "
                    + "a.id_alquiler = '" + id + "'");

            while (rs.next()) {
                Persona persona = new Persona(rs.getString("nombre"), rs.getString("id_persona"),
                        rs.getString("tipoID"), rs.getString("direccion"), rs.getString("telefono"));
                Statement s2 = conexion.createStatement();
                ResultSet rs2 = s2.executeQuery("SELECT h.id_herramienta, h.nombre, h.detalle, h.precio_base, al.cantidad_alquilada "
                        + "FROM  `alquiler` AS a,  `alquilados` AS al, `herramientas` AS h "
                        + "where a.id_alquiler = " + rs.getString("id_alquiler") + " and a.id_alquiler = al.id_alquiler and h.id_herramienta = al.id_herramienta");
                System.out.println("id alquiler> " + rs.getString("id_alquiler"));
                ArrayList<Tupla> herramientas = new ArrayList<>();
                while (rs2.next()) {
                    herramientas.add(new Tupla(new Herramienta(rs2.getString("nombre"), rs2.getString("detalle"),
                            rs2.getDouble("precio_base"), rs2.getString("id_herramienta")), rs2.getInt("cantidad_alquilada")));
                }
                Alquilados.add(new Alquiler(persona, rs.getString("id_alquiler"), rs.getBoolean("estado"), rs.getString("fecha_entrega"),
                        rs.getString("hora_entrega"), rs.getString("fecha_devolucion"), rs.getString("hora_devolucion"),
                        rs.getString("tiempo_Prestamo"), herramientas));
            }
            return Alquilados.get(0);
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla Alquilados");
            System.out.println("Exeption: " + exception);
            return null;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="modificarHerramientas(Tupla)">

    public static boolean modificarHerramientas(Tupla herramienta) {
        createInstance();
        try {
            Statement s = conexion.createStatement();
//            s.execute("UPDATE  `inventario`.`herramientas` SET  `precio_base` = '12000',"
//                    + "`cantidad` =  '80' WHERE  `herramientas`.`id_herramienta` =2 LIMIT 1 ;");
            s.execute("UPDATE  `inventario`.`herramientas` SET  `nombre` =  '" + herramienta.getHerramienta().getNombre() + "',"
                    + "`detalle` =  '" + herramienta.getHerramienta().getDetalles_herramienta() + "',"
                    + "`precio_base` =  '" + herramienta.getHerramienta().getPrecio_base() + "',"
                    + "`cantidad` =  '" + herramienta.getCantidadActual()
                    + "' WHERE  `herramientas`.`id_herramienta` =" + herramienta.getHerramienta().getId() + " LIMIT 1 ;");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="insertarAlquiler(Alquiler)">

    public static boolean insertarAlquiler(Alquiler alquiler) {
        createInstance();
        try {
            Statement s = conexion.createStatement();
            int isAlquilado = 0;
            if (alquiler.isAlquilado()) {
                isAlquilado = 1;
            }
            s.execute("INSERT INTO  `inventario`.`alquiler` (`id_alquiler`,`id_persona`,`estado`,`fecha_entrega`,`fecha_devolucion`,"
                    + "`hora_entrega` ,`hora_devolucion`,`tiempo_Prestamo`)"
                    + "VALUES ('" + alquiler.getId() + "','" + alquiler.getPersona().getId() + "','" + isAlquilado
                    + "','" + DateTime.getFechaActual() + "','" + "00-00-0000"
                    + "','" + DateTime.getHoraActual() + "','00:00:00','" + alquiler.getTiempoPrestamo() + "');");
            for (int j = 0; j < alquiler.getHerramientas().size(); j++) {
                s.execute("INSERT INTO  `inventario`.`alquilados` (`id_herramienta` ,`id_alquiler`,`cantidad_alquilada`)"
                        + "VALUES ('" + alquiler.getHerramientas().get(j).getHerramienta().getId()
                        + "','" + alquiler.getId() + "','" + alquiler.getHerramientas().get(j).getCantidadActual() + "');");
                try {
                    Tupla tupla = (Tupla) BD.buscarHerramienta(alquiler.getHerramientas().get(j).getHerramienta().getId()).clone();
                    tupla.setCantidadActual(tupla.getCantidadActual() - alquiler.getHerramientas().get(j).getCantidadActual());
                    BD.modificarHerramientas(tupla);
                } catch (Exception e) {
                    System.out.println("No se pudo clonar una TUPLA error: " + e);
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error insertarAlquiler(Alquiler alquiler): " + e);
            return false;
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="AgregarInfo()">

    public void AgregarInfo() {
        createInstance();
        int codigo;
        String nombre, telefono, direccion;

        codigo = 7;
        nombre = "Julio";
        telefono = "6632555";
        direccion = "Yo vivo Aqui";

        String comandoSQL = "INSERT INTO proveedores VALUES (10,cc, 'pedro', '6632567', 'Aqui')";
        //String comandoSQL = "insert into proveedores values ('"+codigo+"', '"+nombre+"', '"+telefono+"', '"+direccion+"');";         
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate(comandoSQL);

            System.out.println("Registro agregado!");

            // Cerramos la interfaz Statement
            stm.close();

        } catch (java.sql.SQLException er) {
            System.out.println("No se pudo realizar la operacion: " + er);
        }
    }// </editor-fold>

    public static int generarID() {
        createInstance();
        try {
            int codigo = 1;
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT a.id_alquiler FROM  `inventario`.`alquiler` as a");
            while (rs.next()) {
                while (codigo == rs.getInt("id_alquiler")) {
                    codigo++;
                }
                System.out.println("cod:" + codigo);
            }
            return codigo;
        } catch (Exception exception) {
            System.out.println("Error: no se pudo cargar la tabla Alquier");
            System.out.println("Exeption: " + exception);
            return -1;
        }
    }
}
