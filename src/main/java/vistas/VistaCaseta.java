/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.ControladorVistaCaseta;
import modelos.*;

import javax.swing.*;

/**
 *
 * @author luis
 */
public class VistaCaseta extends javax.swing.JFrame {

    ControladorVistaCaseta controladorVistaCaseta;
    Caseta caseta;
    Usuario usuarioLogeado;
    AdministradorFlota adminFlota;
    int fila;
    int columna;

    /**
     * Creates new form VistaCaseta
     */
    public VistaCaseta(Usuario usuarioLogeado, Caseta caseta, int fila, int columna) {
        initComponents();
        setLocationRelativeTo(this);
        this.controladorVistaCaseta = new ControladorVistaCaseta();
        this.caseta = caseta;
        this.usuarioLogeado = usuarioLogeado;
        this.fila = fila;
        this.columna = columna;
        this.bloquearCampos();
        this.alistarBox();
        this.tieneAdministrador();
    }

    private void bloquearCampos(){
        if(this.controladorVistaCaseta.obtenerCaseta(fila, columna) != null){
            this.llenarCampos();
            txtDocumento.setEnabled(false);
            txtNombre.setEnabled(false);
            txtApellido.setEnabled(false);
            txtEdad.setEnabled(false);
            txtTelefono.setEnabled(false);
            cbxGenero.setEnabled(false);
            txtTelefono.setEnabled(false);
            txtCorreo.setEnabled(false);
            txtContrasena.setEnabled(false);
            txtSueldo.setEnabled(false);

            txtNit.setEnabled(false);
            txtNombreEmpresa.setEnabled(false);
            txtCanonArrendamiento.setEnabled(false);
            txtPlazasEstacionamiento.setEnabled(false);
        }
    }

    private void alistarBox(){
        DefaultComboBoxModel<String> modeloTipoEmpleado = new DefaultComboBoxModel<>();
        modeloTipoEmpleado.addElement("Masculino");
        modeloTipoEmpleado.addElement("Femenino");
        cbxGenero.setModel(modeloTipoEmpleado);
    }

