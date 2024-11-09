/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.ControladorVistaGestionVentas;
import modelos.*;
import util.Lista;
import util.interfaces.ILista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author luis
 */
public class VistaGestionVentas extends javax.swing.JFrame {

    ControladorVistaGestionVentas controladorVistaGestionVentas;
    Caseta caseta;
    AdministradorFlota usuarioLogeado;
    int fila;
    int columna;

    /**
     * Creates new form VistaGestionVentas
     */
    public VistaGestionVentas(Caseta caseta, AdministradorFlota usuarioLogeado, int fila, int columna) {
        initComponents();
        setLocationRelativeTo(this);
        this.controladorVistaGestionVentas = new ControladorVistaGestionVentas();
        this.caseta = caseta;
        this.usuarioLogeado = usuarioLogeado;
        this.fila = fila;
        this.columna = columna;
        this.llenarTabla();
        this.alistarBox();
        this.limpiarCampos();
    }

    private void llenarTabla(){
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id viaje", "Origen", "Destino", "Salida", "Llegada", "Bus", "Cupos", "Valor unitario"});

        // Asegurarse de que la lista no sea null
        if (viajes != null) {
            for (int i = 0; i < viajes.size(); i++) {
                model.addRow(new Object[]{
                        viajes.get(i).getIdViaje(),
                        viajes.get(i).getOrigen(),
                        viajes.get(i).getDestino(),
                        viajes.get(i).getFechaHoraSalida(),
                        viajes.get(i).getFechaHoraLlegada(),
                        viajes.get(i).getBus().getPlaca(),
                        viajes.get(i).getCupos(),
                        viajes.get(i).getValorUnitario()
                });
            }
        }
        tablaViajes.setModel(model);
    }

    private void alistarBox(){
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        for (int i = 0; i < this.caseta.getEmpresa().getViajes().size(); i++) {
            model2.addElement(this.caseta.getEmpresa().getViajes().get(i).getIdViaje());
        }
        cbxViaje.setModel(model2);

        ILista<Usuario> usuariosGlobales = this.controladorVistaGestionVentas.obtenerUsuarios();
        ILista<Cliente> clientes = new Lista<>();

        for(int i = 0; i < usuariosGlobales.size(); i++){
            if(usuariosGlobales.get(i) instanceof Cliente){
                clientes.add((Cliente) usuariosGlobales.get(i));
            }
        }

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (int i = 0; i < clientes.size(); i++) {
            model.addElement(clientes.get(i).getDocumento());
        }
        cbxCliente.setModel(model);
    }

    private void limpiarCampos(){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        lblFechaVenta.setText(fechaHoraActual.format(formatter));
        txtIdTiquete.setText("");
        txtCantidadTiquetes.setText("");
    }

