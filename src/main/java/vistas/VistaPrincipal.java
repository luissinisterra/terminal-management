/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import javax.swing.*;

import controladores.ControladorVistaPrincipal;
import modelos.Caseta;
import modelos.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luis
 */
public class VistaPrincipal extends javax.swing.JFrame implements ActionListener {

    JButton[][] botonesCasetas;
    ControladorVistaPrincipal controladorVistaPrincipal;
    Usuario usuarioLogeado;

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal(Usuario usuarioLogeado) {
        initComponents();
        setLocationRelativeTo(this);
        this.botonesCasetas = new JButton[4][];
        setTitle("Vista Principal - Casetas");
        this.controladorVistaPrincipal = new ControladorVistaPrincipal();
        this.usuarioLogeado = usuarioLogeado;
        this.dibujarBotones();
        this.revisarSiEstaOcupada();
    }

    private void dibujarBotones() {
        int separado = 20;
        int ancho = 75;
        int alto = 75;

        this.botonesCasetas[0] = new JButton[5];

        for (int i = 1; i < this.botonesCasetas.length; i++) {
            this.botonesCasetas[i] = new JButton[2];
        }

        for (int i = 0; i < this.botonesCasetas.length; i++) {
            for (int j = 0; j < this.botonesCasetas[i].length; j++) {
                botonesCasetas[i][j] = new JButton();

                if(i == 1 && j >= 1 && j <= 3){
                    botonesCasetas[i][j].setBounds(ancho * j + (separado + (3 * ancho * j)), alto * i + separado, ancho, alto);
                } else if(i == 3) {
                    int columna = botonesCasetas[i].length - 2 + j;
                    botonesCasetas[i][j].setBounds(ancho * (columna + 3) + separado, alto * i + separado, ancho, alto);
                } else {
                    botonesCasetas[i][j].setBounds(ancho * j + separado, alto * i + separado, ancho, alto);
                }

                botonesCasetas[i][j].addActionListener(this);
                panelBotones.add(botonesCasetas[i][j]);
            }
        }

    }

    private void revisarSiEstaOcupada(){
        for (int i = 0; i < botonesCasetas.length; i++) {
            for (int j = 0; j < botonesCasetas[i].length; j++) {
                Caseta caseta = this.controladorVistaPrincipal.obtenerCaseta(i, j);

                if(caseta == null){
                    continue;
                }

                if(caseta.isDisponibilidad()){
                    JButton button = botonesCasetas[i][j];
                    button.setBackground(Color.ORANGE);
                } else {
                    JButton button = botonesCasetas[i][j];
                    button.setBackground(Color.WHITE);
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < botonesCasetas.length; i++) {
            for (int j = 0; j < botonesCasetas[i].length; j++) {
                if (e.getSource().equals(botonesCasetas[i][j])) {
                    int fila = i;
                    int columna = j;
                    Caseta caseta = this.controladorVistaPrincipal.obtenerCaseta(fila, columna);
                    VistaCaseta vc = new VistaCaseta(this.usuarioLogeado, caseta, fila, columna);
                    vc.setVisible(true);
                    this.dispose();
                }
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

        panelBotones = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBotones.setBorder(javax.swing.BorderFactory.createTitledBorder("Casetas"));

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarSesion)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        VistaLogin vl = new VistaLogin();
        vl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
