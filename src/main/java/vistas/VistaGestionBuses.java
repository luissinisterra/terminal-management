/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.ControladorVistaGestionBuses;
import modelos.AdministradorFlota;
import modelos.Bus;
import modelos.Caseta;
import modelos.Usuario;
import util.interfaces.ILista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class VistaGestionBuses extends javax.swing.JFrame {

    ControladorVistaGestionBuses controladorVistaGestionBuses;
    Caseta caseta;
    Usuario usuarioLogeado;
    int fila;
    int columna;

    /**
     * Creates new form VistaGestionBuses
     */
    public VistaGestionBuses(Caseta caseta, Usuario usuarioLogeado, int fila, int columna) {
        initComponents();
        setLocationRelativeTo(this);
        this.controladorVistaGestionBuses = new ControladorVistaGestionBuses();
        this.caseta = caseta;
        this.usuarioLogeado = usuarioLogeado;
        this.fila = fila;
        this.columna = columna;
        this.llenarTabla();
        this.actualizarPlazasDisponibles();
        this.limpiarCampos();
    }

    public void actualizarPlazasDisponibles(){
        int op = this.caseta.getPlazasEstacionamiento() - this.caseta.getEmpresa().getBuses().size();
        lblPlazasDisponibles.setText(String.valueOf("Plazas disponibles: " + op + "/" + this.caseta.getPlazasEstacionamiento()));
    }

    public void llenarTabla(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Placa", "Cantidad de puestos", "Modelo", "Año", "Conductor", "Disponibilidad"});

        // Asegurarse de que la lista no sea null
        ILista<Bus> buses = this.caseta.getEmpresa().getBuses();
        if (buses != null) {
            for (int i = 0; i < buses.size(); i++) {
                model.addRow(new Object[]{
                        buses.get(i).getPlaca(),
                        buses.get(i).getCantidadPuestos(),
                        buses.get(i).getModelo(),
                        buses.get(i).getAño(),
                        buses.get(i).getNombreConductor(),
                        buses.get(i).isDisponibilidad() != true ? "No disponible" : "Disponible"
                });
            }
        }
        tablaBuses.setModel(model);
    }

    private void limpiarCampos(){
        txtPlaca.setText("");
        txtCantidadPuestos.setText("");
        txtModelo.setText("");
        txtAño.setText("");
        txtConductor.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBuses = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        lblPlazasDisponibles = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        txtCantidadPuestos = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAño = new javax.swing.JTextField();
        txtConductor = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaBuses.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaBuses);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblPlazasDisponibles.setText("Plazas disponibles: 0/0");

        jLabel1.setText("Placa:");

        jLabel2.setText("Cantidad de puestos:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jLabel3.setText("Modelo:");

        jLabel4.setText("Año:");

        jLabel5.setText("Conductor:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadPuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtCantidadPuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEditar))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPlazasDisponibles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlazasDisponibles)
                    .addComponent(btnRegresar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaAdminFlota vaf = new VistaAdminFlota((AdministradorFlota) this.usuarioLogeado, this.caseta, this.fila, this.columna);
        vaf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            if (txtPlaca.getText().trim().isEmpty() || txtCantidadPuestos.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
                return;
            }

            String placa = txtPlaca.getText().trim();
            int cantidadPuestos = Integer.parseInt(txtCantidadPuestos.getText().trim());
            String modelo = txtModelo.getText().trim();
            int año = Integer.parseInt(txtAño.getText().trim());
            String conductor = txtConductor.getText().trim();

            if (this.caseta.getPlazasEstacionamiento() > this.caseta.getEmpresa().getBuses().size()) {
                this.controladorVistaGestionBuses.agregarBus(placa, cantidadPuestos, modelo, año, conductor);
                this.caseta.getEmpresa().getBuses().add(new Bus(placa, cantidadPuestos, modelo, año, conductor));
                this.controladorVistaGestionBuses.asignarCaseta(fila, columna, this.caseta);

                this.llenarTabla();
                this.actualizarPlazasDisponibles();
                this.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Máximo de plazas asignadas.");
                this.limpiarCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad de puestos debe ser un número válido.");
        } catch (RuntimeException e) {
            // Mostrar mensaje de error si ocurre una excepción en otro lugar del código
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (txtPlaca.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
                return;
            }

            String placa = txtPlaca.getText();
            this.controladorVistaGestionBuses.eliminarBus(placa);

            for(int i = 0; i < this.caseta.getEmpresa().getBuses().size(); i++){
                if(this.caseta.getEmpresa().getBuses().get(i).getPlaca().equals(placa)){
                    this.caseta.getEmpresa().getBuses().remove(i);
                }
            }

            this.controladorVistaGestionBuses.asignarCaseta(fila, columna, this.caseta);
            this.llenarTabla();
            this.limpiarCampos();
            this.actualizarPlazasDisponibles();
        } catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            if (txtPlaca.getText().trim().isEmpty() || txtCantidadPuestos.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
                return;
            }

            String placa = txtPlaca.getText().trim();
            int cantidadPuestos = Integer.parseInt(txtCantidadPuestos.getText().trim());
            String modelo = txtModelo.getText().trim();
            int año = Integer.parseInt(txtAño.getText().trim());
            String conductor = txtConductor.getText().trim();

            for(int i = 0; i < this.caseta.getEmpresa().getBuses().size(); i++){
                if(this.caseta.getEmpresa().getBuses().get(i).getPlaca().equals(placa)){
                    this.caseta.getEmpresa().getBuses().get(i).setCantidadPuestos(cantidadPuestos);
                    this.caseta.getEmpresa().getBuses().get(i).setModelo(modelo);
                    this.caseta.getEmpresa().getBuses().get(i).setAño(año);
                    this.caseta.getEmpresa().getBuses().get(i).setNombreConductor(conductor);
                }
            }

            this.controladorVistaGestionBuses.actualizarBus(placa, cantidadPuestos, modelo, año, conductor);
            this.controladorVistaGestionBuses.asignarCaseta(fila, columna, this.caseta);
            this.limpiarCampos();
            this.llenarTabla();
            this.actualizarPlazasDisponibles();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad de puestos debe ser un número válido.");
        } catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(VistaGestionBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGestionBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGestionBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGestionBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGestionBuses(null, null, 0, 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPlazasDisponibles;
    private javax.swing.JTable tablaBuses;
    private javax.swing.JTextField txtAño;
    private javax.swing.JTextField txtCantidadPuestos;
    private javax.swing.JTextField txtConductor;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
