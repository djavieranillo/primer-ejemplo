/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.BD;
import persistencia.CargaArchivo;

/**
 *
 * @author DDV
 */
public class VistaInventario extends javax.swing.JFrame {

    private DefaultTableModel dtmBHerra;
    private DefaultTableModel dtmBDisp;
    private DefaultTableModel dtmRegAlqui;
    private DefaultTableModel dtmAlqui;
    private DefaultTableModel dtmAlqClient;
    /*no dejar poner estacion en los textos*/

    public VistaInventario() {
//        database.AgregarInfo();
        dtmAlqui = new DefaultTableModel() {
            /**
             * Reescribimos este metodo para que las columnas no se puedan
             * editar.
             *
             * @param row
             * @param column
             * @return
             */
            public boolean isCellEditable(int row, int column) {
                if (column == 2 || column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        dtmAlqui.setColumnIdentifiers(new String[]{
            "Id", "Nombre", "Tiempo de Prestamo", "Cantidad", "Precio Base"});
        dtmAlqClient = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtmAlqClient.setColumnIdentifiers(new String[]{
            "Nombre", "Identificacion", "direccion", "telefono"});
        dtmBHerra = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtmBHerra.setColumnIdentifiers(new String[]{
            "Nombre", "Descripcion", "Id", "Cantidad", "Precio Base"});
        dtmBDisp = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtmBDisp.setColumnIdentifiers(new String[]{
            "Nombre", "Descripcion", "Id", "Cantidad", "Precio Base"});
        dtmRegAlqui = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtmRegAlqui.setColumnIdentifiers(new String[]{
            "Id Alquiler", "Nombre", "Estado", "Fecha de recibido", "Fecha devolucion", "Tiempo Prestamo"});
        //Registro.getRegistro().setInventario(BD.cargarHerramientas());
        initComponents();

    }

    /**
     * Actualiza el DefaultTableModel de la tabla que esta en la pestaña de
     * herramientas.
     */
    public void cargarTablaHerramientas(ArrayList<Tupla> list, DefaultTableModel dtm) {
        vaciarTabla(dtm);
        for (int i = 0; i < list.size(); i++) {
            Vector vector = new Vector();
            vector.add(list.get(i).getHerramienta().getNombre());
            vector.add(list.get(i).getHerramienta().getDetalles_herramienta());
            vector.add(list.get(i).getHerramienta().getId());
            vector.add(list.get(i).getCantidadActual());
            vector.add(list.get(i).getHerramienta().getPrecio_base());
            dtm.addRow(vector);
        }
    }

    public void cargarTablaPersonas(ArrayList<Persona> list, DefaultTableModel dtm) {
        vaciarTabla(dtm);
        for (int i = 0; i < list.size(); i++) {
            Vector vector = new Vector();
            vector.add(list.get(i).getNombre());
            vector.add(list.get(i).getId());
            vector.add(list.get(i).getDireccion());
            vector.add(list.get(i).getTelefono());
            dtm.addRow(vector);
        }
    }

    public void cargarTablaAlquiler(ArrayList<Alquiler> list, DefaultTableModel dtm) {
        vaciarTabla(dtm);
        for (int i = 0; i < list.size(); i++) {
            Vector vector = new Vector();
//            System.out.println(""+list.get(i).toString());
            vector.add(list.get(i).getId());
            vector.add(list.get(i).getPersona().getNombre());
            vector.add(list.get(i).isAlquilado());
            vector.add(list.get(i).getFechaEntrega()
                    + " " + list.get(i).getHoraEntrega());
            vector.add(list.get(i).getFechaDevolucion()
                    + " " + list.get(i).getHoraDevolucion());
            if (list.get(i).getTiempoPrestamo().isEmpty()) {
                try {
                    Date date = DateTime.deStringToDate(list.get(i).getFechaEntrega()
                            + " " + list.get(i).getHoraEntrega());
                    Date date2 = DateTime.deStringToDate(DateTime.getFechaActual()
                            + " " + DateTime.getHoraActual());
                    vector.add(DateTime.diferenciasDeFechas(date, date2));
                } catch (Exception e) {
                    System.out.println("error restar fechas: " + e);
                }
            } else {
                vector.add(list.get(i).getTiempoPrestamo());
            }
            dtm.addRow(vector);
        }
    }

    public void vaciarTabla(DefaultTableModel dtm) {
        while (dtm.getRowCount() != 0) {
            dtm.removeRow(0);
        }
    }

    public void resetCampos() {
        textFieldCantidad.setText("");
        textFieldDescrip.setText("");
        textFieldID.setText("");
        textFieldNombre.setText("");
        textFieldPrecBase.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField7.setText("");
        jTextField10.setText("");
        vaciarTabla(dtmAlqui);
        vaciarTabla(dtmBDisp);
        vaciarTabla(dtmBHerra);
        vaciarTabla(dtmRegAlqui);
    }
//    private JFrame miFrame = null;
//
//    public JFrame visible(JFrame frame) {
//        miFrame = frame;
//        frame.setLocationRelativeTo(this);
//        frame.setVisible(true);
//        this.setEnabled(false);
//        return this;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable(dtmAlqClient);
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable(dtmBDisp);
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable(dtmAlqui);
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(dtmRegAlqui);
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        textFieldPrecBase = new javax.swing.JTextField("0");
        jLabel5 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        textFieldCantidad = new javax.swing.JTextField("0");
        textFieldID = new javax.swing.JTextField();
        textFieldDescrip = new javax.swing.JTextField();
        textFieldNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable(dtmBHerra);
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Localización"));
        jPanel8.setPreferredSize(new java.awt.Dimension(375, 68));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario Stock");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Responsable"));

        jLabel23.setText("Direccion");

        jTextField4.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                jComboBox1.setEnabled(true);
                int caracter = e.getKeyChar();
                if( (caracter < 65 || caracter > 90)&& (caracter < 97 || caracter > 122)
                    && (caracter < 48 || caracter > 57) && caracter != 32 && caracter != 45
                    && caracter != 58 && caracter != 95){
                    e.consume();
                    caracter = 0;
                }
                String text = jTextField4.getText()+(char)caracter;
                cargarTablaPersonas(BD.coincidirPersonas(text, "direccion"), dtmAlqClient);
            }
        });

        jLabel8.setText("Telefono");

        jTextField3.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                jComboBox1.setEnabled(true);
                int caracter = e.getKeyChar();
                if( (caracter < 48 || caracter > 57) && caracter != 8){
                    e.consume();
                    caracter = 0;
                }
                String text = jTextField3.getText()+(char)caracter;
                cargarTablaPersonas(BD.coincidirPersonas(text, "telefono"), dtmAlqClient);
            }
        });

        jLabel7.setText("Nombre");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "cc.", "nit."}));

        jTextField1.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                jComboBox1.setEnabled(true);
                int caracter = e.getKeyChar();
                if( (caracter < 65 || caracter > 90)&& (caracter < 97 || caracter > 122) && caracter != 32){
                    e.consume();
                    caracter = 0;
                }
                String text = jTextField1.getText()+(char)caracter;
                cargarTablaPersonas(BD.coincidirPersonas(text, "nombre"), dtmAlqClient);
            }
        });

        //Si lo que se desea es capturar el evento cuando el jtextfield este en focus o seleccionado.

        jTextField2.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                jComboBox1.setEnabled(true);
                int caracter = e.getKeyChar();
                if( (caracter < 48 || caracter > 57)){
                    e.consume();
                    caracter = 0;
                }
                String text = jTextField2.getText()+(char)caracter;
                cargarTablaPersonas(BD.coincidirPersonas(text, "id_persona"), dtmAlqClient);
            }
        });

        jTable6.getTableHeader().setReorderingAllowed(false);
        /*
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        */
        jScrollPane6.setViewportView(jTable6);

        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("No Doc.");

        jLabel11.setText("Tipo Doc.");

        jButton12.setText("Registrar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 231, Short.MAX_VALUE))
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton12))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Herramienta"));

        jLabel14.setText("Busqueda por nombre: ");

        jTextField15.addFocusListener(new FocusListener() {
            //Se ejecuta cuando se seleccione
            public void focusGained(FocusEvent e) {
            }
            //se ejecuta cuando se deja de seleccionar
            public void focusLost(FocusEvent e) {
                jTextField15.setText("");
            }
        });
        jTextField15.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                int caracter = e.getKeyChar();
                if( (caracter < 65 || caracter > 90)&& (caracter < 97 || caracter > 122) && caracter != 32){
                    e.consume();
                    caracter = 0;
                }
                String text = jTextField15.getText()+(char)caracter;
                cargarTablaHerramientas(BD.coincidirHerramientas(text, "nombre"), dtmBDisp);
            }
        });

        jTable4.getTableHeader().setReorderingAllowed(false);
        /*
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "numero factura", "fecha", "hora", "herramientas"
            }
        ));
        */
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(jTable4);

        jButton2.setText("Seleccionar >>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable5.getTableHeader().setReorderingAllowed(false);
        /*
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        */
        jScrollPane5.setViewportView(jTable5);

        jButton7.setText("Alquilar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setText("Remover");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton2)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Alquilar", jPanel4);

        jTable1.getTableHeader().setReorderingAllowed(false);
        /*
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "fecha de reibido", "fecha devolucion", "tiempo de prestamo"
            }
        ));
        */
        jScrollPane1.setViewportView(jTable1);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda Avanzada"));

        jLabel22.setText("Identificacion");

        jTextField7.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                jComboBox1.setEnabled(true);
                int caracter = e.getKeyChar();
                if( (caracter < 48 || caracter > 57)){
                    e.consume();
                    caracter = 0;
                }
                String text = jTextField7.getText()+(char)caracter;
                cargarTablaAlquiler(BD.coincidirAlquilados(text, "p.id_persona"), dtmRegAlqui);
            }
        });

        jButton4.setText("Mostrar todo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText("Nombre");

        jTextField10.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                int caracter = e.getKeyChar();
                /*if( (caracter < 65 || caracter > 90)&& (caracter < 97 || caracter > 122) && (caracter < 48 || caracter > 57)){
                    e.consume();
                }*/
                String text = jTextField10.getText()+(char)caracter;

                cargarTablaAlquiler(BD.coincidirAlquilados(text, "p.nombre"), dtmRegAlqui);

            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton8.setText("Devolución");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton13.setText("Ver detalle");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton13))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Registro de Alquiler", jPanel5);

        //textFieldPrecBase.setText(t);
        textFieldPrecBase.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                int caracter = e.getKeyChar();
                if( (caracter >= 48 && caracter <= 57)){
                    if(textFieldPrecBase.getText().equals("0")){
                        e.consume();
                        textFieldPrecBase.setText(""+(char)caracter);
                    }
                }else{
                    e.consume();
                }
                if(textFieldPrecBase.getText().isEmpty()){
                    textFieldPrecBase.setText("0");
                }
            }
        });

        jLabel5.setText("Precio base");

        jLabel26.setText("Cantidad");

        textFieldCantidad.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                int caracter = e.getKeyChar();
                if((caracter >= 48 && caracter <= 57)){
                    if(textFieldCantidad.getText().equals("0")){
                        e.consume();
                        textFieldCantidad.setText(""+(char)caracter);
                    }
                }else{
                    e.consume();
                }
                if(textFieldCantidad.getText().isEmpty()){
                    textFieldCantidad.setText("0");
                }
            }
        });

        textFieldID.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                int caracter = e.getKeyChar();
                if( (caracter < 65 || caracter > 90)&& (caracter < 97 || caracter > 122)
                    && (caracter < 48 || caracter > 57) && caracter != 32 && caracter != 45
                    && caracter != 58 && caracter != 95){
                    e.consume();
                    caracter = 0;
                }
                String text = textFieldID.getText()+(char)caracter;
                cargarTablaHerramientas(BD.coincidirHerramientas(text,"id_herramienta"), dtmBHerra);
                jLabel2.setText("");
            }
        });

        textFieldDescrip.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                int caracter = e.getKeyChar();
                if( (caracter < 65 || caracter > 90)&& (caracter < 97 || caracter > 122)
                    && (caracter < 48 || caracter > 57) && caracter != 32 && caracter != 45
                    && caracter != 58 && caracter != 95){
                    e.consume();
                    caracter = 0;
                }
                String text = textFieldDescrip.getText()+(char)caracter;
                cargarTablaHerramientas(BD.coincidirHerramientas(text, "detalle"), dtmBHerra);
            }
        });

        textFieldNombre.addKeyListener(new KeyAdapter(){
            /**
            * Uso el metodo keyType para capturar la tecla presionada
            * antes de mostrarla y asi darle el manejo que deseo.
            */
            public void keyTyped(KeyEvent e){
                int caracter = e.getKeyChar();
                if( (caracter < 65 || caracter > 90)&& (caracter < 97 || caracter > 122) && caracter != 32){
                    e.consume();
                    caracter = 0;
                }
                String text = textFieldNombre.getText()+(char)caracter;
                cargarTablaHerramientas(BD.coincidirHerramientas(text, "nombre"), dtmBHerra);
            }
        });

        jLabel1.setText("Nombre");

        jLabel3.setText("Descripción");

        jLabel4.setText("ID");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Subir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setText("Reset");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Mostrar todo");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton9.setText("Modificar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton14.setText("restar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(textFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldPrecBase, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 407, Short.MAX_VALUE))
                            .addComponent(textFieldID)
                            .addComponent(textFieldNombre)
                            .addComponent(textFieldDescrip, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(textFieldPrecBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton10)
                            .addComponent(jButton11)
                            .addComponent(jButton1)
                            .addComponent(jButton9)
                            .addComponent(jButton14))))
                .addContainerGap())
        );

        jTable3.getTableHeader().setReorderingAllowed(false);
        /*
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "nombre", "descripcion", "precio base"
            }
        ));
        */
        jScrollPane3.setViewportView(jTable3);

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Herramientas/Maquinas", jPanel2);

        jMenu1.setText("Base Datos");

        jMenuItem1.setText("Configurar Base datos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        GuardaArchivo.guardarHerramientas(Registro.getRegistro().getInventario());
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int cantNueva = Integer.parseInt(textFieldCantidad.getText());
        String nomNuevo = textFieldNombre.getText();
        String detallesNuevo = textFieldDescrip.getText();
        double precioNuevo = Double.parseDouble(textFieldPrecBase.getText());
        String idNuevo = textFieldID.getText();
        String mensaje = "Error:\n";
        if (BD.buscarHerramienta(idNuevo) != null) {
            mensaje += "\n*El ID esta siendo usado, intente con otro.";
        }
        if (idNuevo.equals("")) {
            mensaje += "\n*Digite un ID que no se encuentra en uso.";
        }
        if (cantNueva <= 0) {
            mensaje += "\n*Debe digitar una cantidad distinta de '0' y que no sea negativa.";
        }
        if (nomNuevo.equals("")) {
            mensaje += "\n*No a digitado un NOMBRE";
        }
        if (precioNuevo <= 0) {
            mensaje += "\n*El precio de la herramienta debe ser MAYOR que 0";
        }
        if (mensaje.equals("Error:\n")) {
            BD.insertarHerramientas(new Tupla(new Herramienta(textFieldNombre.getText(),
                    textFieldDescrip.getText(), Double.parseDouble(textFieldPrecBase.getText()),
                    textFieldID.getText()), Integer.parseInt(textFieldCantidad.getText())));
            this.resetCampos();
        } else {
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        int row = jTable4.getSelectedRow();
        if (row != -1) {
            Tupla tupla = BD.buscarHerramienta("" + dtmBDisp.getValueAt(row, 2));
            moverFilaHerra(dtmAlqui, new Tupla(new Herramienta(tupla.getHerramienta().getNombre(),
                    tupla.getHerramienta().getDetalles_herramienta(), tupla.getHerramienta().getPrecio_base(),
                    tupla.getHerramienta().getId()), 0));
        } else {
            JOptionPane.showMessageDialog(null, "Si desea alquilar algo primero debe seleccionarlo.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (jTextField1.getText().equals("") || jTextField2.getText().equals("")
                || jTextField3.getText().equals("") || jTextField4.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tiene que diligenciar todos los datos del Responsable del alquiler para continuar");
        } else {
            if (BD.buscarPersona(jTextField2.getText()) == null) {
                BD.insertPersonas(new Persona(jTextField1.getText(), jTextField2.getText(), jComboBox1.getSelectedItem().toString(), jTextField4.getText(), jTextField3.getText()), true);
            }
            try {
                ArrayList<Tupla> herramientas = new ArrayList<>();
                for (int i = 0; i < dtmAlqui.getRowCount(); i++) {
                    Tupla tupla = BD.buscarHerramienta(dtmAlqui.getValueAt(i, 0).toString());
                    if (tupla != null && tupla.getCantidadActual() >= Integer.parseInt((String) dtmAlqui.getValueAt(i, 3))) {
                        tupla.setCantidadActual(Integer.parseInt((String) dtmAlqui.getValueAt(i, 3)));
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad que intenta alquilar no esta disponible.\nPruebe colocando una cantidada menos o igual a la existente");
                        return;
                    }
                    herramientas.add(tupla);
                }
                Alquiler alquiler = new Alquiler(new Persona(jTextField1.getText(), jTextField2.getText(), jComboBox1.getSelectedItem().toString(), jTextField4.getText(), jTextField3.getText()),
                        BD.generarID() + "", true, DateTime.getFechaActual(), DateTime.getHoraActual(), "", "", "", herramientas);
                BD.insertarAlquiler(alquiler);
            } catch (Exception e) {
                System.out.println("Error al crear los Alquiler: " + e);
            }
            JOptionPane.showMessageDialog(null, "Se realizo el alquiler Aqui hay que mostrar una factura");
            this.resetCampos();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row = jTable3.getSelectedRow();
        if (row != -1) {
            textFieldNombre.setText(dtmBHerra.getValueAt(row, 0).toString());
            textFieldDescrip.setText(dtmBHerra.getValueAt(row, 1).toString());
            textFieldID.setText(dtmBHerra.getValueAt(row, 2).toString());
            textFieldCantidad.setText("0");
            textFieldPrecBase.setText(dtmBHerra.getValueAt(row, 4).toString());
        } else {
            jLabel2.setText("Debe seleccionar un resultado.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = jTable6.getSelectedRow();
        if (row != -1) {
            Persona persona = BD.buscarPersona(dtmAlqClient.getValueAt(row, 1).toString());
            jTextField2.setText(persona.getId());
            jTextField1.setText(persona.getNombre());
            jTextField4.setText(persona.getDireccion());
            jTextField3.setText(persona.getTelefono());
            for (int i = 0; i < jComboBox1.getItemCount(); i++) {
                if (persona.getTipoId().equals(jComboBox1.getItemAt(i).toString())) {
                    jComboBox1.setSelectedIndex(i);
                    jComboBox1.setEnabled(false);
                }
            }
        } else {
            //jLabel2.setText("Debe seleccionar un resultado.");
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            dtmAlqui.removeRow(jTable5.getSelectedRow());
        } catch (Exception e) {
            System.out.println(".:No selecciono una Fila para remover:.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            try {
                Alquiler alquiler = BD.buscarAlquilado("" + dtmRegAlqui.getValueAt(row, 0));
                if (alquiler != null && alquiler.isAlquilado()) {
                    alquiler.setAlquilado(false);
                    alquiler.setFechaDevolucion(DateTime.getFechaActual());
                    alquiler.setHoraDevolucion(DateTime.getHoraActual());
                    alquiler.setTiempoPrestamo("" + dtmRegAlqui.getValueAt(row, 5));

                    if (BD.devolverAlquiler(alquiler)) {
                        dtmRegAlqui.setValueAt("false", row, 2);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error ActionPerformed() boton devolver: " + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Si desea Devolver herramientas primero debe seleccionar.");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        cargarTablaHerramientas(BD.cargarHerramientas(), dtmBHerra);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        textFieldNombre.setText("");
        textFieldDescrip.setText("");
        textFieldID.setText("");
        textFieldPrecBase.setText("0");
        textFieldCantidad.setText("0");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Tupla tupla = BD.buscarHerramienta(textFieldID.getText());
        if (tupla != null) {
            int cantNueva = Integer.parseInt(textFieldCantidad.getText());
            String nomNuevo = textFieldNombre.getText();
            String detallesNuevo = textFieldDescrip.getText();
            double precioNuevo = Double.parseDouble(textFieldPrecBase.getText());
            String idNuevo = textFieldID.getText();
            String mensaje = "Se realizaran los siguientes cambios:\n";
            if (!nomNuevo.equals("") && !nomNuevo.equalsIgnoreCase(tupla.getHerramienta().getNombre())) {
                mensaje += "\nNombre \n Anterior: " + tupla.getHerramienta().getNombre() + " - Nuevo: " + nomNuevo;
                tupla.getHerramienta().setNombre(nomNuevo);
            }
            if (!detallesNuevo.equals("") && !detallesNuevo.equalsIgnoreCase(tupla.getHerramienta().getDetalles_herramienta())) {
                mensaje += "\nDetalles \n Anterior: " + tupla.getHerramienta().getDetalles_herramienta() + " - Nuevo: " + detallesNuevo;
                tupla.getHerramienta().setDetalles_herramienta(detallesNuevo);
            }
            if (precioNuevo != 0 && precioNuevo != tupla.getHerramienta().getPrecio_base()) {
                mensaje += "\nPrecio \n Anterior: " + tupla.getHerramienta().getPrecio_base() + " - Nuevo: " + precioNuevo;
                tupla.getHerramienta().setPrecio_base(precioNuevo);
            }
            if (cantNueva != 0 && cantNueva != tupla.getCantidadActual()) {
                mensaje += "\nCantidad \n Anterior: " + tupla.getCantidadActual() + " - Nuevo: " + cantNueva;

                System.out.println("cantidad: " + BD.buscarHerramienta(idNuevo).getCantidadActual());
                tupla.setCantidadActual(BD.buscarHerramienta(idNuevo).getCantidadActual() + cantNueva);
            }
            if (!mensaje.equals("Se realizaran los siguientes cambios:\n") && JOptionPane.showConfirmDialog(this, mensaje) == 0) {
                BD.modificarHerramientas(tupla);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe colocar la identificacion 'ID' para poder modificar esa herramienta.");
            return;
        }
        this.resetCampos();
        this.vaciarTabla(dtmBHerra);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cargarTablaAlquiler(BD.cargarAlquilados(), dtmRegAlqui);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (BD.buscarPersona(jTextField2.getText()) != null) {
            JOptionPane.showMessageDialog(null, "El Usuario que intenta registrar ya se encuentra registrado, verifique que el ID de usuario no este en uso.");
            return;
        }
        if (jTextField1.getText().equals("") || jTextField2.getText().equals("") || jComboBox1.getSelectedItem().toString().equals("") || jTextField4.getText().equals("") || jTextField3.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tiene que diligenciar todos los datos del Responsable del alquiler para continuar");
            return;
        }
        if (BD.insertPersonas(new Persona(jTextField1.getText(), jTextField2.getText(), jComboBox1.getSelectedItem().toString(), jTextField4.getText(), jTextField3.getText()), true)) {
            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            try {
                Alquiler alquiler = BD.buscarAlquilado("" + dtmRegAlqui.getValueAt(row, 0));
                if (alquiler != null) {
                    VistaDetalleAlquiler vda = new VistaDetalleAlquiler();
                    vda.cargarAlquiler(alquiler);
                    vda.visibleVInventario(this);
                }
            } catch (Exception e) {
                System.out.println("Error ActionPerformed() boton devolver: " + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Para ver la informacion de las herramientas primero debe seleccionar.");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        LoginBaseD lbd = new LoginBaseD();
        lbd.visibleVInventario(this);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Tupla tupla = BD.buscarHerramienta(textFieldID.getText());
        if (tupla != null) {
            int cantNueva = Integer.parseInt(textFieldCantidad.getText());
            String idNuevo = textFieldID.getText();
            if (cantNueva != 0) {
                System.out.println("cantidad: " + BD.buscarHerramienta(idNuevo).getCantidadActual());
                int cantidadMod = BD.buscarHerramienta(idNuevo).getCantidadActual() - cantNueva;
                if (cantidadMod < 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad que esta restando es mayor de la existente.");
                    return;
                }
                tupla.setCantidadActual(cantidadMod);
                BD.modificarHerramientas(tupla);
                cargarTablaHerramientas(BD.cargarHerramientas(), dtmBHerra);
            } else {
                JOptionPane.showMessageDialog(this, "Coloque una cantidad mayor que cero y que no sea mayor que la existente.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe colocar la identificacion 'ID' para poder restar esa herramienta.");
            return;
        }
        this.resetCampos();
    }//GEN-LAST:event_jButton14ActionPerformed

    public void moverFilaHerra(DefaultTableModel dtm, Tupla tupla) {
        boolean door = true;
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (tupla.getHerramienta().getId().equals(dtm.getValueAt(i, 2))) {
                door = false;
            }
        }
        if (door) {
            Vector vector = new Vector();
            vector.add(tupla.getHerramienta().getId());
            vector.add(tupla.getHerramienta().getNombre());
            vector.add("0");
            vector.add("0");
            vector.add(tupla.getHerramienta().getPrecio_base());
            dtm.addRow(vector);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaInventario vista = new VistaInventario();
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField textFieldCantidad;
    private javax.swing.JTextField textFieldDescrip;
    private javax.swing.JTextField textFieldID;
    private javax.swing.JTextField textFieldNombre;
    private javax.swing.JTextField textFieldPrecBase;
    // End of variables declaration//GEN-END:variables
}