    private void agregarTiquetesEnTodaLaApp(int cantidadTiquetes, String idTiqueteBase, String idViaje, String idCliente, Viaje viaje, Cliente cliente) {
        for (int i = 0; i < cantidadTiquetes; i++) {
            String idTiquete = idTiqueteBase + "-" + (i + 1);
            int indiceViajeCaseta = this.controladorVistaGestionVentas.obtenerViajeIndiceCaseta(this.caseta, idViaje);

            int cupos = this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getCupos();
            if(viaje.getCupos() == 0){
                JOptionPane.showMessageDialog(null, "El viaje está lleno.");
                return;
            } else if ((cupos - cantidadTiquetes) < 0){
                JOptionPane.showMessageDialog(null, "NO hay cupos para la cantidad de tiquetes a comprar.");
                return;
            } else {
                Tiquete tiquete = new Tiquete(idTiquete, viaje, cliente);

                this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).setCupos(cupos - 1);
                this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().add(tiquete);
                this.controladorVistaGestionVentas.agregarTiquete(idTiquete, viaje, cliente);
                this.controladorVistaGestionVentas.agregarTiqueteCliente(idCliente, tiquete);

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        panelCrudGestionVentas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxViaje = new javax.swing.JComboBox<>();
        txtCantidadTiquetes = new javax.swing.JTextField();
        btnVender = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cbxCliente = new javax.swing.JComboBox<>();
        lblFechaVenta = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdTiquete = new javax.swing.JTextField();
        panelTablaGestionVentas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaViajes = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jTextField3.setText("jTextField3");

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Viaje:");

        jLabel2.setText("Cliente:");

        jLabel3.setText("Cantidad de tiquetes:");

        jLabel4.setText("Fecha de venta:");

        cbxViaje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnVender.setText("Vender");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");

        jButton3.setText("Editar");

        jButton5.setText("Buscar");

        cbxCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblFechaVenta.setText("jLabel5");

        jLabel5.setText("Id del tiquete:");

        javax.swing.GroupLayout panelCrudGestionVentasLayout = new javax.swing.GroupLayout(panelCrudGestionVentas);
        panelCrudGestionVentas.setLayout(panelCrudGestionVentasLayout);
        panelCrudGestionVentasLayout.setHorizontalGroup(
            panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                        .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(34, 34, 34)
                                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbxCliente, javax.swing.GroupLayout.Alignment.LEADING, 0, 240, Short.MAX_VALUE)
                                    .addComponent(txtCantidadTiquetes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addComponent(cbxViaje, javax.swing.GroupLayout.Alignment.LEADING, 0, 240, Short.MAX_VALUE)
                                    .addComponent(lblFechaVenta, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdTiquete, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                                .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(96, Short.MAX_VALUE))
                    .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                        .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelCrudGestionVentasLayout.setVerticalGroup(
            panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdTiquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidadTiquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblFechaVenta))
                .addGap(89, 89, 89)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVender)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        tablaViajes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaViajes);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jButton6.setText("ver");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTablaGestionVentasLayout = new javax.swing.GroupLayout(panelTablaGestionVentas);
        panelTablaGestionVentas.setLayout(panelTablaGestionVentasLayout);
        panelTablaGestionVentasLayout.setHorizontalGroup(
            panelTablaGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaGestionVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTablaGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablaGestionVentasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresar)))
                .addContainerGap())
            .addGroup(panelTablaGestionVentasLayout.createSequentialGroup()
                .addComponent(jButton6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelTablaGestionVentasLayout.setVerticalGroup(
            panelTablaGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaGestionVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCrudGestionVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTablaGestionVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTablaGestionVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCrudGestionVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaAdminFlota vaf = new VistaAdminFlota(this.usuarioLogeado, this.caseta, this.fila, this.columna);
        vaf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        try {
            if (txtIdTiquete.getText().trim().isEmpty() ||
                    cbxViaje.getSelectedItem() == null ||
                    cbxCliente.getSelectedItem() == null ||
                    txtCantidadTiquetes.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
                return;
            }

            String idTiqueteBase = txtIdTiquete.getText().trim();
            String idViaje = cbxViaje.getSelectedItem().toString();
            String idCliente = cbxCliente.getSelectedItem().toString();
            int cantidadTiquetes = Integer.parseInt(txtCantidadTiquetes.getText().trim());


            Viaje viaje = this.controladorVistaGestionVentas.obtenerViajePorId(idViaje);
            Cliente cliente = this.controladorVistaGestionVentas.obtenerClientePorId(idCliente);

            if (viaje == null || cliente == null) {
                JOptionPane.showMessageDialog(null, "El viaje o el cliente no se encontraron.");
                return;
            }

            for(int i = 0; i < this.caseta.getEmpresa().getViajes().size(); i++){
                System.out.println(this.caseta.getEmpresa().getViajes().get(i).getDestino());
            }

            this.agregarTiquetesEnTodaLaApp(cantidadTiquetes, idTiqueteBase, idViaje, idCliente, viaje, cliente);
            this.controladorVistaGestionVentas.asignarCaseta(this.fila, this.columna, this.caseta);
            this.limpiarCampos();
            this.llenarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad de tiquetes debe ser un número válido.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        for(int i = 0; i < this.caseta.getEmpresa().getViajes().size(); i++){
            System.out.println(this.caseta.getEmpresa().getViajes().get(i).getCupos());
        }
        System.out.println("*********************************************************************");
        for(int i = 0; i < this.controladorVistaGestionVentas.obtenerTiquetes().size(); i++){
            System.out.println(this.controladorVistaGestionVentas.obtenerTiquetes().get(i).getIdTiquete());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaGestionVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGestionVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGestionVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGestionVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGestionVentas(null, null, 0, 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> cbxCliente;
    private javax.swing.JComboBox<String> cbxViaje;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JPanel panelCrudGestionVentas;
    private javax.swing.JPanel panelTablaGestionVentas;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JTextField txtCantidadTiquetes;
    private javax.swing.JTextField txtIdTiquete;
    // End of variables declaration//GEN-END:variables
}
