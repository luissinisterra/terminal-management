/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import modelos.*;
import util.interfaces.ILista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class VistaHistorialCliente extends javax.swing.JFrame {

    Cliente usuarioLogeado;

    /**
     * Creates new form VistaHistorialCliente
     */
    public VistaHistorialCliente(Cliente usuarioLogeado) {
        initComponents();
        setLocationRelativeTo(this);
        this.usuarioLogeado = usuarioLogeado;
        setTitle("Historial: " + this.usuarioLogeado.getNombre() + " " + this.usuarioLogeado.getApellido());
        lblPuntosAcomulados.setText(String.valueOf(this.usuarioLogeado.getPuntos()));
        this.llenarTablaTransacciones();
        this.llenarTablaNotificaciones();
        this.alistarBox();
    }

    private void llenarTablaTransacciones() {
        ILista<Transaccion> transacciones = this.usuarioLogeado.getTransacciones();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
                "Id Tiquete", "Cliente", "Id Viaje", "Origen", "Destino",
                "Fecha Venta", "Acción", "Puntos", "Precio"
        });

        if (transacciones != null && transacciones.size() > 0) {
            for (int i = 0; i < transacciones.size(); i++) {
                Transaccion transaccion = transacciones.get(i);
                Tiquete tiquete = transaccion.getTiquete();
                Viaje viaje = tiquete.getViaje();
                Cliente cliente = tiquete.getCliente();

                model.addRow(new Object[]{
                        tiquete.getIdTiquete(),
                        cliente.getNombre(),
                        viaje.getIdViaje(),
                        viaje.getOrigen(),
                        viaje.getDestino(),
                        tiquete.getFechaVenta().toString(),
                        transaccion.getAccion(),
                        transaccion.getPuntos(),
                        tiquete.getPrecio()
                });
            }
        }

        tablaTransacciones.setModel(model);
    }

    private void llenarTablaNotificaciones() {
        ILista<Notificacion> notificaciones = this.usuarioLogeado.getNotificaciones();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
                "Mensaje", "Cliente", "Fecha de Creación"
        });

        if (notificaciones != null && notificaciones.size() > 0) {
            for (int i = 0; i < notificaciones.size(); i++) {
                Notificacion notificacion = notificaciones.get(i);

                model.addRow(new Object[]{
                        notificacion.getMensaje(),
                        notificacion.getCliente().getNombre(),
                        notificacion.getFechaCreacion().toString()
                });
            }
        }

        tablaNotificaciones.setModel(model);

        if (tablaNotificaciones.getColumnModel().getColumnCount() > 0) {
            tablaNotificaciones.getColumnModel().getColumn(0).setPreferredWidth(300);
            tablaNotificaciones.getColumnModel().getColumn(1).setPreferredWidth(100); 
            tablaNotificaciones.getColumnModel().getColumn(2).setPreferredWidth(150);
        }
    }


    private void llenarTablaTransaccionesPorTipo(String tipoAccion) {
        ILista<Transaccion> transacciones = this.usuarioLogeado.getTransacciones();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
                "Id Tiquete", "Cliente", "Id Viaje", "Origen", "Destino",
                "Fecha Venta", "Acción", "Puntos", "Precio"
        });

        if (transacciones != null && transacciones.size() > 0) {
            for (int i = 0; i < transacciones.size(); i++) {
                Transaccion transaccion = transacciones.get(i);

                if (transaccion.getAccion().equalsIgnoreCase(tipoAccion)) {
                    Tiquete tiquete = transaccion.getTiquete();
                    Viaje viaje = tiquete.getViaje();
                    Cliente cliente = tiquete.getCliente();

                    model.addRow(new Object[]{
                            tiquete.getIdTiquete(),
                            cliente.getNombre(),
                            viaje.getIdViaje(),
                            viaje.getOrigen(),
                            viaje.getDestino(),
                            tiquete.getFechaVenta().toString(),
                            transaccion.getAccion(),
                            transaccion.getPuntos(),
                            tiquete.getPrecio()
                    });
                }
            }
        }

        tablaTransacciones.setModel(model);
    }

    private void alistarBox(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Compra");
        model.addElement("Redencion");
        model.addElement("Devolucion");
        model.addElement("Devolucion por puntos");
        cbxFiltroTabla.setModel(model);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTransacciones = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaNotificaciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        cbxFiltroTabla = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        lblPuntosAcomulados = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Historial"));

        tablaTransacciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaTransacciones);

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaNotificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(tablaNotificaciones);

        jLabel1.setText("Notificaciones:");

        jLabel2.setText("Tipo:");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        cbxFiltroTabla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel3.setText("Puntos acomulados:");

        lblPuntosAcomulados.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblPuntosAcomulados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cbxFiltroTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnFiltrar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(btnFiltrar)
                    .addComponent(cbxFiltroTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lblPuntosAcomulados))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VistaCliente vc = new VistaCliente(this.usuarioLogeado);
        vc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String accionTipo = cbxFiltroTabla.getSelectedItem().toString();
        this.llenarTablaTransaccionesPorTipo(accionTipo);
    }//GEN-LAST:event_btnFiltrarActionPerformed

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
            java.util.logging.Logger.getLogger(VistaHistorialCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaHistorialCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaHistorialCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaHistorialCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaHistorialCliente(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JComboBox<String> cbxFiltroTabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblPuntosAcomulados;
    private javax.swing.JTable tablaNotificaciones;
    private javax.swing.JTable tablaTransacciones;
    // End of variables declaration//GEN-END:variables
}
