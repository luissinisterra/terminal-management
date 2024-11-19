/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.ControladorVistaReservarTiquete;
import modelos.*;
import util.Lista;
import util.interfaces.ILista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class VistaReservaTiquete extends javax.swing.JFrame {

    ControladorVistaReservarTiquete controladorVistaReservaTiquete;
    Caseta caseta;
    Cliente usuarioLogeado;
    int fila;
    int columna;

    /**
     * Creates new form VistaReservaTiquete
     */
    public VistaReservaTiquete(Cliente usuarioLogeado) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.controladorVistaReservaTiquete = new ControladorVistaReservarTiquete();
        this.usuarioLogeado = usuarioLogeado;
        this.llenarTabla();
        this.alistarBox();
    }

    public void llenarTabla(){

        ILista<Viaje> viajesGlobales = this.controladorVistaReservaTiquete.obtenerViajes();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id viaje", "Origen", "Destino", "Salida", "Llegada", "Bus", "Cupos", "Valor unitario"});

        // Asegurarse de que la lista no sea null
        if (viajesGlobales != null) {
            for (int i = 0; i < viajesGlobales.size(); i++) {
                model.addRow(new Object[]{
                        viajesGlobales.get(i).getIdViaje(),
                        viajesGlobales.get(i).getOrigen(),
                        viajesGlobales.get(i).getDestino(),
                        viajesGlobales.get(i).getFechaHoraSalida(),
                        viajesGlobales.get(i).getFechaHoraLlegada(),
                        viajesGlobales.get(i).getBus().getPlaca(),
                        viajesGlobales.get(i).getBus().getCantidadPuestos() - viajesGlobales.get(i).getTiquetes().size() - viajesGlobales.get(i).getReservas().size(),
                        viajesGlobales.get(i).getValorUnitario()
                });
            }
        }
        tablaViajes.setModel(model);

    }

    private void alistarBox(){
        ILista<Viaje> viajesGlobales = this.controladorVistaReservaTiquete.obtenerViajes();

        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        for (int i = 0; i < viajesGlobales.size(); i++) {
            model2.addElement(viajesGlobales.get(i).getIdViaje());
        }
        cbxViaje.setModel(model2);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Dia");
        model.addElement("Semana");
        model.addElement("Mes");
        cbxFiltro.setModel(model);
    }

    private void limpiarCampos(){
        txtIdReserva.setText("");
        txtCantidadReservas.setText("");
    }


    private void agregarReservasGeneradas(int cantidadReservas, String idReservaBase, String idViaje, Viaje viaje) {
        int indiceViajeCaseta = this.controladorVistaReservaTiquete.obtenerViajeIndiceCaseta(this.caseta, idViaje);
        int cupos = (this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getBus().getCantidadPuestos() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().size() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getReservas().size());

        if (cupos < cantidadReservas) {
            JOptionPane.showMessageDialog(null, "No hay asientos disponibles en este viaje.");
            return;
        }

        for (int i = 0; i < cantidadReservas; i++) {
            String idReserva = idReservaBase + "-" + (i + 1);

            Reserva reserva = new Reserva(idReserva, viaje, this.usuarioLogeado);
            this.usuarioLogeado.getReservas().add(reserva);
            this.controladorVistaReservaTiquete.agregarReservaCliente(idReserva, this.usuarioLogeado.getDocumento(), viaje);
            this.controladorVistaReservaTiquete.agregarReserva(idReserva, viaje, this.usuarioLogeado);
            this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getReservas().add(reserva);
        }

        this.controladorVistaReservaTiquete.asignarViajeBinario(idViaje, this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta));
    }

    private void llenarTablaConFiltroDestino(String destinoBusqueda) {
        ILista<Viaje> viajes = this.controladorVistaReservaTiquete.filtrarViajePorDestino(destinoBusqueda);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id viaje", "Origen", "Destino", "Salida", "Llegada", "Bus", "Cupos", "Valor unitario"});

        if (viajes != null) {
            for (int i = 0; i < viajes.size(); i++) {
                Viaje viaje = viajes.get(i);

                model.addRow(new Object[]{
                        viaje.getIdViaje(),
                        viaje.getOrigen(),
                        viaje.getDestino(),
                        viaje.getFechaHoraSalida(),
                        viaje.getFechaHoraLlegada(),
                        viaje.getBus().getPlaca(),
                        viaje.getBus().getCantidadPuestos() - viaje.getTiquetes().size() - viaje.getReservas().size(),
                        viaje.getValorUnitario()
                });
            }
        }
        tablaViajes.setModel(model);
    }

    private void llenarTablaConFiltroFecha(String filtro) {
        ILista<Viaje> viajesGlobales = this.controladorVistaReservaTiquete.filtrarViajesPorFecha(filtro);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id viaje", "Origen", "Destino", "Salida", "Llegada", "Bus", "Cupos", "Valor unitario"});

        if (viajesGlobales != null) {
            for (int i = 0; i < viajesGlobales.size(); i++) {
                Viaje viaje = viajesGlobales.get(i);

                model.addRow(new Object[]{
                        viaje.getIdViaje(),
                        viaje.getOrigen(),
                        viaje.getDestino(),
                        viaje.getFechaHoraSalida(),
                        viaje.getFechaHoraLlegada(),
                        viaje.getBus().getPlaca(),
                        viaje.getBus().getCantidadPuestos() - viaje.getTiquetes().size() - viaje.getReservas().size(),
                        viaje.getValorUnitario()
                });
            }
        }
        tablaViajes.setModel(model);
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
        tablaViajes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnReservar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdReserva = new javax.swing.JTextField();
        btnBuscarDestino = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCantidadReservas = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        cbxViaje = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxFiltro = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestión de reservas"));

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

        jLabel1.setText("Buscar destino:");

        btnReservar.setText("Reservar tiquete");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        jLabel2.setText("Id del viaje:");

        jLabel3.setText("Id de la reserva:");

        btnBuscarDestino.setText("Buscar");
        btnBuscarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDestinoActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad de reservas:");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cbxViaje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscadorActionPerformed(evt);
            }
        });

        jLabel5.setText("Viajes Globales:");

        jLabel6.setText("Filtrar fecha: ");

        cbxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFiltroActionPerformed(evt);
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCantidadReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnReservar))
                                    .addComponent(cbxViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(cbxFiltro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarDestino)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReservar))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFiltrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidadReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
        try {
            String idReservaBase = txtIdReserva.getText();
            String idViaje = cbxViaje.getSelectedItem().toString();
            int cantidadReservas = Integer.parseInt(txtCantidadReservas.getText());

            Viaje viaje = this.controladorVistaReservaTiquete.obtenerViajePorId(idViaje);

            for (int i = 0; i < this.controladorVistaReservaTiquete.obtenerCasetas().length; i++) {
                for (int j = 0; j < this.controladorVistaReservaTiquete.obtenerCasetas()[i].length; j++) {
                    Caseta caseta = this.controladorVistaReservaTiquete.obtenerCasetas()[i][j];

                    if(caseta == null){
                        continue;
                    }

                    ILista<Viaje> viajes = caseta.getEmpresa().getViajes();
                    if (viajes != null) {
                        for(int k = 0; k < viajes.size(); k++) {
                            if (viajes.get(k).getIdViaje().equals(idViaje)) {
                                this.caseta = caseta;
                                this.fila = i;
                                this.columna = j;
                                this.agregarReservasGeneradas(cantidadReservas, idReservaBase, idViaje, viaje);

                                String mensaje = "Haz reservado " + cantidadReservas + " de tiquetes con id base: " + idReservaBase;
                                Notificacion notificacion = new Notificacion(mensaje, this.usuarioLogeado);

                                this.usuarioLogeado.getNotificaciones().add(notificacion);
                                this.controladorVistaReservaTiquete.enviarNotificacion(this.usuarioLogeado.getDocumento(), mensaje);
                                this.controladorVistaReservaTiquete.asignarCaseta(this.fila, this.columna, this.caseta);
                                break;
                            }
                        }
                    }
                }
            }
            this.limpiarCampos();
            this.llenarTabla();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnReservarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaCliente vc = new VistaCliente(this.usuarioLogeado);
        vc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDestinoActionPerformed
        try{
            String destinoBusqueda = txtBuscador.getText();
            this.llenarTablaConFiltroDestino(destinoBusqueda);
        } catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            this.llenarTabla();
        }
    }//GEN-LAST:event_btnBuscarDestinoActionPerformed

    private void cbxFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        try{
            String filtro = cbxFiltro.getSelectedItem().toString();
            this.llenarTablaConFiltroFecha(filtro);
        } catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscadorActionPerformed

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
            java.util.logging.Logger.getLogger(VistaReservaTiquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaReservaTiquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaReservaTiquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaReservaTiquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaReservaTiquete(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarDestino;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReservar;
    private javax.swing.JComboBox<String> cbxFiltro;
    private javax.swing.JComboBox<String> cbxViaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCantidadReservas;
    private javax.swing.JTextField txtIdReserva;
    // End of variables declaration//GEN-END:variables
}
