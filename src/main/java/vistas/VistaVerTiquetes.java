/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import modelos.Caseta;
import modelos.Tiquete;
import modelos.Viaje;
import util.Lista;
import util.interfaces.ILista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class VistaVerTiquetes extends javax.swing.JFrame {

    Caseta caseta;

    /**
     * Creates new form VistaVerTiquetes
     */
    public VistaVerTiquetes(Caseta caseta) {
        initComponents();
        this.caseta = caseta;
        this.llenarTabla();
        this.setLocationRelativeTo(this);
    }

    private void llenarTabla() {
        // Obtener los viajes de la empresa
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();
        ILista<Tiquete> tiquetes = new Lista<>();

        // Configurar el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id tiquete", "Id viaje", "Id cliente", "Fecha venta", "Precio"});

        // Verificar que la lista de viajes no sea nula ni vacía
        if (viajes != null && !viajes.isEmpty()) {
            // Iterar sobre los viajes y recoger los tiquetes
            for (int i = 0; i < viajes.size(); i++) {
                Viaje viaje = viajes.get(i);
                ILista<Tiquete> tiquetesViaje = viaje.getTiquetes();

                // Verificar que la lista de tiquetes del viaje no sea nula ni vacía
                if (tiquetesViaje != null && !tiquetesViaje.isEmpty()) {
                    for (int j = 0; j < tiquetesViaje.size(); j++) {
                        Tiquete tiquete = tiquetesViaje.get(j);
                        if (tiquete != null) {
                            tiquetes.add(tiquete); // Agregar a la lista consolidada
                        }
                    }
                }
            }
        }

        // Llenar el modelo de la tabla con los datos de los tiquetes
        if (tiquetes != null && !tiquetes.isEmpty()) {
            for (int i = 0; i < tiquetes.size(); i++) {
                Tiquete tiquete = tiquetes.get(i);
                model.addRow(new Object[]{
                        tiquete.getIdTiquete(),
                        tiquete.getViaje().getIdViaje(),
                        tiquete.getCliente().getDocumento(),
                        tiquete.getFechaVenta(),
                        tiquete.getPrecio(),
                });
            }
        }

        // Mostrar el número total de tiquetes
        JOptionPane.showMessageDialog(null, tiquetes.size() + " tiquetes encontrados.");

        // Asignar el modelo a la tabla
        tablaTiquetes.setModel(model);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTablaTiquetes = new java.awt.Panel();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTiquetes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelTablaTiquetes.setBackground(new java.awt.Color(60, 63, 65));

        btnRegresar.setText("Regresar");

        tablaTiquetes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaTiquetes);

        javax.swing.GroupLayout panelTablaTiquetesLayout = new javax.swing.GroupLayout(panelTablaTiquetes);
        panelTablaTiquetes.setLayout(panelTablaTiquetesLayout);
        panelTablaTiquetesLayout.setHorizontalGroup(
            panelTablaTiquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaTiquetesLayout.createSequentialGroup()
                .addGroup(panelTablaTiquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegresar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        panelTablaTiquetesLayout.setVerticalGroup(
            panelTablaTiquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablaTiquetesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
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
                .addComponent(panelTablaTiquetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTablaTiquetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VistaVerTiquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaVerTiquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaVerTiquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaVerTiquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaVerTiquetes(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panelTablaTiquetes;
    private javax.swing.JTable tablaTiquetes;
    // End of variables declaration//GEN-END:variables
}
