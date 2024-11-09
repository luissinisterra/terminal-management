/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import modelos.Cliente;

/**
 *
 * @author luis
 */
public class VistaResumenInformacion extends javax.swing.JFrame {

    Cliente cliente;

    /**
     * Creates new form VistaResumenInformacion
     */
    public VistaResumenInformacion(Cliente cliente) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.cliente = cliente;
        this.llenarCampos();
    }

    private void llenarCampos() {
        lblDocumento.setText(cliente.getDocumento());
        lblNombre.setText(cliente.getNombre());
        lblApellido.setText(cliente.getApellido());
        lblEdad.setText(String.valueOf(cliente.getEdad()));
        lblGenero.setText(cliente.getGenero());
        lblTelefono.setText(cliente.getTelefono());
        lblCorreo.setText(cliente.getCorreo());
        lblContrasena.setText(cliente.getContrasena());
        lblSueldo.setText(String.valueOf(cliente.getSueldo()));
        lblPuntos.setText(String.valueOf(cliente.getPuntos()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelResumenInformacion = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        lblSueldo = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblPuntos = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelResumenInformacion.setBackground(new java.awt.Color(60, 63, 65));

        jLabel1.setText("Documento:");

        lblDocumento.setText("jLabel2");

        jLabel3.setText("Nombre:");

        lblNombre.setText("jLabel4");

        jLabel5.setText("Apellido:");

        lblApellido.setText("jLabel6");

        jLabel7.setText("Genero:");

        lblGenero.setText("jLabel8");

        jLabel9.setText("Telefono:");

        lblTelefono.setText("jLabel10");

        jLabel11.setText("Edad:");

        lblEdad.setText("jLabel12");

        jLabel13.setText("Correo:");

        jLabel14.setText("Contraseña:");

        jLabel15.setText("Sueldo:");

        lblCorreo.setText("jLabel16");

        lblContrasena.setText("jLabel17");

        lblSueldo.setText("jLabel18");

        jLabel19.setText("Puntos:");

        lblPuntos.setText("jLabel20");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelResumenInformacionLayout = new javax.swing.GroupLayout(panelResumenInformacion);
        panelResumenInformacion.setLayout(panelResumenInformacionLayout);
        panelResumenInformacionLayout.setHorizontalGroup(
            panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenInformacionLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResumenInformacionLayout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)))))
                    .addGroup(panelResumenInformacionLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(jLabel9))
                .addGap(36, 36, 36)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGenero)
                    .addComponent(lblTelefono)
                    .addGroup(panelResumenInformacionLayout.createSequentialGroup()
                        .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDocumento)
                            .addComponent(lblNombre)
                            .addComponent(lblApellido)
                            .addComponent(lblEdad))
                        .addGap(198, 198, 198)
                        .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19))
                        .addGap(41, 41, 41)
                        .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPuntos)
                            .addComponent(lblSueldo)
                            .addComponent(lblContrasena)
                            .addComponent(lblCorreo))))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResumenInformacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addContainerGap())
        );
        panelResumenInformacionLayout.setVerticalGroup(
            panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenInformacionLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDocumento)
                    .addComponent(jLabel13)
                    .addComponent(lblCorreo))
                .addGap(18, 18, 18)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNombre)
                    .addComponent(jLabel14)
                    .addComponent(lblContrasena))
                .addGap(18, 18, 18)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblApellido)
                    .addComponent(jLabel15)
                    .addComponent(lblSueldo))
                .addGap(18, 18, 18)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblEdad)
                    .addComponent(jLabel19)
                    .addComponent(lblPuntos))
                .addGap(20, 20, 20)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblGenero))
                .addGap(18, 18, 18)
                .addGroup(panelResumenInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelResumenInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelResumenInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(VistaResumenInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaResumenInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaResumenInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaResumenInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaResumenInformacion(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPuntos;
    private javax.swing.JLabel lblSueldo;
    private javax.swing.JLabel lblTelefono;
    private java.awt.Panel panelResumenInformacion;
    // End of variables declaration//GEN-END:variables
}
