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
        setTitle("Gestión de ventas: " + this.caseta.getEmpresa().getNombreEmpresa());
        this.usuarioLogeado = usuarioLogeado;
        this.fila = fila;
        this.columna = columna;
        this.llenarTabla();
        this.llenarTablaReservas();
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
                        viajes.get(i).getBus().getCantidadPuestos() - viajes.get(i).getTiquetes().size() - viajes.get(i).getReservas().size(),
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
        cbxViajeBuscador.setModel(model2);

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

    private void llenarTablaReservas() {
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();
        ILista<Reserva> reservas = new Lista<>();

        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getReservas() != null) {
                for (int j = 0; j < viajes.get(i).getReservas().size(); j++) {
                    reservas.add(viajes.get(i).getReservas().get(j));
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id Reserva", "Cliente", "Id Viaje", "Origen", "Destino", "Fecha Reserva"});

        if (reservas != null) {
            for (int i = 0; i < reservas.size(); i++) {
                model.addRow(new Object[]{
                        reservas.get(i).getIdReserva(),
                        reservas.get(i).getCliente().getNombre(),
                        reservas.get(i).getViaje().getIdViaje(),
                        reservas.get(i).getViaje().getOrigen(),
                        reservas.get(i).getViaje().getDestino(),
                        reservas.get(i).getFechaReserva()
                });
            }
        }

        tablaReservas.setModel(model);
    }


    private void limpiarCampos(){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        lblFechaVenta.setText(fechaHoraActual.format(formatter));
        txtIdTiquete.setText("");
        txtCantidadTiquetes.setText("");
    }

    private void agregarTiquetesGenerados(int cantidadTiquetes, String idTiqueteBase, String idViaje, String idCliente, Viaje viaje, Cliente cliente) {
        int indiceViajeCaseta = this.controladorVistaGestionVentas.obtenerViajeIndiceCaseta(this.caseta, idViaje);
        int puntosRedimidos = (viaje.getValorUnitario() / 10000) * 3;
        int cupos = (this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getBus().getCantidadPuestos() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().size() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getReservas().size());

        if (cupos < cantidadTiquetes) {
            JOptionPane.showMessageDialog(null, "NO hay cupos suficientes para la cantidad de tiquetes a comprar.");
            return;
        }

        for (int i = 0; i < cantidadTiquetes; i++) {
            String idTiquete = idTiqueteBase + "-" + (i + 1);

            Tiquete tiquete = new Tiquete(idTiquete, viaje, cliente);

            this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().add(tiquete);

            this.controladorVistaGestionVentas.agregarTiquete(idTiquete, viaje, cliente);
            this.controladorVistaGestionVentas.transaccionCliente(idCliente, tiquete, "Compra", puntosRedimidos);
        }
        JOptionPane.showMessageDialog(null, "Venta realizada con exito.");
    }

    private void redirmirTiquetes(int cantidadTiquetes, String idTiqueteBase, String idViaje, String idCliente, Viaje viaje, Cliente cliente){
        int puntosRequeridos = (viaje.getValorUnitario() * cantidadTiquetes / 10000) * 3;
        int puntosRedimidos = (viaje.getValorUnitario() / 10000) * 3;
        int indiceViajeCaseta = this.controladorVistaGestionVentas.obtenerViajeIndiceCaseta(this.caseta, idViaje);
        int cupos = (this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getBus().getCantidadPuestos() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().size() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getReservas().size());

        if (cupos < cantidadTiquetes) {
            JOptionPane.showMessageDialog(null, "NO hay cupos suficientes para la cantidad de tiquetes a comprar.");
            return;
        }

        if(viaje.getValorUnitario() <= 30000){
            JOptionPane.showMessageDialog(null, "El valor unitario del vaije sobrepasa el monto minimo de una redencion por puntos.");
            return;
        }

        if(cliente.getPuntos() < puntosRequeridos){
            JOptionPane.showMessageDialog(null, "Puntos insuficientes para redimir un tiquete de este viaje.");
            return;
        }

        for (int i = 0; i < cantidadTiquetes; i++) {
            String idTiquete = idTiqueteBase + "-" + (i + 1);

            Tiquete tiquete = new Tiquete(idTiquete, viaje, cliente);

            this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().add(tiquete);

            this.controladorVistaGestionVentas.agregarTiquete(idTiquete, viaje, cliente);
            this.controladorVistaGestionVentas.transaccionCliente(idCliente, tiquete, "Redencion", puntosRedimidos);
        }

        JOptionPane.showMessageDialog(null, "Redencion realizada con exito.");
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
        btnRedimir = new javax.swing.JButton();
        cbxCliente = new javax.swing.JComboBox<>();
        lblFechaVenta = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdTiquete = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaViajes = new javax.swing.JTable();
        panelCrudGestionVentas1 = new javax.swing.JPanel();
        cbxReserva = new javax.swing.JComboBox<>();
        btnHacerEfectiva = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbxViajeBuscador = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaReservas = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        jTextField3.setText("jTextField3");

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCrudGestionVentas.setBorder(javax.swing.BorderFactory.createTitledBorder("Viajes"));

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

        btnRedimir.setText("Redimir");
        btnRedimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedimirActionPerformed(evt);
            }
        });

        cbxCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblFechaVenta.setText("jLabel5");

        jLabel5.setText("Id del tiquete:");

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

        javax.swing.GroupLayout panelCrudGestionVentasLayout = new javax.swing.GroupLayout(panelCrudGestionVentas);
        panelCrudGestionVentas.setLayout(panelCrudGestionVentasLayout);
        panelCrudGestionVentasLayout.setHorizontalGroup(
            panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                        .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(panelCrudGestionVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxCliente, javax.swing.GroupLayout.Alignment.LEADING, 0, 240, Short.MAX_VALUE)
                            .addComponent(txtCantidadTiquetes, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxViaje, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechaVenta, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdTiquete, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(panelCrudGestionVentasLayout.createSequentialGroup()
                        .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRedimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(139, 139, 139)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                .addContainerGap())
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
                    .addComponent(btnRedimir))
                .addContainerGap(81, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panelCrudGestionVentas1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservas"));

        cbxReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnHacerEfectiva.setText("Hacer efectiva");
        btnHacerEfectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHacerEfectivaActionPerformed(evt);
            }
        });

        jButton6.setText("Rechazar");

        jLabel10.setText("Id de la reserva:");

        jButton1.setText("Filtrar");

        jLabel6.setText("Id viaje:");

        cbxViajeBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jScrollPane2.setViewportView(tablaReservas);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCrudGestionVentas1Layout = new javax.swing.GroupLayout(panelCrudGestionVentas1);
        panelCrudGestionVentas1.setLayout(panelCrudGestionVentas1Layout);
        panelCrudGestionVentas1Layout.setHorizontalGroup(
            panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudGestionVentas1Layout.createSequentialGroup()
                .addGroup(panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCrudGestionVentas1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHacerEfectiva)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(cbxReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(160, 160, 160)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCrudGestionVentas1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cbxViajeBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCrudGestionVentas1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresar)))
                .addContainerGap())
        );
        panelCrudGestionVentas1Layout.setVerticalGroup(
            panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudGestionVentas1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel6)
                    .addComponent(cbxViajeBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrudGestionVentas1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbxReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(panelCrudGestionVentas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHacerEfectiva)
                            .addComponent(jButton6))
                        .addContainerGap(292, Short.MAX_VALUE))
                    .addGroup(panelCrudGestionVentas1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegresar))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCrudGestionVentas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCrudGestionVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCrudGestionVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCrudGestionVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

            this.agregarTiquetesGenerados(cantidadTiquetes, idTiqueteBase, idViaje, idCliente, viaje, cliente);
            this.controladorVistaGestionVentas.asignarCaseta(this.fila, this.columna, this.caseta);
            this.limpiarCampos();
            this.llenarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad de tiquetes debe ser un número válido.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void btnHacerEfectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHacerEfectivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHacerEfectivaActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaAdminFlota vaf = new VistaAdminFlota(this.usuarioLogeado, this.caseta, this.fila, this.columna);
        vaf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRedimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedimirActionPerformed
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

            this.redirmirTiquetes(cantidadTiquetes, idTiqueteBase, idViaje, idCliente, viaje, cliente);
            this.controladorVistaGestionVentas.asignarCaseta(this.fila, this.columna, this.caseta);
            this.limpiarCampos();
            this.llenarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad de tiquetes debe ser un número válido.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRedimirActionPerformed

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
    private javax.swing.JButton btnHacerEfectiva;
    private javax.swing.JButton btnRedimir;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> cbxCliente;
    private javax.swing.JComboBox<String> cbxReserva;
    private javax.swing.JComboBox<String> cbxViaje;
    private javax.swing.JComboBox<String> cbxViajeBuscador;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JPanel panelCrudGestionVentas;
    private javax.swing.JPanel panelCrudGestionVentas1;
    private javax.swing.JTable tablaReservas;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JTextField txtCantidadTiquetes;
    private javax.swing.JTextField txtIdTiquete;
    // End of variables declaration//GEN-END:variables
}
