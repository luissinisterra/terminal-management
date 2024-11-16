/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.ControladorVistaGestionReservas;
import modelos.*;
import util.Lista;
import util.interfaces.ILista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class VistaGestionReservasActivas extends javax.swing.JFrame {

    ControladorVistaGestionReservas controladorVistaGestionReservas;
    Cliente usuarioLogeado;
    ILista<Reserva> reservasCliente;

    /**
     * Creates new form VistaGestionReservasActivas
     */
    public VistaGestionReservasActivas(Cliente usuarioLogeado) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.controladorVistaGestionReservas = new ControladorVistaGestionReservas();
        this.usuarioLogeado = usuarioLogeado;
        this.llenarTabla();
        this.alistarBox();
    }

    /*public void llenarTabla(){
        ILista<Reserva> reservasGlobales = this.controladorVistaGestionReservas.obtenerReservas();
        ILista<Reserva> reservasCliente = new Lista<>();

        for(int i = 0; i < reservasGlobales.size(); i++){
            if(reservasGlobales.get(i).getCliente().getDocumento().equals(usuarioLogeado.getDocumento())){
                reservasCliente.add(reservasGlobales.get(i));
            }
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id reserva", "Origen", "Destino", "Salida", "Llegada", "Bus", "Valor unitario"});

        // Asegurarse de que la lista no sea null
        if (reservasCliente != null) {
            for (int i = 0; i < reservasCliente.size(); i++) {
                model.addRow(new Object[]{
                        reservasCliente.get(i).getIdReserva(),
                        reservasCliente.get(i).getViaje().getOrigen(),
                        reservasCliente.get(i).getViaje().getDestino(),
                        reservasCliente.get(i).getViaje().getFechaHoraSalida(),
                        reservasCliente.get(i).getViaje().getFechaHoraLlegada(),
                        reservasCliente.get(i).getViaje().getBus().getPlaca(),
                        reservasCliente.get(i).getViaje().getValorUnitario(),
                });
            }
        }
        tablaReservas.setModel(model);
    }*/

    public void llenarTabla(){
        ILista<Reserva> reservasCliente = this.usuarioLogeado.getReservas();

        /*for(int i = 0; i < reservasGlobales.size(); i++){
            if(reservasGlobales.get(i).getCliente().getDocumento().equals(usuarioLogeado.getDocumento())){
                reservasCliente.add(reservasGlobales.get(i));
            }
        }*/

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id reserva", "Origen", "Destino", "Salida", "Llegada", "Bus", "Valor unitario"});

        // Asegurarse de que la lista no sea null
        if (reservasCliente != null) {
            for (int i = 0; i < reservasCliente.size(); i++) {
                model.addRow(new Object[]{
                        reservasCliente.get(i).getIdReserva(),
                        reservasCliente.get(i).getViaje().getOrigen(),
                        reservasCliente.get(i).getViaje().getDestino(),
                        reservasCliente.get(i).getViaje().getFechaHoraSalida(),
                        reservasCliente.get(i).getViaje().getFechaHoraLlegada(),
                        reservasCliente.get(i).getViaje().getBus().getPlaca(),
                        reservasCliente.get(i).getViaje().getValorUnitario(),
                });
            }
        }
        tablaReservas.setModel(model);
    }

    private void alistarBox(){
        ILista<Reserva> reservasGlobales = this.controladorVistaGestionReservas.obtenerReservas();
        this.reservasCliente = new Lista<>();

        for(int i = 0; i < reservasGlobales.size(); i++){
            if(reservasGlobales.get(i).getCliente().getDocumento().equals(usuarioLogeado.getDocumento())){
                this.reservasCliente.add(reservasGlobales.get(i));
            }
        }

        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        for (int i = 0; i < reservasGlobales.size(); i++) {
            model2.addElement(reservasGlobales.get(i).getIdReserva());
        }
        cbxIdReserva.setModel(model2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReservas = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        cbxIdReserva = new javax.swing.JComboBox<>();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaReservas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaReservas);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Id de la reserva:");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cbxIdReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(cbxIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnCancelar)
                    .addComponent(cbxIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try{
            String idReserva = cbxIdReserva.getSelectedItem().toString();

            for(int i = 0; i < this.reservasCliente.size(); i++){
                if(this.reservasCliente.get(i).getIdReserva().equals(idReserva)) {
                    this.reservasCliente.remove(i);
                }
            }

            this.controladorVistaGestionReservas.eliminarReserva(idReserva);
            this.llenarTabla();
        } catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaCliente vc = new VistaCliente(this.usuarioLogeado);
        vc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(VistaGestionReservasActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGestionReservasActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGestionReservasActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGestionReservasActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGestionReservasActivas(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxIdReserva;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReservas;
    // End of variables declaration//GEN-END:variables
}
