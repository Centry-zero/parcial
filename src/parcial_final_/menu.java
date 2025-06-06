package parcial_final_;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import libreria.libreria_total;

public class menu extends javax.swing.JFrame {

    public tablacitas ventanaPrincipal;

    private String nombreUsuario;

    public menu(String cargoUsuario, String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        ventanaPrincipal = new tablacitas();
        ventanaPrincipal = new tablacitas();
        initComponents();
        this.setLocationRelativeTo(this);
        m.imagen_Label(f1, "src/imagenes/LOGO.png");
        this.setTitle("menu principal");
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarInmueblePropietario();
            }
        });
        cargarMisInmuebles();
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarInmuebleArriendo();
            }
        });
        String rutaImagen = null;
        jLabel5.setIcon(libreria_total.cargarYEscalarImagen(rutaImagen, jLabel5.getWidth(), jLabel5.getHeight()));
      if (cargoUsuario.equalsIgnoreCase("CLIENTE")) {
        int total = registro.getTabCount();
        if (total > 4) registro.removeTabAt(4);
        if (total > 3) registro.removeTabAt(3);
        if (total > 2) registro.removeTabAt(2);
    } else if (cargoUsuario.equalsIgnoreCase("PROPIETARIO")) {
        int total = registro.getTabCount();
        if (total > 3) registro.removeTabAt(3);
        if (total > 2) registro.removeTabAt(2);
    } else if (cargoUsuario.equalsIgnoreCase("AGENTE")) {
    for (int i = 0; i < registro.getTabCount(); i++) {
        if (registro.getTitleAt(i).equalsIgnoreCase("MIS INMUEBLES")) {
            registro.removeTabAt(i);
            break;
        }
    }
    }
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarInmuebleSeleccionado();
            }
        });
    }

    private void cargarMisInmuebles() {
        DefaultTableModel model = (DefaultTableModel) ventanaPrincipal.getTblLista().getModel();
        jComboBox3.removeAllItems();

        for (int i = 0; i < model.getRowCount(); i++) {
            String propietario = model.getValueAt(i, 1).toString(); // Nombre
            String codigo = model.getValueAt(i, 4).toString();

            if (nombreUsuario.equalsIgnoreCase("JEFE MAESTRO")) {
                jComboBox3.addItem(codigo); // Admin ve todo
            } else if (propietario.equalsIgnoreCase(nombreUsuario)) {
                jComboBox3.addItem(codigo);
            }
        }
    }

    private void mostrarInmueblePropietario() {
        String codigoSeleccionado = (String) jComboBox3.getSelectedItem();
        if (codigoSeleccionado == null || codigoSeleccionado.equalsIgnoreCase("SELECCIONAR")) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ventanaPrincipal.getTblLista().getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String codigo = model.getValueAt(i, 4).toString();

            if (codigo.equalsIgnoreCase(codigoSeleccionado)) {
                String nombre = model.getValueAt(i, 1).toString();
                String telefono = model.getValueAt(i, 2).toString();
                String tipo = model.getValueAt(i, 3).toString();
                String estado = model.getValueAt(i, 5).toString();
                String valor = model.getValueAt(i, 6).toString();
                String direccion = model.getValueAt(i, 7).toString();
                Object celda = model.getValueAt(i, 8);

                // Mostrar descripción
                jTextArea3.setText(
                        "Propietario: " + nombre + "\n"
                        + "Teléfono: " + telefono + "\n"
                        + "Tipo: " + tipo + "\n"
                        + "Estado: " + estado + "\n"
                        + "Valor: $" + valor + "\n"
                        + "Dirección: " + direccion
                );

                // Mostrar imagen si existe
                if (celda != null && !celda.toString().isEmpty()) {
                    String rutaImagen = celda.toString();
                    System.out.println("Imagen cargada desde: " + rutaImagen);
                    m.imagen_Label(jLabel8, rutaImagen);
                    jLabel8.setText("");
                } else {
                    System.out.println("Imagen no disponible");
                    jLabel8.setIcon(null);
                    jLabel8.setText("Sin imagen");
                }

                break;
            }
        }
    }

    private void filtrarPorTipo(String tipo) {
        DefaultTableModel model = (DefaultTableModel) ventanaPrincipal.getTblLista().getModel();
        jComboBox2.removeAllItems();

        for (int i = 0; i < model.getRowCount(); i++) {
            String tipoInmueble = model.getValueAt(i, 3).toString();
            String codigo = model.getValueAt(i, 4).toString();
            String estado = model.getValueAt(i, 5).toString();

            if ((tipo.equalsIgnoreCase("TODOS") || tipoInmueble.equalsIgnoreCase(tipo))
                    && (estado.equalsIgnoreCase("Venta"))) {

                jComboBox2.addItem(codigo);
            }
        }
    }

    private void filtrarPorTipoArriendo(String tipo) {
        DefaultTableModel model = (DefaultTableModel) ventanaPrincipal.getTblLista().getModel();
        jComboBox1.removeAllItems();

        for (int i = 0; i < model.getRowCount(); i++) {
            String tipoInmueble = model.getValueAt(i, 3).toString();
            String codigo = model.getValueAt(i, 4).toString();
            String estado = model.getValueAt(i, 5).toString();

            if ((tipo.equalsIgnoreCase("TODOS") || tipoInmueble.equalsIgnoreCase(tipo))
                    && estado.equalsIgnoreCase("Arriendo")) {

                jComboBox1.addItem(codigo);
            }
        }
    }

    private void mostrarInmuebleSeleccionado() {
        String codigoSeleccionado = (String) jComboBox2.getSelectedItem();
        if (codigoSeleccionado == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ventanaPrincipal.getTblLista().getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String codigo = model.getValueAt(i, 4).toString();

            if (codigo.equalsIgnoreCase(codigoSeleccionado)) {
                String nombre = model.getValueAt(i, 1).toString();
                String telefono = model.getValueAt(i, 2).toString();
                String tipo = model.getValueAt(i, 3).toString();
                String estado = model.getValueAt(i, 5).toString();
                String valor = model.getValueAt(i, 6).toString();
                String direccion = model.getValueAt(i, 7).toString();
                Object celda = model.getValueAt(i, 8);

                jTextArea2.setText(
                        "Propietario: " + nombre + "\n"
                        + "Teléfono: " + telefono + "\n"
                        + "Tipo: " + tipo + "\n"
                        + "Estado: " + estado + "\n"
                        + "Valor: $" + valor + "\n"
                        + "Dirección: " + direccion
                );

                if (celda != null && !celda.toString().isEmpty()) {
                    String rutaImagen = celda.toString();
                    m.imagen_Label(jLabel5, rutaImagen);
                    jLabel5.setText("");
                } else {
                    jLabel5.setIcon(null);
                    jLabel5.setText("Sin imagen");
                }

                break;
            }
        }
    }

    private void mostrarInmuebleArriendo() {
        String codigoSeleccionado = (String) jComboBox1.getSelectedItem();
        if (codigoSeleccionado == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ventanaPrincipal.getTblLista().getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String codigo = model.getValueAt(i, 4).toString();
            String estado = model.getValueAt(i, 5).toString();

            if (codigo.equalsIgnoreCase(codigoSeleccionado) && estado.equalsIgnoreCase("Arriendo")) {
                String nombre = model.getValueAt(i, 1).toString();
                String telefono = model.getValueAt(i, 2).toString();
                String tipo = model.getValueAt(i, 3).toString();
                String valor = model.getValueAt(i, 6).toString();
                String direccion = model.getValueAt(i, 7).toString();
                Object celda = model.getValueAt(i, 8);

                jTextAreaArriendo.setText("Propietario: " + nombre + "\n"
                        + "Teléfono: " + telefono + "\n"
                        + "Tipo: " + tipo + "\n"
                        + "Estado: " + estado + "\n"
                        + "Valor: $" + valor + "\n"
                        + "Dirección: " + direccion);

                if (celda != null) {
                    String rutaImagen = celda.toString();
                    m.imagen_Label(jLabel4, rutaImagen);
                    jLabel4.setText("");
                } else {
                    jLabel4.setIcon(null);
                    jLabel4.setText("Sin imagen");
                }

                break;
            }
        }
    }
    libreria_total m = new libreria_total();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        registro = new javax.swing.JTabbedPane();
        arriendo = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaArriendo = new javax.swing.JTextArea();
        jButton11 = new javax.swing.JButton();
        venta = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        r1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        rcasa = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        rcontrato = new javax.swing.JButton();
        tablas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        f1 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        arriendo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setText("CASA");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("APARTAMENTO");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("LOCAL");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("TODOS");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CODIGO", "1", "2", "3", "4", "5", "6", "7" }));

        jTextAreaArriendo.setColumns(20);
        jTextAreaArriendo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaArriendo);

        jButton11.setText("video");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout arriendoLayout = new javax.swing.GroupLayout(arriendo);
        arriendo.setLayout(arriendoLayout);
        arriendoLayout.setHorizontalGroup(
            arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arriendoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(arriendoLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(arriendoLayout.createSequentialGroup()
                        .addGroup(arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(arriendoLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45)
                        .addGroup(arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(arriendoLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jButton11)))))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        arriendoLayout.setVerticalGroup(
            arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arriendoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(arriendoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        registro.addTab("ARRIENDO", arriendo);

        jButton6.setText("CASA");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("APARTAMENTO");
        jButton8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("LOCAL");
        jButton9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("TODOS");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CODIGO", "1", "2", "3", "4", "5", "6", "7" }));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout ventaLayout = new javax.swing.GroupLayout(venta);
        venta.setLayout(ventaLayout);
        ventaLayout.setHorizontalGroup(
            ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventaLayout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ventaLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ventaLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        ventaLayout.setVerticalGroup(
            ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        registro.addTab("VENTA", venta);

        jButton7.setText("AGENDAR CITAS");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setText("OBSERVACION DE CITA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout r1Layout = new javax.swing.GroupLayout(r1);
        r1.setLayout(r1Layout);
        r1Layout.setHorizontalGroup(
            r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(r1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        r1Layout.setVerticalGroup(
            r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(r1Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addGroup(r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        registro.addTab("CITAS", r1);

        rcasa.setText("REGISTROS INMUEBLE");
        rcasa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rcasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rcasaActionPerformed(evt);
            }
        });

        jButton15.setText("SEGUIMIENTOS");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        rcontrato.setText("REGISTRA CONTRATOS");
        rcontrato.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rcontrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rcontratoActionPerformed(evt);
            }
        });

        tablas.setText("TABLAS DE REGISTRO");
        tablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rcontrato, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rcasa, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(512, Short.MAX_VALUE)
                    .addComponent(tablas, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(78, 78, 78)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(rcasa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rcontrato, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addComponent(tablas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(195, Short.MAX_VALUE)))
        );

        registro.addTab("REGISTROS", jPanel3);

        jLabel7.setText("CODIGO");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR" }));

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        registro.addTab("MIS INMUEBLES", jPanel4);

        jPanel1.add(registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo_logo.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -4, 1100, 490));

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("INMOBILIARIA HABITAT");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, 55));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("EMPRESA LIDER EN CÚCUTA");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));

        f1.setText("IMAGEN");
        jPanel2.add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 120));

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton12.setText("salir ");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        filtrarPorTipoArriendo("Casa");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        filtrarPorTipoArriendo("Todos");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        filtrarPorTipo("Casa");

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        filtrarPorTipo("TODOS");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        agendarcitas cita = new agendarcitas(
                ventanaPrincipal.getJTable1(),
                ventanaPrincipal.getTblLista()
        );
        cita.setVisible(true);
        ventanaPrincipal.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void rcasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rcasaActionPerformed
        registro reg = new registro(ventanaPrincipal.getTblLista(), ventanaPrincipal);
        reg.setVisible(true);
        ventanaPrincipal.setVisible(false);
    }//GEN-LAST:event_rcasaActionPerformed

    private void rcontratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rcontratoActionPerformed
        agendarcontratos contrato = new agendarcontratos(ventanaPrincipal.getTblLista1());
        contrato.setVisible(true);
        ventanaPrincipal.setVisible(false);
    }//GEN-LAST:event_rcontratoActionPerformed

    private void tablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablasActionPerformed
        ventanaPrincipal.setVisible(true);
    }//GEN-LAST:event_tablasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ventanaPrincipal.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        filtrarPorTipo("Apartamento");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        filtrarPorTipo("Local");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        filtrarPorTipoArriendo("Apartamento");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        filtrarPorTipoArriendo("Local");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
  libreria_total.abrirVideo("src/imagenes/casa.mp4");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        formularioseguimiento form = new formularioseguimiento(ventanaPrincipal.getTblLista2());
        form.setVisible(true);
        ventanaPrincipal.setVisible(false);

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu("ADMINISTRADOR", "JEFE MAESTRO").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel arriendo;
    private javax.swing.JLabel f1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextAreaArriendo;
    private javax.swing.JPanel r1;
    private javax.swing.JButton rcasa;
    private javax.swing.JButton rcontrato;
    private javax.swing.JTabbedPane registro;
    private javax.swing.JButton tablas;
    private javax.swing.JPanel venta;
    // End of variables declaration//GEN-END:variables
}