    private void tieneAdministrador(){
        if(this.caseta != null && this.caseta.isDisponibilidad()){
            btnDesocupar.setEnabled(true);
            btnAsignarCaseta.setEnabled(false);
        } else {
            btnDesocupar.setEnabled(false);
            btnAsignarCaseta.setEnabled(true);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtSueldo = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<>();
        panelCrudEmpresas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        btnAsignarCaseta = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        panelCrudCaseta = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCanonArrendamiento = new javax.swing.JTextField();
        txtPlazasEstacionamiento = new javax.swing.JTextField();
        btnDesocupar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel12.setText("jLabel12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Admin Flota"));

        jLabel5.setText("Documento:");

        jLabel6.setText("Nombre:");

        jLabel7.setText("Contraseña:");

        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });

        jLabel8.setText("Apellido:");

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

        jLabel9.setText("Edad");

        jLabel10.setText("Genero:");

        jLabel11.setText("Telefono:");

        jLabel13.setText("Correo:");

        jLabel14.setText("Sueldo:");

        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(39, 39, 39)
                        .addComponent(txtNombre))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(cbxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtTelefono))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        panelCrudEmpresas.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Empresa"));
        panelCrudEmpresas.setToolTipText("");

        jLabel1.setText("Nit:");

        jLabel2.setText("Nombre de la empresa:");

        javax.swing.GroupLayout panelCrudEmpresasLayout = new javax.swing.GroupLayout(panelCrudEmpresas);
        panelCrudEmpresas.setLayout(panelCrudEmpresasLayout);
        panelCrudEmpresasLayout.setHorizontalGroup(
            panelCrudEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudEmpresasLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(panelCrudEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(91, 91, 91)
                .addGroup(panelCrudEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93))
        );
        panelCrudEmpresasLayout.setVerticalGroup(
            panelCrudEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudEmpresasLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelCrudEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCrudEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        btnAsignarCaseta.setText("Asignar caseta");
        btnAsignarCaseta.setToolTipText("");
        btnAsignarCaseta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarCasetaActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        panelCrudCaseta.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Caseta"));

        jLabel3.setText("Canon de arrendamiento:");

        jLabel4.setText("Plazas de estacionamiento:");

        txtPlazasEstacionamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlazasEstacionamientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCrudCasetaLayout = new javax.swing.GroupLayout(panelCrudCaseta);
        panelCrudCaseta.setLayout(panelCrudCasetaLayout);
        panelCrudCasetaLayout.setHorizontalGroup(
            panelCrudCasetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudCasetaLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(panelCrudCasetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(77, 77, 77)
                .addGroup(panelCrudCasetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCanonArrendamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlazasEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCrudCasetaLayout.setVerticalGroup(
            panelCrudCasetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudCasetaLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(panelCrudCasetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCanonArrendamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCrudCasetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPlazasEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        btnDesocupar.setText("Desocupar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCrudCaseta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCrudEmpresas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDesocupar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAsignarCaseta)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCrudEmpresas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCrudCaseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsignarCaseta)
                    .addComponent(btnRegresar)
                    .addComponent(btnDesocupar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void llenarCampos(){
        if(this.controladorVistaCaseta.obtenerCaseta(fila, columna) != null){
            Caseta caseta = this.controladorVistaCaseta.obtenerCaseta(fila, columna);
            AdministradorFlota admin = caseta.getEmpresa().getAdministradorFlota();

            txtDocumento.setText(admin.getDocumento());
            txtNombre.setText(admin.getNombre());
            txtApellido.setText(admin.getApellido());
            txtEdad.setText(String.valueOf(admin.getEdad()));
            cbxGenero.setSelectedItem(admin.getGenero());
            txtTelefono.setText(admin.getTelefono());
            txtCorreo.setText(admin.getCorreo());
            txtContrasena.setText(admin.getContrasena());
            txtSueldo.setText(String.valueOf(admin.getSueldo()));

            txtNit.setText(caseta.getEmpresa().getNit());
            txtNombreEmpresa.setText(caseta.getEmpresa().getNombreEmpresa());
            txtCanonArrendamiento.setText(String.valueOf(caseta.getCanonArrendamiento()));
            txtPlazasEstacionamiento.setText(String.valueOf(caseta.getPlazasEstacionamiento()));
        }
    }

    private void limpiarCampos(){
        txtNombre.setText("");
        txtDocumento.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        cbxGenero.setSelectedItem("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        txtSueldo.setText("");

        txtNit.setText("");
        txtNombreEmpresa.setText("");
        txtCanonArrendamiento.setText("");
        txtPlazasEstacionamiento.setText("");
    }

    private void txtPlazasEstacionamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlazasEstacionamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlazasEstacionamientoActionPerformed

    private void btnAsignarCasetaActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        try{
            if (txtNombre.getText().trim().isEmpty() ||
                    txtDocumento.getText().trim().isEmpty() ||
                    txtApellido.getText().trim().isEmpty() ||
                    txtEdad.getText().trim().isEmpty() ||
                    cbxGenero.getSelectedItem() == null ||
                    txtTelefono.getText().trim().isEmpty() ||
                    txtCorreo.getText().trim().isEmpty() ||
                    txtContrasena.getText().trim().isEmpty() ||
                    txtSueldo.getText().trim().isEmpty() ||
                    txtNit.getText().trim().isEmpty() ||
                    txtNombreEmpresa.getText().trim().isEmpty() ||
                    txtCanonArrendamiento.getText().trim().isEmpty() ||
                    txtPlazasEstacionamiento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                return;
            }

            String documento = txtDocumento.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String genero = (String) cbxGenero.getSelectedItem();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String contrasena = txtContrasena.getText();
            double sueldo = Double.parseDouble(txtSueldo.getText());

            this.adminFlota = new AdministradorFlota(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
            this.controladorVistaCaseta.agregarAdmininistradorFlota(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);

            String nit = txtNit.getText();
            String nombreEmpresa = txtNombreEmpresa.getText();
            Empresa empresa = new Empresa(nit, nombreEmpresa, this.adminFlota);

            double canonArrendamiento = Double.parseDouble(txtCanonArrendamiento.getText());
            int plazasEstablecimiento = Integer.parseInt(txtPlazasEstacionamiento.getText());
            this.caseta = new Caseta(empresa, canonArrendamiento, plazasEstablecimiento);
            this.controladorVistaCaseta.asignarCaseta(this.fila, this.columna, this.caseta);

            if (!this.caseta.isDisponibilidad()) {
                btnDesocupar.setEnabled(false);
                btnAsignarCaseta.setEnabled(true);
            } else {
                btnDesocupar.setEnabled(true);
                btnAsignarCaseta.setEnabled(false);
            }
            this.bloquearCampos();
            JOptionPane.showMessageDialog(null, "Caseta asignada con exito.");
        }catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                                   

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaPrincipal vp = new VistaPrincipal(this.usuarioLogeado);
        vp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

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
            java.util.logging.Logger.getLogger(VistaCaseta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCaseta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCaseta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCaseta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCaseta(null, null,  0, 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarCaseta;
    private javax.swing.JButton btnDesocupar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelCrudCaseta;
    private javax.swing.JPanel panelCrudEmpresas;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCanonArrendamiento;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtPlazasEstacionamiento;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
