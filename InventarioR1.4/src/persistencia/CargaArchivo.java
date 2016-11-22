/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CargaArchivo {

    public CargaArchivo() {
    }

    public static void cargarLogin(String archivo) {
        try {
            File file = new File(archivo);
            Scanner in = new Scanner(file);
            String login, pass;
            login = in.next();
            pass = in.next();
            BD.setLogin(login);
            if (!pass.equals("null")) {
                BD.setPassword(pass);
            } else {
                BD.setPassword("");
            }
            System.out.println("user: " + BD.getLogin() + " Pass: " + BD.getPassword());
        } catch (Exception exception) {
            System.out.println("Error al cargar Login: " + exception);
        }
    }
//    public static boolean cargarRegistro(String nameFile) throws IOException {
//        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nameFile));
//        try {
//            ArrayList<Factura> facturas = (ArrayList<Factura>) entrada.readObject();
//            RegistroVenta.getFacturas().addAll(facturas);
//        } catch (ClassNotFoundException ex) {
//            return false;
//        }
//
//        entrada.close();
//        return true;
//    }
}
