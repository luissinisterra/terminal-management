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
import java.time.LocalDate;
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
        btnHacerDevolucion.setEnabled(false);
    }

    private void llenarTabla(){
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id viaje", "Origen", "Destino", "Salida", "Llegada", "Bus", "Cupos", "Valor unitario"});

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

        DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>();
        model3.addElement("Reservas");
        model3.addElement("Tiquetes");
        cbxFiltroTabla.setModel(model3);


        DefaultComboBoxModel<String> model4 = new DefaultComboBoxModel<>();
        for (int i = 0; i < this.caseta.getEmpresa().getViajes().size(); i++) {
            ILista<Reserva> reservas = this.caseta.getEmpresa().getViajes().get(i).getReservas();
            for (int j = 0; j < reservas.size(); j++) {
                model4.addElement(reservas.get(j).getIdReserva());
            }
        }
        cbxReserva.setModel(model4);

        DefaultComboBoxModel<String> model5 = new DefaultComboBoxModel<>();
        for (int i = 0; i < this.caseta.getEmpresa().getViajes().size(); i++) {
            ILista<Tiquete> tiquetes = this.caseta.getEmpresa().getViajes().get(i).getTiquetes();
            for (int j = 0; j < tiquetes.size(); j++) {
                model5.addElement(tiquetes.get(j).getIdTiquete());
            }
        }
        cbxTiquete.setModel(model5);

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

        tablaFiltro.setModel(model);
    }

    private void llenarTablaTiquetes() {
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();
        ILista<Tiquete> tiquetes = new Lista<>();

        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getTiquetes() != null) {
                for (int j = 0; j < viajes.get(i).getTiquetes().size(); j++) {
                    tiquetes.add(viajes.get(i).getTiquetes().get(j));
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id Tiquete", "Cliente", "Id Viaje", "Origen", "Destino", "Fecha Venta", "Precio"});

        if (tiquetes != null) {
            for (int i = 0; i < tiquetes.size(); i++) {
                model.addRow(new Object[]{
                        tiquetes.get(i).getIdTiquete(),
                        tiquetes.get(i).getCliente().getNombre(),
                        tiquetes.get(i).getViaje().getIdViaje(),
                        tiquetes.get(i).getViaje().getOrigen(),
                        tiquetes.get(i).getViaje().getDestino(),
                        tiquetes.get(i).getFechaVenta(),
                        tiquetes.get(i).getPrecio()
                });
            }
        }

        tablaFiltro.setModel(model);
    }

    private void llenarTablaReservasPorViaje(String idViaje) {
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();
        ILista<Reserva> reservasFiltradas = new Lista<>();

        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getIdViaje().equals(idViaje) && viajes.get(i).getReservas() != null) {
                for (int j = 0; j < viajes.get(i).getReservas().size(); j++) {
                    reservasFiltradas.add(viajes.get(i).getReservas().get(j));
                }
                break;
            }
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id Reserva", "Cliente", "Id Viaje", "Origen", "Destino", "Fecha Reserva"});

        for (int i = 0; i < reservasFiltradas.size(); i++) {
            model.addRow(new Object[]{
                    reservasFiltradas.get(i).getIdReserva(),
                    reservasFiltradas.get(i).getCliente().getNombre(),
                    reservasFiltradas.get(i).getViaje().getIdViaje(),
                    reservasFiltradas.get(i).getViaje().getOrigen(),
                    reservasFiltradas.get(i).getViaje().getDestino(),
                    reservasFiltradas.get(i).getFechaReserva()
            });
        }

        tablaFiltro.setModel(model);
    }

    private void llenarTablaTiquetesPorViaje(String idViaje) {
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();
        ILista<Tiquete> tiquetesFiltrados = new Lista<>();

        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getIdViaje().equals(idViaje) && viajes.get(i).getTiquetes() != null) {
                for (int j = 0; j < viajes.get(i).getTiquetes().size(); j++) {
                    tiquetesFiltrados.add(viajes.get(i).getTiquetes().get(j));
                }
                break;
            }
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id Tiquete", "Cliente", "Id Viaje", "Origen", "Destino", "Fecha Venta", "Precio"});

        for (int i = 0; i < tiquetesFiltrados.size(); i++) {
            model.addRow(new Object[]{
                    tiquetesFiltrados.get(i).getIdTiquete(),
                    tiquetesFiltrados.get(i).getCliente().getNombre(),
                    tiquetesFiltrados.get(i).getViaje().getIdViaje(),
                    tiquetesFiltrados.get(i).getViaje().getOrigen(),
                    tiquetesFiltrados.get(i).getViaje().getDestino(),
                    tiquetesFiltrados.get(i).getFechaVenta(),
                    tiquetesFiltrados.get(i).getPrecio()
            });
        }

        tablaFiltro.setModel(model);
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
            System.out.println(this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().size() + "tiquetes");

            this.controladorVistaGestionVentas.agregarTiquete(idTiquete, viaje, cliente);
            this.controladorVistaGestionVentas.transaccionCliente(idCliente, tiquete, "Compra", puntosRedimidos);
        }
        JOptionPane.showMessageDialog(null, "Venta realizada con exito.");
    }

    private void redirmirTiquetes(int cantidadTiquetes, String idTiqueteBase, String idViaje, String idCliente, Viaje viaje, Cliente cliente){
        int indiceViajeCaseta = this.controladorVistaGestionVentas.obtenerViajeIndiceCaseta(this.caseta, idViaje);
        int cupos = (this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getBus().getCantidadPuestos() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().size() - this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getReservas().size());

        if (cupos < cantidadTiquetes) {
            JOptionPane.showMessageDialog(null, "NO hay cupos suficientes para la cantidad de tiquetes a comprar.");
            return;
        }

        if(viaje.getValorUnitario() > 30000){
            JOptionPane.showMessageDialog(null, "El valor unitario del vaije sobrepasa el monto minimo de una redencion por puntos.");
            return;
        }

        int puntosRequeridosPorTiquete = 90;
        int puntosRequeridosTotales = puntosRequeridosPorTiquete * cantidadTiquetes;

        if (cliente.getPuntos() < puntosRequeridosTotales) {
            JOptionPane.showMessageDialog(null, "Puntos insuficientes. El cliente necesita al menos " + puntosRequeridosTotales + " puntos para redimir estos tiquetes.");
            return;
        }

        for (int i = 0; i < cantidadTiquetes; i++) {
            String idTiquete = idTiqueteBase + "-" + (i + 1);

            Tiquete tiquete = new Tiquete(idTiquete, viaje, cliente);

            this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().add(tiquete);

            this.controladorVistaGestionVentas.agregarTiquete(idTiquete, viaje, cliente);
            this.controladorVistaGestionVentas.transaccionCliente(idCliente, tiquete, "Redencion", puntosRequeridosPorTiquete);
        }

        JOptionPane.showMessageDialog(null, "Redencion realizada con exito.");
    }

    private Tiquete obtenerTiquete(String idTiquete) {
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();

        for (int i = 0; i < viajes.size(); i++) {
            ILista<Tiquete> tiquetes = viajes.get(i).getTiquetes();

            if (tiquetes != null) {
                for (int j = 0; j < tiquetes.size(); j++) {
                    if (tiquetes.get(j).getIdTiquete().equals(idTiquete)) {
                        return tiquetes.get(j);
                    }
                }
            }
        }
        return null;
    }

    private void eliminarTiquete(String idTiquete) {
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();

        for (int i = 0; i < viajes.size(); i++) {
            ILista<Tiquete> tiquetes = viajes.get(i).getTiquetes();

            if (tiquetes != null) {
                for (int j = 0; j < tiquetes.size(); j++) {
                    if (tiquetes.get(j).getIdTiquete().equals(idTiquete)) {
                        tiquetes.remove(j);
                    }
                }
            }
        }
    }

    private Reserva obtenerReserva(String idReserva){
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();

        for (int i = 0; i < viajes.size(); i++) {
            ILista<Reserva> reservas = viajes.get(i).getReservas();

            if (reservas != null) {
                for (int j = 0; j < reservas.size(); j++) {
                    if (reservas.get(j).getIdReserva().equals(idReserva)) {
                        return reservas.get(j);
                    }
                }
            }
        }
        return null;
    }

    private void eliminarReserva(String idReserva) {
        ILista<Viaje> viajes = this.caseta.getEmpresa().getViajes();

        for (int i = 0; i < viajes.size(); i++) {
            ILista<Reserva> reservas = viajes.get(i).getReservas();

            if (reservas != null) {
                for (int j = 0; j < reservas.size(); j++) {
                    if (reservas.get(j).getIdReserva().equals(idReserva)) {
                        reservas.remove(j);
                    }
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

        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel14 = new javax.swing.JLabel();
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
        panelCrudGestionVentas4 = new javax.swing.JPanel();
        cbxReserva = new javax.swing.JComboBox<>();
        btnHacerEfectiva = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbxViajeBuscador = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaFiltro = new javax.swing.JTable();
        btnRegresar3 = new javax.swing.JButton();
        cbxFiltroTabla = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxTiquete = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnHacerDevolucion = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();

        jTextField3.setText("jTextField3");

        jButton4.setText("jButton4");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel14.setText("jLabel14");

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

        panelCrudGestionVentas4.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservas"));

        cbxReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnHacerEfectiva.setText("Hacer efectiva");
        btnHacerEfectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHacerEfectivaActionPerformed(evt);
            }
        });

        jLabel13.setText("Id de la reserva:");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jLabel9.setText("Id viaje:");

        cbxViajeBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaFiltro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tablaFiltro);

        btnRegresar3.setText("Regresar");
        btnRegresar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cbxFiltroTabla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Mostrar:");

        cbxTiquete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Id del tiquete:");

        btnHacerDevolucion.setText("Hacer devolucion");
        btnHacerDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHacerDevolucionActionPerformed(evt);
            }
        });

        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCrudGestionVentas4Layout = new javax.swing.GroupLayout(panelCrudGestionVentas4);
        panelCrudGestionVentas4.setLayout(panelCrudGestionVentas4Layout);
        panelCrudGestionVentas4Layout.setHorizontalGroup(
            panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudGestionVentas4Layout.createSequentialGroup()
                .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCrudGestionVentas4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresar3))
                    .addGroup(panelCrudGestionVentas4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHacerEfectiva)
                            .addComponent(jLabel13)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxReserva, 0, 240, Short.MAX_VALUE)
                            .addComponent(btnHacerDevolucion)
                            .addComponent(cbxTiquete, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(160, 160, 160)
                        .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCrudGestionVentas4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cbxFiltroTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMostrar)
                                .addGap(177, 177, 177)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cbxViajeBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFiltrar))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelCrudGestionVentas4Layout.setVerticalGroup(
            panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudGestionVentas4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbxViajeBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar)
                    .addComponent(cbxFiltroTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrudGestionVentas4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbxReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTiquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(38, 38, 38)
                        .addGroup(panelCrudGestionVentas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHacerEfectiva)
                            .addComponent(btnHacerDevolucion))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelCrudGestionVentas4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegresar3)
                        .addGap(0, 6, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCrudGestionVentas4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCrudGestionVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCrudGestionVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCrudGestionVentas4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            this.alistarBox();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad de tiquetes debe ser un número válido.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVenderActionPerformed

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
            this.alistarBox();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad de tiquetes debe ser un número válido.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRedimirActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaAdminFlota vaf = new VistaAdminFlota(this.usuarioLogeado, this.caseta, this.fila, this.columna);
        vaf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnHacerEfectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHacerEfectivaActionPerformed
        String idReserva = cbxReserva.getSelectedItem().toString().trim();
        Reserva reserva = this.obtenerReserva(idReserva);

        Tiquete tiquete = new Tiquete(reserva.getIdReserva(), reserva.getViaje(), reserva.getCliente());

        int indiceViajeCaseta = this.controladorVistaGestionVentas.obtenerViajeIndiceCaseta(this.caseta, reserva.getViaje().getIdViaje());
        this.caseta.getEmpresa().getViajes().get(indiceViajeCaseta).getTiquetes().add(tiquete);

        this.eliminarReserva(idReserva);

        int puntos = (reserva.getViaje().getValorUnitario() / 10000) * 3;
        this.controladorVistaGestionVentas.transaccionCliente(reserva.getCliente().getDocumento(), tiquete, "Compra", puntos);


        this.controladorVistaGestionVentas.enviarNotificacion(reserva.getCliente().getDocumento(), idReserva);
        this.controladorVistaGestionVentas.eliminarReservaCliente(idReserva, reserva.getCliente().getDocumento());
        this.controladorVistaGestionVentas.asignarCaseta(this.fila, this.columna, this.caseta);
        this.llenarTablaTiquetes();
        this.alistarBox();

        JOptionPane.showMessageDialog(null, "El nuevo tiquete ha sido registrado.");
    }//GEN-LAST:event_btnHacerEfectivaActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        String filtro = cbxFiltroTabla.getSelectedItem().toString();
        if(filtro.equals("Reservas")){
            btnHacerDevolucion.setEnabled(false);
            btnHacerEfectiva.setEnabled(true);
            this.llenarTablaReservas();
        } else {
            btnHacerDevolucion.setEnabled(true);
            btnHacerEfectiva.setEnabled(false);
            this.llenarTablaTiquetes();
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String idViaje = cbxViaje.getSelectedItem().toString();
        String filtro = cbxFiltroTabla.getSelectedItem().toString();

        if(filtro.equals("Reservas")){
            this.llenarTablaReservasPorViaje(idViaje);
        } else {
            this.llenarTablaTiquetesPorViaje(idViaje);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnHacerDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHacerDevolucionActionPerformed
        try {
            String idTiquete = cbxTiquete.getSelectedItem().toString().trim();

            Tiquete tiquete = this.obtenerTiquete(idTiquete);
            if (tiquete == null) {
                throw new RuntimeException("No se encontró el tiquete con ID: " + idTiquete);
            }

            Cliente cliente = this.controladorVistaGestionVentas.obtenerClientePorId(tiquete.getCliente().getDocumento());
            if (cliente == null) {
                throw new RuntimeException("No se encontró el cliente asociado al tiquete.");
            }

            int puntos = cliente.getPuntosTransacion(idTiquete);

            Devolucion devolucion = new Devolucion(tiquete, LocalDate.now());

            boolean redencionExistente = false;
            for (int i = 0; i < cliente.getTransacciones().size(); i++) {
                Transaccion transaccion = cliente.getTransacciones().get(i);
                if (transaccion.getTiquete().getIdTiquete().equals(idTiquete)) {
                    if (transaccion.getAccion().equals("Redencion")) {
                        redencionExistente = true;
                        break;
                    }
                }
            }

            String accion = redencionExistente ? "Devolucion por puntos" : "Devolucion";
            this.controladorVistaGestionVentas.transaccionCliente(tiquete.getCliente().getDocumento(), tiquete, accion, puntos);

            this.caseta.getEmpresa().getDevoluciones().add(devolucion);
            this.eliminarTiquete(idTiquete);

            JOptionPane.showMessageDialog(null, "Devolución concretada con éxito.");

            this.llenarTablaTiquetes();
            this.alistarBox();

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btnHacerDevolucionActionPerformed

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
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnHacerDevolucion;
    private javax.swing.JButton btnHacerEfectiva;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnRedimir;
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> cbxCliente;
    private javax.swing.JComboBox<String> cbxFiltroTabla;
    private javax.swing.JComboBox<String> cbxReserva;
    private javax.swing.JComboBox<String> cbxTiquete;
    private javax.swing.JComboBox<String> cbxViaje;
    private javax.swing.JComboBox<String> cbxViajeBuscador;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JPanel panelCrudGestionVentas;
    private javax.swing.JPanel panelCrudGestionVentas4;
    private javax.swing.JTable tablaFiltro;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JTextField txtCantidadTiquetes;
    private javax.swing.JTextField txtIdTiquete;
    // End of variables declaration//GEN-END:variables
}
