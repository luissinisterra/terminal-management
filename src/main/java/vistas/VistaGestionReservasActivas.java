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
import java.util.Random;

/**
 *
 * @author luis
 */
public class VistaGestionReservasActivas extends javax.swing.JFrame {

    ControladorVistaGestionReservas controladorVistaGestionReservas;
    Cliente usuarioLogeado;
    Caseta caseta;
    int fila;
    int columna;

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

    public void llenarTabla(){
        ILista<Reserva> reservasCliente = this.usuarioLogeado.getReservas();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id reserva", "Origen", "Destino", "Salida", "Llegada", "Bus", "Valor unitario"});

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
        ILista<Reserva> reservasCliente = this.usuarioLogeado.getReservas();

        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        for (int i = 0; i < reservasCliente.size(); i++) {
            model2.addElement(reservasCliente.get(i).getIdReserva());
        }
        cbxIdReserva.setModel(model2);
    }


    public void revisarCola(String idViaje){
        int indiceViajeCaseta = this.controladorVistaGestionReservas.obtenerViajeIndiceCaseta(this.caseta, idViaje);
        int cupos = (this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getBus().getCantidadPuestos() -
                this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().size() -
                this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getReservas().size());

        System.out.println(idViaje);

        if (cupos > 0) {
            Cliente cliente = this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getColaEspera().dequeve();
            Random random = new Random();
            String idReservaBase;
            String idReserva;

            boolean existeIdReserva;

            int contador = 1;
            do {
                idReservaBase = String.valueOf(random.nextInt(100));
                idReserva = idReservaBase + "-" + (contador + 1);
                existeIdReserva = this.controladorVistaGestionReservas.buscarReservaPorId(idReserva);
                contador++;
            } while (existeIdReserva);

            Reserva reserva = new Reserva(idReserva, this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta), cliente);
            this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getReservas().add(reserva);

            this.controladorVistaGestionReservas.enviarNotificacion(cliente.getDocumento(),
                    "Tienes un nuevo tiquete reservado con id: " + idReserva);

            this.controladorVistaGestionReservas.agregarReservaCliente(idReserva,
                    cliente.getDocumento(), this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta));
        }
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbxIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(btnCancelar)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try{
            ILista<Reserva> reservasCliente = this.usuarioLogeado.getReservas();
            String idReserva = cbxIdReserva.getSelectedItem().toString();

            Reserva reserva = null;
            for(int i = 0; i < reservasCliente.size(); i++){
                if(reservasCliente.get(i).getIdReserva().equals(idReserva)) {
                    reserva = reservasCliente.get(i);
                    reservasCliente.remove(i);
                }
            }

            this.controladorVistaGestionReservas.eliminarReserva(idReserva);

            Viaje viaje = this.controladorVistaGestionReservas.obtenerViajePorId(reserva.getViaje().getIdViaje());

            for (int i = 0; i < this.controladorVistaGestionReservas.obtenerCasetas().length; i++) {
                for (int j = 0; j < this.controladorVistaGestionReservas.obtenerCasetas()[i].length; j++) {
                    Caseta caseta = this.controladorVistaGestionReservas.obtenerCasetas()[i][j];

                    if(caseta == null){
                        continue;
                    }

                    ILista<Viaje> viajes = caseta.getEmpresa().getViajes();
                    if (viajes != null) {
                        for(int k = 0; k < viajes.size(); k++) {
                            if (viajes.get(k).getIdViaje().equals(viaje.getIdViaje())) {
                                this.caseta = caseta;
                                this.fila = i;
                                this.columna = j;

                                for (int n = 0; n < viajes.size(); n++) {
                                    if(viajes.get(k).getReservas().get(n).equals(idReserva)) {
                                        viajes.remove(i);
                                    }
                                }

                                this.revisarCola(viajes.get(i).getIdViaje());
                                this.controladorVistaGestionReservas.asignarCaseta(this.fila, this.columna, this.caseta);
                                break;
                            }
                        }
                    }
                }
            }
            this.llenarTabla();

            this.llenarTabla();
        } catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
