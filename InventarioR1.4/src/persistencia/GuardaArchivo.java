package persistencia;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GuardaArchivo {

    public GuardaArchivo() {
    }

    public static boolean guardarPass(String usuario, String pass) {
        try {
            File archivo = new File("Login");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            PrintWriter pw = new PrintWriter(archivo);
            pw.write(usuario + "\t");
            pw.write(pass);
            pw.close();
        } catch (Exception exception) {
            return false;
        }
        return true;
    }
}
